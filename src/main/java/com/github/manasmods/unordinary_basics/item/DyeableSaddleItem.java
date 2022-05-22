package com.github.manasmods.unordinary_basics.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;

import java.util.List;

public class DyeableSaddleItem extends SaddleItem implements DyeableSaddleItem {
    String TAG_COLOR = "color";
    String TAG_DISPLAY = "display";
    int DEFAULT_SADDLE_COLOR = 10511680;

    public DyeableSaddleItem(Properties p_43053_) {
        super(p_43053_);
    }

    boolean hasCustomColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99);
    }

    int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : 10511680;
    }

    void clearColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        if (compoundtag != null && compoundtag.contains("color")) {
            compoundtag.remove("color");
        }

    }

    void setColor(ItemStack pStack, int pColor) {
        pStack.getOrCreateTagElement("display").putInt("color", pColor);
    }

    static ItemStack dyeArmor(ItemStack pStack, List<DyeItem> pDyes) {
        ItemStack itemstack = ItemStack.EMPTY;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        DyeableSaddleItem DyeableSaddleItem = null;
        Item item = pStack.getItem();
        if (item instanceof DyeableSaddleItem) {
            DyeableSaddleItem = (DyeableSaddleItem)item;
            itemstack = pStack.copy();
            itemstack.setCount(1);
            if (DyeableSaddleItem.hasCustomColor(pStack)) {
                int k = DyeableSaddleItem.getColor(itemstack);
                float f = (float)(k >> 16 & 255) / 255.0F;
                float f1 = (float)(k >> 8 & 255) / 255.0F;
                float f2 = (float)(k & 255) / 255.0F;
                i += (int)(Math.max(f, Math.max(f1, f2)) * 255.0F);
                aint[0] += (int)(f * 255.0F);
                aint[1] += (int)(f1 * 255.0F);
                aint[2] += (int)(f2 * 255.0F);
                ++j;
            }

            for(DyeItem dyeitem : pDyes) {
                float[] afloat = dyeitem.getDyeColor().getTextureDiffuseColors();
                int i2 = (int)(afloat[0] * 255.0F);
                int l = (int)(afloat[1] * 255.0F);
                int i1 = (int)(afloat[2] * 255.0F);
                i += Math.max(i2, Math.max(l, i1));
                aint[0] += i2;
                aint[1] += l;
                aint[2] += i1;
                ++j;
            }
        }

        if (DyeableSaddleItem == null) {
            return ItemStack.EMPTY;
        } else {
            int j1 = aint[0] / j;
            int k1 = aint[1] / j;
            int l1 = aint[2] / j;
            float f3 = (float)i / (float)j;
            float f4 = (float)Math.max(j1, Math.max(k1, l1));
            j1 = (int)((float)j1 * f3 / f4);
            k1 = (int)((float)k1 * f3 / f4);
            l1 = (int)((float)l1 * f3 / f4);
            int j2 = (j1 << 8) + k1;
            j2 = (j2 << 8) + l1;
            DyeableSaddleItem.setColor(itemstack, j2);
            return itemstack;
        }
    }
}