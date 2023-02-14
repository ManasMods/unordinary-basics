package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.data.*;
import com.github.manasmods.unordinary_basics.handler.UBEntityHandler;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisIntegration;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.capability.RedstonePouchCapabilityProvider;
import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toclient.UBInventoryClientSync;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBInventoryMenu;
import com.github.manasmods.unordinary_basics.painting.UBPaintings;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRegistry;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
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

@Mod(Unordinary_Basics.MOD_ID)
public class Unordinary_Basics {
    public static final String MOD_ID = "unordinary_basics";
    private static final Logger LOGGER = LogManager.getLogger();
    @Getter
    private static Unordinary_Basics instance;
    @Getter
    private Optional<ApotheosisIntegration> apotheosisIntegration = Optional.empty();

    public Unordinary_Basics() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        Unordinary_BasicsRegistry.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::generateData);
        modEventBus.addListener(this::registerCapabilities);
        forgeBus.addListener(this::entityPlaceEvent);
        forgeBus.addListener(this::entityPickupEvent);
        forgeBus.addListener(this::itemTossEvent);
        forgeBus.addListener(this::handleUBInventoryDrops);
        forgeBus.addListener(this::playerJoinWorld);
        forgeBus.addListener(this::playerTick);
        forgeBus.addGenericListener(Entity.class,this::attachCapabilities);
        modEventBus.addListener(UBEntityHandler::entityAttributeEvent);
        UBPaintings.register(modEventBus);
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
        event.getGenerator().addProvider(new Unordinary_BasicsBlockStateProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsItemModelProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsRecipeProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsLootTableProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsBlockTagProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsFletchingRecipeProvider(event));
        Unordinary_BasicsBlockTagProvider blockTagProvider = new Unordinary_BasicsBlockTagProvider(event);
        event.getGenerator().addProvider(blockTagProvider);
        event.getGenerator().addProvider(new Unordinary_BasicsItemTagProvider(event, blockTagProvider));
    }

    private void entityPlaceEvent(final BlockEvent.EntityPlaceEvent event){
        if (event.getEntity() instanceof Player &&
                ((Player) event.getEntity()).getMainHandItem().getItem() == Unordinary_BasicsItems.BUILDERS_GLOVE
                && ((Player) event.getEntity()).getInventory().offhand.get(0).getItem() instanceof BlockItem){
            event.setCanceled(true);
        }
    }

    public void playerJoinWorld(EntityJoinWorldEvent event){
        if(!event.getWorld().isClientSide()) {
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

        event.addCapability(new ResourceLocation(Unordinary_Basics.MOD_ID,"ub_inventory"),provider);
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
        if (!event.getEntityItem().getItem().getItem().equals(Unordinary_BasicsItems.REDSTONE_POUCH)) return;
        if (Screen.hasShiftDown()) return;
        if (event.getPlayer().getLevel().isClientSide) return;
        if (Minecraft.getInstance().screen != null) return;

        ItemStack stack = event.getEntityItem().getItem();
        ItemStack toReplaceWith = stack.copy();
        Player player = event.getPlayer();
        Inventory inv = player.getInventory();
        int slotToModify;

        slotToModify = inv.selected;

        AtomicBoolean result = new AtomicBoolean(false);
        toReplaceWith.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
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
        Player player = event.getPlayer();
        final ItemStack stackToPickup = event.getItem().getItem();

        if (player.level.isClientSide) return;
        if (!stackToPickup.getItem().equals(Items.REDSTONE)) return;

        ItemStack pouchItem = RedstonePouchCapabilityProvider.findFirstItemInInventory(player,Unordinary_BasicsItems.REDSTONE_POUCH);
        AtomicInteger remainingCount = new AtomicInteger(stackToPickup.getCount());

        if (pouchItem == null) return;

            pouchItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
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
                InputStream in = Unordinary_Basics.class.getResourceAsStream("/assets/unordinary_basics/improved_textures.zip");
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


