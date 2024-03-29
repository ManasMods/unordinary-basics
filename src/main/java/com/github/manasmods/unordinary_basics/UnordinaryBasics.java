package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import com.github.manasmods.unordinary_basics.capability.RedstonePouchCapabilityProvider;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.data.UBBlockStateProvider;
import com.github.manasmods.unordinary_basics.data.UBBlockTagProvider;
import com.github.manasmods.unordinary_basics.data.UBEntityTypeTagProvider;
import com.github.manasmods.unordinary_basics.data.UBFletchingRecipeProvider;
import com.github.manasmods.unordinary_basics.data.UBItemModelProvider;
import com.github.manasmods.unordinary_basics.data.UBItemTagProvider;
import com.github.manasmods.unordinary_basics.data.UBLootTableProvider;
import com.github.manasmods.unordinary_basics.data.UBRecipeProvider;
import com.github.manasmods.unordinary_basics.data.UBStructureTagProvider;
import com.github.manasmods.unordinary_basics.handler.UBEntityHandler;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisIntegration;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.loot.ModLootModifiers;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toclient.UBInventoryClientSync;
import com.github.manasmods.unordinary_basics.registry.UBPaintingVariants;
import com.github.manasmods.unordinary_basics.registry.UBRegistry;
import com.github.manasmods.unordinary_basics.utils.TreasureMapForEmeralds;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Mod(UnordinaryBasics.MOD_ID)
public class UnordinaryBasics {
    public static final String MOD_ID = "unordinary_basics";
    public static final Logger LOGGER = LogManager.getLogger();

    public static UnordinaryBasics getInstance() {
        return instance;
    }

    public Optional<ApotheosisIntegration> getApotheosisIntegration() {
        return apotheosisIntegration;
    }

    private static UnordinaryBasics instance;
    private Optional<ApotheosisIntegration> apotheosisIntegration = Optional.empty();

    public UnordinaryBasics() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        UBRegistry.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::generateData);
        modEventBus.addListener(this::registerCapabilities);
        forgeBus.addListener(this::entityPlaceEvent);
        forgeBus.addListener(this::entityPickupEvent);
        forgeBus.addListener(this::itemTossEvent);
        forgeBus.addListener(this::handleUBInventoryDrops);
        forgeBus.addListener(this::playerJoinWorld);
        forgeBus.addListener(this::playerTick);
        forgeBus.addListener(this::villagerTradesEvent);
        forgeBus.addGenericListener(Entity.class,this::attachCapabilities);
        modEventBus.addListener(UBEntityHandler::entityAttributeEvent);
        UBPaintingVariants.register(modEventBus);
        ModLootModifiers.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("apotheosis")) {
                this.apotheosisIntegration = Optional.of(new ApotheosisIntegration());
            }
        });
        event.enqueueWork(Unordinary_BasicsNetwork::registerPackets);
        event.enqueueWork(UBEntityHandler::registerEntityPlacements);

        copyImprovedTexturesIfMissing();

    }

    private void generateData(final GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeClient(),new UBBlockStateProvider(event));
        event.getGenerator().addProvider(event.includeClient(),new UBItemModelProvider(event));
        event.getGenerator().addProvider(event.includeServer(),new UBRecipeProvider(event));
        event.getGenerator().addProvider(event.includeServer(),new UBLootTableProvider(event));
        event.getGenerator().addProvider(event.includeServer(),new UBBlockTagProvider(event));
        event.getGenerator().addProvider(event.includeServer(),new UBEntityTypeTagProvider(event));
        event.getGenerator().addProvider(event.includeServer(),new UBFletchingRecipeProvider(event));
        UBBlockTagProvider blockTagProvider = new UBBlockTagProvider(event);
        event.getGenerator().addProvider(event.includeServer(),blockTagProvider);
        event.getGenerator().addProvider(event.includeServer(),new UBItemTagProvider(event, blockTagProvider));
        event.getGenerator().addProvider(event.includeServer(), new UBStructureTagProvider(event.getGenerator(),event.getExistingFileHelper()));
    }

    private void entityPlaceEvent(final BlockEvent.EntityPlaceEvent event){
        if (event.getEntity() instanceof Player &&
                ((Player) event.getEntity()).getMainHandItem().getItem() == Unordinary_BasicsItems.BUILDERS_GLOVE
                && ((Player) event.getEntity()).getInventory().offhand.get(0).getItem() instanceof BlockItem){
            event.setCanceled(true);
        }
    }

    private void villagerTradesEvent(final VillagerTradesEvent event){
        if (event.getType().equals(VillagerProfession.CARTOGRAPHER)) {
            event.getTrades().get(3).add(new TreasureMapForEmeralds(12, UBTags.Structures.MASTER_SWORD_SHRINE,"filled_map.unordinary_basics.master_sword_shrine", MapDecoration.Type.RED_X,2,5));
        }
    }

    public void playerJoinWorld(EntityJoinLevelEvent event){
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
                    Unordinary_BasicsNetwork.getInstance().send(PacketDistributor.PLAYER.with(() -> player), new UBInventoryClientSync(handler));
                });
            }
        }
    }

    public void playerTick(TickEvent.PlayerTickEvent event){
        if (!event.player.level.isClientSide){
            if (event.player instanceof ServerPlayer player){
                player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
                    Unordinary_BasicsNetwork.getInstance().send(PacketDistributor.PLAYER.with(() -> player), new UBInventoryClientSync(handler));
                });
            }
        }
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event){
        event.register(IUBInventoryHandler.class);
    }

    private void attachCapabilities(final AttachCapabilitiesEvent<Entity> event){
        if (!(event.getObject() instanceof Player)) return;

        UBInventoryItemStackHandler stackHandler = new UBInventoryItemStackHandler();
        LazyOptional<IUBInventoryHandler> handlerLazyOptional = LazyOptional.of(() -> stackHandler);

        ICapabilityProvider provider = new ICapabilitySerializable<CompoundTag>() {

            @Override
            public CompoundTag serializeNBT() {
                return stackHandler.serializeNBT();
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                stackHandler.deserializeNBT(nbt);
            }

            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                if (cap == CapabilityUBInventory.UB_INVENTORY_CAPABILITY){
                    return handlerLazyOptional.cast();
                }
                return LazyOptional.empty();
            }
        };

        event.addCapability(new ResourceLocation(UnordinaryBasics.MOD_ID,"ub_inventory"),provider);
    }

    private void handleUBInventoryDrops(final LivingDropsEvent event){
        if (!(event.getEntity() instanceof Player player)) return;

        Level level = player.getLevel();
        List<ItemStack> drops = new ArrayList<>();

        player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); ++i){
                if (!handler.getStackInSlot(i).isEmpty()) {
                    drops.add(handler.getStackInSlot(i));
                }
            }
        });
        for (ItemStack drop : drops) {
            ItemEntity dropEntity = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), drop);
            event.getDrops().add(dropEntity);
        }
    }

    private void itemTossEvent(final ItemTossEvent event){
        if (!event.getEntity().getItem().getItem().equals(Unordinary_BasicsItems.REDSTONE_POUCH)) return;
        if (Screen.hasShiftDown()) return;
        if (event.getPlayer().getLevel().isClientSide) return;
        if (Minecraft.getInstance().screen != null) return;

        ItemStack stack = event.getEntity().getItem();
        ItemStack toReplaceWith = stack.copy();
        Player player = event.getPlayer();
        Inventory inv = player.getInventory();
        int slotToModify;

        slotToModify = inv.selected;

        AtomicBoolean result = new AtomicBoolean(false);
        toReplaceWith.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            ((ItemStackHandler)handler).deserializeNBT(toReplaceWith.getOrCreateTag().getCompound("inventory"));
            if (Screen.hasControlDown()){
                result.set(RedstonePouchCapabilityProvider.dropOneStack(handler,player,Items.REDSTONE));
            } else {
                result.set(RedstonePouchCapabilityProvider.dropOneItem(handler,player));
            }
            toReplaceWith.getOrCreateTag().put("inventory",((ItemStackHandler)handler).serializeNBT());
        });

        if (result.get()){
            event.setCanceled(true);
            event.setResult(Event.Result.DENY);
            inv.setItem(slotToModify,toReplaceWith);
        }

    }

    private void entityPickupEvent(final EntityItemPickupEvent event){
        Player player = event.getEntity();
        final ItemStack stackToPickup = event.getItem().getItem();

        if (player.level.isClientSide) return;
        if (!stackToPickup.getItem().equals(Items.REDSTONE)) return;

        ItemStack pouchItem = RedstonePouchCapabilityProvider.findFirstItemInInventory(player,Unordinary_BasicsItems.REDSTONE_POUCH);
        AtomicInteger remainingCount = new AtomicInteger(stackToPickup.getCount());

        if (pouchItem == null) return;

            pouchItem.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                ((ItemStackHandler) handler).deserializeNBT(pouchItem.getOrCreateTag().getCompound("inventory"));
                remainingCount.set(RedstonePouchCapabilityProvider.dumpItemStackInt(stackToPickup, handler));
                pouchItem.getOrCreateTag().put("inventory", ((ItemStackHandler) handler).serializeNBT());
            });

        stackToPickup.setCount(remainingCount.get());
        event.setResult(Event.Result.DEFAULT);
        if (stackToPickup.isEmpty()) event.setResult(Event.Result.ALLOW);
    }

    /**
     * Public Getter for the Logger instance of this mod.
     *
     * @return the current Logger instance
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    private static void copyImprovedTexturesIfMissing() {
        File dir = new File(".", "resourcepacks");
        File target = new File(dir, "Improved Textures.zip");

        if(!target.exists())
            try {
                dir.mkdirs();
                InputStream in = UnordinaryBasics.class.getResourceAsStream("/assets/unordinary_basics/improved_textures.zip");
                FileOutputStream out = new FileOutputStream(target);

                byte[] buf = new byte[16384];
                int len;
                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);

                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}


