package com.github.manasmods.unordinary_basics.integration;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.container.FletchingContainer;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FletchingTableRecipeCategory implements IRecipeCategory<FletchingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Unordinary_Basics.MOD_ID, "fletching");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/fletching_table.png");
    public static final RecipeType<FletchingRecipe> TYPE = RecipeType.create(Unordinary_Basics.MOD_ID, "fletching", FletchingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FletchingTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 86);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.FLETCHING_TABLE));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends FletchingRecipe> getRecipeClass() {
        return FletchingRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Fletching");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull FletchingRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        List<String> ingredients = recipe.getIngredients().stream().map(i -> Arrays.toString(i.getItems())).collect(Collectors.toList());
        Unordinary_Basics.getLogger().info(ingredients);

        boolean[] slotsFilled = new boolean[6];
        ingredientsLoop:
        for (int i = 0, size = recipeIngredients.size(); i < size; i++) {
            Ingredient ingredient = recipeIngredients.get(i);
            if (ingredient.getItems().length == 0) {
                for (int j = 0; j < 6; j++) {
                    if (!slotsFilled[j]) {
                        builder.addSlot(RecipeIngredientRole.INPUT, 24 + (j % 2) * 21, 16 + Math.floorDiv(j, 2) * 21).addIngredients(ingredient);
                        slotsFilled[j] = true;
                        continue ingredientsLoop;
                    }
                }
            }
            for (int j = 0; j < 6; j++) {
                if (FletchingContainer.isSlotValid(j, ingredient.getItems()[0])) {
                    builder.addSlot(RecipeIngredientRole.INPUT, 24 + (j % 2) * 21, 16 + Math.floorDiv(j, 2) * 21).addIngredients(ingredient);
                    slotsFilled[i] = true;
                    continue ingredientsLoop;
                }
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 36).addItemStack(recipe.getResultItem());
    }
}