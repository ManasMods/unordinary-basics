package com.github.manasmods.unordinary_basics.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;

public class Unordinary_BasicsRecipeSerializers {
    @ObjectHolder(registryName = "recipe_serializer", value = "unordinary_basics:fletching_recipe")
    public static final RecipeSerializer<FletchingRecipe> FLETCHING_RECIPE = null;
    @ObjectHolder(registryName = "recipe_serializer", value = "unordinary_basics:prismarine_smoking_recipe")
    public static final RecipeSerializer<PrismarineSmokingRecipe> PRISMARINE_SMOKING_RECIPE = null;
}
