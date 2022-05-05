package com.github.manasmods.vanilla_plus.menu.container;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class JukeboxContainer extends ItemStackHandler {
    public JukeboxContainer() {
        super(17);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof RecordItem;
    }

    public ItemStack getCurrentRecord() {
        return getStackInSlot(8);
    }

    public ItemStack setCurrentRecord(ItemStack pRecord) {
        return insertItem(8, pRecord, false);
    }

    public void clear() {
        Collections.fill(stacks, ItemStack.EMPTY);
    }
}
