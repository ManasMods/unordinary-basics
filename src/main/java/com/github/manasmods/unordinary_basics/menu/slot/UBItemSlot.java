package com.github.manasmods.unordinary_basics.menu.slot;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class UBItemSlot extends SlotItemHandler implements IUBItemSlot {
    public UBItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
}
