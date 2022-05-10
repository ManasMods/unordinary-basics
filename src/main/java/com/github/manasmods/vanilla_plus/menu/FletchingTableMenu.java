package com.github.manasmods.vanilla_plus.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class FletchingTableMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess levelAccess;
    private final InvWrapper playerInvWrapper;
    private final CraftingContainer craftSlots = new CraftingContainer(this, 3, 3);
    private final ResultContainer resultSlots = new ResultContainer();


    public FletchingTableMenu(int pContainerId, ContainerLevelAccess levelAccess, Inventory playerInvWrapper) {
        super(Vanilla_PlusMenuTypes.FLETCHING_TABLE_MENU, pContainerId);
        this.levelAccess = levelAccess;
        this.playerInvWrapper = new InvWrapper(playerInvWrapper);

        setupFletchingTableSlots();
        setupPlayerSlots();
    }

    private void setupFletchingTableSlots() {
        int slotIndex = 0;
        this.addSlot(new Slot(this.craftSlots,0,29, 21 - 5));
        this.addSlot(new Slot(this.craftSlots,1,29, 42 - 5));
        this.addSlot(new Slot(this.craftSlots,2,29, 63 - 5));
        this.addSlot(new Slot(this.craftSlots,3,42 + 8, 21 - 5));
        this.addSlot(new Slot(this.craftSlots,4,42 + 8, 42 - 5));
        this.addSlot(new Slot(this.craftSlots,5,42 + 8, 63 - 5));
    }

    private void setupPlayerSlots() {
        int index = 0;

        //Hot bar
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(this.playerInvWrapper, index++, 8 + 18 * col, 95 + 18 * 3));
        }
        int lastHotBarIndex = slots.size();

        //Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new SlotItemHandler(this.playerInvWrapper, index++, 8 + 18 * col, 91 + 18 * row));
            }
        }
        int lastInventoryIndex = slots.size();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, Blocks.FLETCHING_TABLE);
    }

}

