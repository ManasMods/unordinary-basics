package com.github.manasmods.unordinary_basics.menu.container;

import net.minecraftforge.items.ItemStackHandler;

@FunctionalInterface
public interface InventoryListener {
    void onChange(ItemStackHandler handler, int slot);
}
