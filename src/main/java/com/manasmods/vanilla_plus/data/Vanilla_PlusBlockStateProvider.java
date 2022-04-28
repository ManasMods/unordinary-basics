package com.manasmods.vanilla_plus.data;

import com.manasmods.vanilla_plus.Vanilla_Plus;
import com.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Vanilla_PlusBlockStateProvider extends BlockStateProvider {
    public Vanilla_PlusBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Vanilla_Plus.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {


        //STAIRS

        stairsBlock((StairBlock) Vanilla_PlusBlocks.DIRT_STAIRS, Blocks.DIRT);


        //SLABS

        slabBlock((SlabBlock) Vanilla_PlusBlocks.DIRT_SLAB, Blocks.DIRT);

    }

        private void defaultBlock (Block block){
            getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(cubeAll(block)).build());
            itemModels().getBuilder(block.getRegistryName().getPath())
                    .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Vanilla_Plus.MOD_ID, "block/" + block.getRegistryName().getPath())));
        }

        /**
         * Generates blockstate, block and item model json file.
         *
         * @param stairBlock   the {@link StairBlock} Object
         * @param textureBlock the {@link Block} you want to use as texture
         */
        private void stairsBlock (Block stairBlock, Block textureBlock){
            if (!(stairBlock instanceof StairBlock block)) {
                throw new IllegalArgumentException(stairBlock.getRegistryName().toString() + " is not a instance of StairBlock.");
            } else {
                stairsBlock(block, new ResourceLocation(Vanilla_Plus.MOD_ID, "block/" + textureBlock.getRegistryName().getPath()));
                itemModels().getBuilder(block.getRegistryName().getPath())
                        .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Vanilla_Plus.MOD_ID, "block/" + block.getRegistryName().getPath())));
            }
        }

        private void slabBlock (Block slabBlock, Block textureBlock){
            if (!(slabBlock instanceof SlabBlock block)) {
                throw new IllegalArgumentException(slabBlock.getRegistryName().toString() + " is not a instance of StairBlock.");
            } else {
                slabBlock(block, new ResourceLocation(Vanilla_Plus.MOD_ID, "block/" + textureBlock.getRegistryName().getPath()),
                        new ResourceLocation(Vanilla_Plus.MOD_ID, "block/" + textureBlock.getRegistryName().getPath()));
                itemModels().getBuilder(block.getRegistryName().getPath())
                        .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Vanilla_Plus.MOD_ID, "block/" + block.getRegistryName().getPath())));
            }
        }
    }
