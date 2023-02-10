package com.github.manasmods.unordinary_basics.capability;

import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsInventoryScreen;
import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Should be implemented if item should be compatible with any {@link IUBInventoryHandler} slot.
 */
public interface IUBInventoryItem {

    /**
     * Slot that this item should go in, inside the UB Inventory.
     */
    CapabilityUBInventory.UBSlot getSlot();

    /**
     * Should return the amount of slots that this item adds.
     */
    int getSlotCount();

    /**
     * This method should be overridden to determine how the visual changes made by this item will be rendered when equipped. <br> <br>
     * All parameters are directly transferred over from the given screen's native <b>renderBg</b> method.
     */
    void renderUsed(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, Unordinary_BasicsInventoryScreen screen);

    /**
     * This method should be overridden in order to add slots that this item may contain. You should handle any item capabilities yourself, possibly using {@link ItemStackHandlerCapabilityProvider} <br> <br>
     * Method is called in the constructor for the menu. <br> <br>
     * Method may be used to conduct other operations on the menu that'd usually be done in the constructor, however it is discouraged. <br> <br>
     * All parameters are directly transferred over.
     */
    void addSlots(int windowId, Inventory inventory, Player player, UBInventoryMenu menu, ItemStack stack);
}
