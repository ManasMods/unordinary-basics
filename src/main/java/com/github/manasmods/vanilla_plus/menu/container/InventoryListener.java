package com.github.manasmods.vanilla_plus.menu.container;

import net.minecraftforge.items.ItemStackHandler;

@FunctionalInterface
public interface InventoryListener {
    void onChange(ItemStackHandler handler, int slot);
}
