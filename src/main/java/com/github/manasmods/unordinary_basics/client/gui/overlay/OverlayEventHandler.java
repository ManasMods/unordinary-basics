package com.github.manasmods.unordinary_basics.client.gui.overlay;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = UnordinaryBasics.MOD_ID)
public class OverlayEventHandler {

    private static final QuiverArrowHudOverlay quiverOverlay = new QuiverArrowHudOverlay();

    @SubscribeEvent
    public static void renderOverlays(final RenderGuiOverlayEvent event){
        //I wrote this but forgot what it does so I can't port it now
        //if (event.getOverlay() != RenderGuiOverlayEvent.ElementType.ALL) return;

        int x = (event.getWindow().getGuiScaledWidth() - 24) / 2;
        int y = event.getWindow().getGuiScaledHeight() - 64;

        quiverOverlay.drawHUD(event.getPoseStack(),x,y);
    }
}
