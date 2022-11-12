package com.github.manasmods.unordinary_basics.tab;

import com.github.manasmods.manascore.api.tab.AbstractInventoryTab;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBInventoryMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.MutableComponent;
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
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestUBInventoryMenu());
    }
}
