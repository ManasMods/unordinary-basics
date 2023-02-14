package com.github.manasmods.unordinary_basics.client.gui.overlay;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Unordinary_Basics.MOD_ID)
public class OverlayEventHandler {

    private static final QuiverArrowHudOverlay quiverOverlay = new QuiverArrowHudOverlay();

    @SubscribeEvent
    public static void renderOverlays(final RenderGameOverlayEvent.Post event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        quiverOverlay.drawHUD(event.getMatrixStack());
    }
}
