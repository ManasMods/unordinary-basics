package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsBlockStateProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsBlockTagProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsFletchingRecipeProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsItemModelProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsItemTagProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsLootTableProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsRecipeProvider;
import com.github.manasmods.unordinary_basics.handler.UBEntityHandler;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisIntegration;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.item.capability.RedstonePouchCapability;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.painting.UBPaintings;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRegistry;
import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
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
        forgeBus.addListener(this::entityPlaceEvent);
        forgeBus.addListener(this::entityPickupEvent);
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

    private void itemTossEvent(final ItemTossEvent event){

    }

    private void entityPickupEvent(final EntityItemPickupEvent event){
        Player player = event.getPlayer();
        final ItemStack stackToPickup = event.getItem().getItem();

        if (!stackToPickup.getItem().equals(Items.REDSTONE)) return;

        ItemStack pouchItem = RedstonePouchCapability.findFirstItemInInventory(player,Unordinary_BasicsItems.REDSTONE_POUCH);
        AtomicInteger remainingCount = new AtomicInteger(stackToPickup.getCount());

        if (pouchItem == null) return;

            pouchItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                ((ItemStackHandler) handler).deserializeNBT(pouchItem.getOrCreateTag().getCompound("inventory"));
                remainingCount.set(RedstonePouchCapability.dumpItemStackInt(stackToPickup, handler));
                pouchItem.getOrCreateTag().put("inventory", ((ItemStackHandler) handler).serializeNBT());
            });

        stackToPickup.setCount(remainingCount.get());

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


