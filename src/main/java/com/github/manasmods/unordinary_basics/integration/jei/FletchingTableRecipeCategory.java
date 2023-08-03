package com.github.manasmods.unordinary_basics.integration.jei;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.container.FletchingContainer;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import com.github.manasmods.unordinary_basics.utils.Translation;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
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
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;

@Log4j2
public class FletchingTableRecipeCategory implements IRecipeCategory<FletchingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Unordinary_Basics.MOD_ID, "fletching");
    public final static ResourceLocation TEXTURE =
        new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/fletching_table.png");
    public static final RecipeType<FletchingRecipe> TYPE = RecipeType.create(Unordinary_Basics.MOD_ID, "fletching", FletchingRecipe.class);

    @Getter
    private final IDrawable background;
    @Getter
    private final IDrawable icon;
    @Getter
    private final MutableComponent title = Component.translatable("fletching");

    public FletchingTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 86);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.FLETCHING_TABLE));
    }

    @Override
    public RecipeType<FletchingRecipe> getRecipeType() {
        return RecipeType.create(Unordinary_Basics.MOD_ID,"fletching",FletchingRecipe.class);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull FletchingRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();

        for (Ingredient ingredient : recipeIngredients) {
            for (int i = 0; i < 6; i++) {
                if (ingredient.getItems().length > 0) {
                    //Add Ingredient to the correct slot
                    if (FletchingContainer.isSlotValid(i, ingredient.getItems()[0])) {
                        builder.addSlot(RecipeIngredientRole.INPUT, getXForIndex(i), getYForIndex(i)).addIngredients(ingredient);
                        break;
                    }
                } else {
                    //Add empty Ingredient as placeholder
                    builder.addSlot(RecipeIngredientRole.INPUT, getXForIndex(i), getYForIndex(i)).addIngredients(Ingredient.EMPTY);
                    break;
                }
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 36).addItemStack(recipe.getResultItem());
    }

    private int getXForIndex(int index) {
        return index >= 3 ? 45 : 24;
    }

    private int getYForIndex(int index) {
        if (index >= 3) index -= 3;
        return 16 + index * 21;
    }
}