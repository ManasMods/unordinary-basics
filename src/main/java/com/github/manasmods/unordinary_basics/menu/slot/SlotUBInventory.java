package com.github.manasmods.unordinary_basics.menu.slot;

import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class SlotUBInventory extends Slot {
    private static Container emptyInventory = new SimpleContainer(0);
    private final IUBInventoryHandler itemHandler;
    private final int index;

    public SlotUBInventory(IUBInventoryHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(emptyInventory, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack)
    {
        if (stack.isEmpty())
            return false;
        return itemHandler.isItemValid(stack,index);
    }

    @Override
    @Nonnull
    public ItemStack getItem()
    {
        return this.getItemHandler().getStackInSlot(index);
    }

    @Override
    public void set(@Nonnull ItemStack stack)
    {
        this.getItemHandler().setStackInSlot(index, stack);
        this.setChanged();
    }

    @Override
    public void onQuickCraft(@Nonnull ItemStack oldStackIn, @Nonnull ItemStack newStackIn)
    {

    }

    @Override
    public int getMaxStackSize()
    {
        return 64;
    }

    @Override
    public int getMaxStackSize(@Nonnull ItemStack stack)
    {
        ItemStack maxAdd = stack.copy();
        int maxInput = stack.getMaxStackSize();
        maxAdd.setCount(maxInput);

        IUBInventoryHandler handler = this.getItemHandler();
        ItemStack currentStack = handler.getStackInSlot(index);

        handler.setStackInSlot(index, ItemStack.EMPTY);

        ItemStack remainder = handler.insertItem(index, maxAdd, true);

        handler.setStackInSlot(index, currentStack);

        return maxInput - remainder.getCount();

    }

    @Override
    public boolean mayPickup(Player playerIn)
    {
        return !this.getItemHandler().extractItem(index, 1, true).isEmpty();
    }

    @Override
    @Nonnull
    public ItemStack remove(int amount)
    {
        return this.getItemHandler().extractItem(index, amount, false);
    }

    public IUBInventoryHandler getItemHandler()
    {
        return itemHandler;
    }
}
