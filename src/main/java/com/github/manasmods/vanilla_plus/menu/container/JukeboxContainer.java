package com.github.manasmods.vanilla_plus.menu.container;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JukeboxContainer extends ItemStackHandler {
    private final List<InventoryListener> inventoryListeners = new ArrayList<>();
    private final List<InventoryLoadListener> inventoryLoadListeners = new ArrayList<>();

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

    public void addListener(InventoryListener listener) {
        inventoryListeners.add(listener);
    }

    public void removeListener(InventoryListener listener) {
        inventoryListeners.remove(listener);
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.inventoryListeners.forEach(listener -> listener.onChange(this, slot));
    }

    @Override
    protected void onLoad() {
        inventoryLoadListeners.forEach(listener -> listener.load(this));
    }

    public void addLoadListener(InventoryLoadListener listener) {
        this.inventoryLoadListeners.add(listener);
    }
}
