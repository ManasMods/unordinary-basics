package com.github.manasmods.vanilla_plus.recipe;

import com.github.manasmods.vanilla_plus.menu.container.FletchingContainer;
import com.github.manasmods.vanilla_plus.registry.Vanilla_PlusRecipeTypeRegistry;
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

@RequiredArgsConstructor
public class FletchingRecipe implements Recipe<FletchingContainer> {
    final NonNullList<Ingredient> recipeItems;
    final ItemStack result;
    @Getter
    private final ResourceLocation id;

    @Override
    public boolean matches(FletchingContainer pCraftingInventory, Level pLevel) {
        for (int i = 0; i < pCraftingInventory.getWidth(); ++i) {
            for (int j = 0; j < pCraftingInventory.getHeight(); ++j) {
                int k = i - 2;
                int l = j - 3;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < 2 && l < 3) {
                    ingredient = this.recipeItems.get(k + l * 2);
                }

                if (!ingredient.test(pCraftingInventory.getItem(i + j * pCraftingInventory.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
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
