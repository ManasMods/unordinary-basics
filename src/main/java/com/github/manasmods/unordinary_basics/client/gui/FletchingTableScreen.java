package com.github.manasmods.unordinary_basics.client.gui;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FletchingTableScreen extends AbstractContainerScreen<FletchingTableMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/fletching_table.png");

    public FletchingTableScreen(FletchingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        imageHeight = 178;
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
        int y = (height - imageHeight) / 2;
        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 8;
        titleLabelY = 6;
        inventoryLabelX = 8;
        inventoryLabelY = imageHeight / 2 - 3;
    }
}
