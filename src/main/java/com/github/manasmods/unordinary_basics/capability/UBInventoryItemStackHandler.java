package com.github.manasmods.unordinary_basics.capability;

import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copy of {@link ItemStackHandler} with some minor changes. <br> <br>
 * Slot indexes should generally be obtained with {@link CapabilityUBInventory}'s SLOT_INDEX and {@link CapabilityUBInventory.UBSlot}
 */
public class UBInventoryItemStackHandler implements IUBInventoryHandler, INBTSerializable<CompoundTag> {
    protected NonNullList<ItemStack> stacks;
    private UBInventoryMenu menu;

    public UBInventoryItemStackHandler()
    {
        stacks = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    public UBInventoryItemStackHandler(NonNullList<ItemStack> stacks)
    {
        if (stacks.size() == 2) {
            this.stacks = stacks;
        } else throw new IllegalArgumentException("When creating a new UBInventoryItemStackHandler, the passed in NonNullList should be of size 2. The current is " + stacks.size());
    }

    //---UB---

    /**
     * @return true - if given itemstack exists in the handler
     */
    public boolean isItemEquipped(ItemStack stack){
        if (stack.getItem() instanceof IUBInventoryItem){
            for (int i = 0; i < this.getSlots(); ++i){
                if (this.getStackInSlot(i).equals(stack)) return true;
            }
        }
        return false;
    }

    /**
     * @return true - if given item type exists in handler
     */
    public boolean isItemEquipped(Item item){
        if (item instanceof IUBInventoryItem){
            for (int i = 0; i < this.getSlots(); ++i){
                if (this.getStackInSlot(i).getItem().equals(item)) return true;
            }
        }
        return false;
    }

    @Nullable
    public ItemStack findFirstInstanceOf(Item item){
        for (int i = 0; i < this.getSlots(); i++){
            if (this.getStackInSlot(i).getItem().equals(item)) return this.getStackInSlot(i);
        }
        return null;
    }

    public static ItemStack findFirstInstanceOf(Item item, IUBInventoryHandler handler){
        for (int i = 0; i < handler.getSlots(); i++){
            if (handler.getStackInSlot(i).getItem().equals(item)) return handler.getStackInSlot(i);
        }
        return null;
    }

    public static boolean isItemEquipped(Item item, IUBInventoryHandler handler){
        if (item instanceof IUBInventoryItem){
            for (int i = 0; i < handler.getSlots(); ++i){
                if (handler.getStackInSlot(i).getItem().equals(item)) return true;
            }
        }
        return false;
    }

    /**
     * Don't call this unless working on {@link UBInventoryMenu}
     */
    public void setMenu(UBInventoryMenu menu) {
        this.menu = menu;
    }

    //---FORGE---

    public void setSize(int size)
    {
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack)
    {
        validateSlotIndex(slot);
        this.stacks.set(slot, stack);
        onContentsChanged(slot);
    }

    @Override
    public int getSlots()
    {
        return stacks.size();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot)
    {
        validateSlotIndex(slot);
        return this.stacks.get(slot);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if (!isItemValid(stack,slot)) return stack;

        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
                return existing;
            }
            else
            {
                return existing.copy();
            }
        }
        else
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                onContentsChanged(slot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    @Override
    public CompoundTag serializeNBT()
    {
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < stacks.size(); i++)
        {
            if (!stacks.get(i).isEmpty())
            {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                stacks.get(i).save(itemTag);
                nbtTagList.add(itemTag);
            }
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        nbt.putInt("Size", stacks.size());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setSize(nbt.contains("Size", Tag.TAG_INT) ? nbt.getInt("Size") : stacks.size());
        ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < stacks.size())
            {
                stacks.set(slot, ItemStack.of(itemTags));
            }
        }
        onLoad();
    }

    protected int getStackLimit(@Nonnull ItemStack stack)
    {
        return Math.min(64, stack.getMaxStackSize());
    }

    protected void validateSlotIndex(int slot)
    {
        if (slot < 0 || slot >= stacks.size())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.size() + ")");
    }

    protected void onLoad()
    {

    }

    protected void onContentsChanged(int slot)
    {
        if (menu != null){
            menu.resetScreen();
        }

    }

    @Override
    public boolean isItemValid(ItemStack stack, int slot){
        if (stack.getItem() instanceof IUBInventoryItem || stack.is(UBTags.Items.UB_SLOT_BACK) || stack.is(UBTags.Items.UB_SLOT_WAIST)){
            boolean isBackTagged = stack.is(UBTags.Items.UB_SLOT_BACK);
            boolean isWaistTagged = stack.is(UBTags.Items.UB_SLOT_WAIST);

            TagKey<Item> itemTagKey;

            if (isBackTagged || isWaistTagged){
                itemTagKey = isBackTagged ? UBTags.Items.UB_SLOT_BACK : UBTags.Items.UB_SLOT_WAIST;
                return slot == CapabilityUBInventory.SLOT_INDEX_TAG.get(itemTagKey);
            }

            return slot == CapabilityUBInventory.SLOT_INDEX.get(((IUBInventoryItem)stack.getItem()).getSlot());
        }
        return false;
    }
}
