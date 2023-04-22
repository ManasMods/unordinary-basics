package com.github.manasmods.unordinary_basics.client.gui;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.BuildersGloveMenu;
import com.github.manasmods.unordinary_basics.menu.ItemSorterMenu;
import com.github.manasmods.unordinary_basics.menu.slot.GhostSlot;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class ItemSorterScreen extends AbstractContainerScreen<ItemSorterMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/item_sorter.png");

    public ItemSorterScreen(ItemSorterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
        this.font.draw(pPoseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY - 33, 4210752);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
        renderGhostItems(x,y);
    }

    public void renderGhostItems(int pX, int pY){
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        for (int i = 0 ; i < 2; ++i){
            for (int k = 0 ; k < 3; ++k){
                ItemStack stack = new ItemStack(((GhostSlot) this.menu.getSlot(i * 3 + k)).currentItem);
                itemRenderer.renderAndDecorateFakeItem(stack,114 + k * 18 + pX, 8 + i * 18 + pY);
            }
        }
    }
}
