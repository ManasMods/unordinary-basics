package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.BlockTagProvider;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

public class Unordinary_BasicsBlockTagProvider extends BlockTagProvider {
    public Unordinary_BasicsBlockTagProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Unordinary_Basics.MOD_ID);
    }

    @Override
    protected void generate() {
        tag(UBTags.Blocks.VEIN_MINER_VALID)
                .addTag(Tags.Blocks.ORES);
        tag(UBTags.Blocks.TREE_FELLER_VALID)
                .addTag(BlockTags.LOGS)
                .addTag(BlockTags.CRIMSON_STEMS)
                .addTag(BlockTags.WARPED_STEMS)
                .add(Blocks.MUSHROOM_STEM)
                .add(Blocks.BROWN_MUSHROOM_BLOCK)
                .add(Blocks.RED_MUSHROOM_BLOCK);
        tag(UBTags.Blocks.CROP_TENDER_VALID)
                .add(Blocks.DRIED_KELP_BLOCK)
                .add(Blocks.HAY_BLOCK)
                .add(Blocks.MOSS_BLOCK)
                .add(Blocks.NETHER_WART_BLOCK)
                .add(Blocks.SCULK)
                .add(Blocks.SCULK_CATALYST)
                .add(Blocks.SCULK_SENSOR)
                .add(Blocks.SCULK_SHRIEKER)
                .add(Blocks.SCULK_VEIN)
                .add(Blocks.SHROOMLIGHT)
                .add(Blocks.SPONGE)
                .add(Blocks.TARGET)
                .add(Blocks.WARPED_WART_BLOCK)
                .add(Blocks.WET_SPONGE)
                .addTag(BlockTags.CROPS)
                .addTag(BlockTags.LEAVES);
        tag(UBTags.Blocks.TRIMMER_VALID)
                .add(Blocks.COBWEB)
                .add(Blocks.DEAD_BUSH)
                .add(Blocks.FERN)
                .add(Blocks.GLOW_LICHEN)
                .add(Blocks.GRASS)
                .add(Blocks.HANGING_ROOTS)
                .add(Blocks.LARGE_FERN)
                .add(Blocks.NETHER_SPROUTS)
                .add(Blocks.SEAGRASS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.TALL_SEAGRASS)
                .add(Blocks.TRIPWIRE)
                .add(Blocks.TWISTING_VINES)
                .add(Blocks.TWISTING_VINES_PLANT)
                .add(Blocks.WEEPING_VINES)
                .add(Blocks.WEEPING_VINES_PLANT)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.WOOL);
        tag(UBTags.Blocks.GRASS_BLOCKS)
                .add(Blocks.GRASS)
                .add(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB)
                .add(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
        tag(UBTags.Blocks.MYCELIUM_BLOCKS)
                .add(Blocks.MYCELIUM)
                .add(Unordinary_BasicsBlocks.MYCELIUM_SLAB)
                .add(Unordinary_BasicsBlocks.MYCELIUM_STAIRS);
        tag(UBTags.Blocks.PODZOL_BLOCKS)
                .add(Blocks.PODZOL)
                .add(Unordinary_BasicsBlocks.PODZOL_SLAB)
                .add(Unordinary_BasicsBlocks.PODZOL_STAIRS);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Unordinary_BasicsBlocks.ENCHANTMENT_LIBRARY)
                .add(Unordinary_BasicsBlocks.ITEM_SORTER)
                .add(Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(Unordinary_BasicsBlocks.ITEM_SORTER);
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Unordinary_BasicsBlocks.ENCHANTMENT_LIBRARY);

        tag(BlockTags.STAIRS)
            .add(Unordinary_BasicsBlocks.CALCITE_STAIRS, Unordinary_BasicsBlocks.TUFF_STAIRS, Unordinary_BasicsBlocks.DRIPSTONE_STAIRS, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS,
                Unordinary_BasicsBlocks.DIRT_STAIRS, Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS, Unordinary_BasicsBlocks.PODZOL_STAIRS, Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS,
                Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS,
                Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS, Unordinary_BasicsBlocks.SAND_STAIRS, Unordinary_BasicsBlocks.RED_SAND_STAIRS, Unordinary_BasicsBlocks.GRAVEL_STAIRS,
                Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS,
                Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS, Unordinary_BasicsBlocks.GLASS_STAIRS,
                Unordinary_BasicsBlocks.TINTED_GLASS_STAIRS, Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.OBSIDIAN_STAIRS, Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS,
                Unordinary_BasicsBlocks.ICE_STAIRS,
                Unordinary_BasicsBlocks.SNOW_STAIRS, Unordinary_BasicsBlocks.CLAY_STAIRS, Unordinary_BasicsBlocks.PUMPKIN_STAIRS, Unordinary_BasicsBlocks.NETHERRACK_STAIRS, Unordinary_BasicsBlocks.SOUL_SAND_STAIRS,
                Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS, Unordinary_BasicsBlocks.BASALT_STAIRS, Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS, Unordinary_BasicsBlocks.GLOWSTONE_STAIRS,
                Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS,
                Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS, Unordinary_BasicsBlocks.MELON_STAIRS, Unordinary_BasicsBlocks.MYCELIUM_STAIRS,
                Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS, Unordinary_BasicsBlocks.END_STONE_STAIRS, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS,
                Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS, Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS, Unordinary_BasicsBlocks.HAY_BALE_STAIRS, Unordinary_BasicsBlocks.TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.PACKED_ICE_STAIRS, Unordinary_BasicsBlocks.SEA_LANTERN_STAIRS, Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS,
                Unordinary_BasicsBlocks.NETHER_WART_BLOCK_STAIRS, Unordinary_BasicsBlocks.WARPED_WART_BLOCK_STAIRS, Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS, Unordinary_BasicsBlocks.BLUE_ICE_STAIRS,
                Unordinary_BasicsBlocks.DRIED_KELP_STAIRS, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.WHITE_WOOL_STAIRS,
                Unordinary_BasicsBlocks.ORANGE_WOOL_STAIRS, Unordinary_BasicsBlocks.MAGENTA_WOOL_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_STAIRS, Unordinary_BasicsBlocks.YELLOW_WOOL_STAIRS,
                Unordinary_BasicsBlocks.LIME_WOOL_STAIRS, Unordinary_BasicsBlocks.PINK_WOOL_STAIRS, Unordinary_BasicsBlocks.GRAY_WOOL_STAIRS, Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_STAIRS,
                Unordinary_BasicsBlocks.CYAN_WOOL_STAIRS, Unordinary_BasicsBlocks.PURPLE_WOOL_STAIRS, Unordinary_BasicsBlocks.BLUE_WOOL_STAIRS, Unordinary_BasicsBlocks.BROWN_WOOL_STAIRS,
                Unordinary_BasicsBlocks.GREEN_WOOL_STAIRS,
                Unordinary_BasicsBlocks.RED_WOOL_STAIRS, Unordinary_BasicsBlocks.BLACK_WOOL_STAIRS, Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.LIME_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.PINK_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.RED_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_STAIRS, Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS, Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS, Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS);

        tag(BlockTags.WOODEN_STAIRS)
            .add(Unordinary_BasicsBlocks.OAK_LOG_STAIRS, Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS, Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS, Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS,
                Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS, Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS,
                Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS,
                Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS, Unordinary_BasicsBlocks.WARPED_STEM_STAIRS, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS,
                Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS);

        tag(BlockTags.SLABS)
            .add(Unordinary_BasicsBlocks.CALCITE_SLAB, Unordinary_BasicsBlocks.TUFF_SLAB, Unordinary_BasicsBlocks.DRIPSTONE_SLAB, Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Unordinary_BasicsBlocks.DIRT_SLAB,
                Unordinary_BasicsBlocks.COARSE_DIRT_SLAB, Unordinary_BasicsBlocks.PODZOL_SLAB, Unordinary_BasicsBlocks.ROOTED_DIRT_SLAB, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB, Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB,
                Unordinary_BasicsBlocks.SAND_SLAB, Unordinary_BasicsBlocks.RED_SAND_SLAB, Unordinary_BasicsBlocks.GRAVEL_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB,
                Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB, Unordinary_BasicsBlocks.GLASS_SLAB, Unordinary_BasicsBlocks.TINTED_GLASS_SLAB,
                Unordinary_BasicsBlocks.OBSIDIAN_SLAB,
                Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB, Unordinary_BasicsBlocks.ICE_SLAB, Unordinary_BasicsBlocks.SNOW_SLAB, Unordinary_BasicsBlocks.CLAY_SLAB, Unordinary_BasicsBlocks.PUMPKIN_SLAB,
                Unordinary_BasicsBlocks.NETHERRACK_SLAB, Unordinary_BasicsBlocks.SOUL_SAND_SLAB, Unordinary_BasicsBlocks.SOUL_SOIL_SLAB, Unordinary_BasicsBlocks.BASALT_SLAB, Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB,
                Unordinary_BasicsBlocks.GLOWSTONE_SLAB, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB,
                Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB, Unordinary_BasicsBlocks.MELON_SLAB, Unordinary_BasicsBlocks.MYCELIUM_SLAB,
                Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB, Unordinary_BasicsBlocks.END_STONE_SLAB, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB,
                Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB, Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB, Unordinary_BasicsBlocks.HAY_BALE_SLAB, Unordinary_BasicsBlocks.TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PACKED_ICE_SLAB,
                Unordinary_BasicsBlocks.SEA_LANTERN_SLAB, Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB, Unordinary_BasicsBlocks.NETHER_WART_BLOCK_SLAB, Unordinary_BasicsBlocks.WARPED_WART_BLOCK_SLAB,
                Unordinary_BasicsBlocks.BONE_BLOCK_SLAB, Unordinary_BasicsBlocks.BLUE_ICE_SLAB, Unordinary_BasicsBlocks.DRIED_KELP_SLAB, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB,
                Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.WHITE_WOOL_SLAB, Unordinary_BasicsBlocks.ORANGE_WOOL_SLAB, Unordinary_BasicsBlocks.MAGENTA_WOOL_SLAB,
                Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_SLAB, Unordinary_BasicsBlocks.YELLOW_WOOL_SLAB, Unordinary_BasicsBlocks.LIME_WOOL_SLAB, Unordinary_BasicsBlocks.PINK_WOOL_SLAB, Unordinary_BasicsBlocks.GRAY_WOOL_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_SLAB, Unordinary_BasicsBlocks.CYAN_WOOL_SLAB, Unordinary_BasicsBlocks.PURPLE_WOOL_SLAB, Unordinary_BasicsBlocks.BLUE_WOOL_SLAB, Unordinary_BasicsBlocks.BROWN_WOOL_SLAB,
                Unordinary_BasicsBlocks.GREEN_WOOL_SLAB, Unordinary_BasicsBlocks.RED_WOOL_SLAB, Unordinary_BasicsBlocks.BLACK_WOOL_SLAB, Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.LIME_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.PINK_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.RED_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_SLAB, Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB, Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB, Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB, Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB, Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB, Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB, Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB, Unordinary_BasicsBlocks.RED_CONCRETE_SLAB, Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_SLAB,
                Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB,
                Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_SLAB,
                Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_SLAB,
                Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_SLAB, Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_SLAB,
                Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_SLAB);

        tag(BlockTags.WOODEN_SLABS)
            .add(Unordinary_BasicsBlocks.OAK_LOG_SLAB, Unordinary_BasicsBlocks.BIRCH_LOG_SLAB, Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB, Unordinary_BasicsBlocks.ACACIA_LOG_SLAB,
                Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB, Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB,
                Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB,
                Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB, Unordinary_BasicsBlocks.WARPED_STEM_SLAB, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB);

        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(Unordinary_BasicsBlocks.CALCITE_STAIRS, Unordinary_BasicsBlocks.TUFF_STAIRS, Unordinary_BasicsBlocks.DRIPSTONE_STAIRS, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS,
                Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS, Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.NETHERRACK_STAIRS,
                Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS, Unordinary_BasicsBlocks.BASALT_STAIRS, Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS,
                Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS,
                Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS, Unordinary_BasicsBlocks.END_STONE_STAIRS,
                Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS, Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS, Unordinary_BasicsBlocks.TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS, Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS,
                Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS, Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS, Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS, Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS, Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS)
            .add(Unordinary_BasicsBlocks.CALCITE_SLAB, Unordinary_BasicsBlocks.TUFF_SLAB, Unordinary_BasicsBlocks.DRIPSTONE_SLAB, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB, Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB,
                Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB, Unordinary_BasicsBlocks.NETHERRACK_SLAB, Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB, Unordinary_BasicsBlocks.BASALT_SLAB,
                Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB,
                Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB,
                Unordinary_BasicsBlocks.END_STONE_SLAB, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB, Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB,
                Unordinary_BasicsBlocks.TERRACOTTA_SLAB, Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB, Unordinary_BasicsBlocks.BONE_BLOCK_SLAB, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB,
                Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB, Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB, Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB, Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB, Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB, Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB, Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB, Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB, Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB, Unordinary_BasicsBlocks.RED_CONCRETE_SLAB, Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB)
            .add(Unordinary_BasicsBlocks.OAK_LOG_STAIRS, Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS, Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS, Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS,
                Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS, Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS,
                Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS,
                Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS, Unordinary_BasicsBlocks.WARPED_STEM_STAIRS, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS,
                Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS, Unordinary_BasicsBlocks.OAK_LOG_SLAB, Unordinary_BasicsBlocks.BIRCH_LOG_SLAB, Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB,
                Unordinary_BasicsBlocks.ACACIA_LOG_SLAB,
                Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB, Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB,
                Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB,
                Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB, Unordinary_BasicsBlocks.WARPED_STEM_SLAB, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB)
            .add(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS, Unordinary_BasicsBlocks.DIRT_STAIRS, Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS, Unordinary_BasicsBlocks.PODZOL_STAIRS, Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS,
                Unordinary_BasicsBlocks.SAND_STAIRS, Unordinary_BasicsBlocks.RED_SAND_STAIRS, Unordinary_BasicsBlocks.GRAVEL_STAIRS, Unordinary_BasicsBlocks.CLAY_STAIRS, Unordinary_BasicsBlocks.SOUL_SAND_STAIRS,
                Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS, Unordinary_BasicsBlocks.MYCELIUM_STAIRS, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS)

                .add(Unordinary_BasicsBlocks.ANDESITE_BRICKS)
                .add(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS)
                .add(Unordinary_BasicsBlocks.CALCITE_BRICKS)
                .add(Unordinary_BasicsBlocks.DIORITE_BRICKS)
                .add(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS)
                .add(Unordinary_BasicsBlocks.DRIPSTONE_BRICKS)
                .add(Unordinary_BasicsBlocks.GRANITE_BRICKS)
                .add(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS)
                .add(Unordinary_BasicsBlocks.TUFF_BRICKS)
                .add(Unordinary_BasicsBlocks.POLISHED_TUFF)
                .add(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS)
                //.add(Unordinary_BasicsBlocks.SOUL_SANDSTONE)
                //.add(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE)
                //.add(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE)
                .add(Unordinary_BasicsBlocks.SANDSTONE_BRICKS)
                .add(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS)
                .add(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);

        tag(BlockTags.NEEDS_STONE_TOOL)
            .add(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS)
            .add(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB);

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(Unordinary_BasicsBlocks.OBSIDIAN_STAIRS, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS)
            .add(Unordinary_BasicsBlocks.OBSIDIAN_SLAB, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(Unordinary_BasicsBlocks.CALCITE_STAIRS, Unordinary_BasicsBlocks.TUFF_STAIRS, Unordinary_BasicsBlocks.DRIPSTONE_STAIRS, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS,
                Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS, Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.NETHERRACK_STAIRS,
                Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS, Unordinary_BasicsBlocks.BASALT_STAIRS, Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS,
                Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS,
                Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS, Unordinary_BasicsBlocks.END_STONE_STAIRS,
                Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS, Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS, Unordinary_BasicsBlocks.TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS, Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS,
                Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS,
                Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS, Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS, Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS, Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS, Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS,
                Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS, Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS, Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS)
            .add(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS)
            .add(Unordinary_BasicsBlocks.OBSIDIAN_STAIRS, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS)
            .add(Unordinary_BasicsBlocks.CALCITE_SLAB, Unordinary_BasicsBlocks.TUFF_SLAB, Unordinary_BasicsBlocks.DRIPSTONE_SLAB, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB, Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB,
                Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB, Unordinary_BasicsBlocks.NETHERRACK_SLAB, Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB, Unordinary_BasicsBlocks.BASALT_SLAB,
                Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB,
                Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB,
                Unordinary_BasicsBlocks.END_STONE_SLAB, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB, Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB,
                Unordinary_BasicsBlocks.TERRACOTTA_SLAB, Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB, Unordinary_BasicsBlocks.BONE_BLOCK_SLAB, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB,
                Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB,
                Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB, Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB, Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB, Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB, Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB, Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB, Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB, Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB,
                Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB, Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB, Unordinary_BasicsBlocks.RED_CONCRETE_SLAB, Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB)
            .add(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB)
            .add(Unordinary_BasicsBlocks.OBSIDIAN_SLAB, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB)

            .add(Unordinary_BasicsBlocks.ANDESITE_BRICKS)
            .add(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS)
            .add(Unordinary_BasicsBlocks.CALCITE_BRICKS)
            .add(Unordinary_BasicsBlocks.DIORITE_BRICKS)
            .add(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS)
            .add(Unordinary_BasicsBlocks.DRIPSTONE_BRICKS)
            .add(Unordinary_BasicsBlocks.GRANITE_BRICKS)
            .add(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS)
            .add(Unordinary_BasicsBlocks.TUFF_BRICKS)
            .add(Unordinary_BasicsBlocks.POLISHED_TUFF)
            .add(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS)
            .add(Unordinary_BasicsBlocks.SOUL_SANDSTONE)
            .add(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE)
            .add(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE)
            .add(Unordinary_BasicsBlocks.SANDSTONE_BRICKS)
            .add(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS)
            .add(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);

        tag(BlockTags.MINEABLE_WITH_AXE).add(Unordinary_BasicsBlocks.OAK_LOG_STAIRS, Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS, Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS, Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS,
            Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS, Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS,
            Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS,
            Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS, Unordinary_BasicsBlocks.WARPED_STEM_STAIRS, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS,
            Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS, Unordinary_BasicsBlocks.OAK_LOG_SLAB, Unordinary_BasicsBlocks.BIRCH_LOG_SLAB, Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB, Unordinary_BasicsBlocks.ACACIA_LOG_SLAB,
            Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB, Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB,
            Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB,
            Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB, Unordinary_BasicsBlocks.WARPED_STEM_SLAB, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS, Unordinary_BasicsBlocks.DIRT_STAIRS, Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS, Unordinary_BasicsBlocks.PODZOL_STAIRS, Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS,
                Unordinary_BasicsBlocks.SAND_STAIRS, Unordinary_BasicsBlocks.RED_SAND_STAIRS, Unordinary_BasicsBlocks.GRAVEL_STAIRS, Unordinary_BasicsBlocks.CLAY_STAIRS, Unordinary_BasicsBlocks.SOUL_SAND_STAIRS,
                Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS, Unordinary_BasicsBlocks.MYCELIUM_STAIRS, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS,
                Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS);

    }
}
