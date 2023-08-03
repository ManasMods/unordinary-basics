package com.github.manasmods.unordinary_basics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class FallingSlabBlock extends SlabBlock implements Fallable {

    public FallingSlabBlock(Properties pProperties) {
        super(pProperties);
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        pLevel.scheduleTick(pPos, this, this.getDelayAfterPlace());
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        pLevel.scheduleTick(pCurrentPos, this, this.getDelayAfterPlace());
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand) {
        if (isFree(pLevel.getBlockState(pPos.below()),pPos,pLevel) && pPos.getY() >= pLevel.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(pLevel, pPos, pState);
            this.falling(fallingblockentity);
        }
    }

    protected void falling(FallingBlockEntity pEntity) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public boolean isFree(BlockState pState, BlockPos pPos, Level pLevel) {
        Material material = pState.getMaterial();

        return pState.isAir() || pState.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }
}
