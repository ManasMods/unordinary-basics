package com.github.manasmods.unordinary_basics.integration;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRecipeTypeRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIUnordinaryBasicsPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Unordinary_Basics.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FletchingTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<FletchingRecipe> recipes = rm.getAllRecipesFor(Unordinary_BasicsRecipeTypeRegistry.FLETCHING_RECIPE.get());
        registration.addRecipes(new RecipeType<>(FletchingTableRecipeCategory.UID, FletchingRecipe.class), recipes);
    }
}