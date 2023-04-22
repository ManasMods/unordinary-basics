package com.github.manasmods.unordinary_basics.menu.slot;

import com.github.manasmods.unordinary_basics.block.entity.ItemSorterBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GhostSlot extends Slot {

    private final ItemSorterBlockEntity itemSorterBlockEntity;
    public Item currentItem;
    private final int slotIndex;

    public GhostSlot(int pSlot, int pX, int pY, ItemSorterBlockEntity itemSorterBlockEntity, Item toLoad) {
        super(new Container() {
            @Override
            public int getContainerSize() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public ItemStack getItem(int pSlot) {
                return null;
            }

            @Override
            public ItemStack removeItem(int pSlot, int pAmount) {
                return null;
            }

            @Override
            public ItemStack removeItemNoUpdate(int pSlot) {
                return null;
            }

            @Override
            public void setItem(int pSlot, ItemStack pStack) {

            }

            @Override
            public void setChanged() {

            }

            @Override
            public boolean stillValid(Player pPlayer) {
                return false;
            }

            @Override
            public void clearContent() {

            }
        }, pSlot, pX, pY);

        this.itemSorterBlockEntity = itemSorterBlockEntity;
        this.slotIndex = pSlot;
        this.currentItem = toLoad;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        this.currentItem = pStack.getItem();
        itemSorterBlockEntity.filterItems.set(this.slotIndex,this.currentItem);
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public void set(ItemStack pStack) {
        //NO - OP
        return;
    }

    @Override
    public void setChanged() {
        //NO - OP
        return;
    }
}
