package com.github.manasmods.unordinary_basics.item;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Unordinary_BasicsCreativeTab {

    public static final CreativeModeTab BLOCKS = new CreativeModeTab(Unordinary_Basics.MOD_ID + ".tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BRICKS);
        }
    };

}
