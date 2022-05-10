package com.github.manasmods.vanilla_plus.recipe;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Vanilla_PlusRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vanilla_Plus.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FletchingTableRecipe>> FLETCHING_TABLE_SERIALIZER =
            SERIALIZERS.register("fletching", () -> FletchingTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, FletchingTableRecipe.Type.ID, FletchingTableRecipe.Type.INSTANCE);
    }
}