package com.github.manasmods.unordinary_basics.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class UBInventoryMenu extends AbstractContainerMenu {
    private final Player player;
    private final InvWrapper inventoryHelper;

    public UBInventoryMenu(int windowId, Inventory inventory, Player player) {
        super(Unordinary_BasicsMenuTypes.INVENTORY_MENU, windowId);
        this.player = player;
        this.inventoryHelper = new InvWrapper(inventory);
        addPlayerInventorySlots();
    }

    private void addPlayerInventorySlots() {
        int index = 0;

        //Hot bar
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(this.inventoryHelper, index++, 8 + 18 * col, 142));
        }

        //Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new SlotItemHandler(this.inventoryHelper, index++, 8 + 18 * col, 84 + 18 * row));
            }
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(Player player) {
        return true;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
