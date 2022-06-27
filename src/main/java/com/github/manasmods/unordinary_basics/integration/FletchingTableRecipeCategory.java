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
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

public class FletchingTableRecipeCategory implements IRecipeCategory<FletchingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Unordinary_Basics.MOD_ID, "fletching");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/fletching_table.png");

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
                if (fitsSlot(ingredient.getItems()[0].getItem(), j)) {
                    builder.addSlot(RecipeIngredientRole.INPUT, 24 + (j % 2) * 21, 16 + Math.floorDiv(j, 2) * 21).addIngredients(ingredient);
                    slotsFilled[i] = true;
                    continue ingredientsLoop;
                }
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 36).addItemStack(recipe.getResultItem());
    }

    // Slots numbered top to bottom, left to right, 0 to 5; conditions will need to be updated if more fletching recipes are added
    private static boolean fitsSlot(Item item, int slot) {
        switch (slot) {
            case 0 -> {return item == Items.FLINT;}
            case 1 -> {return item == Items.LINGERING_POTION;}
            case 2 -> {return hasForgeTag(item, "rods/wooden");}
            case 3 -> {return item == Items.ARROW;} // Arrow
            case 4 -> {return hasForgeTag(item, "feathers");}
            case 5 -> {return item == Items.GLOWSTONE_DUST;}
        }
        return false;
    }

    private static boolean hasForgeTag(Item item, String tag) {
        return item.builtInRegistryHolder().is(ItemTags.create(new ResourceLocation("forge", tag)));
    }
}