package com.github.manasmods.unordinary_basics.item.capability;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Capability holder for the redstone pouch - additionally contains many utility methods
 */
public class RedstonePouchCapability implements ICapabilityProvider {

    public ItemStackHandler stackHandler = new ItemStackHandler(64){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.getItem().equals(Items.REDSTONE);
        }
    };

    private final LazyOptional<IItemHandler> optional = LazyOptional.of(() -> stackHandler);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return optional.cast();
        }
            return LazyOptional.empty();
    }


    /**
     * @return the first ItemStack in the inventory matching the given Item, if the stack has item capabilities, only returns if its inventory isn't full
     */
    @Nullable
    public static ItemStack findFirstItemInInventory(Player pPlayer, Item pItem){

        Inventory inv = pPlayer.getInventory();

        for (int i = 0; i < inv.getContainerSize(); ++i){
            if (inv.getItem(i).getItem().equals(pItem)){
                ItemStack stack = inv.getItem(i);
                if (!stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()) return stack;

                AtomicBoolean shouldReturn = new AtomicBoolean(false);

                stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    shouldReturn.set(getFirstAvailableSlot(handler) != -8);
                });

                if (shouldReturn.get()) return stack;
            }
        }

        return null;
    }

    /**
     *Dumps the contents of an ItemStack into the first available slots of an item handler
     * @return false if operation was unsuccessful
     */
    public static boolean dumpItemStack(ItemStack stack, IItemHandler handler){
        int availableSlot;

        while (stack.getCount() > 0){
            availableSlot = getFirstAvailableSlot(handler);
            if (availableSlot == -8) return false;
            if (handler.getStackInSlot(availableSlot).getCount() + stack.getCount() <= 64){
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(stack.getCount());
                return true;
            } else {
                int toFill = 64 - handler.getStackInSlot(availableSlot).getCount();
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(toFill);
            }
        }
        return false;
    }

    /**
     *Dumps the contents of an ItemStack into the first available slots of an item handler
     * @return the number of items unprocessed if unsuccessful
     */
    public static int dumpItemStackInt(ItemStack stack, IItemHandler handler){
        int availableSlot;

        while (stack.getCount() > 0){
            availableSlot = getFirstAvailableSlot(handler);
            if (availableSlot == -8) return stack.getCount();
            if (handler.getStackInSlot(availableSlot).getCount() + stack.getCount() <= 64){
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(stack.getCount());
                return 0;
            } else {
                int toFill = 64 - handler.getStackInSlot(availableSlot).getCount();
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(toFill);
            }
        }
        return stack.getCount();
    }

    /**
     *Adds one item from the given stack to the first available slot on the handler
     * @return false if operation was unsuccessful
     */
    public static boolean addOneItem(ItemStack stack, IItemHandler handler){
        int availableSlot = getFirstAvailableSlot(handler);

        if (availableSlot != -8){
            handler.insertItem(availableSlot,new ItemStack(stack.getItem()),false);
            stack.shrink(1);
            return true;
        }

        return false;
    }

    /**
     * Removes one item from the given handler into the given slot
     * @param itemToAdd the type of the item that will be added to the slot, if left null, the slot's present item will be used, if you do this, never enter an empty slot
     * @return false if operation was unsuccessful
     */
    public static boolean removeOneItem(IItemHandler handler, Slot pSlot, @Nullable Item itemToAdd){
        int availableSlot = getFirstNonEmptySlot(handler);

        if (availableSlot != -8){
                if (itemToAdd != null) {
                    pSlot.safeInsert(new ItemStack(itemToAdd));
                    handler.extractItem(availableSlot, 1, false);
                    return true;
                } else {
                    pSlot.safeInsert(new ItemStack(pSlot.getItem().getItem()));
                    handler.extractItem(availableSlot, 1, false);
                    return true;
                }
            }

        return false;
    }

    /**
     *Gets the first slot in an item handler that isn't full
     * @return the slot number, -8 if all slots are full
     */
    public static int getFirstAvailableSlot(IItemHandler handler){
        for (int i = 0; i < handler.getSlots(); ++i){
            if (handler.getStackInSlot(i).getCount() < 64){
                return i;
            }
        }
        return -8;
    }

    /**
     *Gets the first slot in an item handler that isn't empty
     * @return the slot number, -8 if all slots are empty
     */
    public static int getFirstNonEmptySlot(IItemHandler handler){
        for (int i = 0; i < handler.getSlots(); ++i){
            if (handler.getStackInSlot(i).getCount() > 0){
                return i;
            }
        }
        return -8;
    }

    /**
     * Gets the total number of items in an IItemhandler
     */
    public static int getItemAmount(IItemHandler handler){
            int count = 0;
            for (int i = 0; i < handler.getSlots(); ++i){
                count = count + handler.getStackInSlot(i).getCount();
            }
            return count;
    }

    /**
     * Drops one item from an IItemhandler directly to the world
     * @return false if operation was unsuccessful
     */
    public static boolean dropOneItem(IItemHandler handler, Player player){
        int availableSlot = getFirstNonEmptySlot(handler);

        if (availableSlot == -8) return false;

        spawnAtLocationWithVelocity(new ItemStack(handler.getStackInSlot(availableSlot).getItem()),1f,player);
        handler.extractItem(availableSlot,1,false);
        return true;
    }

    public static boolean dropOneStack(IItemHandler handler, Player player, Item type){
        int removed = 0;
        int availableSlot;

        while (removed < 64){
            availableSlot = getFirstNonEmptySlot(handler);
            if (availableSlot == -8){
                if (removed > 0){
                    ItemStack stack = new ItemStack(type,removed);
                    spawnAtLocationWithVelocity(stack,1f,player);
                    return true;
                } else {
                    return false;
                }
            }

            int currentlyChecked = handler.getStackInSlot(availableSlot).getCount();
            if (currentlyChecked + removed < 64){
                removed = removed + currentlyChecked;
                handler.extractItem(availableSlot,currentlyChecked,false);
            } else if (currentlyChecked + removed >= 64){
                handler.extractItem(availableSlot, 64 - removed,false);
                removed = 64;
                spawnAtLocationWithVelocity(new ItemStack(type,removed),1f,player);
                return true;
            }
        }

        return false;
    }

    public static ItemEntity spawnAtLocationWithVelocity(ItemStack pStack, float pOffsetY, Player pPlayer) {
        if (pStack.isEmpty()) {
            return null;
        } else if (pPlayer.level.isClientSide) {
            return null;
        } else {

            Vec3 viewVector = pPlayer.getViewVector(1.0F);

            ItemEntity itementity = new ItemEntity(pPlayer.level, pPlayer.getX(), pPlayer.getY() + (double)pOffsetY, pPlayer.getZ(), pStack, viewVector.x * 0.35, viewVector.y * 0.35, viewVector.z * 0.35);
            itementity.setDefaultPickUpDelay();
            if (pPlayer.captureDrops() != null) pPlayer.captureDrops().add(itementity);
            else
                pPlayer.level.addFreshEntity(itementity);
            return itementity;
        }
    }
}
