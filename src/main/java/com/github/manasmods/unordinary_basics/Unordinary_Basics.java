package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsBlockStateProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsBlockTagProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsFletchingRecipeProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsItemModelProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsLootTableProvider;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsRecipeProvider;
import com.github.manasmods.unordinary_basics.integration.ApotheosisIntegration;
import lombok.Getter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
    private final Unordinary_BasicsCommon proxy;
    @Getter
    private Optional<ApotheosisIntegration> apotheosisIntegration = Optional.empty();

    public Unordinary_Basics() {
        instance = this;
        proxy = DistExecutor.safeRunForDist(() -> Unordinary_BasicsClient::new, () -> Unordinary_BasicsServer::new);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        proxy.preInit(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::generateData);
    }

    private void setup(final FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("apotheosis")) {
            this.apotheosisIntegration = Optional.of(new ApotheosisIntegration());
        }
        proxy.init(event);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        proxy.clientInit(event);
    }

    private void generateData(final GatherDataEvent event) {
        event.getGenerator().addProvider(new Unordinary_BasicsBlockStateProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsItemModelProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsRecipeProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsLootTableProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsBlockTagProvider(event));
        event.getGenerator().addProvider(new Unordinary_BasicsFletchingRecipeProvider(event));
    }

    /**
     * Public Getter for the Logger instance of this mod.
     *
     * @return the current Logger instance
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    public static Unordinary_BasicsCommon getProxy() {
        return instance.proxy;
    }
}


