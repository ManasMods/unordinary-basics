package com.github.manasmods.vanilla_plus.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class FletchingTableMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess levelAccess;
    private final InvWrapper playerInvWrapper;
    private int lastHotBarIndex, lastInventoryIndex;

    protected FletchingTableMenu(@Nullable MenuType<?> pMenuType, int pContainerId, ContainerLevelAccess levelAccess, InvWrapper playerInvWrapper) {
        super(pMenuType, pContainerId);
        this.levelAccess = levelAccess;
        this.playerInvWrapper = playerInvWrapper;
    }

    private void setupPlayerSlots() {
        int index = 0;

        //Hot bar
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(this.playerInvWrapper, index++, 8 + 18 * col, 83 + 18 * 3));
        }
        lastHotBarIndex = slots.size();

        //Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new SlotItemHandler(this.playerInvWrapper, index++, 8 + 18 * col, 79 + 18 * row));
            }
        }
        lastInventoryIndex = slots.size();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, Blocks.FLETCHING_TABLE);
    }

}
