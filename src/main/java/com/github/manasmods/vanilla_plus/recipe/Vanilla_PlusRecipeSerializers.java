package com.github.manasmods.vanilla_plus.recipe;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Vanilla_Plus.MOD_ID)
public class Vanilla_PlusRecipeSerializers {
    @ObjectHolder("fletching_recipe")
    public static final RecipeSerializer<FletchingRecipe> FLETCHING_RECIPE =null;
}
