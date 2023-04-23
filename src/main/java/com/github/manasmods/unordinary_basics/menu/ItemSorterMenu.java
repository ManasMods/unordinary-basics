package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.block.entity.ItemSorterBlockEntity;
import com.github.manasmods.unordinary_basics.menu.slot.GhostSlot;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemSorterMenu extends AbstractContainerMenu {

    public final ItemSorterBlockEntity blockEntity;
    private final Level level;

    public ItemSorterMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId,inv,inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public ItemSorterMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(Unordinary_BasicsMenuTypes.ITEM_SORTER_MENU, pContainerId);
        checkContainerSize(inv,6);

        this.level = inv.player.level;
        this.blockEntity = (ItemSorterBlockEntity) entity;

        NonNullList<Item> filterItems = blockEntity.filterItems;

        addGhostSlots(filterItems);
        addPlayerHotbar(inv);
        addPlayerInventory(inv);
    }

    private void addGhostSlots(NonNullList<Item> filterItems){
        for (int i = 0 ; i < 2; ++i){
            for (int k = 0 ; k < 3; ++k){
                this.addSlot(new GhostSlot(i * 3 + k , 114 + k * 18, 8 + i * 18,this.blockEntity,filterItems.get(i * 3 + k)));
            }
        }
    }

    private static final int SLOT_COUNT = 6;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            final ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < SLOT_COUNT) {
                if (!moveItemStackTo(itemstack1, SLOT_COUNT, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(itemstack1, 0, SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 51 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 109));
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, Unordinary_BasicsBlocks.ITEM_SORTER);
    }
}
