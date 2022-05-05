package com.github.manasmods.vanilla_plus.client.gui;

import com.github.manasmods.vanilla_plus.menu.JukeBoxMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class JukeBoxScreen extends AbstractContainerScreen<JukeBoxMenu> {
    public JukeBoxScreen(JukeBoxMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
