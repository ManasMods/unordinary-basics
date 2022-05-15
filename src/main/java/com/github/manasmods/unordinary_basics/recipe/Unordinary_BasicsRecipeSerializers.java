package com.github.manasmods.unordinary_basics.recipe;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Unordinary_Basics.MOD_ID)
public class Unordinary_BasicsRecipeSerializers {
    @ObjectHolder("fletching_recipe")
    public static final RecipeSerializer<FletchingRecipe> FLETCHING_RECIPE =null;
}
