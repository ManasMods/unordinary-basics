package com.github.manasmods.vanilla_plus.menu;

import com.github.manasmods.vanilla_plus.block.entity.JukeboxBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class JukeBoxMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess levelAccess;
    private final InvWrapper playerInvWrapper;
    private final ItemStackHandler container;
    private int lastHotBarIndex, lastInventoryIndex, lastJukeBoxIndex, discPlayerSlotIndex;
    private final JukeboxBlockEntity blockEntity;

    public JukeBoxMenu(int containerId, Inventory inventory, JukeboxBlockEntity blockEntity, ContainerLevelAccess levelAccess) {
        super(Vanilla_PlusMenuTypes.JUKE_BOX_MENU, containerId);
        this.levelAccess = levelAccess;
        this.playerInvWrapper = new InvWrapper(inventory);
        this.container = blockEntity.getContainer();
        this.blockEntity = blockEntity;

        setupJukeBoxSlots();
        setupPlayerSlots();
    }

    private void setupJukeBoxSlots() {
        int slotIndex = 0;
        slotIndex = placeCol(8, 3, 4, slotIndex);
        slotIndex = placeCol(8 + 18, 3, 4, slotIndex);
        this.discPlayerSlotIndex = slotIndex;
        addSlot(new SlotItemHandler(this.container, slotIndex++, 80, 30));
        slotIndex = placeCol(134, 3, 4, slotIndex);
        placeCol(134 + 18, 3, 4, slotIndex);
        lastJukeBoxIndex = slots.size();
    }

    private int placeCol(int x, int y, int amount, int startIndex) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(this.container, startIndex++, x, y + 18 * i));
        }

        return startIndex;
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
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack slotItemStack = slots.get(pIndex).getItem();
        if (slotItemStack.isEmpty()) return ItemStack.EMPTY;

        if (pIndex < lastJukeBoxIndex) {
            //Handle Jukebox Inventory Shift-Click
            if (pIndex == this.discPlayerSlotIndex) {
                //Try to move it into the Jukebox storage
                slotItemStack = moveToJukeBoxInventory(slotItemStack);
                //Move it to the player inventory if the jukebox storage is full
                if (!slotItemStack.isEmpty()) {
                    slotItemStack = moveToPlayerInventory(slotItemStack);
                }
            } else {
                slotItemStack = moveToPlayerInventory(slotItemStack);
            }
        } else {
            slotItemStack = moveToJukeBoxInventory(slotItemStack);
        }

        Slot slot = slots.get(pIndex);
        slot.set(slotItemStack);
        slot.setChanged();
        return ItemStack.EMPTY;
    }

    private ItemStack moveToPlayerInventory(int slotIndex, ItemStack itemStack) {
        Slot currentCurrentSlot = getSlot(slotIndex);
        //Check if slot exist
        if (currentCurrentSlot == null) return itemStack;
        //Try to place Item into the Player Slot
        if (currentCurrentSlot.mayPlace(itemStack)) {
            itemStack = currentCurrentSlot.safeInsert(itemStack);
            currentCurrentSlot.setChanged();
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, Blocks.JUKEBOX);
    }

    private ItemStack moveToPlayerInventory(ItemStack stack) {
        //Check Space in hot-bar
        for (int i = lastJukeBoxIndex; i < lastHotBarIndex; i++) {
            stack = moveToPlayerInventory(i, stack);
            //Check if there are no items left
            if (stack.isEmpty()) {
                break;
            }
        }

        //Check Space in Inventory
        if (!stack.isEmpty()) {
            for (int i = lastHotBarIndex; i < lastInventoryIndex; i++) {
                stack = moveToPlayerInventory(i, stack);
                //Check if there are no items left
                if (stack.isEmpty()) {
                    break;
                }
            }
        }

        return stack;
    }

    private ItemStack moveToJukeBoxInventory(ItemStack stack) {
        //Handle Player Inventory Shift-Click
        for (int i = 0; i < lastJukeBoxIndex; i++) {
            if (i == discPlayerSlotIndex) continue;
            Slot currentCurrentSlot = getSlot(i);
            //Check if slot exist
            if (currentCurrentSlot == null) break;

            //Try to place Item into the Player Slot
            if (currentCurrentSlot.mayPlace(stack)) {
                stack = currentCurrentSlot.safeInsert(stack);
                currentCurrentSlot.setChanged();

            }
        }
        return stack;
    }

    public void startPlaying() {
        this.blockEntity.play();
    }

    public void stopPlaying() {
        this.blockEntity.stop();
    }

    public boolean isPlaying() {
        return this.blockEntity.isPlaying();
    }
}
