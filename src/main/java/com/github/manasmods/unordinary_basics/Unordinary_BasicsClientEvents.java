package com.github.manasmods.unordinary_basics.client;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.client.renderer.entity.GrappleHookRenderer;
import com.github.manasmods.unordinary_basics.registry.EntityTypeRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Unordinary_BasicsClientEvents {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.GRAPPLE_HOOK.get(), GrappleHookRenderer::new);
    }
}
