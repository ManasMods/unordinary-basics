package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Unordinary_BasicsRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Unordinary_Basics.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Unordinary_Basics.MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Unordinary_Basics.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Unordinary_Basics.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Unordinary_Basics.MOD_ID);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, Unordinary_Basics.MOD_ID);

    public static Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    /**
     * Registers all registries.
     * This needs to be called in the Mod Constructor.
     *
     * @param modEventBus needs to be the modEventbus!
     */
    public static void register(IEventBus modEventBus) {
        BlockRegistry.register(ITEMS, BLOCKS); //Register Blocks with their BlockItems
        ItemRegistry.register(ITEMS); //Register Items to our Registry
        MenuRegistry.register(MENUS);
        BlockEntityRegistry.register(BLOCK_ENTITIES);
        RecipeSerializerRegistry.register(RECIPE_SERIALIZERS);
        Unordinary_BasicsRecipeTypeRegistry.register(RECIPE_TYPES);

        // Add our Registries to Forge
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        MENUS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
    }
}
