package com.manasmods.vanilla_plus.data;

import com.manasmods.vanilla_plus.Vanilla_Plus;
import com.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import com.github.manasmods.manascore.data.gen.BlockStateProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Vanilla_PlusBlockStateProvider extends BlockStateProvider {
    public Vanilla_PlusBlockStateProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Vanilla_Plus.MOD_ID);
    }

    @Override
    protected void generate() {

        //STAIRS

        stairs(Vanilla_PlusBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        stairs(Vanilla_PlusBlocks.TUFF_STAIRS, Blocks.TUFF);
        stairs(Vanilla_PlusBlocks.DRIPSTONE_STAIRS, Blocks.DRIPSTONE_BLOCK);
        stairs(Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS, Blocks.GRASS_BLOCK);
        stairs(Vanilla_PlusBlocks.DIRT_STAIRS, Blocks.DIRT);
        stairs(Vanilla_PlusBlocks.COARSE_DIRT_STAIRS, Blocks.COARSE_DIRT);
        stairs(Vanilla_PlusBlocks.PODZOL_STAIRS, Blocks.PODZOL);
        stairs(Vanilla_PlusBlocks.ROOTED_DIRT_STAIRS, Blocks.ROOTED_DIRT);
        stairs(Vanilla_PlusBlocks.CRIMSON_NYLIUM_STAIRS, Blocks.CRIMSON_NYLIUM);
        stairs(Vanilla_PlusBlocks.WARPED_NYLIUM_STAIRS, Blocks.WARPED_NYLIUM);
        stairs(Vanilla_PlusBlocks.SAND_STAIRS, Blocks.SAND);
        stairs(Vanilla_PlusBlocks.RED_SAND_STAIRS, Blocks.RED_SAND);
        stairs(Vanilla_PlusBlocks.GRAVEL_STAIRS, Blocks.GRAVEL);
        stairs(Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_STAIRS, Blocks.RAW_IRON_BLOCK);
        stairs(Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Blocks.RAW_COPPER_BLOCK);
        stairs(Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_STAIRS, Blocks.RAW_GOLD_BLOCK);
        stairs(Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_STAIRS, Blocks.AMETHYST_BLOCK);
        stairs(Vanilla_PlusBlocks.OAK_LOG_STAIRS, Blocks.OAK_LOG);
        stairs(Vanilla_PlusBlocks.SPRUCE_LOG_STAIRS, Blocks.SPRUCE_LOG);
        stairs(Vanilla_PlusBlocks.BIRCH_LOG_STAIRS, Blocks.BIRCH_LOG);
        stairs(Vanilla_PlusBlocks.JUNGLE_LOG_STAIRS, Blocks.JUNGLE_LOG);
        stairs(Vanilla_PlusBlocks.ACACIA_LOG_STAIRS, Blocks.ACACIA_LOG);
        stairs(Vanilla_PlusBlocks.DARK_OAK_LOG_STAIRS, Blocks.DARK_OAK_LOG);
        stairs(Vanilla_PlusBlocks.CRIMSON_STEM_STAIRS, Blocks.CRIMSON_STEM);
        stairs(Vanilla_PlusBlocks.WARPED_STEM_STAIRS, Blocks.WARPED_STEM);
        stairs(Vanilla_PlusBlocks.STRIPPED_OAK_LOG_STAIRS, Blocks.STRIPPED_OAK_LOG);
        stairs(Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Blocks.STRIPPED_SPRUCE_LOG);
        stairs(Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_STAIRS, Blocks.STRIPPED_BIRCH_LOG);
        stairs(Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Blocks.STRIPPED_JUNGLE_LOG);
        stairs(Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_STAIRS, Blocks.STRIPPED_ACACIA_LOG);
        stairs(Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, Blocks.STRIPPED_DARK_OAK_LOG);
        stairs(Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_STAIRS, Blocks.STRIPPED_CRIMSON_STEM);
        stairs(Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_STAIRS, Blocks.STRIPPED_WARPED_STEM);
        stairs(Vanilla_PlusBlocks.GLASS_STAIRS, Blocks.GLASS);
        stairs(Vanilla_PlusBlocks.TINTED_GLASS_STAIRS, Blocks.TINTED_GLASS);
        stairs(Vanilla_PlusBlocks.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE);
        stairs(Vanilla_PlusBlocks.OBSIDIAN_STAIRS, Blocks.OBSIDIAN);
        stairs(Vanilla_PlusBlocks.PURPUR_PILLAR_STAIRS, Blocks.PURPUR_PILLAR);
        stairs(Vanilla_PlusBlocks.ICE_STAIRS, Blocks.ICE);
        stairs(Vanilla_PlusBlocks.SNOW_STAIRS, Blocks.SNOW_BLOCK);
        stairs(Vanilla_PlusBlocks.CLAY_STAIRS, Blocks.CLAY);
        stairs(Vanilla_PlusBlocks.PUMPKIN_STAIRS, Blocks.PUMPKIN);
        stairs(Vanilla_PlusBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK);
        stairs(Vanilla_PlusBlocks.SOUL_SAND_STAIRS, Blocks.SOUL_SAND);
        stairs(Vanilla_PlusBlocks.SOUL_SOIL_STAIRS, Blocks.SOUL_SOIL);
        stairs(Vanilla_PlusBlocks.BASALT_STAIRS, Blocks.BASALT);
        stairs(Vanilla_PlusBlocks.POLISHED_BASALT_STAIRS, Blocks.POLISHED_BASALT);
        stairs(Vanilla_PlusBlocks.GLOWSTONE_STAIRS, Blocks.GLOWSTONE);
        stairs(Vanilla_PlusBlocks.CRACKED_STONE_BRICK_STAIRS, Blocks.CRACKED_STONE_BRICKS);
        stairs(Vanilla_PlusBlocks.CHISELED_STONE_BRICK_STAIRS, Blocks.CHISELED_STONE_BRICKS);
        stairs(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        stairs(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Blocks.CRACKED_DEEPSLATE_TILES);
        stairs(Vanilla_PlusBlocks.CHISELED_DEEPSLATE_STAIRS, Blocks.CHISELED_DEEPSLATE);
        stairs(Vanilla_PlusBlocks.MELON_STAIRS, Blocks.MELON);
        stairs(Vanilla_PlusBlocks.MYCELIUM_STAIRS, Blocks.MYCELIUM);
        stairs(Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_STAIRS, Blocks.CRACKED_NETHER_BRICKS);
        stairs(Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_STAIRS, Blocks.CHISELED_NETHER_BRICKS);
        stairs(Vanilla_PlusBlocks.END_STONE_STAIRS, Blocks.END_STONE);
        stairs(Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Blocks.CHISELED_QUARTZ_BLOCK);
        stairs(Vanilla_PlusBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        stairs(Vanilla_PlusBlocks.QUARTZ_PILLAR_STAIRS, Blocks.QUARTZ_PILLAR);
        stairs(Vanilla_PlusBlocks.HAY_BALE_STAIRS, Blocks.HAY_BLOCK);
        stairs(Vanilla_PlusBlocks.TERRACOTTA_STAIRS, Blocks.TERRACOTTA);
        stairs(Vanilla_PlusBlocks.PACKED_ICE_STAIRS, Blocks.PACKED_ICE);
        stairs(Vanilla_PlusBlocks.SEA_LANTERN_STAIRS, Blocks.SEA_LANTERN);
        stairs(Vanilla_PlusBlocks.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE);
        stairs(Vanilla_PlusBlocks.MAGMA_BLOCK_STAIRS, Blocks.MAGMA_BLOCK);
        stairs(Vanilla_PlusBlocks.NETHER_WART_BLOCK_STAIRS, Blocks.NETHER_WART_BLOCK);
        stairs(Vanilla_PlusBlocks.WARPED_WART_BLOCK_STAIRS, Blocks.WARPED_WART_BLOCK);
        stairs(Vanilla_PlusBlocks.BONE_BLOCK_STAIRS, Blocks.BONE_BLOCK);
        stairs(Vanilla_PlusBlocks.BLUE_ICE_STAIRS, Blocks.BLUE_ICE);
        stairs(Vanilla_PlusBlocks.DRIED_KELP_STAIRS, Blocks.DRIED_KELP_BLOCK);
        stairs(Vanilla_PlusBlocks.CRYING_OBSIDIAN_STAIRS, Blocks.CRYING_OBSIDIAN);
        stairs(Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);


        //SLABS

        slab(Vanilla_PlusBlocks.CALCITE_SLAB, Blocks.CALCITE);
        slab(Vanilla_PlusBlocks.TUFF_SLAB, Blocks.TUFF);
        slab(Vanilla_PlusBlocks.DRIPSTONE_SLAB, Blocks.DRIPSTONE_BLOCK);
        slab(Vanilla_PlusBlocks.GRASS_BLOCK_SLAB, Blocks.GRASS_BLOCK);
        slab(Vanilla_PlusBlocks.DIRT_SLAB, Blocks.DIRT);
        slab(Vanilla_PlusBlocks.COARSE_DIRT_SLAB, Blocks.COARSE_DIRT);
        slab(Vanilla_PlusBlocks.PODZOL_SLAB, Blocks.PODZOL);
        slab(Vanilla_PlusBlocks.ROOTED_DIRT_SLAB, Blocks.ROOTED_DIRT);
        slab(Vanilla_PlusBlocks.CRIMSON_NYLIUM_SLAB, Blocks.CRIMSON_NYLIUM);
        slab(Vanilla_PlusBlocks.WARPED_NYLIUM_SLAB, Blocks.WARPED_NYLIUM);
        slab(Vanilla_PlusBlocks.SAND_SLAB, Blocks.SAND);
        slab(Vanilla_PlusBlocks.RED_SAND_SLAB, Blocks.RED_SAND);
        slab(Vanilla_PlusBlocks.GRAVEL_SLAB, Blocks.GRAVEL);
        slab(Vanilla_PlusBlocks.BLOCK_OF_RAW_IRON_SLAB, Blocks.RAW_IRON_BLOCK);
        slab(Vanilla_PlusBlocks.BLOCK_OF_RAW_COPPER_SLAB, Blocks.RAW_COPPER_BLOCK);
        slab(Vanilla_PlusBlocks.BLOCK_OF_RAW_GOLD_SLAB, Blocks.RAW_GOLD_BLOCK);
        slab(Vanilla_PlusBlocks.BLOCK_OF_AMETHYST_SLAB, Blocks.AMETHYST_BLOCK);
        slab(Vanilla_PlusBlocks.OAK_LOG_SLAB, Blocks.OAK_LOG);
        slab(Vanilla_PlusBlocks.SPRUCE_LOG_SLAB, Blocks.SPRUCE_LOG);
        slab(Vanilla_PlusBlocks.BIRCH_LOG_SLAB, Blocks.BIRCH_LOG);
        slab(Vanilla_PlusBlocks.JUNGLE_LOG_SLAB, Blocks.JUNGLE_LOG);
        slab(Vanilla_PlusBlocks.ACACIA_LOG_SLAB, Blocks.ACACIA_LOG);
        slab(Vanilla_PlusBlocks.DARK_OAK_LOG_SLAB, Blocks.DARK_OAK_LOG);
        slab(Vanilla_PlusBlocks.CRIMSON_STEM_SLAB, Blocks.CRIMSON_STEM);
        slab(Vanilla_PlusBlocks.WARPED_STEM_SLAB, Blocks.WARPED_STEM);
        slab(Vanilla_PlusBlocks.STRIPPED_OAK_LOG_SLAB, Blocks.STRIPPED_OAK_LOG);
        slab(Vanilla_PlusBlocks.STRIPPED_SPRUCE_LOG_SLAB, Blocks.STRIPPED_SPRUCE_LOG);
        slab(Vanilla_PlusBlocks.STRIPPED_BIRCH_LOG_SLAB, Blocks.STRIPPED_BIRCH_LOG);
        slab(Vanilla_PlusBlocks.STRIPPED_JUNGLE_LOG_SLAB, Blocks.STRIPPED_JUNGLE_LOG);
        slab(Vanilla_PlusBlocks.STRIPPED_ACACIA_LOG_SLAB, Blocks.STRIPPED_ACACIA_LOG);
        slab(Vanilla_PlusBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Blocks.STRIPPED_DARK_OAK_LOG);
        slab(Vanilla_PlusBlocks.STRIPPED_CRIMSON_STEM_SLAB, Blocks.STRIPPED_CRIMSON_STEM);
        slab(Vanilla_PlusBlocks.STRIPPED_WARPED_STEM_SLAB, Blocks.STRIPPED_WARPED_STEM);
        slab(Vanilla_PlusBlocks.GLASS_SLAB, Blocks.GLASS);
        slab(Vanilla_PlusBlocks.TINTED_GLASS_SLAB, Blocks.TINTED_GLASS);
        slab(Vanilla_PlusBlocks.OBSIDIAN_SLAB, Blocks.OBSIDIAN);
        slab(Vanilla_PlusBlocks.PURPUR_PILLAR_SLAB, Blocks.PURPUR_PILLAR);
        slab(Vanilla_PlusBlocks.ICE_SLAB, Blocks.ICE);
        slab(Vanilla_PlusBlocks.SNOW_SLAB, Blocks.SNOW_BLOCK);
        slab(Vanilla_PlusBlocks.CLAY_SLAB, Blocks.CLAY);
        slab(Vanilla_PlusBlocks.PUMPKIN_SLAB, Blocks.PUMPKIN);
        slab(Vanilla_PlusBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK);
        slab(Vanilla_PlusBlocks.SOUL_SAND_SLAB, Blocks.SOUL_SAND);
        slab(Vanilla_PlusBlocks.SOUL_SOIL_SLAB, Blocks.SOUL_SOIL);
        slab(Vanilla_PlusBlocks.BASALT_SLAB, Blocks.BASALT);
        slab(Vanilla_PlusBlocks.POLISHED_BASALT_SLAB, Blocks.POLISHED_BASALT);
        slab(Vanilla_PlusBlocks.GLOWSTONE_SLAB, Blocks.GLOWSTONE);
        slab(Vanilla_PlusBlocks.CRACKED_STONE_BRICK_SLAB, Blocks.CRACKED_STONE_BRICKS);
        slab(Vanilla_PlusBlocks.CHISELED_STONE_BRICK_SLAB, Blocks.CHISELED_STONE_BRICKS);
        slab(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Blocks.CRACKED_DEEPSLATE_BRICKS);
        slab(Vanilla_PlusBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Blocks.CRACKED_DEEPSLATE_TILES);
        slab(Vanilla_PlusBlocks.CHISELED_DEEPSLATE_SLAB, Blocks.CHISELED_DEEPSLATE);
        slab(Vanilla_PlusBlocks.MELON_SLAB, Blocks.MELON);
        slab(Vanilla_PlusBlocks.MYCELIUM_SLAB, Blocks.MYCELIUM);
        slab(Vanilla_PlusBlocks.CRACKED_NETHER_BRICK_SLAB, Blocks.CRACKED_NETHER_BRICKS);
        slab(Vanilla_PlusBlocks.CHISELED_NETHER_BRICK_SLAB, Blocks.CHISELED_NETHER_BRICKS);
        slab(Vanilla_PlusBlocks.END_STONE_SLAB, Blocks.END_STONE);
        slab(Vanilla_PlusBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Blocks.CHISELED_QUARTZ_BLOCK);
        slab(Vanilla_PlusBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS);
        slab(Vanilla_PlusBlocks.QUARTZ_PILLAR_SLAB, Blocks.QUARTZ_PILLAR);
        slab(Vanilla_PlusBlocks.HAY_BALE_SLAB, Blocks.HAY_BLOCK);
        slab(Vanilla_PlusBlocks.TERRACOTTA_SLAB, Blocks.TERRACOTTA);
        slab(Vanilla_PlusBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE);
        slab(Vanilla_PlusBlocks.SEA_LANTERN_SLAB, Blocks.SEA_LANTERN);
        slab(Vanilla_PlusBlocks.MAGMA_BLOCK_SLAB, Blocks.MAGMA_BLOCK);
        slab(Vanilla_PlusBlocks.NETHER_WART_BLOCK_SLAB, Blocks.NETHER_WART_BLOCK);
        slab(Vanilla_PlusBlocks.WARPED_WART_BLOCK_SLAB, Blocks.WARPED_WART_BLOCK);
        slab(Vanilla_PlusBlocks.BONE_BLOCK_SLAB, Blocks.BONE_BLOCK);
        slab(Vanilla_PlusBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE);
        slab(Vanilla_PlusBlocks.DRIED_KELP_SLAB, Blocks.DRIED_KELP_BLOCK);
        slab(Vanilla_PlusBlocks.CRYING_OBSIDIAN_SLAB, Blocks.CRYING_OBSIDIAN);
        slab(Vanilla_PlusBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        
    }
}
