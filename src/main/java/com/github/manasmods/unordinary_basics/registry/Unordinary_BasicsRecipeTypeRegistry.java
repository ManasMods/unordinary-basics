package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Unordinary_BasicsRecipeTypeRegistry {
    public static RegistryObject<RecipeType<FletchingRecipe>> FLETCHING_RECIPE;

    public static void register(DeferredRegister<RecipeType<?>> registry) {
        FLETCHING_RECIPE = registry.register("fletching_registry", () -> new RecipeType<FletchingRecipe>() {});
    }
}
