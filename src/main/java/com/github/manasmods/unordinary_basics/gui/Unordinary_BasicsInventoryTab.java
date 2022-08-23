package com.github.manasmods.unordinary_basics.gui;

import com.github.manasmods.manascore.ManasCore;
import com.github.manasmods.manascore.tab.AbstractInventoryTab;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Unordinary_BasicsInventoryTab extends AbstractInventoryTab {

    private final ItemStack iconStack = new ItemStack(Items.NAME_TAG);

    public Unordinary_BasicsInventoryTab(MutableComponent tooltip, MutableComponent... tooltips) {
        super(tooltip, tooltips);
    }

    @Override
    protected void renderIcon(PoseStack poseStack, int i, int i1, float v) {
        this.minecraft.getItemRenderer().renderAndDecorateFakeItem(this.iconStack, this.x + 6, this.y + 8);
    }

    @Override
    public void sendOpenContainerPacket() {
        if (this.minecraft.player == null) {
            ManasCore.getLogger().fatal("Local Player is null!?");
        } else {
            this.minecraft.setScreen(new Unordinary_BasicsInventoryScreen(new TextComponent("Unordinary Basics")));
        }
    }
}
