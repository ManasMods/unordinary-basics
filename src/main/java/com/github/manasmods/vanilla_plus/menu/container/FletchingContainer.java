package com.github.manasmods.vanilla_plus.menu.container;

import lombok.RequiredArgsConstructor;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

@RequiredArgsConstructor
public class FletchingContainer implements Container, StackedContentsCompatible {
    private static final int size = 6;
    private final AbstractContainerMenu menu;
    private final NonNullList<ItemStack> items = NonNullList.withSize(size, ItemStack.EMPTY);

    @Override
    public int getContainerSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int pIndex) {
        return pIndex >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(pIndex);
    }

    @Override
    public ItemStack removeItem(int pIndex, int pCount) {
        ItemStack itemstack = ContainerHelper.removeItem(this.items, pIndex, pCount);
        if (!itemstack.isEmpty()) {
            this.menu.slotsChanged(this);
        }

        return itemstack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.items, pIndex);
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {
        this.items.set(pIndex, pStack);
        this.menu.slotsChanged(this);
    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void fillStackedContents(StackedContents pHelper) {
        for (ItemStack itemstack : this.items) {
            pHelper.accountSimpleStack(itemstack);
        }
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        return switch (pIndex) {
            case 0 -> pStack.is(Items.FLINT);
            case 1 -> pStack.is(Items.LINGERING_POTION);
            case 2 -> pStack.is(Tags.Items.RODS_WOODEN);
            case 3 -> pStack.is(Tags.Items.FEATHERS);
            case 4 -> pStack.is(Items.ARROW);
            case 5 -> pStack.is(Tags.Items.DUSTS_GLOWSTONE);
            default -> false;
        };
    }
}
