package com.github.manasmods.unordinary_basics.item;


import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

/**
 * Sets the color to the nbt that is provided
 */
public class UBSaddleColourHandler implements ItemColor {

    @Override
    public int getColor(ItemStack pStack, int pTintIndex) {
        if (pStack.hasTag()) {
            switch (pTintIndex) {
                case 0 -> {
                    assert pStack.getTag() != null;
                    return pStack.getTag().getInt("SecItemColor");
                }
                case 1 -> {
                    assert pStack.getTag() != null;
                    return pStack.getTag().getInt("ItemColor");
                }
                default -> {
                    return 15869935;
                }
            }
        } else {
            if (pTintIndex == 0) {
                return 15869935;
            } else return -1;
        }
    }
}