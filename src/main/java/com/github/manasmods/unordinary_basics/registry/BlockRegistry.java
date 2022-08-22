package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.block.EnchantmentLibraryBlock;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.block.SimpleBlock;
import com.github.manasmods.unordinary_basics.item.templates.SimpleBlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
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
     * It is called though the {@link Unordinary_BasicsRegistry#register(IEventBus)} Method.
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

        //BLOCKS FOR STAIRS + SLABS

        RegistryObject<Block> oak_log = registry.register("oak_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> birch_log = registry.register("birch_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> spruce_log = registry.register("spruce_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> acacia_log = registry.register("acacia_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> dark_oak_log = registry.register("dark_oak_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> jungle_log = registry.register("jungle_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> warped_stem = registry.register("warped_stem", () -> new SimpleBlock(Material.NETHER_WOOD, properties -> properties.strength(2.0F).sound(SoundType.STEM).requiresCorrectToolForDrops()));
        RegistryObject<Block> crimson_stem = registry.register("crimson_stem", () -> new SimpleBlock(Material.NETHER_WOOD, properties -> properties.strength(2.0F).sound(SoundType.STEM).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_oak_log = registry.register("stripped_oak_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_birch_log = registry.register("stripped_birch_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_spruce_log = registry.register("stripped_spruce_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_acacia_log = registry.register("stripped_acacia_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_dark_oak_log = registry.register("stripped_dark_oak_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_jungle_log = registry.register("stripped_jungle_log", () -> new SimpleBlock(Material.WOOD, properties -> properties.strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_warped_stem = registry.register("stripped_warped_stem", () -> new SimpleBlock(Material.NETHER_WOOD, properties -> properties.strength(2.0F).sound(SoundType.STEM).requiresCorrectToolForDrops()));
        RegistryObject<Block> stripped_crimson_stem = registry.register("stripped_crimson_stem", () -> new SimpleBlock(Material.NETHER_WOOD, properties -> properties.strength(2.0F).sound(SoundType.STEM).requiresCorrectToolForDrops()));
        RegistryObject<Block> purpur_pillar = registry.register("purpur_pillar", () -> new SimpleBlock(Material.STONE, properties -> properties.strength(1.5F, 6.0F).requiresCorrectToolForDrops()));
        RegistryObject<Block> quartz_pillar = registry.register("quartz_pillar", () -> new SimpleBlock(Material.STONE, properties -> properties.strength(0.8F).requiresCorrectToolForDrops()));
        RegistryObject<Block> hay_block = registry.register("hay_block", () -> new SimpleBlock(Material.GRASS, properties -> properties.strength(0.5F).sound(SoundType.GRASS).requiresCorrectToolForDrops()));

        registry.register("enchantment_library", () -> new EnchantmentLibraryBlock(BlockBehaviour.Properties.of(Material.WOOD)
                .strength(1F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .requiresCorrectToolForDrops()));


        //STAIRS

        registry.register("calcite_stairs", () -> new StairBlock(Blocks.CALCITE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CALCITE).requiresCorrectToolForDrops())));
        registry.register("tuff_stairs", () -> new StairBlock(Blocks.TUFF::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.TUFF).requiresCorrectToolForDrops())));
        registry.register("dripstone_stairs", () -> new StairBlock(Blocks.DRIPSTONE_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK).requiresCorrectToolForDrops())));
        registry.register("grass_block_stairs", () -> new StairBlock(Blocks.GRASS_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).requiresCorrectToolForDrops())));
        registry.register("dirt_stairs", () -> new StairBlock(Blocks.DIRT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops())));
        registry.register("coarse_dirt_stairs", () -> new StairBlock(Blocks.COARSE_DIRT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).requiresCorrectToolForDrops())));
        registry.register("podzol_stairs", () -> new StairBlock(Blocks.PODZOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PODZOL).requiresCorrectToolForDrops())));
        registry.register("rooted_dirt_stairs", () -> new StairBlock(Blocks.ROOTED_DIRT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT).requiresCorrectToolForDrops())));
        registry.register("crimson_nylium_stairs", () -> new StairBlock(Blocks.CRIMSON_NYLIUM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRIMSON_NYLIUM).requiresCorrectToolForDrops())));
        registry.register("warped_nylium_stairs", () -> new StairBlock(Blocks.WARPED_NYLIUM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM).requiresCorrectToolForDrops())));
        registry.register("sand_stairs", () -> new StairBlock(Blocks.SAND::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SAND).requiresCorrectToolForDrops())));
        registry.register("red_sand_stairs", () -> new StairBlock(Blocks.RED_SAND::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_SAND).requiresCorrectToolForDrops())));
        registry.register("gravel_stairs", () -> new StairBlock(Blocks.GRAVEL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAVEL).requiresCorrectToolForDrops())));
        registry.register("block_of_raw_iron_stairs", () -> new StairBlock(Blocks.RAW_IRON_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops())));
        registry.register("block_of_raw_copper_stairs", () -> new StairBlock(Blocks.RAW_COPPER_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).requiresCorrectToolForDrops())));
        registry.register("block_of_raw_gold_stairs", () -> new StairBlock(Blocks.RAW_GOLD_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).requiresCorrectToolForDrops())));
        registry.register("block_of_amethyst_stairs", () -> new StairBlock(Blocks.AMETHYST_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops())));
        registry.register("oak_log_stairs", () -> new StairBlock(oak_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(oak_log.get()).requiresCorrectToolForDrops())));
        registry.register("spruce_log_stairs", () -> new StairBlock(spruce_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(spruce_log.get()).requiresCorrectToolForDrops())));
        registry.register("birch_log_stairs", () -> new StairBlock(birch_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(birch_log.get()).requiresCorrectToolForDrops())));
        registry.register("jungle_log_stairs", () -> new StairBlock(jungle_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(jungle_log.get()).requiresCorrectToolForDrops())));
        registry.register("acacia_log_stairs", () -> new StairBlock(acacia_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(acacia_log.get()).requiresCorrectToolForDrops())));
        registry.register("dark_oak_log_stairs", () -> new StairBlock(dark_oak_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(dark_oak_log.get()).requiresCorrectToolForDrops())));
        registry.register("crimson_stem_stairs", () -> new StairBlock(crimson_stem.get()::defaultBlockState, (BlockBehaviour.Properties.copy(crimson_stem.get()).requiresCorrectToolForDrops())));
        registry.register("warped_stem_stairs", () -> new StairBlock(warped_stem.get()::defaultBlockState, (BlockBehaviour.Properties.copy(warped_stem.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_oak_log_stairs", () -> new StairBlock(stripped_oak_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_oak_log.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_spruce_log_stairs", () -> new StairBlock(stripped_spruce_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_spruce_log.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_birch_log_stairs", () -> new StairBlock(stripped_birch_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_birch_log.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_jungle_log_stairs", () -> new StairBlock(stripped_jungle_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_jungle_log.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_acacia_log_stairs", () -> new StairBlock(stripped_acacia_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_acacia_log.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_dark_oak_log_stairs", () -> new StairBlock(stripped_dark_oak_log.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_dark_oak_log.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_crimson_stem_stairs", () -> new StairBlock(stripped_crimson_stem.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_crimson_stem.get()).requiresCorrectToolForDrops())));
        registry.register("stripped_warped_stem_stairs", () -> new StairBlock(stripped_warped_stem.get()::defaultBlockState, (BlockBehaviour.Properties.copy(stripped_warped_stem.get()).requiresCorrectToolForDrops())));
        registry.register("glass_stairs", () -> new StairBlock(Blocks.GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops())));
        registry.register("tinted_glass_stairs", () -> new StairBlock(Blocks.TINTED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.TINTED_GLASS).requiresCorrectToolForDrops())));
        registry.register("cut_sandstone_stairs", () -> new StairBlock(Blocks.CUT_SANDSTONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE).requiresCorrectToolForDrops())));
        registry.register("obsidian_stairs", () -> new StairBlock(Blocks.OBSIDIAN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops())));
        registry.register("purpur_pillar_stairs", () -> new StairBlock(purpur_pillar.get()::defaultBlockState, (BlockBehaviour.Properties.copy(purpur_pillar.get()).requiresCorrectToolForDrops())));
        registry.register("ice_stairs", () -> new StairBlock(Blocks.ICE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ICE).requiresCorrectToolForDrops())));
        registry.register("snow_stairs", () -> new StairBlock(Blocks.SNOW_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).requiresCorrectToolForDrops())));
        registry.register("clay_stairs", () -> new StairBlock(Blocks.CLAY::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CLAY).requiresCorrectToolForDrops())));
        registry.register("pumpkin_stairs", () -> new StairBlock(Blocks.PUMPKIN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PUMPKIN).requiresCorrectToolForDrops())));
        registry.register("netherrack_stairs", () -> new StairBlock(Blocks.NETHERRACK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.NETHERRACK).requiresCorrectToolForDrops())));
        registry.register("soul_sand_stairs", () -> new StairBlock(Blocks.SOUL_SAND::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SOUL_SAND).requiresCorrectToolForDrops())));
        registry.register("soul_soil_stairs", () -> new StairBlock(Blocks.SOUL_SOIL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL).requiresCorrectToolForDrops())));
        registry.register("basalt_stairs", () -> new StairBlock(Blocks.BASALT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BASALT).requiresCorrectToolForDrops())));
        registry.register("polished_basalt_stairs", () -> new StairBlock(Blocks.POLISHED_BASALT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT).requiresCorrectToolForDrops())));
        registry.register("glowstone_stairs", () -> new StairBlock(Blocks.GLOWSTONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).requiresCorrectToolForDrops())));
        registry.register("cracked_stone_brick_stairs", () -> new StairBlock(Blocks.CRACKED_STONE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS).requiresCorrectToolForDrops())));
        registry.register("chiseled_stone_brick_stairs", () -> new StairBlock(Blocks.CHISELED_STONE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS).requiresCorrectToolForDrops())));
        registry.register("cracked_deepslate_brick_stairs", () -> new StairBlock(Blocks.CRACKED_DEEPSLATE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS).requiresCorrectToolForDrops())));
        registry.register("cracked_deepslate_tile_stairs", () -> new StairBlock(Blocks.CRACKED_DEEPSLATE_TILES::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES).requiresCorrectToolForDrops())));
        registry.register("chiseled_deepslate_stairs", () -> new StairBlock(Blocks.CHISELED_DEEPSLATE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_DEEPSLATE).requiresCorrectToolForDrops())));
        registry.register("melon_stairs", () -> new StairBlock(Blocks.MELON::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MELON).requiresCorrectToolForDrops())));
        registry.register("mycelium_stairs", () -> new StairBlock(Blocks.MYCELIUM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MYCELIUM).requiresCorrectToolForDrops())));
        registry.register("cracked_nether_brick_stairs", () -> new StairBlock(Blocks.CRACKED_NETHER_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS).requiresCorrectToolForDrops())));
        registry.register("chiseled_nether_brick_stairs", () -> new StairBlock(Blocks.CHISELED_NETHER_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_NETHER_BRICKS).requiresCorrectToolForDrops())));
        registry.register("end_stone_stairs", () -> new StairBlock(Blocks.END_STONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.END_STONE).requiresCorrectToolForDrops())));
        registry.register("chiseled_quartz_block_stairs", () -> new StairBlock(Blocks.CHISELED_QUARTZ_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_QUARTZ_BLOCK).requiresCorrectToolForDrops())));
        registry.register("quartz_brick_stairs", () -> new StairBlock(Blocks.QUARTZ_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS).requiresCorrectToolForDrops())));
        registry.register("quartz_pillar_stairs", () -> new StairBlock(quartz_pillar.get()::defaultBlockState, (BlockBehaviour.Properties.copy(quartz_pillar.get()).requiresCorrectToolForDrops())));
        registry.register("hay_bale_stairs", () -> new StairBlock(hay_block.get()::defaultBlockState, (BlockBehaviour.Properties.copy(hay_block.get()).requiresCorrectToolForDrops())));
        registry.register("terracotta_stairs", () -> new StairBlock(Blocks.TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("packed_ice_stairs", () -> new StairBlock(Blocks.PACKED_ICE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).requiresCorrectToolForDrops())));
        registry.register("sea_lantern_stairs", () -> new StairBlock(Blocks.SEA_LANTERN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN).requiresCorrectToolForDrops())));
        registry.register("cut_red_sandstone_stairs", () -> new StairBlock(Blocks.CUT_RED_SANDSTONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CUT_RED_SANDSTONE).requiresCorrectToolForDrops())));
        registry.register("magma_block_stairs", () -> new StairBlock(Blocks.MAGMA_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK).requiresCorrectToolForDrops())));
        registry.register("nether_wart_block_stairs", () -> new StairBlock(Blocks.NETHER_WART_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK).requiresCorrectToolForDrops())));
        registry.register("warped_wart_block_stairs", () -> new StairBlock(Blocks.WARPED_WART_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK).requiresCorrectToolForDrops())));
        registry.register("bone_block_stairs", () -> new StairBlock(Blocks.BONE_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).requiresCorrectToolForDrops())));
        registry.register("blue_ice_stairs", () -> new StairBlock(Blocks.BLUE_ICE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_ICE).requiresCorrectToolForDrops())));
        registry.register("dried_kelp_stairs", () -> new StairBlock(Blocks.DRIED_KELP_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DRIED_KELP_BLOCK).requiresCorrectToolForDrops())));
        registry.register("crying_obsidian_stairs", () -> new StairBlock(Blocks.CRYING_OBSIDIAN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN).requiresCorrectToolForDrops())));
        registry.register("cracked_polished_blackstone_brick_stairs", () -> new StairBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS).requiresCorrectToolForDrops())));
        registry.register("white_wool_stairs", () -> new StairBlock(Blocks.WHITE_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).requiresCorrectToolForDrops())));
        registry.register("orange_wool_stairs", () -> new StairBlock(Blocks.ORANGE_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ORANGE_WOOL).requiresCorrectToolForDrops())));
        registry.register("magenta_wool_stairs", () -> new StairBlock(Blocks.MAGENTA_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL).requiresCorrectToolForDrops())));
        registry.register("light_blue_wool_stairs", () -> new StairBlock(Blocks.LIGHT_BLUE_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_WOOL).requiresCorrectToolForDrops())));
        registry.register("yellow_wool_stairs", () -> new StairBlock(Blocks.YELLOW_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL).requiresCorrectToolForDrops())));
        registry.register("lime_wool_stairs", () -> new StairBlock(Blocks.LIME_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIME_WOOL).requiresCorrectToolForDrops())));
        registry.register("pink_wool_stairs", () -> new StairBlock(Blocks.PINK_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PINK_WOOL).requiresCorrectToolForDrops())));
        registry.register("gray_wool_stairs", () -> new StairBlock(Blocks.GRAY_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAY_WOOL).requiresCorrectToolForDrops())));
        registry.register("light_gray_wool_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_WOOL).requiresCorrectToolForDrops())));
        registry.register("cyan_wool_stairs", () -> new StairBlock(Blocks.CYAN_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CYAN_WOOL).requiresCorrectToolForDrops())));
        registry.register("purple_wool_stairs", () -> new StairBlock(Blocks.PURPLE_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PURPLE_WOOL).requiresCorrectToolForDrops())));
        registry.register("blue_wool_stairs", () -> new StairBlock(Blocks.BLUE_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL).requiresCorrectToolForDrops())));
        registry.register("brown_wool_stairs", () -> new StairBlock(Blocks.BROWN_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL).requiresCorrectToolForDrops())));
        registry.register("green_wool_stairs", () -> new StairBlock(Blocks.GREEN_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL).requiresCorrectToolForDrops())));
        registry.register("red_wool_stairs", () -> new StairBlock(Blocks.RED_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_WOOL).requiresCorrectToolForDrops())));
        registry.register("black_wool_stairs", () -> new StairBlock(Blocks.BLACK_WOOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).requiresCorrectToolForDrops())));
        registry.register("white_terracotta_stairs", () -> new StairBlock(Blocks.WHITE_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WHITE_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("orange_terracotta_stairs", () -> new StairBlock(Blocks.ORANGE_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ORANGE_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("magenta_terracotta_stairs", () -> new StairBlock(Blocks.MAGENTA_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGENTA_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("light_blue_terracotta_stairs", () -> new StairBlock(Blocks.LIGHT_BLUE_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("yellow_terracotta_stairs", () -> new StairBlock(Blocks.YELLOW_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.YELLOW_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("lime_terracotta_stairs", () -> new StairBlock(Blocks.LIME_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIME_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("pink_terracotta_stairs", () -> new StairBlock(Blocks.PINK_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PINK_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("gray_terracotta_stairs", () -> new StairBlock(Blocks.GRAY_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAY_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("light_gray_terracotta_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("cyan_terracotta_stairs", () -> new StairBlock(Blocks.CYAN_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CYAN_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("purple_terracotta_stairs", () -> new StairBlock(Blocks.PURPLE_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PURPLE_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("blue_terracotta_stairs", () -> new StairBlock(Blocks.BLUE_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("brown_terracotta_stairs", () -> new StairBlock(Blocks.BROWN_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BROWN_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("green_terracotta_stairs", () -> new StairBlock(Blocks.GREEN_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GREEN_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("red_terracotta_stairs", () -> new StairBlock(Blocks.RED_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("black_terracotta_stairs", () -> new StairBlock(Blocks.BLACK_TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLACK_TERRACOTTA).requiresCorrectToolForDrops())));
        registry.register("white_stained_glass_stairs", () -> new StairBlock(Blocks.WHITE_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WHITE_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("orange_stained_glass_stairs", () -> new StairBlock(Blocks.ORANGE_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ORANGE_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("magenta_stained_glass_stairs", () -> new StairBlock(Blocks.MAGENTA_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGENTA_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("light_blue_stained_glass_stairs", () -> new StairBlock(Blocks.LIGHT_BLUE_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("yellow_stained_glass_stairs", () -> new StairBlock(Blocks.YELLOW_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.YELLOW_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("lime_stained_glass_stairs", () -> new StairBlock(Blocks.LIME_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIME_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("pink_stained_glass_stairs", () -> new StairBlock(Blocks.PINK_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PINK_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("gray_stained_glass_stairs", () -> new StairBlock(Blocks.GRAY_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAY_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("light_gray_stained_glass_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("cyan_stained_glass_stairs", () -> new StairBlock(Blocks.CYAN_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CYAN_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("purple_stained_glass_stairs", () -> new StairBlock(Blocks.PURPLE_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PURPLE_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("blue_stained_glass_stairs", () -> new StairBlock(Blocks.BLUE_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("brown_stained_glass_stairs", () -> new StairBlock(Blocks.BROWN_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BROWN_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("green_stained_glass_stairs", () -> new StairBlock(Blocks.GREEN_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("red_stained_glass_stairs", () -> new StairBlock(Blocks.RED_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("black_stained_glass_stairs", () -> new StairBlock(Blocks.BLACK_STAINED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLACK_STAINED_GLASS).requiresCorrectToolForDrops())));
        registry.register("white_concrete_stairs", () -> new StairBlock(Blocks.WHITE_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("orange_concrete_stairs", () -> new StairBlock(Blocks.ORANGE_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("magenta_concrete_stairs", () -> new StairBlock(Blocks.MAGENTA_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("light_blue_concrete_stairs", () -> new StairBlock(Blocks.LIGHT_BLUE_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("yellow_concrete_stairs", () -> new StairBlock(Blocks.YELLOW_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("lime_concrete_stairs", () -> new StairBlock(Blocks.LIME_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("pink_concrete_stairs", () -> new StairBlock(Blocks.PINK_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("gray_concrete_stairs", () -> new StairBlock(Blocks.GRAY_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("light_gray_concrete_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("cyan_concrete_stairs", () -> new StairBlock(Blocks.CYAN_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("purple_concrete_stairs", () -> new StairBlock(Blocks.PURPLE_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("blue_concrete_stairs", () -> new StairBlock(Blocks.BLUE_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("brown_concrete_stairs", () -> new StairBlock(Blocks.BROWN_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("green_concrete_stairs", () -> new StairBlock(Blocks.GREEN_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("red_concrete_stairs", () -> new StairBlock(Blocks.RED_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("black_concrete_stairs", () -> new StairBlock(Blocks.BLACK_CONCRETE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE).requiresCorrectToolForDrops())));
        registry.register("white_concrete_powder_stairs", () -> new StairBlock(Blocks.WHITE_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("orange_concrete_powder_stairs", () -> new StairBlock(Blocks.ORANGE_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("magenta_concrete_powder_stairs", () -> new StairBlock(Blocks.MAGENTA_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("light_blue_concrete_powder_stairs", () -> new StairBlock(Blocks.LIGHT_BLUE_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("yellow_concrete_powder_stairs", () -> new StairBlock(Blocks.YELLOW_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("lime_concrete_powder_stairs", () -> new StairBlock(Blocks.LIME_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("pink_concrete_powder_stairs", () -> new StairBlock(Blocks.PINK_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("gray_concrete_powder_stairs", () -> new StairBlock(Blocks.GRAY_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("light_gray_concrete_powder_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("cyan_concrete_powder_stairs", () -> new StairBlock(Blocks.CYAN_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("purple_concrete_powder_stairs", () -> new StairBlock(Blocks.PURPLE_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("blue_concrete_powder_stairs", () -> new StairBlock(Blocks.BLUE_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("brown_concrete_powder_stairs", () -> new StairBlock(Blocks.BROWN_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("green_concrete_powder_stairs", () -> new StairBlock(Blocks.GREEN_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("red_concrete_powder_stairs", () -> new StairBlock(Blocks.RED_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE_POWDER).requiresCorrectToolForDrops())));
        registry.register("black_concrete_powder_stairs", () -> new StairBlock(Blocks.BLACK_CONCRETE_POWDER::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE_POWDER).requiresCorrectToolForDrops())));


        //SLABS

        registry.register("calcite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE).requiresCorrectToolForDrops()));
        registry.register("tuff_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).requiresCorrectToolForDrops()));
        registry.register("dripstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK).requiresCorrectToolForDrops()));
        registry.register("grass_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).requiresCorrectToolForDrops()));
        registry.register("dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
        registry.register("coarse_dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).requiresCorrectToolForDrops()));
        registry.register("podzol_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PODZOL).requiresCorrectToolForDrops()));
        registry.register("rooted_dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT).requiresCorrectToolForDrops()));
        registry.register("crimson_nylium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_NYLIUM).requiresCorrectToolForDrops()));
        registry.register("warped_nylium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM).requiresCorrectToolForDrops()));
        registry.register("sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SAND).requiresCorrectToolForDrops()));
        registry.register("red_sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_SAND).requiresCorrectToolForDrops()));
        registry.register("gravel_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL).requiresCorrectToolForDrops()));
        registry.register("block_of_raw_iron_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
        registry.register("block_of_raw_copper_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).requiresCorrectToolForDrops()));
        registry.register("block_of_raw_gold_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).requiresCorrectToolForDrops()));
        registry.register("block_of_amethyst_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops()));
        registry.register("oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(oak_log.get()).requiresCorrectToolForDrops()));
        registry.register("spruce_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(spruce_log.get()).requiresCorrectToolForDrops()));
        registry.register("birch_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(birch_log.get()).requiresCorrectToolForDrops()));
        registry.register("jungle_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(jungle_log.get()).requiresCorrectToolForDrops()));
        registry.register("acacia_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(acacia_log.get()).requiresCorrectToolForDrops()));
        registry.register("dark_oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(dark_oak_log.get()).requiresCorrectToolForDrops()));
        registry.register("crimson_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(crimson_stem.get()).requiresCorrectToolForDrops()));
        registry.register("warped_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(warped_stem.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_oak_log.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_spruce_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_spruce_log.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_birch_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_birch_log.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_jungle_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_jungle_log.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_acacia_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_acacia_log.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_dark_oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_dark_oak_log.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_crimson_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_crimson_stem.get()).requiresCorrectToolForDrops()));
        registry.register("stripped_warped_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(stripped_warped_stem.get()).requiresCorrectToolForDrops()));
        registry.register("glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).requiresCorrectToolForDrops()));
        registry.register("tinted_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TINTED_GLASS).requiresCorrectToolForDrops()));
        registry.register("obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));
        registry.register("purpur_pillar_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(purpur_pillar.get()).requiresCorrectToolForDrops()));
        registry.register("ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ICE).requiresCorrectToolForDrops()));
        registry.register("snow_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).requiresCorrectToolForDrops()));
        registry.register("clay_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CLAY).requiresCorrectToolForDrops()));
        registry.register("pumpkin_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN).requiresCorrectToolForDrops()));
        registry.register("netherrack_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).requiresCorrectToolForDrops()));
        registry.register("soul_sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND).requiresCorrectToolForDrops()));
        registry.register("soul_soil_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL).requiresCorrectToolForDrops()));
        registry.register("basalt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BASALT).requiresCorrectToolForDrops()));
        registry.register("polished_basalt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT).requiresCorrectToolForDrops()));
        registry.register("glowstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).requiresCorrectToolForDrops()));
        registry.register("cracked_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS).requiresCorrectToolForDrops()));
        registry.register("chiseled_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS).requiresCorrectToolForDrops()));
        registry.register("cracked_deepslate_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS).requiresCorrectToolForDrops()));
        registry.register("cracked_deepslate_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES).requiresCorrectToolForDrops()));
        registry.register("chiseled_deepslate_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_DEEPSLATE).requiresCorrectToolForDrops()));
        registry.register("melon_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MELON).requiresCorrectToolForDrops()));
        registry.register("mycelium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MYCELIUM).requiresCorrectToolForDrops()));
        registry.register("cracked_nether_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS).requiresCorrectToolForDrops()));
        registry.register("chiseled_nether_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_NETHER_BRICKS).requiresCorrectToolForDrops()));
        registry.register("end_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).requiresCorrectToolForDrops()));
        registry.register("chiseled_quartz_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_QUARTZ_BLOCK).requiresCorrectToolForDrops()));
        registry.register("quartz_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS).requiresCorrectToolForDrops()));
        registry.register("quartz_pillar_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(quartz_pillar.get()).requiresCorrectToolForDrops()));
        registry.register("hay_bale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(hay_block.get()).requiresCorrectToolForDrops()));
        registry.register("terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("packed_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).requiresCorrectToolForDrops()));
        registry.register("sea_lantern_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN).requiresCorrectToolForDrops()));
        registry.register("magma_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK).requiresCorrectToolForDrops()));
        registry.register("nether_wart_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK).requiresCorrectToolForDrops()));
        registry.register("warped_wart_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK).requiresCorrectToolForDrops()));
        registry.register("bone_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).requiresCorrectToolForDrops()));
        registry.register("blue_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE).requiresCorrectToolForDrops()));
        registry.register("dried_kelp_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DRIED_KELP_BLOCK).requiresCorrectToolForDrops()));
        registry.register("crying_obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN).requiresCorrectToolForDrops()));
        registry.register("cracked_polished_blackstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS).requiresCorrectToolForDrops()));
        registry.register("white_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).requiresCorrectToolForDrops()));
        registry.register("orange_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_WOOL).requiresCorrectToolForDrops()));
        registry.register("magenta_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL).requiresCorrectToolForDrops()));
        registry.register("light_blue_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_WOOL).requiresCorrectToolForDrops()));
        registry.register("yellow_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL).requiresCorrectToolForDrops()));
        registry.register("lime_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_WOOL).requiresCorrectToolForDrops()));
        registry.register("pink_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_WOOL).requiresCorrectToolForDrops()));
        registry.register("gray_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_WOOL).requiresCorrectToolForDrops()));
        registry.register("light_gray_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_WOOL).requiresCorrectToolForDrops()));
        registry.register("cyan_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_WOOL).requiresCorrectToolForDrops()));
        registry.register("purple_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_WOOL).requiresCorrectToolForDrops()));
        registry.register("blue_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL).requiresCorrectToolForDrops()));
        registry.register("brown_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL).requiresCorrectToolForDrops()));
        registry.register("green_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL).requiresCorrectToolForDrops()));
        registry.register("red_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL).requiresCorrectToolForDrops()));
        registry.register("black_wool_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).requiresCorrectToolForDrops()));
        registry.register("white_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("orange_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("magenta_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("light_blue_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("yellow_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("lime_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("pink_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("gray_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("light_gray_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("cyan_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("purple_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("blue_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("brown_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("green_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("red_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("black_terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_TERRACOTTA).requiresCorrectToolForDrops()));
        registry.register("white_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("orange_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("magenta_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("light_blue_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("yellow_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("lime_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("pink_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("gray_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("light_gray_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("cyan_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("purple_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("blue_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("brown_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("green_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("red_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("black_stained_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_STAINED_GLASS).requiresCorrectToolForDrops()));
        registry.register("white_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("orange_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("magenta_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("light_blue_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("yellow_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("lime_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("pink_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("gray_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("light_gray_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("cyan_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("purple_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("blue_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("brown_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("green_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("red_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("black_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE).requiresCorrectToolForDrops()));
        registry.register("white_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("orange_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("magenta_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("light_blue_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("yellow_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("lime_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("pink_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("gray_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("light_gray_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("cyan_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("purple_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("blue_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("brown_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("green_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("red_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE_POWDER).requiresCorrectToolForDrops()));
        registry.register("black_concrete_powder_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE_POWDER).requiresCorrectToolForDrops()));
    }
    /**
     * This Method allows to create {@link BlockItem} objects with non-default settings.
     * They will be loaded before the defaulted {@link BlockItem} object will be initialized.
     */
    @SuppressWarnings("ConstantConditions")
    static void registerItems(DeferredRegister<Item> registry) {
        //Custom BlockItems here
        registry.register("oak_log", () -> new BlockItem(Unordinary_BasicsBlocks.OAK_LOG, new Item.Properties()));
        registry.register("birch_log", () -> new BlockItem(Unordinary_BasicsBlocks.BIRCH_LOG, new Item.Properties()));
        registry.register("spruce_log", () -> new BlockItem(Unordinary_BasicsBlocks.SPRUCE_LOG, new Item.Properties()));
        registry.register("acacia_log", () -> new BlockItem(Unordinary_BasicsBlocks.ACACIA_LOG, new Item.Properties()));
        registry.register("dark_oak_log", () -> new BlockItem(Unordinary_BasicsBlocks.DARK_OAK_LOG, new Item.Properties()));
        registry.register("jungle_log", () -> new BlockItem(Unordinary_BasicsBlocks.JUNGLE_LOG, new Item.Properties()));
        registry.register("warped_stem", () -> new BlockItem(Unordinary_BasicsBlocks.WARPED_STEM, new Item.Properties()));
        registry.register("crimson_stem", () -> new BlockItem(Unordinary_BasicsBlocks.CRIMSON_STEM, new Item.Properties()));
        registry.register("stripped_oak_log", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG, new Item.Properties()));
        registry.register("stripped_birch_log", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG, new Item.Properties()));
        registry.register("stripped_spruce_log", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG, new Item.Properties()));
        registry.register("stripped_acacia_log", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG, new Item.Properties()));
        registry.register("stripped_dark_oak_log", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG, new Item.Properties()));
        registry.register("stripped_jungle_log", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG, new Item.Properties()));
        registry.register("stripped_warped_stem", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM, new Item.Properties()));
        registry.register("stripped_crimson_stem", () -> new BlockItem(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM, new Item.Properties()));
        registry.register("purpur_pillar", () -> new BlockItem(Unordinary_BasicsBlocks.PURPUR_PILLAR, new Item.Properties()));
        registry.register("quartz_pillar", () -> new BlockItem(Unordinary_BasicsBlocks.QUARTZ_PILLAR, new Item.Properties()));
        registry.register("hay_block", () -> new BlockItem(Unordinary_BasicsBlocks.HAY_BLOCK, new Item.Properties()));

        registry.register("enchantment_library", () -> new SimpleBlockItem(Unordinary_BasicsBlocks.ENCHANTMENT_LIBRARY));
    }

}