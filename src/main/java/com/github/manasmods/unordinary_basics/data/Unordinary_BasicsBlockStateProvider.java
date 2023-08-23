package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.BlockStateProvider;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Objects;


public class Unordinary_BasicsBlockStateProvider extends BlockStateProvider {

    public Unordinary_BasicsBlockStateProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Unordinary_Basics.MOD_ID);
    }

    @Override
    protected void generate() {
        modBlocks();
        modStairs();
        modSlabs();
        modWalls();
        modMisc();
    }

        private void modBlocks() {
            defaultBlock(Unordinary_BasicsBlocks.ANDESITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.CALCITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.DIORITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.GRANITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.TUFF_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.POLISHED_TUFF);
            defaultBlock(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
            nonRotatableColumn(Unordinary_BasicsBlocks.SOUL_SANDSTONE, mcLoc("unordinary_basics:block/soul_sandstone_top"), mcLoc("unordinary_basics:block/soul_sandstone"), mcLoc("unordinary_basics:block/soul_sandstone_bottom"));
            nonRotatableColumn(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE, mcLoc("unordinary_basics:block/soul_sandstone_top"), mcLoc("unordinary_basics:block/soul_sandstone_top"), mcLoc("unordinary_basics:block/soul_sandstone_top"));
            nonRotatableColumn(Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE, mcLoc("unordinary_basics:block/soul_sandstone_top"), mcLoc("unordinary_basics:block/chiseled_soul_sandstone"), mcLoc("unordinary_basics:block/soul_sandstone_top"));
            nonRotatableColumn(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE, mcLoc("unordinary_basics:block/soul_sandstone_top"), mcLoc("unordinary_basics:block/cut_soul_sandstone"), mcLoc("unordinary_basics:block/soul_sandstone_top"));
            defaultBlock(Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
            defaultBlock(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);


        }
        private void modStairs() {
            stairs(Unordinary_BasicsBlocks.ANDESITE_BRICK_STAIRS, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.CALCITE_BRICK_STAIRS, Unordinary_BasicsBlocks.CALCITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.DIORITE_BRICK_STAIRS, Unordinary_BasicsBlocks.DIORITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.DRIPSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
            stairs(Unordinary_BasicsBlocks.GRANITE_BRICK_STAIRS, Unordinary_BasicsBlocks.GRANITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
            stairs(Unordinary_BasicsBlocks.TUFF_BRICK_STAIRS, Unordinary_BasicsBlocks.TUFF_BRICKS);
            stairs(Unordinary_BasicsBlocks.POLISHED_TUFF_STAIRS, Unordinary_BasicsBlocks.POLISHED_TUFF);
            stairs(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
            stairs(Unordinary_BasicsBlocks.SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.SOUL_SANDSTONE);
            stairs(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
            stairs(Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);
            stairs(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
            stairs(Unordinary_BasicsBlocks.SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
            stairs(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
            stairs(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
        
            stairs(Unordinary_BasicsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
            stairs(Unordinary_BasicsBlocks.TUFF_STAIRS, Blocks.TUFF);
            stairs(Unordinary_BasicsBlocks.DRIPSTONE_STAIRS, Blocks.DRIPSTONE_BLOCK);
            stairs(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS, mcLoc("block/grass_block_top"), mcLoc("block/dirt"), mcLoc("block/grass_block_side"), mcLoc("block/grass_block_side_overlay"));
            stairs(Unordinary_BasicsBlocks.DIRT_STAIRS, Blocks.DIRT);
            stairs(Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS, Blocks.COARSE_DIRT);
            sidedStairs(Unordinary_BasicsBlocks.PODZOL_STAIRS, mcLoc("block/podzol_top"), mcLoc("block/dirt"), mcLoc("block/podzol_side"));
            stairs(Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS, Blocks.ROOTED_DIRT);
            sidedStairs(Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS, mcLoc("block/crimson_nylium"), mcLoc("block/netherrack"), mcLoc("block/crimson_nylium_side"));
            sidedStairs(Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS, mcLoc("block/warped_nylium"), mcLoc("block/netherrack"), mcLoc("block/warped_nylium_side"));
            stairs(Unordinary_BasicsBlocks.SAND_STAIRS, Blocks.SAND);
            stairs(Unordinary_BasicsBlocks.RED_SAND_STAIRS, Blocks.RED_SAND);
            stairs(Unordinary_BasicsBlocks.GRAVEL_STAIRS, Blocks.GRAVEL);
            stairs(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS, Blocks.RAW_IRON_BLOCK);
            stairs(Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Blocks.RAW_COPPER_BLOCK);
            stairs(Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS, Blocks.RAW_GOLD_BLOCK);
            stairs(Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS, Blocks.AMETHYST_BLOCK);
            stairs(Unordinary_BasicsBlocks.OAK_LOG_STAIRS, mcLoc("block/oak_log"), mcLoc("block/oak_log_top"));
            stairs(Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS, mcLoc("block/spruce_log"), mcLoc("block/spruce_log_top"));
            stairs(Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS, mcLoc("block/birch_log"), mcLoc("block/birch_log_top"));
            stairs(Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS, mcLoc("block/jungle_log"), mcLoc("block/jungle_log_top"));
            stairs(Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS, mcLoc("block/acacia_log"), mcLoc("block/acacia_log_top"));
            stairs(Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS, mcLoc("block/dark_oak_log"), mcLoc("block/dark_oak_log_top"));
            stairs(Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS, mcLoc("block/crimson_stem"), mcLoc("block/crimson_stem_top"));
            stairs(Unordinary_BasicsBlocks.WARPED_STEM_STAIRS, mcLoc("block/warped_stem"), mcLoc("block/warped_stem_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS, mcLoc("block/stripped_oak_log"), mcLoc("block/stripped_oak_log_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS, mcLoc("block/stripped_spruce_log"), mcLoc("block/stripped_spruce_log_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS, mcLoc("block/stripped_birch_log"), mcLoc("block/stripped_birch_log_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS, mcLoc("block/stripped_jungle_log"), mcLoc("block/stripped_jungle_log_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS, mcLoc("block/stripped_acacia_log"), mcLoc("block/stripped_acacia_log_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, mcLoc("block/stripped_dark_oak_log"), mcLoc("block/stripped_dark_oak_log_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS, mcLoc("block/stripped_crimson_stem"), mcLoc("block/stripped_crimson_stem_top"));
            stairs(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS, mcLoc("block/stripped_warped_stem"), mcLoc("block/stripped_warped_stem_top"));
            stairs(Unordinary_BasicsBlocks.GLASS_STAIRS, Blocks.GLASS);
            stairs(Unordinary_BasicsBlocks.TINTED_GLASS_STAIRS, Blocks.TINTED_GLASS);
            stairs(Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE);
            stairs(Unordinary_BasicsBlocks.OBSIDIAN_STAIRS, Blocks.OBSIDIAN);
            stairs(Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS, mcLoc("block/purpur_pillar"), mcLoc("block/purpur_pillar_top"));
            stairs(Unordinary_BasicsBlocks.ICE_STAIRS, Blocks.ICE);
            stairs(Unordinary_BasicsBlocks.SNOW_STAIRS, Blocks.SNOW);
            stairs(Unordinary_BasicsBlocks.CLAY_STAIRS, Blocks.CLAY);
            stairs(Unordinary_BasicsBlocks.PUMPKIN_STAIRS, mcLoc("block/pumpkin_side"), mcLoc("block/pumpkin_top"));
            stairs(Unordinary_BasicsBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK);
            stairs(Unordinary_BasicsBlocks.SOUL_SAND_STAIRS, Blocks.SOUL_SAND);
            stairs(Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS, Blocks.SOUL_SOIL);
            stairs(Unordinary_BasicsBlocks.BASALT_STAIRS, mcLoc("block/basalt_side"), mcLoc("block/basalt_top"));
            stairs(Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS, mcLoc("block/polished_basalt_side"), mcLoc("block/polished_basalt_top"));
            stairs(Unordinary_BasicsBlocks.GLOWSTONE_STAIRS, Blocks.GLOWSTONE);
            stairs(Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS, Blocks.CRACKED_STONE_BRICKS);
            stairs(Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS, Blocks.CHISELED_STONE_BRICKS);
            stairs(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Blocks.CRACKED_DEEPSLATE_BRICKS);
            stairs(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Blocks.CRACKED_DEEPSLATE_TILES);
            stairs(Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS, Blocks.CHISELED_DEEPSLATE);
            stairs(Unordinary_BasicsBlocks.MELON_STAIRS, mcLoc("block/melon_side"), mcLoc("block/melon_top"));
            sidedStairs(Unordinary_BasicsBlocks.MYCELIUM_STAIRS, mcLoc("block/mycelium_top"), mcLoc("block/dirt"), mcLoc("block/mycelium_side"));
            stairs(Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS, Blocks.CRACKED_NETHER_BRICKS);
            stairs(Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS, Blocks.CHISELED_NETHER_BRICKS);
            stairs(Unordinary_BasicsBlocks.END_STONE_STAIRS, Blocks.END_STONE);
            stairs(Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Blocks.CHISELED_QUARTZ_BLOCK);
            stairs(Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
            stairs(Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS, mcLoc("block/quartz_pillar"), mcLoc("block/quartz_pillar_top"));
            stairs(Unordinary_BasicsBlocks.HAY_BALE_STAIRS, mcLoc("block/hay_block_side"), mcLoc("block/hay_block_top"));
            stairs(Unordinary_BasicsBlocks.TERRACOTTA_STAIRS, Blocks.TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.PACKED_ICE_STAIRS, Blocks.PACKED_ICE);
            stairs(Unordinary_BasicsBlocks.SEA_LANTERN_STAIRS, Blocks.SEA_LANTERN);
            stairs(Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE);
            stairs(Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS, mcLoc("block/magma"), mcLoc("block/magma"));
            stairs(Unordinary_BasicsBlocks.NETHER_WART_BLOCK_STAIRS, Blocks.NETHER_WART_BLOCK);
            stairs(Unordinary_BasicsBlocks.WARPED_WART_BLOCK_STAIRS, Blocks.WARPED_WART_BLOCK);
            stairs(Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS, mcLoc("block/bone_block_side"), mcLoc("block/bone_block_top"));
            stairs(Unordinary_BasicsBlocks.BLUE_ICE_STAIRS, Blocks.BLUE_ICE);
            stairs(Unordinary_BasicsBlocks.DRIED_KELP_STAIRS, mcLoc("block/dried_kelp_side"), mcLoc("block/dried_kelp_top"));
            stairs(Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS, Blocks.CRYING_OBSIDIAN);
            stairs(Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);

            stairs(Unordinary_BasicsBlocks.WHITE_WOOL_STAIRS, Blocks.WHITE_WOOL);
            stairs(Unordinary_BasicsBlocks.ORANGE_WOOL_STAIRS, Blocks.ORANGE_WOOL);
            stairs(Unordinary_BasicsBlocks.MAGENTA_WOOL_STAIRS, Blocks.MAGENTA_WOOL);
            stairs(Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_STAIRS, Blocks.LIGHT_BLUE_WOOL);
            stairs(Unordinary_BasicsBlocks.YELLOW_WOOL_STAIRS, Blocks.YELLOW_WOOL);
            stairs(Unordinary_BasicsBlocks.LIME_WOOL_STAIRS, Blocks.LIME_WOOL);
            stairs(Unordinary_BasicsBlocks.PINK_WOOL_STAIRS, Blocks.PINK_WOOL);
            stairs(Unordinary_BasicsBlocks.GRAY_WOOL_STAIRS, Blocks.GRAY_WOOL);
            stairs(Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_STAIRS, Blocks.LIGHT_GRAY_WOOL);
            stairs(Unordinary_BasicsBlocks.CYAN_WOOL_STAIRS, Blocks.CYAN_WOOL);
            stairs(Unordinary_BasicsBlocks.PURPLE_WOOL_STAIRS, Blocks.PURPLE_WOOL);
            stairs(Unordinary_BasicsBlocks.BLUE_WOOL_STAIRS, Blocks.BLUE_WOOL);
            stairs(Unordinary_BasicsBlocks.BROWN_WOOL_STAIRS, Blocks.BROWN_WOOL);
            stairs(Unordinary_BasicsBlocks.GREEN_WOOL_STAIRS, Blocks.GREEN_WOOL);
            stairs(Unordinary_BasicsBlocks.RED_WOOL_STAIRS, Blocks.RED_WOOL);
            stairs(Unordinary_BasicsBlocks.BLACK_WOOL_STAIRS, Blocks.BLACK_WOOL);
            stairs(Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS, Blocks.WHITE_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS, Blocks.ORANGE_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS, Blocks.MAGENTA_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, Blocks.LIGHT_BLUE_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS, Blocks.YELLOW_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS, Blocks.LIME_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS, Blocks.PINK_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS, Blocks.GRAY_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Blocks.LIGHT_GRAY_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS, Blocks.CYAN_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS, Blocks.PURPLE_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS, Blocks.BLUE_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS, Blocks.BROWN_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS, Blocks.GREEN_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS, Blocks.RED_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS, Blocks.BLACK_TERRACOTTA);
            stairs(Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_STAIRS, Blocks.WHITE_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_STAIRS, Blocks.ORANGE_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_STAIRS, Blocks.MAGENTA_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, Blocks.LIGHT_BLUE_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_STAIRS, Blocks.YELLOW_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.LIME_STAINED_GLASS_STAIRS, Blocks.LIME_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.PINK_STAINED_GLASS_STAIRS, Blocks.PINK_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_STAIRS, Blocks.GRAY_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, Blocks.LIGHT_GRAY_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_STAIRS, Blocks.CYAN_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_STAIRS, Blocks.PURPLE_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_STAIRS, Blocks.BLUE_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_STAIRS, Blocks.BROWN_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_STAIRS, Blocks.GREEN_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.RED_STAINED_GLASS_STAIRS, Blocks.RED_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_STAIRS, Blocks.BLACK_STAINED_GLASS);
            stairs(Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS, Blocks.WHITE_CONCRETE);
            stairs(Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS, Blocks.ORANGE_CONCRETE);
            stairs(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS, Blocks.MAGENTA_CONCRETE);
            stairs(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS, Blocks.LIGHT_BLUE_CONCRETE);
            stairs(Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS, Blocks.YELLOW_CONCRETE);
            stairs(Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS, Blocks.LIME_CONCRETE);
            stairs(Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS, Blocks.PINK_CONCRETE);
            stairs(Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS, Blocks.GRAY_CONCRETE);
            stairs(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Blocks.LIGHT_GRAY_CONCRETE);
            stairs(Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS, Blocks.CYAN_CONCRETE);
            stairs(Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS, Blocks.PURPLE_CONCRETE);
            stairs(Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS, Blocks.BLUE_CONCRETE);
            stairs(Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS, Blocks.BROWN_CONCRETE);
            stairs(Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS, Blocks.GREEN_CONCRETE);
            stairs(Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS, Blocks.RED_CONCRETE);
            stairs(Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS, Blocks.BLACK_CONCRETE);
            stairs(Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS, Blocks.WHITE_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS, Blocks.ORANGE_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Blocks.MAGENTA_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS, Blocks.YELLOW_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS, Blocks.LIME_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS, Blocks.PINK_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS, Blocks.GRAY_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS, Blocks.CYAN_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS, Blocks.PURPLE_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS, Blocks.BLUE_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS, Blocks.BROWN_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS, Blocks.GREEN_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS, Blocks.RED_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS, Blocks.BLACK_CONCRETE_POWDER);
            stairs(Unordinary_BasicsBlocks.DIRT_PATH_STAIRS,modLoc("block/dirt_path_side"),mcLoc("block/dirt_path_top"));
            
        }
        
        private void modSlabs() {
            slab(Unordinary_BasicsBlocks.ANDESITE_BRICK_SLAB, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
            slab(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
            slab(Unordinary_BasicsBlocks.CALCITE_BRICK_SLAB, Unordinary_BasicsBlocks.CALCITE_BRICKS);
            slab(Unordinary_BasicsBlocks.DIORITE_BRICK_SLAB, Unordinary_BasicsBlocks.DIORITE_BRICKS);
            slab(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
            slab(Unordinary_BasicsBlocks.DRIPSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
            slab(Unordinary_BasicsBlocks.GRANITE_BRICK_SLAB, Unordinary_BasicsBlocks.GRANITE_BRICKS);
            slab(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
            slab(Unordinary_BasicsBlocks.TUFF_BRICK_SLAB, Unordinary_BasicsBlocks.TUFF_BRICKS);
            slab(Unordinary_BasicsBlocks.POLISHED_TUFF_SLAB, Unordinary_BasicsBlocks.POLISHED_TUFF);
            slab(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
            slab(Unordinary_BasicsBlocks.SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.SOUL_SANDSTONE);
            slab(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
            slab(Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);
            slab(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
            slab(Unordinary_BasicsBlocks.SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
            slab(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
            slab(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
            
        slab(Unordinary_BasicsBlocks.CALCITE_SLAB, Blocks.CALCITE);
        slab(Unordinary_BasicsBlocks.TUFF_SLAB, Blocks.TUFF);
        slab(Unordinary_BasicsBlocks.DRIPSTONE_SLAB, Blocks.DRIPSTONE_BLOCK);
        slab(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Blocks.GRASS_BLOCK, mcLoc("block/grass_block_top"), mcLoc("block/dirt"), mcLoc("block/grass_block_side"), mcLoc("block/grass_block_side_overlay"));
        slab(Unordinary_BasicsBlocks.DIRT_SLAB, Blocks.DIRT);
        slab(Unordinary_BasicsBlocks.COARSE_DIRT_SLAB, Blocks.COARSE_DIRT);
        sidedSlab(Unordinary_BasicsBlocks.PODZOL_SLAB, Blocks.PODZOL, mcLoc("block/podzol_top"), mcLoc("block/dirt"), mcLoc("block/podzol_side"));
        slab(Unordinary_BasicsBlocks.ROOTED_DIRT_SLAB, Blocks.ROOTED_DIRT);
        sidedSlab(Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB, Blocks.CRIMSON_NYLIUM, mcLoc("block/crimson_nylium"), mcLoc("block/netherrack"), mcLoc("block/crimson_nylium_side"));
        sidedSlab(Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB, Blocks.WARPED_NYLIUM, mcLoc("block/warped_nylium"), mcLoc("block/netherrack"), mcLoc("block/warped_nylium_side"));
        slab(Unordinary_BasicsBlocks.SAND_SLAB, Blocks.SAND);
        slab(Unordinary_BasicsBlocks.RED_SAND_SLAB, Blocks.RED_SAND);
        slab(Unordinary_BasicsBlocks.GRAVEL_SLAB, Blocks.GRAVEL);
        slab(Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB, Blocks.RAW_IRON_BLOCK);
        slab(Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB, Blocks.RAW_COPPER_BLOCK);
        slab(Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB, Blocks.RAW_GOLD_BLOCK);
        slab(Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB, Blocks.AMETHYST_BLOCK);
        slab(Unordinary_BasicsBlocks.OAK_LOG_SLAB, Blocks.OAK_LOG, mcLoc("block/oak_log"), mcLoc("block/oak_log_top"));
        slab(Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB, Blocks.SPRUCE_LOG, mcLoc("block/spruce_log"), mcLoc("block/spruce_log_top"));
        slab(Unordinary_BasicsBlocks.BIRCH_LOG_SLAB, Blocks.BIRCH_LOG, mcLoc("block/birch_log"), mcLoc("block/birch_log_top"));
        slab(Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB, Blocks.JUNGLE_LOG, mcLoc("block/jungle_log"), mcLoc("block/jungle_log_top"));
        slab(Unordinary_BasicsBlocks.ACACIA_LOG_SLAB, Blocks.ACACIA_LOG, mcLoc("block/acacia_log"), mcLoc("block/acacia_log_top"));
        slab(Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB, Blocks.DARK_OAK_LOG, mcLoc("block/dark_oak_log"), mcLoc("block/dark_oak_log_top"));
        slab(Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB, Blocks.CRIMSON_STEM, mcLoc("block/crimson_stem"), mcLoc("block/crimson_stem_top"));
        slab(Unordinary_BasicsBlocks.WARPED_STEM_SLAB, Blocks.WARPED_STEM, mcLoc("block/warped_stem"), mcLoc("block/warped_stem_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB, Blocks.STRIPPED_OAK_LOG, mcLoc("block/stripped_oak_log"), mcLoc("block/stripped_oak_log_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB, Blocks.STRIPPED_SPRUCE_LOG, mcLoc("block/stripped_spruce_log"), mcLoc("block/stripped_spruce_log_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB, Blocks.STRIPPED_BIRCH_LOG, mcLoc("block/stripped_birch_log"), mcLoc("block/stripped_birch_log_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB, Blocks.STRIPPED_JUNGLE_LOG, mcLoc("block/stripped_jungle_log"), mcLoc("block/stripped_jungle_log_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB, Blocks.STRIPPED_ACACIA_LOG, mcLoc("block/stripped_acacia_log"), mcLoc("block/stripped_acacia_log_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Blocks.STRIPPED_DARK_OAK_LOG, mcLoc("block/stripped_dark_oak_log"), mcLoc("block/stripped_dark_oak_log_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB, Blocks.STRIPPED_CRIMSON_STEM, mcLoc("block/stripped_crimson_stem"), mcLoc("block/stripped_crimson_stem_top"));
        slab(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB, Blocks.STRIPPED_WARPED_STEM, mcLoc("block/stripped_warped_stem"), mcLoc("block/stripped_warped_stem_top"));
        slab(Unordinary_BasicsBlocks.GLASS_SLAB, Blocks.GLASS);
        slab(Unordinary_BasicsBlocks.TINTED_GLASS_SLAB, Blocks.TINTED_GLASS);
        slab(Unordinary_BasicsBlocks.OBSIDIAN_SLAB, Blocks.OBSIDIAN);
        slab(Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB, Blocks.PURPUR_PILLAR, mcLoc("block/purpur_pillar"), mcLoc("block/purpur_pillar_top"));
        slab(Unordinary_BasicsBlocks.ICE_SLAB, Blocks.ICE);
        slab(Unordinary_BasicsBlocks.SNOW_SLAB, Blocks.SNOW_BLOCK, mcLoc("block/snow"), mcLoc("block/snow"));
        slab(Unordinary_BasicsBlocks.CLAY_SLAB, Blocks.CLAY);
        slab(Unordinary_BasicsBlocks.PUMPKIN_SLAB, Blocks.PUMPKIN, mcLoc("block/pumpkin_side"), mcLoc("block/pumpkin_top"));
        slab(Unordinary_BasicsBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK);
        slab(Unordinary_BasicsBlocks.SOUL_SAND_SLAB, Blocks.SOUL_SAND);
        slab(Unordinary_BasicsBlocks.SOUL_SOIL_SLAB, Blocks.SOUL_SOIL);
        slab(Unordinary_BasicsBlocks.BASALT_SLAB, Blocks.BASALT, mcLoc("block/basalt_side"), mcLoc("block/basalt_top"));
        slab(Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB, Blocks.POLISHED_BASALT, mcLoc("block/polished_basalt_side"), mcLoc("block/polished_basalt_top"));
        slab(Unordinary_BasicsBlocks.GLOWSTONE_SLAB, Blocks.GLOWSTONE);
        slab(Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB, Blocks.CRACKED_STONE_BRICKS);
        slab(Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB, Blocks.CHISELED_STONE_BRICKS);
        slab(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Blocks.CRACKED_DEEPSLATE_BRICKS);
        slab(Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Blocks.CRACKED_DEEPSLATE_TILES);
        slab(Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB, Blocks.CHISELED_DEEPSLATE);
        slab(Unordinary_BasicsBlocks.MELON_SLAB, Blocks.MELON, mcLoc("block/melon_side"), mcLoc("block/melon_top"));
        sidedSlab(Unordinary_BasicsBlocks.MYCELIUM_SLAB, Blocks.MYCELIUM, mcLoc("block/mycelium_top"), mcLoc("block/dirt"), mcLoc("block/mycelium_side"));
        slab(Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB, Blocks.CRACKED_NETHER_BRICKS);
        slab(Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB, Blocks.CHISELED_NETHER_BRICKS);
        slab(Unordinary_BasicsBlocks.END_STONE_SLAB, Blocks.END_STONE);
        slab(Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Blocks.CHISELED_QUARTZ_BLOCK);
        slab(Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS);
        slab(Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB, Blocks.QUARTZ_PILLAR, mcLoc("block/quartz_pillar"), mcLoc("block/quartz_pillar_top"));
        slab(Unordinary_BasicsBlocks.HAY_BALE_SLAB, Blocks.HAY_BLOCK, mcLoc("block/hay_block_side"), mcLoc("block/hay_block_top"));
        slab(Unordinary_BasicsBlocks.TERRACOTTA_SLAB, Blocks.TERRACOTTA);
        slab(Unordinary_BasicsBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE);
        slab(Unordinary_BasicsBlocks.SEA_LANTERN_SLAB, Blocks.SEA_LANTERN);
        slab(Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB, Blocks.MAGMA_BLOCK, mcLoc("block/magma"), mcLoc("block/magma"));
        slab(Unordinary_BasicsBlocks.NETHER_WART_BLOCK_SLAB, Blocks.NETHER_WART_BLOCK);
        slab(Unordinary_BasicsBlocks.WARPED_WART_BLOCK_SLAB, Blocks.WARPED_WART_BLOCK);
        slab(Unordinary_BasicsBlocks.BONE_BLOCK_SLAB, Blocks.BONE_BLOCK, mcLoc("block/bone_block_side"), mcLoc("block/bone_block_top"));
        slab(Unordinary_BasicsBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE);
        slab(Unordinary_BasicsBlocks.DRIED_KELP_SLAB, Blocks.DRIED_KELP_BLOCK, mcLoc("block/dried_kelp_side"), mcLoc("block/dried_kelp_top"));
        slab(Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB, Blocks.CRYING_OBSIDIAN);
        slab(Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        sidedSlab(Unordinary_BasicsBlocks.DIRT_PATH_SLAB,Blocks.DIRT_PATH,mcLoc("block/dirt_path_top"),mcLoc("block/dirt"),modLoc("block/dirt_path_side"));

        slab(Unordinary_BasicsBlocks.WHITE_WOOL_SLAB, Blocks.WHITE_WOOL);
        slab(Unordinary_BasicsBlocks.ORANGE_WOOL_SLAB, Blocks.ORANGE_WOOL);
        slab(Unordinary_BasicsBlocks.MAGENTA_WOOL_SLAB, Blocks.MAGENTA_WOOL);
        slab(Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_SLAB, Blocks.LIGHT_BLUE_WOOL);
        slab(Unordinary_BasicsBlocks.YELLOW_WOOL_SLAB, Blocks.YELLOW_WOOL);
        slab(Unordinary_BasicsBlocks.LIME_WOOL_SLAB, Blocks.LIME_WOOL);
        slab(Unordinary_BasicsBlocks.PINK_WOOL_SLAB, Blocks.PINK_WOOL);
        slab(Unordinary_BasicsBlocks.GRAY_WOOL_SLAB, Blocks.GRAY_WOOL);
        slab(Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_SLAB, Blocks.LIGHT_GRAY_WOOL);
        slab(Unordinary_BasicsBlocks.CYAN_WOOL_SLAB, Blocks.CYAN_WOOL);
        slab(Unordinary_BasicsBlocks.PURPLE_WOOL_SLAB, Blocks.PURPLE_WOOL);
        slab(Unordinary_BasicsBlocks.BLUE_WOOL_SLAB, Blocks.BLUE_WOOL);
        slab(Unordinary_BasicsBlocks.BROWN_WOOL_SLAB, Blocks.BROWN_WOOL);
        slab(Unordinary_BasicsBlocks.GREEN_WOOL_SLAB, Blocks.GREEN_WOOL);
        slab(Unordinary_BasicsBlocks.RED_WOOL_SLAB, Blocks.RED_WOOL);
        slab(Unordinary_BasicsBlocks.BLACK_WOOL_SLAB, Blocks.BLACK_WOOL);
        slab(Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB, Blocks.WHITE_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB, Blocks.ORANGE_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB, Blocks.MAGENTA_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, Blocks.LIGHT_BLUE_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB, Blocks.YELLOW_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB, Blocks.LIME_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB, Blocks.PINK_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB, Blocks.GRAY_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Blocks.LIGHT_GRAY_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB, Blocks.CYAN_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB, Blocks.PURPLE_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB, Blocks.BLUE_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB, Blocks.BROWN_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB, Blocks.GREEN_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB, Blocks.RED_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB, Blocks.BLACK_TERRACOTTA);
        slab(Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_SLAB, Blocks.WHITE_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_SLAB, Blocks.ORANGE_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_SLAB, Blocks.MAGENTA_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, Blocks.LIGHT_BLUE_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_SLAB, Blocks.YELLOW_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.LIME_STAINED_GLASS_SLAB, Blocks.LIME_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.PINK_STAINED_GLASS_SLAB, Blocks.PINK_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_SLAB, Blocks.GRAY_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, Blocks.LIGHT_GRAY_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_SLAB, Blocks.CYAN_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_SLAB, Blocks.PURPLE_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_SLAB, Blocks.BLUE_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_SLAB, Blocks.BROWN_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_SLAB, Blocks.GREEN_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.RED_STAINED_GLASS_SLAB, Blocks.RED_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_SLAB, Blocks.BLACK_STAINED_GLASS);
        slab(Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB, Blocks.WHITE_CONCRETE);
        slab(Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB, Blocks.ORANGE_CONCRETE);
        slab(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB, Blocks.MAGENTA_CONCRETE);
        slab(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB, Blocks.LIGHT_BLUE_CONCRETE);
        slab(Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB, Blocks.YELLOW_CONCRETE);
        slab(Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB, Blocks.LIME_CONCRETE);
        slab(Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB, Blocks.PINK_CONCRETE);
        slab(Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB, Blocks.GRAY_CONCRETE);
        slab(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB, Blocks.LIGHT_GRAY_CONCRETE);
        slab(Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB, Blocks.CYAN_CONCRETE);
        slab(Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB, Blocks.PURPLE_CONCRETE);
        slab(Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB, Blocks.BLUE_CONCRETE);
        slab(Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB, Blocks.BROWN_CONCRETE);
        slab(Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB, Blocks.GREEN_CONCRETE);
        slab(Unordinary_BasicsBlocks.RED_CONCRETE_SLAB, Blocks.RED_CONCRETE);
        slab(Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB, Blocks.BLACK_CONCRETE);
        slab(Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_SLAB, Blocks.WHITE_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_SLAB, Blocks.ORANGE_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_SLAB, Blocks.MAGENTA_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_SLAB, Blocks.YELLOW_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_SLAB, Blocks.LIME_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_SLAB, Blocks.PINK_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_SLAB, Blocks.GRAY_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_SLAB, Blocks.CYAN_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_SLAB, Blocks.PURPLE_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_SLAB, Blocks.BLUE_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_SLAB, Blocks.BROWN_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_SLAB, Blocks.GREEN_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_SLAB, Blocks.RED_CONCRETE_POWDER);
        slab(Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_SLAB, Blocks.BLACK_CONCRETE_POWDER);
        }


        private void modWalls() {
        System.out.println("ModWalls");

        wallBlock((WallBlock) Unordinary_BasicsBlocks.ANDESITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.ANDESITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.CALCITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.CALCITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.DIORITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.DIORITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.DRIPSTONE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.DRIPSTONE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.GRANITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.GRANITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.TUFF_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.TUFF_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.SOUL_SANDSTONE_WALL, blockTexture(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_WALL, blockTexture(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_WALL, blockTexture(Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_WALL, blockTexture(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.SANDSTONE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.SANDSTONE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS));
        wallBlock((WallBlock) Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_WALL, blockTexture(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS));

        //TODO: Walls for vanilla blocks

        }

        private void modMisc() {

        nonRotatablePillar(Unordinary_BasicsBlocks.OAK_LOG, mcLoc("block/oak_log_top"), mcLoc("block/oak_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.BIRCH_LOG, mcLoc("block/birch_log_top"), mcLoc("block/birch_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.SPRUCE_LOG, mcLoc("block/spruce_log_top"), mcLoc("block/spruce_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.ACACIA_LOG, mcLoc("block/acacia_log_top"), mcLoc("block/acacia_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.DARK_OAK_LOG, mcLoc("block/dark_oak_log_top"), mcLoc("block/dark_oak_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.JUNGLE_LOG, mcLoc("block/jungle_log_top"), mcLoc("block/jungle_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.WARPED_STEM, mcLoc("block/warped_stem_top"), mcLoc("block/warped_stem"));
        nonRotatablePillar(Unordinary_BasicsBlocks.CRIMSON_STEM, mcLoc("block/crimson_stem_top"), mcLoc("block/crimson_stem"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_OAK_LOG, mcLoc("block/stripped_oak_log_top"), mcLoc("block/stripped_oak_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG, mcLoc("block/stripped_birch_log_top"), mcLoc("block/stripped_birch_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG, mcLoc("block/stripped_spruce_log_top"), mcLoc("block/stripped_spruce_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG, mcLoc("block/stripped_acacia_log_top"), mcLoc("block/stripped_acacia_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG, mcLoc("block/dark_oak_log_top"), mcLoc("block/dark_oak_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG, mcLoc("block/stripped_jungle_log_top"), mcLoc("block/stripped_jungle_log"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM, mcLoc("block/stripped_warped_stem_top"), mcLoc("block/stripped_warped_stem"));
        nonRotatablePillar(Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM, mcLoc("block/stripped_crimson_stem_top"), mcLoc("block/stripped_crimson_stem"));
        nonRotatablePillar(Unordinary_BasicsBlocks.PURPUR_PILLAR, mcLoc("block/purpur_pillar_top"), mcLoc("block/purpur_pillar"));
        nonRotatablePillar(Unordinary_BasicsBlocks.QUARTZ_PILLAR, mcLoc("block/quartz_pillar_top"), mcLoc("block/quartz_pillar"));
        nonRotatablePillar(Unordinary_BasicsBlocks.HAY_BLOCK, mcLoc("block/hay_block_top"), mcLoc("block/hay_block_side"));
    }

    protected void nonRotatableColumn(Block block, ResourceLocation textureTop, ResourceLocation textureSides, ResourceLocation textureBottom) {
        this.getVariantBuilder(block).forAllStates((blockState) -> {
            return ConfiguredModel.builder().modelFile(this.models().cubeBottomTop(this.name(block), textureSides, textureBottom, textureTop)).build();
        });
        ItemModelBuilder var10000 = (ItemModelBuilder)this.itemModels().getBuilder(((ResourceLocation) Objects.requireNonNull(this.rl(block))).getPath());
        String var10004 = this.name(block);
        var10000.parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + var10004)));
    }

}

