package com.github.manasmods.unordinary_basics.utils;

import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerHelper {

    public static Tuple<Boolean,Integer> itemExists(Item item, ItemStackHandler stackHandler){
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            if (stackHandler.getStackInSlot(i).is(item)) return new Tuple<>(true,i);
        }
        return new Tuple<>(false,-1);
    }

    public static Tuple<Boolean,Integer> itemExistsInAvailableSlot(Item item, ItemStackHandler stackHandler){
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            if (stackHandler.getStackInSlot(i).is(item) && stackHandler.getStackInSlot(i).getCount() < 64) return new Tuple<>(true,i);
        }
        return new Tuple<>(false,-1);
    }

}
