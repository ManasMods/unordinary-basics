package com.github.manasmods.unordinary_basics.integration.apotheosis;

import com.github.manasmods.manascore.client.gui.widget.ImagePredicateButton;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.core.AbstractContainerScreenAccessor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.client.event.ScreenEvent;
import shadows.apotheosis.village.fletching.FletchingScreen;

public class ApotheosisClientHandler {
    public static void onOpenApotheosisMenu(final ScreenEvent.InitScreenEvent.Post e) {
        if (!(e.getScreen() instanceof FletchingScreen screen)) return;
        int imageWidth = ((AbstractContainerScreenAccessor) screen).getImageWidth();
        e.addListener(new ImagePredicateButton(screen.getGuiLeft() + imageWidth - 24 - 4, screen.getGuiTop() + 6, 24, 24, ApotheosisIntegration.UNORDINARY_BASICS_LOGO, pButton -> {
            Unordinary_Basics.getInstance().getApotheosisIntegration().ifPresent(ApotheosisIntegration::requestUnordinaryBasicsFletchingMenu);
        }, (pButton, pPoseStack, pMouseX, pMouseY) -> {
            screen.renderTooltip(pPoseStack, new TranslatableComponent(Unordinary_Basics.MOD_ID + ".menu.fletching_table.switch.ub"), pMouseX, pMouseY);
        }, () -> Unordinary_Basics.getInstance().getApotheosisIntegration().isPresent()));
    }
}
