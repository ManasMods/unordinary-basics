package com.github.manasmods.unordinary_basics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

import java.util.Random;
import java.util.function.Supplier;

public class DirtWiltableSlabBlock extends PathableSlabBlock {

    public final Supplier<Block> dirtVariant;

    public DirtWiltableSlabBlock(Properties pProperties, Supplier<Block> pDirtVariant,Supplier<Block> pPathVariant) {
        super(pProperties,pPathVariant);

        this.dirtVariant = pDirtVariant;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextBoolean()) return;
        if (!pLevel.getBlockState(pPos.above()).getCollisionShape(pLevel,pPos.above()).isEmpty() && !pState.getValue(SlabBlock.TYPE).equals(SlabType.BOTTOM)){

            BlockState currentState = pState;

            pLevel.setBlockAndUpdate(pPos,this.dirtVariant.get().defaultBlockState()
                    .setValue(SlabBlock.WATERLOGGED,currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE,currentState.getValue(SlabBlock.TYPE)));
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
