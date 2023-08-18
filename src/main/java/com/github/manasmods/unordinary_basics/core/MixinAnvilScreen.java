package com.github.manasmods.unordinary_basics.core;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public class MixinAnvilScreen extends ItemCombinerScreen<AnvilMenu> {

    @Shadow @Final private Player player;

    public MixinAnvilScreen(AnvilMenu pMenu, Inventory pPlayerInventory, Component pTitle, ResourceLocation pMenuResource) {
        super(pMenu, pPlayerInventory, pTitle, pMenuResource);
    }

    @Inject(method = "renderLabels(Lcom/mojang/blaze3d/vertex/PoseStack;II)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/inventory/AnvilScreen;menu:Lnet/minecraft/world/inventory/AbstractContainerMenu;", ordinal = 0), cancellable = true)
    public void renderLabels(PoseStack poseStack, int x, int y, CallbackInfo callback) {
        int cost = menu.getCost();
        if (cost >= 40) {
            int textColour = 8453920;
            Component component = Component.translatable("container.repair.cost", cost);
            if (!this.menu.getSlot(2).mayPickup(player)) {
                textColour = 16736352;
            }
            int i = this.imageWidth - 8 - this.font.width(component) - 2;
            fill(poseStack, i - 2, 67, this.imageWidth - 8, 79, 1325400064);
            this.font.drawShadow(poseStack, component, (float) i, 69, textColour);
            callback.cancel();
        }
    }
}
