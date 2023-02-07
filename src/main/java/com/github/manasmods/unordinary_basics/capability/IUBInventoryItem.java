package com.github.manasmods.unordinary_basics.capability;

/**
 * Should be implemented if item should be compatible with any {@link IUBInventoryHandler} slot.
 */
public interface IUBInventoryItem {

    CapabilityUBInventory.UBSlot getSlot();

}
