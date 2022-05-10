package com.github.manasmods.vanilla_plus.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class FletchingTableMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess levelAccess;
    private final InvWrapper playerInvWrapper;

    public FletchingTableMenu(int pContainerId, ContainerLevelAccess levelAccess, Inventory playerInvWrapper) {
        super(Vanilla_PlusMenuTypes.FLETCHING_TABLE_MENU, pContainerId);
        this.levelAccess = levelAccess;
        this.playerInvWrapper = new InvWrapper(playerInvWrapper);

        setupFletchingTableSlots();
        setupPlayerSlots();
    }

    private void setupFletchingTableSlots() {
        int slotIndex = 0;
        this..getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
        this.addSlot(new SlotItemHandler(handler, 0, 30, 22));
        this.addSlot(new SlotItemHandler(handler, 1, 30, 43));
        this.addSlot(new SlotItemHandler(handler, 2, 30, 64));
        this.addSlot(new SlotItemHandler(handler, 3, 38, 22));
        this.addSlot(new SlotItemHandler(handler, 4, 38, 43));
        this.addSlot(new SlotItemHandler(handler, 5, 38, 64));
        this.addSlot(new ResultSlot(handler, 6, 76, 42) {
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return false;
            }
        });
        });
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

