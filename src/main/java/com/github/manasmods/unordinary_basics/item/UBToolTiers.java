package com.github.manasmods.unordinary_basics.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class UBToolTiers {

        public static final ForgeTier ZENITH = new ForgeTier(4, 10000, 12.0F, 0, 30,
                Tags.Blocks.NEEDS_NETHERITE_TOOL,
                Ingredient::of);

}
