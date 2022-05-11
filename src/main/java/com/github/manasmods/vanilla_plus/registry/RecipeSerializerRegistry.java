package com.github.manasmods.vanilla_plus.registry;

import com.github.manasmods.vanilla_plus.recipe.serializer.FletchingRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;

class RecipeSerializerRegistry {
    public static void register(DeferredRegister<RecipeSerializer<?>> registry) {
        registry.register("fletching_recipe", FletchingRecipeSerializer::new);
    }
}
