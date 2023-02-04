package com.github.manasmods.unordinary_basics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

import java.util.Random;
import java.util.function.Supplier;

public class DirtWiltableStairBlock extends PathableStairBlock {

    public final Supplier<Block> dirtVariant;

    public DirtWiltableStairBlock(Supplier<BlockState> pBlockType,Properties pProperties, Supplier<Block> pDirtVariant,Supplier<Block> pPathVariant) {
        super(pBlockType,pProperties,pPathVariant);

        this.dirtVariant = pDirtVariant;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pRandom.nextBoolean()) return;
        if (!pLevel.getBlockState(pPos.above()).getCollisionShape(pLevel,pPos.above()).isEmpty()){

            BlockState currentState = pState;

            pLevel.setBlockAndUpdate(pPos,this.dirtVariant.get().defaultBlockState()
                    .setValue(StairBlock.WATERLOGGED,currentState.getValue(StairBlock.WATERLOGGED)).setValue(StairBlock.FACING,currentState.getValue(StairBlock.FACING))
                    .setValue(StairBlock.SHAPE,currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.HALF,currentState.getValue(StairBlock.HALF)));
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
