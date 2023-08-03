package com.github.manasmods.unordinary_basics.block;

import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Supplier;

public class GrassSproutableStairBlock extends PathableStairBlock {

    public final Supplier<Block> blockTypeGrass;
    public final Supplier<Block> blockTypeMycelium;
    public final Supplier<Block> blockTypePodzol;

    public GrassSproutableStairBlock(Supplier<BlockState> pBlockType,Properties pProperties, Supplier<Block> pBlockTypeMycelium, Supplier<Block> pBlockTypeGrass, Supplier<Block> pBlockTypePodzol, Supplier<Block> pBlockTypePath) {
        super(pBlockType,pProperties,pBlockTypePath);

        this.blockTypeMycelium = pBlockTypeMycelium;
        this.blockTypeGrass = pBlockTypeGrass;
        this.blockTypePodzol = pBlockTypePodzol;
    }

    private void trySprout(Level pLevel, BlockPos pPos){

        if (!pLevel.getBlockState(pPos.above()).getCollisionShape(pLevel,pPos.above()).isEmpty()) return;

        boolean grassExists = false;
        boolean myceliumExists = false;
        boolean podzolExists = false;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .is(UBTags.Blocks.GRASS_BLOCKS)){
                        grassExists = true;
                    } else if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .is(UBTags.Blocks.MYCELIUM_BLOCKS)){
                        myceliumExists = true;
                        break;
                    } else if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .is(UBTags.Blocks.PODZOL_BLOCKS)){
                        podzolExists = true;
                    }
                }
            }
        }

        BlockState currentState = pLevel.getBlockState(pPos);

        if (myceliumExists){
            pLevel.setBlockAndUpdate(pPos,this.blockTypeMycelium.get().defaultBlockState()
                    .setValue(StairBlock.WATERLOGGED,currentState.getValue(StairBlock.WATERLOGGED)).setValue(StairBlock.FACING,currentState.getValue(StairBlock.FACING))
                    .setValue(StairBlock.SHAPE,currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.HALF,currentState.getValue(StairBlock.HALF)));
        } else if (podzolExists) {
            pLevel.setBlockAndUpdate(pPos,this.blockTypePodzol.get().defaultBlockState()
                    .setValue(StairBlock.WATERLOGGED,currentState.getValue(StairBlock.WATERLOGGED)).setValue(StairBlock.FACING,currentState.getValue(StairBlock.FACING))
                    .setValue(StairBlock.SHAPE,currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.HALF,currentState.getValue(StairBlock.HALF)));
        } else if (grassExists) {
            pLevel.setBlockAndUpdate(pPos,this.blockTypeGrass.get().defaultBlockState()
                    .setValue(StairBlock.WATERLOGGED,currentState.getValue(StairBlock.WATERLOGGED)).setValue(StairBlock.FACING,currentState.getValue(StairBlock.FACING))
                    .setValue(StairBlock.SHAPE,currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.HALF,currentState.getValue(StairBlock.HALF)));
        }

    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pRandom.nextBoolean()) {
            trySprout(pLevel, pPos);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
