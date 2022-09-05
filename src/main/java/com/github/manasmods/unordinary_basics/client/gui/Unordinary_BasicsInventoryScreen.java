package com.github.manasmods.unordinary_basics.client.gui;

import com.github.manasmods.manascore.tab.annotation.ScreenForTab;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.tab.Unordinary_BasicsInventoryTab;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@ScreenForTab(Unordinary_BasicsInventoryTab.class)
@OnlyIn(Dist.CLIENT)
public class Unordinary_BasicsInventoryScreen extends AbstractContainerScreen<UBInventoryMenu> {

    public static final ResourceLocation UB_INVENTORY_LOCATION = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/ub_inventory.png");

    public Unordinary_BasicsInventoryScreen(UBInventoryMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {

    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, UB_INVENTORY_LOCATION);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}
