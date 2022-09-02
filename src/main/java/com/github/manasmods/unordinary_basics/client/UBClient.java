package com.github.manasmods.unordinary_basics.client;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.entity.client.GrizzlyBearRenderer;
import com.github.manasmods.unordinary_basics.entity.client.GrolarBearRenderer;
import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UBClient {
    @SubscribeEvent
    public static void init(final FMLClientSetupEvent e) {
        e.enqueueWork(UBClient::registerEntityRenderer);
    }
    private static void registerEntityRenderer() {
        EntityRenderers.register(UBEntityTypes.GROLAR_BEAR.get(), GrolarBearRenderer::new);
        EntityRenderers.register(UBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
    }
}
