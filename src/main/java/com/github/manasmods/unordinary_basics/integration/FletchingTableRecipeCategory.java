package com.github.manasmods.unordinary_basics.integration;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;

public class FletchingTableRecipeCategory implements IRecipeCategory<FletchingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Unordinary_Basics.MOD_ID, "gem_cutting");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/fletching_table.png");

    private final IDrawable background;
    private final IDrawable icon;

    public FletchingTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 256, 256);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Blocks.FLETCHING_TABLE));
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
        return new TextComponent("Fletching Table");
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
    }
}