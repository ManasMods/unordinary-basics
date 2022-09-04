package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.data.*;
import com.github.manasmods.unordinary_basics.handler.UBEntityHandler;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisIntegration;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRegistry;
import lombok.Getter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

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
        Unordinary_BasicsRegistry.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::generateData);
        modEventBus.addListener(UBEntityHandler::entityAttributeEvent);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("apotheosis")) {
                this.apotheosisIntegration = Optional.of(new ApotheosisIntegration());
            }
        });
        event.enqueueWork(Unordinary_BasicsNetwork::registerPackets);
        event.enqueueWork(UBEntityHandler::registerEntityPlacements);
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

    /**
     * Public Getter for the Logger instance of this mod.
     *
     * @return the current Logger instance
     */
    public static Logger getLogger() {
        return LOGGER;
    }
}


