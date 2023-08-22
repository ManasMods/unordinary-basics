package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRegistry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;

public class Unordinary_BasicsBlockLoot extends BlockLoot {

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Unordinary_BasicsRegistry.getKnownBlocks();
    }

    protected void addTables() {
        this.dropSelf(Unordinary_BasicsBlocks.ENCHANTMENT_LIBRARY);
        this.dropSelf(Unordinary_BasicsBlocks.ITEM_SORTER);

        this.dropSelf(Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.CALCITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.DIORITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.GRANITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.TUFF_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_TUFF);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.SOUL_SANDSTONE);
        this.dropSelf(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
        this.dropSelf(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
        this.dropSelf(Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
        this.dropSelf(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);

        this.dropSelf(Unordinary_BasicsBlocks.CALCITE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CALCITE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.TUFF_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.TUFF_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.DRIPSTONE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.DRIPSTONE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.DIRT_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.DIRT_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.COARSE_DIRT_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PODZOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PODZOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ROOTED_DIRT_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.SAND_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.SAND_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.RED_SAND_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_SAND_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRAVEL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRAVEL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.OAK_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.OAK_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BIRCH_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ACACIA_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_STEM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_STEM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.TINTED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.TINTED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.OBSIDIAN_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.OBSIDIAN_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ICE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ICE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.SNOW_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.SNOW_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CLAY_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CLAY_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PUMPKIN_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PUMPKIN_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.NETHERRACK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.NETHERRACK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.SOUL_SAND_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.SOUL_SAND_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.SOUL_SOIL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BASALT_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BASALT_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GLOWSTONE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GLOWSTONE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MELON_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MELON_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MYCELIUM_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MYCELIUM_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.END_STONE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.END_STONE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.HAY_BALE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.HAY_BALE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PACKED_ICE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PACKED_ICE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.SEA_LANTERN_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.SEA_LANTERN_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.NETHER_WART_BLOCK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.NETHER_WART_BLOCK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_WART_BLOCK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_WART_BLOCK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BONE_BLOCK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_ICE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_ICE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.DRIED_KELP_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.DRIED_KELP_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.DIRT_PATH_SLAB);
        
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.RED_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_WOOL_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_WOOL_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.RED_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_SLAB);
        this.dropSelf(Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Unordinary_BasicsBlocks.DIRT_PATH_STAIRS);

        this.dropSelf(Unordinary_BasicsBlocks.OAK_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.BIRCH_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.SPRUCE_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.ACACIA_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.DARK_OAK_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.JUNGLE_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.WARPED_STEM);
        this.dropSelf(Unordinary_BasicsBlocks.CRIMSON_STEM);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM);
        this.dropSelf(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM);
        this.dropSelf(Unordinary_BasicsBlocks.PURPUR_PILLAR);
        this.dropSelf(Unordinary_BasicsBlocks.QUARTZ_PILLAR);
        this.dropSelf(Unordinary_BasicsBlocks.HAY_BLOCK);

    }
}
