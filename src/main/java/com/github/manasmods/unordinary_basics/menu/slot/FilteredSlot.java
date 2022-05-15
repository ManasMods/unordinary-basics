package com.github.manasmods.unordinary_basics.menu.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FilteredSlot extends Slot {
    public FilteredSlot(Container pContainer, int pIndex, int pX, int pY) {
        super(pContainer, pIndex, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return container.canPlaceItem(this.index, pStack);
    }
}
