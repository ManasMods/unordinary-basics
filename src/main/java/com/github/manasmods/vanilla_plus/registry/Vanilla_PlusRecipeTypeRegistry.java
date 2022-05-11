package com.github.manasmods.vanilla_plus.registry;

import com.github.manasmods.vanilla_plus.recipe.FletchingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Vanilla_PlusRecipeTypeRegistry {
    public static RegistryObject<RecipeType<FletchingRecipe>> FLETCHING_RECIPE;

    public static void register(DeferredRegister<RecipeType<?>> registry) {
        FLETCHING_RECIPE = registry.register("fletching_registry", () -> new RecipeType<>() {});
    }
}
