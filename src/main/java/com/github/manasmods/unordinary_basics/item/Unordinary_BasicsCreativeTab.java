package com.github.manasmods.unordinary_basics.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Unordinary_BasicsCreativeTab {

    public static final CreativeModeTab BLOCKS = new CreativeModeTab("vanilla_plustab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BRICKS);
        }
    };

}
