package com.github.manasmods.unordinary_basics.client.gui;

import com.github.manasmods.manascore.api.client.gui.widget.ImagePredicateButton;
import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.menu.JukeBoxMenu;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestJukeboxUpdate;
import com.github.manasmods.unordinary_basics.utils.Translation;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class JukeBoxScreen extends AbstractContainerScreen<JukeBoxMenu> {
    private final ImagePredicateButton playButton, stopButton;
    private static final ResourceLocation BACKGROUND = new ResourceLocation(UnordinaryBasics.MOD_ID, "textures/gui/jukebox.png");
    private static final ResourceLocation PLAY_BUTTON = new ResourceLocation(UnordinaryBasics.MOD_ID, "textures/gui/play_button.png");
    private static final ResourceLocation STOP_BUTTON = new ResourceLocation(UnordinaryBasics.MOD_ID, "textures/gui/stop_button.png");

    public JukeBoxScreen(JukeBoxMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.playButton = new ImagePredicateButton(0, 0, 12, 12, PLAY_BUTTON, pButton -> {
            Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestJukeboxUpdate(true));
        }, (pButton, pPoseStack, pMouseX, pMouseY) -> {
            renderTooltip(pPoseStack, Translation.of("jukebox.button.play"), pMouseX, pMouseY);
        }, () -> !pMenu.isPlaying() && pMenu.hasDisc());
        this.stopButton = new ImagePredicateButton(0, 0, 12, 12, STOP_BUTTON, pButton -> {
            Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestJukeboxUpdate(false));
        }, (pButton, pPoseStack, pMouseX, pMouseY) -> {
            renderTooltip(pPoseStack, Translation.of("jukebox.button.stop"), pMouseX, pMouseY);
        }, pMenu::isPlaying);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
        titleLabelY = 4;
        inventoryLabelX = (imageWidth - font.width(playerInventoryTitle)) / 2;
        inventoryLabelY = titleLabelY + 60;

        removeWidget(this.playButton);
        this.playButton.x = getGuiLeft() + imageWidth / 2 - 26;
        this.playButton.y = getGuiTop() + 32;
        addRenderableWidget(this.playButton);
        removeWidget(this.stopButton);
        this.stopButton.x = getGuiLeft() + imageWidth / 2 + 14;
        this.stopButton.y = getGuiTop() + 32;
        addRenderableWidget(this.stopButton);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        int x = (width - imageWidth) / 2;
        int y = (height - imageWidth) / 2;
        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}
