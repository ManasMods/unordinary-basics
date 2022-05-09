package com.github.manasmods.vanilla_plus.data;

import com.github.manasmods.manascore.data.gen.BlockTagProvider;
import com.github.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Vanilla_PlusBlockTagProvider extends BlockTagProvider {
    public Vanilla_PlusBlockTagProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, "<mod_id>");
    }

    @Override
    protected void generate() {
        tag(BlockTags.STAIRS).add
                (Vanilla_PlusBlocks.CALCITE_STAIRS,Vanilla_PlusBlocks.TUFF_STAIRS, Vanilla_PlusBlocks.DRIPSTONE_STAIRS, Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS, Vanilla_PlusBlocks.DIRT_STAIRS, Vanilla_PlusBlocks.COARSE_DIRT_STAIRS, Vanilla_PlusBlocks.PODZOL_STAIRS, Vanilla_PlusBlocks.ROOTED_DIRT_STAIRS, Vanilla_PlusBlocks.CRIMSON_NYLIUM_STAIRS, Vanilla_PlusBlocks.WARPED_NYLIUM_STAIRS, Vanilla_PlusBlocks.SAND_STAIRS, Vanilla_PlusBlocks.RED_SAND_STAIRS, Vanilla_PlusBlocks.GRAVEL_STAIRS, Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_STAIRS, Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_STAIRS, Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_STAIRS, Vanilla_PlusBlocks.GLASS_STAIRS, Vanilla_PlusBlocks.TINTED_GLASS_STAIRS, Vanilla_PlusBlocks.CUT_SANDSTONE_STAIRS, Vanilla_PlusBlocks.OBSIDIAN_STAIRS, Vanilla_PlusBlocks.PURPUR_PILLAR_STAIRS, Vanilla_PlusBlocks.ICE_STAIRS, Vanilla_PlusBlocks.SNOW_STAIRS, Vanilla_PlusBlocks.CLAY_STAIRS, Vanilla_PlusBlocks.PUMPKIN_STAIRS, Vanilla_PlusBlocks.NETHERRACK_STAIRS, Vanilla_PlusBlocks.SOUL_SAND_STAIRS, Vanilla_PlusBlocks.SOUL_SOIL_STAIRS, Vanilla_PlusBlocks.BASALT_STAIRS, Vanilla_PlusBlocks.POLISHED_BASALT_STAIRS, Vanilla_PlusBlocks.GLOWSTONE_STAIRS, Vanilla_PlusBlocks.CRACKED_STONE_BRICK_STAIRS, Vanilla_PlusBlocks.CHISELED_STONE_BRICK_STAIRS, Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Vanilla_PlusBlocks.CHISELED_DEEPSLATE_STAIRS, Vanilla_PlusBlocks.MELON_STAIRS, Vanilla_PlusBlocks.MYCELIUM_STAIRS, Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_STAIRS, Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_STAIRS, Vanilla_PlusBlocks.END_STONE_STAIRS, Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Vanilla_PlusBlocks.QUARTZ_BRICK_STAIRS, Vanilla_PlusBlocks.QUARTZ_PILLAR_STAIRS, Vanilla_PlusBlocks.HAY_BALE_STAIRS, Vanilla_PlusBlocks.TERRACOTTA_STAIRS, Vanilla_PlusBlocks.PACKED_ICE_STAIRS, Vanilla_PlusBlocks.SEA_LANTERN_STAIRS, Vanilla_PlusBlocks.CUT_RED_SANDSTONE_STAIRS, Vanilla_PlusBlocks.MAGMA_BLOCK_STAIRS, Vanilla_PlusBlocks.NETHER_WART_BLOCK_STAIRS, Vanilla_PlusBlocks.WARPED_WART_BLOCK_STAIRS, Vanilla_PlusBlocks.BONE_BLOCK_STAIRS, Vanilla_PlusBlocks.BLUE_ICE_STAIRS, Vanilla_PlusBlocks.DRIED_KELP_STAIRS, Vanilla_PlusBlocks.CRYING_OBSIDIAN_STAIRS, Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, Vanilla_PlusBlocks.WHITE_WOOL_STAIRS, Vanilla_PlusBlocks.ORANGE_WOOL_STAIRS, Vanilla_PlusBlocks.MAGENTA_WOOL_STAIRS, Vanilla_PlusBlocks.LIGHT_BLUE_WOOL_STAIRS, Vanilla_PlusBlocks.YELLOW_WOOL_STAIRS, Vanilla_PlusBlocks.LIME_WOOL_STAIRS, Vanilla_PlusBlocks.PINK_WOOL_STAIRS, Vanilla_PlusBlocks.GRAY_WOOL_STAIRS, Vanilla_PlusBlocks.LIGHT_GRAY_WOOL_STAIRS, Vanilla_PlusBlocks.CYAN_WOOL_STAIRS, Vanilla_PlusBlocks.PURPLE_WOOL_STAIRS, Vanilla_PlusBlocks.BLUE_WOOL_STAIRS, Vanilla_PlusBlocks.BROWN_WOOL_STAIRS, Vanilla_PlusBlocks.GREEN_WOOL_STAIRS, Vanilla_PlusBlocks.RED_WOOL_STAIRS, Vanilla_PlusBlocks.BLACK_WOOL_STAIRS, Vanilla_PlusBlocks.WHITE_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.ORANGE_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.MAGENTA_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.YELLOW_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.LIME_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.PINK_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.GRAY_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.CYAN_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.PURPLE_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.BLUE_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.BROWN_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.GREEN_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.RED_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.BLACK_TERRACOTTA_STAIRS, Vanilla_PlusBlocks.WHITE_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.ORANGE_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.MAGENTA_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.YELLOW_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.LIME_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.PINK_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.GRAY_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.CYAN_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.PURPLE_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.BLUE_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.BROWN_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.GREEN_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.RED_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.BLACK_STAINED_GLASS_STAIRS, Vanilla_PlusBlocks.WHITE_CONCRETE_STAIRS, Vanilla_PlusBlocks.ORANGE_CONCRETE_STAIRS, Vanilla_PlusBlocks.MAGENTA_CONCRETE_STAIRS, Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_STAIRS, Vanilla_PlusBlocks.YELLOW_CONCRETE_STAIRS, Vanilla_PlusBlocks.LIME_CONCRETE_STAIRS, Vanilla_PlusBlocks.PINK_CONCRETE_STAIRS, Vanilla_PlusBlocks.GRAY_CONCRETE_STAIRS, Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Vanilla_PlusBlocks.CYAN_CONCRETE_STAIRS, Vanilla_PlusBlocks.PURPLE_CONCRETE_STAIRS, Vanilla_PlusBlocks.BLUE_CONCRETE_STAIRS, Vanilla_PlusBlocks.BROWN_CONCRETE_STAIRS, Vanilla_PlusBlocks.GREEN_CONCRETE_STAIRS, Vanilla_PlusBlocks.RED_CONCRETE_STAIRS, Vanilla_PlusBlocks.BLACK_CONCRETE_STAIRS, Vanilla_PlusBlocks.WHITE_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.ORANGE_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.YELLOW_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.LIME_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.PINK_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.GRAY_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.CYAN_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.PURPLE_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.BLUE_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.BROWN_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.GREEN_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.RED_CONCRETE_POWDER_STAIRS, Vanilla_PlusBlocks.BLACK_CONCRETE_POWDER_STAIRS);
        tag(BlockTags.WOODEN_STAIRS).add
                (Vanilla_PlusBlocks.OAK_LOG_STAIRS, Vanilla_PlusBlocks.BIRCH_LOG_STAIRS, Vanilla_PlusBlocks.SPRUCE_LOG_STAIRS, Vanilla_PlusBlocks.ACACIA_LOG_STAIRS, Vanilla_PlusBlocks.DARK_OAK_LOG_STAIRS, Vanilla_PlusBlocks.JUNGLE_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_OAK_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Vanilla_PlusBlocks.CRIMSON_STEM_STAIRS, Vanilla_PlusBlocks.WARPED_STEM_STAIRS, Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_STAIRS, Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_STAIRS);
        tag(BlockTags.SLABS).add
                (Vanilla_PlusBlocks.CALCITE_SLAB, Vanilla_PlusBlocks.TUFF_SLAB, Vanilla_PlusBlocks.DRIPSTONE_SLAB, Vanilla_PlusBlocks.GRASS_BLOCK_SLAB, Vanilla_PlusBlocks.DIRT_SLAB, Vanilla_PlusBlocks.COARSE_DIRT_SLAB, Vanilla_PlusBlocks.PODZOL_SLAB, Vanilla_PlusBlocks.ROOTED_DIRT_SLAB, Vanilla_PlusBlocks.CRIMSON_NYLIUM_SLAB, Vanilla_PlusBlocks.WARPED_NYLIUM_SLAB, Vanilla_PlusBlocks.SAND_SLAB, Vanilla_PlusBlocks.RED_SAND_SLAB, Vanilla_PlusBlocks.GRAVEL_SLAB, Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_SLAB, Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_SLAB, Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_SLAB, Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_SLAB, Vanilla_PlusBlocks.GLASS_SLAB, Vanilla_PlusBlocks.TINTED_GLASS_SLAB, Vanilla_PlusBlocks.OBSIDIAN_SLAB, Vanilla_PlusBlocks.PURPUR_PILLAR_SLAB, Vanilla_PlusBlocks.ICE_SLAB, Vanilla_PlusBlocks.SNOW_SLAB, Vanilla_PlusBlocks.CLAY_SLAB, Vanilla_PlusBlocks.PUMPKIN_SLAB, Vanilla_PlusBlocks.NETHERRACK_SLAB, Vanilla_PlusBlocks.SOUL_SAND_SLAB, Vanilla_PlusBlocks.SOUL_SOIL_SLAB, Vanilla_PlusBlocks.BASALT_SLAB, Vanilla_PlusBlocks.POLISHED_BASALT_SLAB, Vanilla_PlusBlocks.GLOWSTONE_SLAB, Vanilla_PlusBlocks.CRACKED_STONE_BRICK_SLAB, Vanilla_PlusBlocks.CHISELED_STONE_BRICK_SLAB, Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Vanilla_PlusBlocks.CHISELED_DEEPSLATE_SLAB, Vanilla_PlusBlocks.MELON_SLAB, Vanilla_PlusBlocks.MYCELIUM_SLAB, Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_SLAB, Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_SLAB, Vanilla_PlusBlocks.END_STONE_SLAB, Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Vanilla_PlusBlocks.QUARTZ_BRICK_SLAB, Vanilla_PlusBlocks.QUARTZ_PILLAR_SLAB, Vanilla_PlusBlocks.HAY_BALE_SLAB, Vanilla_PlusBlocks.TERRACOTTA_SLAB, Vanilla_PlusBlocks.PACKED_ICE_SLAB, Vanilla_PlusBlocks.SEA_LANTERN_SLAB, Vanilla_PlusBlocks.MAGMA_BLOCK_SLAB, Vanilla_PlusBlocks.NETHER_WART_BLOCK_SLAB, Vanilla_PlusBlocks.WARPED_WART_BLOCK_SLAB, Vanilla_PlusBlocks.BONE_BLOCK_SLAB, Vanilla_PlusBlocks.BLUE_ICE_SLAB, Vanilla_PlusBlocks.DRIED_KELP_SLAB, Vanilla_PlusBlocks.CRYING_OBSIDIAN_SLAB, Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, Vanilla_PlusBlocks.WHITE_WOOL_SLAB, Vanilla_PlusBlocks.ORANGE_WOOL_SLAB, Vanilla_PlusBlocks.MAGENTA_WOOL_SLAB, Vanilla_PlusBlocks.LIGHT_BLUE_WOOL_SLAB, Vanilla_PlusBlocks.YELLOW_WOOL_SLAB, Vanilla_PlusBlocks.LIME_WOOL_SLAB, Vanilla_PlusBlocks.PINK_WOOL_SLAB, Vanilla_PlusBlocks.GRAY_WOOL_SLAB, Vanilla_PlusBlocks.LIGHT_GRAY_WOOL_SLAB, Vanilla_PlusBlocks.CYAN_WOOL_SLAB, Vanilla_PlusBlocks.PURPLE_WOOL_SLAB, Vanilla_PlusBlocks.BLUE_WOOL_SLAB, Vanilla_PlusBlocks.BROWN_WOOL_SLAB, Vanilla_PlusBlocks.GREEN_WOOL_SLAB, Vanilla_PlusBlocks.RED_WOOL_SLAB, Vanilla_PlusBlocks.BLACK_WOOL_SLAB, Vanilla_PlusBlocks.WHITE_TERRACOTTA_SLAB, Vanilla_PlusBlocks.ORANGE_TERRACOTTA_SLAB, Vanilla_PlusBlocks.MAGENTA_TERRACOTTA_SLAB, Vanilla_PlusBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, Vanilla_PlusBlocks.YELLOW_TERRACOTTA_SLAB, Vanilla_PlusBlocks.LIME_TERRACOTTA_SLAB, Vanilla_PlusBlocks.PINK_TERRACOTTA_SLAB, Vanilla_PlusBlocks.GRAY_TERRACOTTA_SLAB, Vanilla_PlusBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Vanilla_PlusBlocks.CYAN_TERRACOTTA_SLAB, Vanilla_PlusBlocks.PURPLE_TERRACOTTA_SLAB, Vanilla_PlusBlocks.BLUE_TERRACOTTA_SLAB, Vanilla_PlusBlocks.BROWN_TERRACOTTA_SLAB, Vanilla_PlusBlocks.GREEN_TERRACOTTA_SLAB, Vanilla_PlusBlocks.RED_TERRACOTTA_SLAB, Vanilla_PlusBlocks.BLACK_TERRACOTTA_SLAB, Vanilla_PlusBlocks.WHITE_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.ORANGE_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.MAGENTA_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.YELLOW_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.LIME_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.PINK_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.GRAY_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.CYAN_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.PURPLE_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.BLUE_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.BROWN_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.GREEN_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.RED_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.BLACK_STAINED_GLASS_SLAB, Vanilla_PlusBlocks.WHITE_CONCRETE_SLAB, Vanilla_PlusBlocks.ORANGE_CONCRETE_SLAB, Vanilla_PlusBlocks.MAGENTA_CONCRETE_SLAB, Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_SLAB, Vanilla_PlusBlocks.YELLOW_CONCRETE_SLAB, Vanilla_PlusBlocks.LIME_CONCRETE_SLAB, Vanilla_PlusBlocks.PINK_CONCRETE_SLAB, Vanilla_PlusBlocks.GRAY_CONCRETE_SLAB, Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_SLAB, Vanilla_PlusBlocks.CYAN_CONCRETE_SLAB, Vanilla_PlusBlocks.PURPLE_CONCRETE_SLAB, Vanilla_PlusBlocks.BLUE_CONCRETE_SLAB, Vanilla_PlusBlocks.BROWN_CONCRETE_SLAB, Vanilla_PlusBlocks.GREEN_CONCRETE_SLAB, Vanilla_PlusBlocks.RED_CONCRETE_SLAB, Vanilla_PlusBlocks.BLACK_CONCRETE_SLAB, Vanilla_PlusBlocks.WHITE_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.ORANGE_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.MAGENTA_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.YELLOW_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.LIME_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.PINK_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.GRAY_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.CYAN_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.PURPLE_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.BLUE_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.BROWN_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.GREEN_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.RED_CONCRETE_POWDER_SLAB, Vanilla_PlusBlocks.BLACK_CONCRETE_POWDER_SLAB);
        tag(BlockTags.WOODEN_SLABS).add
                (Vanilla_PlusBlocks.OAK_LOG_SLAB, Vanilla_PlusBlocks.BIRCH_LOG_SLAB, Vanilla_PlusBlocks.SPRUCE_LOG_SLAB, Vanilla_PlusBlocks.ACACIA_LOG_SLAB, Vanilla_PlusBlocks.DARK_OAK_LOG_SLAB, Vanilla_PlusBlocks.JUNGLE_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_OAK_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_SLAB, Vanilla_PlusBlocks.CRIMSON_STEM_SLAB, Vanilla_PlusBlocks.WARPED_STEM_SLAB, Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_SLAB, Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_SLAB);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                Vanilla_PlusBlocks.CALCITE_STAIRS,
                Vanilla_PlusBlocks.TUFF_STAIRS,
                Vanilla_PlusBlocks.DRIPSTONE_STAIRS,
                Vanilla_PlusBlocks.CRIMSON_NYLIUM_STAIRS,
                Vanilla_PlusBlocks.WARPED_NYLIUM_STAIRS,
                Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_STAIRS,
                Vanilla_PlusBlocks.CUT_SANDSTONE_STAIRS,
                Vanilla_PlusBlocks.NETHERRACK_STAIRS,
                Vanilla_PlusBlocks.PURPUR_PILLAR_STAIRS,
                Vanilla_PlusBlocks.BASALT_STAIRS,
                Vanilla_PlusBlocks.POLISHED_BASALT_STAIRS,
                Vanilla_PlusBlocks.CRACKED_STONE_BRICK_STAIRS,
                Vanilla_PlusBlocks.CHISELED_STONE_BRICK_STAIRS,
                Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS,
                Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_STAIRS,
                Vanilla_PlusBlocks.CHISELED_DEEPSLATE_STAIRS,
                Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_STAIRS,
                Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_STAIRS,
                Vanilla_PlusBlocks.END_STONE_STAIRS,
                Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_STAIRS,
                Vanilla_PlusBlocks.QUARTZ_BRICK_STAIRS,
                Vanilla_PlusBlocks.QUARTZ_PILLAR_STAIRS,
                Vanilla_PlusBlocks.TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.CUT_RED_SANDSTONE_STAIRS,
                Vanilla_PlusBlocks.MAGMA_BLOCK_STAIRS,
                Vanilla_PlusBlocks.BONE_BLOCK_STAIRS,
                Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS,
                Vanilla_PlusBlocks.WHITE_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.ORANGE_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.MAGENTA_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.YELLOW_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.LIME_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.PINK_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.GRAY_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.CYAN_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.PURPLE_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.BLUE_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.BROWN_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.GREEN_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.RED_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.BLACK_TERRACOTTA_STAIRS,
                Vanilla_PlusBlocks.WHITE_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.ORANGE_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.MAGENTA_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.YELLOW_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.LIME_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.PINK_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.GRAY_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.CYAN_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.PURPLE_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.BLUE_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.BROWN_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.GREEN_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.RED_CONCRETE_STAIRS,
                Vanilla_PlusBlocks.BLACK_CONCRETE_STAIRS);
        (Tags.Blocks.NEEDS_WOOD_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_STAIRS,
                Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_STAIRS,
                Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_STAIRS);
        (Tags.Blocks.NEEDS_IRON_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                Vanilla_PlusBlocks.OBSIDIAN_STAIRS,
                Vanilla_PlusBlocks.CRYING_OBSIDIAN_STAIRS);
        (Tags.Blocks.NEEDS_DIAMOND_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                Vanilla_PlusBlocks.CALCITE_SLAB,
                Vanilla_PlusBlocks.TUFF_SLAB,
                Vanilla_PlusBlocks.DRIPSTONE_SLAB,
                Vanilla_PlusBlocks.CRIMSON_NYLIUM_SLAB,
                Vanilla_PlusBlocks.WARPED_NYLIUM_SLAB,
                Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_SLAB,
                Vanilla_PlusBlocks.NETHERRACK_SLAB,
                Vanilla_PlusBlocks.PURPUR_PILLAR_SLAB,
                Vanilla_PlusBlocks.BASALT_SLAB,
                Vanilla_PlusBlocks.POLISHED_BASALT_SLAB,
                Vanilla_PlusBlocks.CRACKED_STONE_BRICK_SLAB,
                Vanilla_PlusBlocks.CHISELED_STONE_BRICK_SLAB,
                Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_SLAB,
                Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_SLAB,
                Vanilla_PlusBlocks.CHISELED_DEEPSLATE_SLAB,
                Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_SLAB,
                Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_SLAB,
                Vanilla_PlusBlocks.END_STONE_SLAB,
                Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_SLAB,
                Vanilla_PlusBlocks.QUARTZ_BRICK_SLAB,
                Vanilla_PlusBlocks.QUARTZ_PILLAR_SLAB,
                Vanilla_PlusBlocks.TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.MAGMA_BLOCK_SLAB,
                Vanilla_PlusBlocks.BONE_BLOCK_SLAB,
                Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB,
                Vanilla_PlusBlocks.WHITE_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.ORANGE_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.MAGENTA_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.LIGHT_BLUE_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.YELLOW_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.LIME_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.PINK_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.GRAY_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.LIGHT_GRAY_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.CYAN_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.PURPLE_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.BLUE_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.BROWN_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.GREEN_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.RED_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.BLACK_TERRACOTTA_SLAB,
                Vanilla_PlusBlocks.WHITE_CONCRETE_SLAB,
                Vanilla_PlusBlocks.ORANGE_CONCRETE_SLAB,
                Vanilla_PlusBlocks.MAGENTA_CONCRETE_SLAB,
                Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_SLAB,
                Vanilla_PlusBlocks.YELLOW_CONCRETE_SLAB,
                Vanilla_PlusBlocks.LIME_CONCRETE_SLAB,
                Vanilla_PlusBlocks.PINK_CONCRETE_SLAB,
                Vanilla_PlusBlocks.GRAY_CONCRETE_SLAB,
                Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_SLAB,
                Vanilla_PlusBlocks.CYAN_CONCRETE_SLAB,
                Vanilla_PlusBlocks.PURPLE_CONCRETE_SLAB,
                Vanilla_PlusBlocks.BLUE_CONCRETE_SLAB,
                Vanilla_PlusBlocks.BROWN_CONCRETE_SLAB,
                Vanilla_PlusBlocks.GREEN_CONCRETE_SLAB,
                Vanilla_PlusBlocks.RED_CONCRETE_SLAB,
                Vanilla_PlusBlocks.BLACK_CONCRETE_SLAB);
        (Tags.Blocks.NEEDS_WOOD_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_SLAB,
                Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_SLAB,
                Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_SLAB);
        (Tags.Blocks.NEEDS_IRON_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                Vanilla_PlusBlocks.OBSIDIAN_SLAB,
                Vanilla_PlusBlocks.CRYING_OBSIDIAN_SLAB);
        (Tags.Blocks.NEEDS_DIAMOND_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                Vanilla_PlusBlocks.OAK_LOG_STAIRS, Vanilla_PlusBlocks.BIRCH_LOG_STAIRS, Vanilla_PlusBlocks.SPRUCE_LOG_STAIRS, Vanilla_PlusBlocks.ACACIA_LOG_STAIRS, Vanilla_PlusBlocks.DARK_OAK_LOG_STAIRS, Vanilla_PlusBlocks.JUNGLE_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_OAK_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Vanilla_PlusBlocks.CRIMSON_STEM_STAIRS, Vanilla_PlusBlocks.WARPED_STEM_STAIRS, Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_STAIRS, Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_STAIRS,
                Vanilla_PlusBlocks.OAK_LOG_SLAB, Vanilla_PlusBlocks.BIRCH_LOG_SLAB, Vanilla_PlusBlocks.SPRUCE_LOG_SLAB, Vanilla_PlusBlocks.ACACIA_LOG_SLAB, Vanilla_PlusBlocks.DARK_OAK_LOG_SLAB, Vanilla_PlusBlocks.JUNGLE_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_OAK_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_SLAB, Vanilla_PlusBlocks.CRIMSON_STEM_SLAB, Vanilla_PlusBlocks.WARPED_STEM_SLAB, Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_SLAB, Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_SLAB);
        (Tags.Blocks.NEEDS_WOOD_TOOL).add
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS,
                Vanilla_PlusBlocks.DIRT_STAIRS,
                Vanilla_PlusBlocks.COARSE_DIRT_STAIRS,
                Vanilla_PlusBlocks.PODZOL_STAIRS,
                Vanilla_PlusBlocks.ROOTED_DIRT_STAIRS,
                Vanilla_PlusBlocks.SAND_STAIRS,
                Vanilla_PlusBlocks.RED_SAND_STAIRS,
                Vanilla_PlusBlocks.GRAVEL_STAIRS,
                Vanilla_PlusBlocks.CLAY_STAIRS,
                Vanilla_PlusBlocks.SOUL_SAND_STAIRS,
                Vanilla_PlusBlocks.SOUL_SOIL_STAIRS,
                Vanilla_PlusBlocks.MYCELIUM_STAIRS,
                Vanilla_PlusBlocks.WHITE_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.ORANGE_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.MAGENTA_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.YELLOW_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.LIME_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.PINK_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.GRAY_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.CYAN_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.PURPLE_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.BLUE_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.BROWN_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.GREEN_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.RED_CONCRETE_POWDER_STAIRS,
                Vanilla_PlusBlocks.BLACK_CONCRETE_POWDER_STAIRS);
        (Tags.Blocks.NEEDS_WOOD_TOOL).add
    }
}
