package com.github.manasmods.unordinary_basics.gui;

import com.github.manasmods.manascore.tab.annotation.ScreenForTab;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@ScreenForTab(Unordinary_BasicsInventoryTab.class)
@OnlyIn(Dist.CLIENT)
public class Unordinary_BasicsInventoryScreen extends Screen {

    public static final ResourceLocation UB_INVENTORY_LOCATION = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/ub_inventory.png");
    public static final int WIDTH = 256;
    public static final int HEIGHT = 256;

    protected Unordinary_BasicsInventoryScreen(Component title) {
        super(title);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, UB_INVENTORY_LOCATION);
        this.blit(pPoseStack, 50, 50, 0, 0, WIDTH, HEIGHT);
        this.minecraft.getItemRenderer().renderAndDecorateFakeItem(new ItemStack(Items.LIME_WOOL), 50, 50);
    }
}
