package com.github.manasmods.unordinary_basics.item;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Unordinary_BasicsCreativeTab {

    public static final CreativeModeTab CUSTOM_BLOCKS = new CreativeModeTab(Unordinary_Basics.MOD_ID + ".tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Unordinary_BasicsBlocks.ITEM_SORTER);
        }
    };
    public static final CreativeModeTab BLOCKS = new CreativeModeTab(Unordinary_Basics.MOD_ID + ".tab2") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        }
    };
    public static final CreativeModeTab ITEMS = new CreativeModeTab(Unordinary_Basics.MOD_ID + ".tab3") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Unordinary_BasicsItems.POUCH);
        }
    };
}
