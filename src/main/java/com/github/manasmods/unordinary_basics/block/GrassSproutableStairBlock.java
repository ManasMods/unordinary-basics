package com.github.manasmods.unordinary_basics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Supplier;

public class GrassSproutableStairBlock extends StairBlock {

    public final Block blockTypeGrass;
    public final Block blockTypeMycelium;
    public GrassSproutableStairBlock(Supplier<BlockState> pBlockState, Properties pProperties, Block pBlockTypeMycelium, Block pBlockTypeGrass) {
        super(pBlockState,pProperties);

        this.blockTypeMycelium = pBlockTypeMycelium;
        this.blockTypeGrass = pBlockTypeGrass;
    }

    private void trySprout(Level pLevel, BlockPos pPos){

        boolean grassExists = false;
        boolean myceliumExists = false;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .getBlock().equals(Blocks.GRASS) ||
                            pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .getBlock().equals(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB)){
                        grassExists = true;
                        break;
                    }
                }
            }
        }

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .getBlock().equals(Blocks.MYCELIUM) ||
                            pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                                    .getBlock().equals(Unordinary_BasicsBlocks.MYCELIUM_SLAB)){
                        myceliumExists = true;
                        break;
                    }
                }
            }
        }

        if (myceliumExists){
            pLevel.setBlockAndUpdate(pPos,this.blockTypeMycelium.defaultBlockState());
        } else if (grassExists) {
            pLevel.setBlockAndUpdate(pPos,this.blockTypeGrass.defaultBlockState());
        }

    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {

        //if (pLevel.getGameTime() % 20 == 0 && pRandom.nextInt(5) == 1){
            trySprout(pLevel,pPos);
        //}

    }
}
