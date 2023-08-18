package com.github.manasmods.unordinary_basics.recipe;

import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRecipeTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class PrismarineSmokingRecipe extends AbstractCookingRecipe {

    public PrismarineSmokingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(Unordinary_BasicsRecipeTypeRegistry.PRISMARINE_SMOKING_RECIPE.get(), id, group, ingredient, result, experience, cookingTime);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(Unordinary_BasicsBlocks.PRISMARINE_SMOKER);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Unordinary_BasicsRecipeSerializers.PRISMARINE_SMOKING_RECIPE;
    }
}
