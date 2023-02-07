package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.menu.slot.SlotUBInventory;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;
import net.minecraftforge.items.wrapper.PlayerOffhandInvWrapper;

public class UBInventoryMenu extends AbstractContainerMenu {
    private final Player player;
    private final InvWrapper inventoryHelper;
    private final PlayerArmorInvWrapper armorHelper;
    private final PlayerOffhandInvWrapper offhandHelper;
    private UBInventoryItemStackHandler stackHandler;

    public UBInventoryMenu(int windowId, Inventory inventory, Player player) {
        super(Unordinary_BasicsMenuTypes.INVENTORY_MENU, windowId);
        this.player = player;
        this.inventoryHelper = new InvWrapper(inventory);
        this.armorHelper = new PlayerArmorInvWrapper(inventory);
        this.offhandHelper = new PlayerOffhandInvWrapper(inventory);


        addPlayerInventorySlots();

        player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
            this.stackHandler = (UBInventoryItemStackHandler) handler;
        });

        this.addSlot(new SlotUBInventory(stackHandler,0,77,26));
        this.addSlot(new SlotUBInventory(stackHandler,1,77,62));

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

        //Armor
        for (int row = 0; row < 4; row++){
            addSlot(new SlotItemHandler(armorHelper,3 - row,8,8 + row * 18));
        }

        //Offhand
        addSlot(new SlotItemHandler(offhandHelper,40,77,44));
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
