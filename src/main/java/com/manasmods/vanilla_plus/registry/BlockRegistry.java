package com.manasmods.vanilla_plus.registry;

import com.manasmods.vanilla_plus.item.templates.SimpleBlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class BlockRegistry {
    /**
     * This Method will register all Blocks with their BlockItems to Forge.
     * It is called though the {@link Vanilla_PlusRegistry#register(IEventBus)} Method.
     */
    static void register(DeferredRegister<Item> itemRegistry, DeferredRegister<Block> blockRegistry) {
        registerBlocks(blockRegistry); // Registers all Blocks
        registerItems(itemRegistry); // Registers our custom BlockItems

        //Loads a list of all BlockItems which already exist
        Collection<ResourceLocation> registeredItems = itemRegistry.getEntries()
                .stream()
                .map(RegistryObject::getId)
                .toList();
        //Creates a SimpleBlockItems for all Block which didn't get a BlockItem yet
        blockRegistry.getEntries().forEach(registryObject -> {
            if (!registeredItems.contains(registryObject.getId())) {
                itemRegistry.register(registryObject.getId().getPath(), () -> new SimpleBlockItem(registryObject.get()));
            }
        });
    }

    /**
     * This Method will register all custom {@link Block} object to Forge.
     */
    private static void registerBlocks(DeferredRegister<Block> registry) {

        registry.register("dirt_stairs", () -> new StairBlock(() -> Blocks.DIRT.defaultBlockState(),
            BlockBehaviour.Properties.of(Material.DIRT)
            .strength(2F)
                .sound(SoundType.ROOTED_DIRT)));

        registry.register("dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT)
                .strength(2F)
                .sound(SoundType.ROOTED_DIRT)));
    }
    /**
     * This Method allows to create {@link BlockItem} objects with non-default settings.
     * They will be loaded before the defaulted {@link BlockItem} object will be initialized.
     */
    static void registerItems(DeferredRegister<Item> registry) {
        //Custom BlockItems here
    }

}