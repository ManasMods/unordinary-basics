package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.recipe.serializer.FletchingRecipeSerializer;
import com.github.manasmods.unordinary_basics.recipe.serializer.PrismarineSmokingRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;

class RecipeSerializerRegistry {
    public static void register(DeferredRegister<RecipeSerializer<?>> registry) {
        registry.register("fletching_recipe", FletchingRecipeSerializer::new);
        registry.register("prismarine_smoking_recipe", PrismarineSmokingRecipeSerializer::new);
    }
}
