package com.github.manasmods.vanilla_plus.data;

import com.github.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import com.github.manasmods.vanilla_plus.registry.Vanilla_PlusRegistry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;

public class Vanilla_PlusBlockLoot extends BlockLoot {

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Vanilla_PlusRegistry.getKnownBlocks();
    }

    protected void addTables() {
        this.dropSelf(Vanilla_PlusBlocks.CALCITE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CALCITE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.TUFF_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.TUFF_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.DRIPSTONE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.DRIPSTONE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRASS_BLOCK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.DIRT_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.DIRT_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.COARSE_DIRT_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.COARSE_DIRT_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PODZOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PODZOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ROOTED_DIRT_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ROOTED_DIRT_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRIMSON_NYLIUM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRIMSON_NYLIUM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_NYLIUM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_NYLIUM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.SAND_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.SAND_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.RED_SAND_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.RED_SAND_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRAVEL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRAVEL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.OAK_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.OAK_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.SPRUCE_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.SPRUCE_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BIRCH_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BIRCH_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.JUNGLE_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.JUNGLE_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ACACIA_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ACACIA_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.DARK_OAK_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.DARK_OAK_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRIMSON_STEM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRIMSON_STEM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_STEM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_STEM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_OAK_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_OAK_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.TINTED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.TINTED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CUT_SANDSTONE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.OBSIDIAN_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.OBSIDIAN_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PURPUR_PILLAR_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PURPUR_PILLAR_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ICE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ICE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.SNOW_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.SNOW_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CLAY_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CLAY_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PUMPKIN_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PUMPKIN_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.NETHERRACK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.NETHERRACK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.SOUL_SAND_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.SOUL_SAND_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.SOUL_SOIL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.SOUL_SOIL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BASALT_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BASALT_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.POLISHED_BASALT_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.POLISHED_BASALT_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GLOWSTONE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GLOWSTONE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_STONE_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_STONE_BRICK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_STONE_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_STONE_BRICK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_DEEPSLATE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_DEEPSLATE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MELON_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MELON_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MYCELIUM_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MYCELIUM_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.END_STONE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.END_STONE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.QUARTZ_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.QUARTZ_BRICK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.QUARTZ_PILLAR_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.QUARTZ_PILLAR_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.HAY_BALE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.HAY_BALE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PACKED_ICE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PACKED_ICE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.SEA_LANTERN_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.SEA_LANTERN_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CUT_RED_SANDSTONE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGMA_BLOCK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGMA_BLOCK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.NETHER_WART_BLOCK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.NETHER_WART_BLOCK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_WART_BLOCK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_WART_BLOCK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BONE_BLOCK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BONE_BLOCK_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_ICE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_ICE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.DRIED_KELP_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.DRIED_KELP_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRYING_OBSIDIAN_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRYING_OBSIDIAN_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB);
        
        this.dropSelf(Vanilla_PlusBlocks.WHITE_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIME_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIME_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PINK_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PINK_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.RED_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.RED_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_WOOL_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_WOOL_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIME_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIME_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PINK_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PINK_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.RED_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.RED_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_TERRACOTTA_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_TERRACOTTA_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIME_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIME_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PINK_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PINK_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.RED_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.RED_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_STAINED_GLASS_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_STAINED_GLASS_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIME_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIME_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PINK_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PINK_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.RED_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.RED_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_CONCRETE_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_CONCRETE_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.WHITE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.ORANGE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.MAGENTA_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.YELLOW_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIME_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIME_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PINK_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PINK_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GRAY_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.CYAN_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.PURPLE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLUE_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BROWN_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.GREEN_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.RED_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.RED_CONCRETE_POWDER_SLAB);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_CONCRETE_POWDER_STAIRS);
        this.dropSelf(Vanilla_PlusBlocks.BLACK_CONCRETE_POWDER_SLAB);

        this.dropSelf(Vanilla_PlusBlocks.OAK_LOG);
        this.dropSelf(Vanilla_PlusBlocks.BIRCH_LOG);
        this.dropSelf(Vanilla_PlusBlocks.SPRUCE_LOG);
        this.dropSelf(Vanilla_PlusBlocks.ACACIA_LOG);
        this.dropSelf(Vanilla_PlusBlocks.DARK_OAK_LOG);
        this.dropSelf(Vanilla_PlusBlocks.JUNGLE_LOG);
        this.dropSelf(Vanilla_PlusBlocks.WARPED_STEM);
        this.dropSelf(Vanilla_PlusBlocks.CRIMSON_STEM);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_OAK_LOG);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_WARPED_STEM);
        this.dropSelf(Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM);
        this.dropSelf(Vanilla_PlusBlocks.PURPUR_PILLAR);
        this.dropSelf(Vanilla_PlusBlocks.QUARTZ_PILLAR);
        this.dropSelf(Vanilla_PlusBlocks.HAY_BLOCK);

    }
}