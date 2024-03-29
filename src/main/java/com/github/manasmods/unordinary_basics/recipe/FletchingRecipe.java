package com.github.manasmods.unordinary_basics.recipe;

import com.github.manasmods.unordinary_basics.core.NBTIngredientAccessor;
import com.github.manasmods.unordinary_basics.menu.container.FletchingContainer;
import com.github.manasmods.unordinary_basics.registry.UBRecipeTypeRegistry;
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
import net.minecraftforge.common.crafting.StrictNBTIngredient;

import java.util.List;

@RequiredArgsConstructor
public class FletchingRecipe implements Recipe<FletchingContainer> {
    @Getter
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
                    if (ingredient instanceof StrictNBTIngredient nbtIngredient) {
                        NBTIngredientAccessor accessor = (NBTIngredientAccessor) nbtIngredient;
                        int requiredCount = accessor.getStack().getCount();
                        return pCraftingInventory.getItem(i).getCount() >= requiredCount;
                    } else {
                        return true;
                    }
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
        return pWidth <= 2 && pHeight <= 3;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Unordinary_BasicsRecipeSerializers.FLETCHING_RECIPE;
    }

    @Override
    public RecipeType<?> getType() {
        return UBRecipeTypeRegistry.FLETCHING_RECIPE.get();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(FletchingContainer pContainer) {
        NonNullList<ItemStack> containerItems = NonNullList.withSize(pContainer.getContainerSize(), ItemStack.EMPTY);
        //set initial values
        for (int i = 0; i < containerItems.size(); ++i) {
            ItemStack item = pContainer.getItem(i);
            if (item.hasCraftingRemainingItem()) {
                containerItems.set(i, item.getCraftingRemainingItem());
            }
        }

        //update amounts
        for (Ingredient ingredient : this.recipeItems) {
            if (ingredient instanceof StrictNBTIngredient nbtIngredient) {
                //Handle NBTIngredient
                NBTIngredientAccessor accessor = (NBTIngredientAccessor) nbtIngredient;
                ItemStack recipeStack = accessor.getStack();

                for (int i = 0; i < containerItems.size(); ++i) {
                    ItemStack item = pContainer.getItem(i);
                    if (recipeStack.getItem().equals(item.getItem())) {
                        item.shrink(recipeStack.getCount());
                    }
                    containerItems.set(i, item);
                }
            } else {
                //Handle other Ingredients
                for (int i = 0; i < containerItems.size(); ++i) {
                    ItemStack item = pContainer.getItem(i);
                    if (ingredient.test(item)) {
                        item.shrink(1);
                    }
                    containerItems.set(i, item);
                }
            }
        }

        return containerItems;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

}
