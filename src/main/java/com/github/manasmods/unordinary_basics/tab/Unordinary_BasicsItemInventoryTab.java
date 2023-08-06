package com.github.manasmods.unordinary_basics.tab;

import com.github.manasmods.manascore.api.tab.AbstractInventoryTab;
import com.github.manasmods.manascore.tab.TabPosition;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBInventoryMenu;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBItemInventoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Unordinary_BasicsItemInventoryTab extends AbstractInventoryTab {

    private final ItemStack iconStack = new ItemStack(Items.CHEST);

    public Unordinary_BasicsItemInventoryTab(MutableComponent tooltip, MutableComponent... tooltips) {
        super(tooltip, tooltips);
    }

    @Override
    protected void renderIcon(PoseStack poseStack, int i, int i1, float v) {
        this.minecraft.getItemRenderer().renderAndDecorateFakeItem(this.iconStack, this.x + 6, this.y + 8);
    }

    @Override
    public void sendOpenContainerPacket() {
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestUBItemInventoryMenu());
        System.out.println(getPosition());
    }
}
