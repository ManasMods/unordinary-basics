package com.github.manasmods.unordinary_basics.integration;

import com.github.manasmods.manascore.client.gui.widget.ImagePredicateButton;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.core.AbstractContainerScreenAccessor;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import shadows.apotheosis.village.fletching.FletchingScreen;

public class ApotheosisIntegrationClient
{

    private static final ResourceLocation UNORDINARY_BASICS_LOGO = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/ub_logo.png");
    
    public static void onOpenApotheosisMenu(final ScreenEvent.InitScreenEvent.Post e) {
        if (!(e.getScreen() instanceof FletchingScreen screen)) return;
        int imageWidth = ((AbstractContainerScreenAccessor) screen).getImageWidth();
        e.addListener(new ImagePredicateButton(screen.getGuiLeft() + imageWidth - 24 - 4, screen.getGuiTop() + 6, 24, 24, UNORDINARY_BASICS_LOGO, pButton -> {
            Unordinary_Basics.getInstance().getApotheosisIntegration().ifPresent(ApotheosisIntegration::requestUnordinaryBasicsFletchingMenu);
        }, (pButton, pPoseStack, pMouseX, pMouseY) -> {
            screen.renderTooltip(pPoseStack, new TranslatableComponent(Unordinary_Basics.MOD_ID + ".menu.fletching_table.switch.ub"), pMouseX, pMouseY);
        }, () -> Unordinary_Basics.getInstance().getApotheosisIntegration().isPresent()));
    }
    
}
