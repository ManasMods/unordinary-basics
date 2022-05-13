package com.github.manasmods.vanilla_plus.recipe;

import com.github.manasmods.vanilla_plus.menu.container.FletchingContainer;
import com.github.manasmods.vanilla_plus.registry.Vanilla_PlusRecipeTypeRegistry;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

@RequiredArgsConstructor
public class FletchingRecipe implements Recipe<FletchingContainer> {
    final NonNullList<Ingredient> recipeItems;
    final ItemStack result;
    @Getter
    private final ResourceLocation id;

    @Override
    public boolean matches(FletchingContainer pCraftingInventory, Level pLevel) {
        List<Ingredient> remainingIngredients = Lists.newArrayList(recipeItems);
        remainingIngredients.removeIf(ingredient -> {
            for (int i = 0; i < 6; i++) {
                if (ingredient.test(pCraftingInventory.getItem(i))) {
                    return true;
                }
            }
            return false;
        });

        return remainingIngredients.isEmpty();
    }

    @Override
    public ItemStack assemble(FletchingContainer pContainer) {
        return this.getResultItem().copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= 2 && pHeight >= 3;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return Vanilla_PlusRecipeSerializers.FLETCHING_RECIPE;
    }

    @Override
    public RecipeType<?> getType() {
        return Vanilla_PlusRecipeTypeRegistry.FLETCHING_RECIPE.get();
    }
}
