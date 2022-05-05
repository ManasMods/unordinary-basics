package com.github.manasmods.vanilla_plus.client.gui;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import com.github.manasmods.vanilla_plus.menu.JukeBoxMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class JukeBoxScreen extends AbstractContainerScreen<JukeBoxMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Vanilla_Plus.MOD_ID, "textures/gui/jukebox.png");

    public JukeBoxScreen(JukeBoxMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
        titleLabelY = 4;
        inventoryLabelX = (imageWidth - font.width(playerInventoryTitle)) / 2;
        inventoryLabelY = titleLabelY + 60;
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
