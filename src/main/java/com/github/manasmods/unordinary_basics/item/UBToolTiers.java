package com.github.manasmods.unordinary_basics.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class UBToolTiers {
        public static final ForgeTier ZENITH = new ForgeTier(4, 10000, 12.0F, 0, 30,
                Tags.Blocks.NEEDS_NETHERITE_TOOL, Ingredient::of);
        public static final ForgeTier MASTER = new ForgeTier(5, 2500, 10.0F, 0, 30,
                Tags.Blocks.NEEDS_NETHERITE_TOOL, Ingredient::of);
}
