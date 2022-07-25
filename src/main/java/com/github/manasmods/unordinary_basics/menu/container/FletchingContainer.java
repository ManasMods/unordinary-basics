package com.github.manasmods.unordinary_basics.menu.container;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class FletchingContainer extends CraftingContainer {
    private static final int size = 6;

    public FletchingContainer(AbstractContainerMenu menu) {
        super(menu, 2, 3);
    }

    @Override
    public int getContainerSize() {
        return size;
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        return isSlotValid(pIndex, pStack);
    }

    public static boolean isSlotValid(int pIndex, ItemStack pStack) {
        return switch (pIndex) {
            case 0 -> pStack.is(Items.FLINT);
            case 1 -> pStack.is(Tags.Items.RODS_WOODEN);
            case 2 -> pStack.is(Tags.Items.FEATHERS);
            case 3 -> pStack.is(Items.LINGERING_POTION);
            case 4 -> pStack.is(Items.ARROW);
            case 5 -> pStack.is(Tags.Items.DUSTS_GLOWSTONE);
            default -> false;
        };
    }

    public List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(getItem(i));
        }
        return items;
    }
}
