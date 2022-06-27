package com.github.manasmods.unordinary_basics.integration;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.github.manasmods.unordinary_basics.menu.container.FletchingContainer;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRecipeTypeRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;

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

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(FletchingTableMenu.class, FletchingTableRecipeCategory.TYPE, 0, 6, 7, 9 * 4);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Blocks.FLETCHING_TABLE), FletchingTableRecipeCategory.TYPE);
    }
}