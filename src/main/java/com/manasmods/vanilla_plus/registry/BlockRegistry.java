package com.manasmods.vanilla_plus.registry;

import com.manasmods.vanilla_plus.item.templates.SimpleBlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

        //STAIRS

        registry.register("calcite_stairs", () -> new StairBlock(Blocks.CALCITE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CALCITE))));
        registry.register("tuff_stairs", () -> new StairBlock(Blocks.TUFF::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.TUFF))));
        registry.register("dripstone_stairs", () -> new StairBlock(Blocks.DRIPSTONE_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK))));
        registry.register("grass_block_stairs", () -> new StairBlock(Blocks.GRASS_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK))));
        registry.register("dirt_stairs", () -> new StairBlock(Blocks.DIRT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DIRT))));
        registry.register("coarse_dirt_stairs", () -> new StairBlock(Blocks.COARSE_DIRT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT))));
        registry.register("podzol_stairs", () -> new StairBlock(Blocks.PODZOL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PODZOL))));
        registry.register("rooted_dirt_stairs", () -> new StairBlock(Blocks.ROOTED_DIRT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT))));
        registry.register("crimson_nylium_stairs", () -> new StairBlock(Blocks.CRIMSON_NYLIUM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRIMSON_NYLIUM))));
        registry.register("warped_nylium_stairs", () -> new StairBlock(Blocks.WARPED_NYLIUM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM))));
        registry.register("sand_stairs", () -> new StairBlock(Blocks.SAND::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SAND))));
        registry.register("red_sand_stairs", () -> new StairBlock(Blocks.RED_SAND::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RED_SAND))));
        registry.register("gravel_stairs", () -> new StairBlock(Blocks.GRAVEL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GRAVEL))));
        registry.register("block_of_raw_iron_stairs", () -> new StairBlock(Blocks.RAW_IRON_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK))));
        registry.register("block_of_raw_copper_stairs", () -> new StairBlock(Blocks.RAW_COPPER_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK))));
        registry.register("block_of_raw_gold_stairs", () -> new StairBlock(Blocks.RAW_GOLD_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK))));
        registry.register("block_of_amethyst_stairs", () -> new StairBlock(Blocks.AMETHYST_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK))));
        registry.register("oak_log_stairs", () -> new StairBlock(Blocks.OAK_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.OAK_LOG))));
        registry.register("spruce_log_stairs", () -> new StairBlock(Blocks.SPRUCE_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG))));
        registry.register("birch_log_stairs", () -> new StairBlock(Blocks.BIRCH_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BIRCH_LOG))));
        registry.register("jungle_log_stairs", () -> new StairBlock(Blocks.JUNGLE_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG))));
        registry.register("acacia_log_stairs", () -> new StairBlock(Blocks.ACACIA_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG))));
        registry.register("dark_oak_log_stairs", () -> new StairBlock(Blocks.DARK_OAK_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG))));
        registry.register("crimson_stem_stairs", () -> new StairBlock(Blocks.CRIMSON_STEM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRIMSON_STEM))));
        registry.register("warped_stem_stairs", () -> new StairBlock(Blocks.WARPED_STEM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WARPED_STEM))));
        registry.register("stripped_oak_log_stairs", () -> new StairBlock(Blocks.STRIPPED_OAK_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG))));
        registry.register("stripped_spruce_log_stairs", () -> new StairBlock(Blocks.STRIPPED_SPRUCE_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_LOG))));
        registry.register("stripped_birch_log_stairs", () -> new StairBlock(Blocks.STRIPPED_BIRCH_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_BIRCH_LOG))));
        registry.register("stripped_jungle_log_stairs", () -> new StairBlock(Blocks.STRIPPED_JUNGLE_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_LOG))));
        registry.register("stripped_acacia_log_stairs", () -> new StairBlock(Blocks.STRIPPED_ACACIA_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_ACACIA_LOG))));
        registry.register("stripped_dark_oak_log_stairs", () -> new StairBlock(Blocks.STRIPPED_DARK_OAK_LOG::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG))));
        registry.register("stripped_crimson_stem_stairs", () -> new StairBlock(Blocks.STRIPPED_CRIMSON_STEM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_CRIMSON_STEM))));
        registry.register("stripped_warped_stem_stairs", () -> new StairBlock(Blocks.STRIPPED_WARPED_STEM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_STEM))));
        registry.register("glass_stairs", () -> new StairBlock(Blocks.GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GLASS))));
        registry.register("tinted_glass_stairs", () -> new StairBlock(Blocks.TINTED_GLASS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.TINTED_GLASS))));
        registry.register("cut_sandstone_stairs", () -> new StairBlock(Blocks.CUT_SANDSTONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE))));
        registry.register("obsidian_stairs", () -> new StairBlock(Blocks.OBSIDIAN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.OBSIDIAN))));
        registry.register("purpur_pillar_stairs", () -> new StairBlock(Blocks.PURPUR_PILLAR::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PURPUR_PILLAR))));
        registry.register("ice_stairs", () -> new StairBlock(Blocks.ICE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.ICE))));
        registry.register("snow_stairs", () -> new StairBlock(Blocks.SNOW_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK))));
        registry.register("clay_stairs", () -> new StairBlock(Blocks.CLAY::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CLAY))));
        registry.register("pumpkin_stairs", () -> new StairBlock(Blocks.PUMPKIN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PUMPKIN))));
        registry.register("netherrack_stairs", () -> new StairBlock(Blocks.NETHERRACK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.NETHERRACK))));
        registry.register("soul_sand_stairs", () -> new StairBlock(Blocks.SOUL_SAND::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SOUL_SAND))));
        registry.register("soul_soil_stairs", () -> new StairBlock(Blocks.SOUL_SOIL::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL))));
        registry.register("basalt_stairs", () -> new StairBlock(Blocks.BASALT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BASALT))));
        registry.register("polished_basalt_stairs", () -> new StairBlock(Blocks.POLISHED_BASALT::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT))));
        registry.register("glowstone_stairs", () -> new StairBlock(Blocks.GLOWSTONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.GLOWSTONE))));
        registry.register("cracked_stone_brick_stairs", () -> new StairBlock(Blocks.CRACKED_STONE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS))));
        registry.register("chiseled_stone_brick_stairs", () -> new StairBlock(Blocks.CHISELED_STONE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS))));
        registry.register("cracked_deepslate_brick_stairs", () -> new StairBlock(Blocks.CRACKED_DEEPSLATE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS))));
        registry.register("cracked_deepslate_tile_stairs", () -> new StairBlock(Blocks.CRACKED_DEEPSLATE_TILES::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES))));
        registry.register("chiseled_deepslate_stairs", () -> new StairBlock(Blocks.CHISELED_DEEPSLATE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_DEEPSLATE))));
        registry.register("melon_stairs", () -> new StairBlock(Blocks.MELON::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MELON))));
        registry.register("mycelium_stairs", () -> new StairBlock(Blocks.MYCELIUM::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MYCELIUM))));
        registry.register("cracked_nether_brick_stairs", () -> new StairBlock(Blocks.CRACKED_NETHER_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS))));
        registry.register("chiseled_nether_brick_stairs", () -> new StairBlock(Blocks.CHISELED_NETHER_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_NETHER_BRICKS))));
        registry.register("end_stone_stairs", () -> new StairBlock(Blocks.END_STONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.END_STONE))));
        registry.register("chiseled_quartz_block_stairs", () -> new StairBlock(Blocks.CHISELED_QUARTZ_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CHISELED_QUARTZ_BLOCK))));
        registry.register("quartz_brick_stairs", () -> new StairBlock(Blocks.QUARTZ_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS))));
        registry.register("quartz_pillar_stairs", () -> new StairBlock(Blocks.QUARTZ_PILLAR::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR))));
        registry.register("hay_bale_stairs", () -> new StairBlock(Blocks.HAY_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK))));
        registry.register("terracotta_stairs", () -> new StairBlock(Blocks.TERRACOTTA::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.TERRACOTTA))));
        registry.register("packed_ice_stairs", () -> new StairBlock(Blocks.PACKED_ICE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.PACKED_ICE))));
        registry.register("sea_lantern_stairs", () -> new StairBlock(Blocks.SEA_LANTERN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN))));
        registry.register("cut_red_sandstone_stairs", () -> new StairBlock(Blocks.CUT_RED_SANDSTONE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CUT_RED_SANDSTONE))));
        registry.register("magma_block_stairs", () -> new StairBlock(Blocks.MAGMA_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK))));
        registry.register("nether_wart_block_stairs", () -> new StairBlock(Blocks.NETHER_WART_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK))));
        registry.register("warped_wart_block_stairs", () -> new StairBlock(Blocks.WARPED_WART_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK))));
        registry.register("bone_block_stairs", () -> new StairBlock(Blocks.BONE_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK))));
        registry.register("blue_ice_stairs", () -> new StairBlock(Blocks.BLUE_ICE::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.BLUE_ICE))));
        registry.register("dried_kelp_stairs", () -> new StairBlock(Blocks.DRIED_KELP_BLOCK::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.DRIED_KELP_BLOCK))));
        registry.register("crying_obsidian_stairs", () -> new StairBlock(Blocks.CRYING_OBSIDIAN::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN))));
        registry.register("cracked_polished_blackstone_brick_stairs", () -> new StairBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS::defaultBlockState, (BlockBehaviour.Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS))));



        //SLABS

        registry.register("calcite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE)));
        registry.register("tuff_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
        registry.register("dripstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK)));
        registry.register("grass_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
        registry.register("dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
        registry.register("coarse_dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
        registry.register("podzol_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PODZOL)));
        registry.register("rooted_dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT)));
        registry.register("crimson_nylium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_NYLIUM)));
        registry.register("warped_nylium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)));
        registry.register("sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
        registry.register("red_sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_SAND)));
        registry.register("gravel_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
        registry.register("block_of_raw_iron_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
        registry.register("block_of_raw_copper_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)));
        registry.register("block_of_raw_gold_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)));
        registry.register("block_of_amethyst_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
        registry.register("oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
        registry.register("spruce_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG)));
        registry.register("birch_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_LOG)));
        registry.register("jungle_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG)));
        registry.register("acacia_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG)));
        registry.register("dark_oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG)));
        registry.register("crimson_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_STEM)));
        registry.register("warped_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM)));
        registry.register("stripped_oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
        registry.register("stripped_spruce_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_LOG)));
        registry.register("stripped_birch_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_BIRCH_LOG)));
        registry.register("stripped_jungle_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_LOG)));
        registry.register("stripped_acacia_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_ACACIA_LOG)));
        registry.register("stripped_dark_oak_log_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG)));
        registry.register("stripped_crimson_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_CRIMSON_STEM)));
        registry.register("stripped_warped_stem_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_STEM)));
        registry.register("glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
        registry.register("tinted_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TINTED_GLASS)));
        registry.register("obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
        registry.register("purpur_pillar_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_PILLAR)));
        registry.register("ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));
        registry.register("snow_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));
        registry.register("clay_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));
        registry.register("pumpkin_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN)));
        registry.register("netherrack_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));
        registry.register("soul_sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND)));
        registry.register("soul_soil_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL)));
        registry.register("basalt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BASALT)));
        registry.register("polished_basalt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT)));
        registry.register("glowstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)));
        registry.register("cracked_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
        registry.register("chiseled_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));
        registry.register("cracked_deepslate_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_BRICKS)));
        registry.register("cracked_deepslate_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_DEEPSLATE_TILES)));
        registry.register("chiseled_deepslate_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_DEEPSLATE)));
        registry.register("melon_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MELON)));
        registry.register("mycelium_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MYCELIUM)));
        registry.register("cracked_nether_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_NETHER_BRICKS)));
        registry.register("chiseled_nether_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_NETHER_BRICKS)));
        registry.register("end_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
        registry.register("chiseled_quartz_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_QUARTZ_BLOCK)));
        registry.register("quartz_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BRICKS)));
        registry.register("quartz_pillar_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR)));
        registry.register("hay_bale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
        registry.register("terracotta_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TERRACOTTA)));
        registry.register("packed_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)));
        registry.register("sea_lantern_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)));
        registry.register("magma_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK)));
        registry.register("nether_wart_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
        registry.register("warped_wart_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK)));
        registry.register("bone_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
        registry.register("blue_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE)));
        registry.register("dried_kelp_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DRIED_KELP_BLOCK)));
        registry.register("crying_obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN)));
        registry.register("cracked_polished_blackstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)));


    }
    /**
     * This Method allows to create {@link BlockItem} objects with non-default settings.
     * They will be loaded before the defaulted {@link BlockItem} object will be initialized.
     */
    static void registerItems(DeferredRegister<Item> registry) {
        //Custom BlockItems here
    }
}