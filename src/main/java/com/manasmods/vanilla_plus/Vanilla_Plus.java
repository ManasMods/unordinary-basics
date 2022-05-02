package com.manasmods.vanilla_plus;

import com.manasmods.vanilla_plus.data.Vanilla_PlusBlockStateProvider;
import com.manasmods.vanilla_plus.data.Vanilla_PlusRecipeProvider;
import lombok.Getter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

@Mod(Vanilla_Plus.MOD_ID)
public class Vanilla_Plus {
    public static final String MOD_ID = "vanilla_plus";
    private static final Logger LOGGER = (Logger) LogManager.getLogger();
    @Getter
    private final Vanilla_PlusCommon proxy;

    public Vanilla_Plus() {
        proxy = DistExecutor.safeRunForDist(() -> Vanilla_PlusClient::new, () -> Vanilla_PlusCommon::new);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        proxy.preInit(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::generateData);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init(event);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(proxy::clientInit);
    }

    private void generateData(final GatherDataEvent event) {
        event.getGenerator().addProvider(new Vanilla_PlusBlockStateProvider(event));
        //event.getGenerator().addProvider(new Vanilla_PlusItemModelProvider(event));
        event.getGenerator().addProvider(new Vanilla_PlusRecipeProvider(event));
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


