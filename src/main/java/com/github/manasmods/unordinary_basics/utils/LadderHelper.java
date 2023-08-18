package com.github.manasmods.unordinary_basics.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class LadderHelper {
    public static boolean canSurvive(BlockGetter level, BlockPos thisPos, Direction facing, @Nullable BlockPos alreadyBroken) {
        BlockPos relativePos = thisPos.relative(facing.getOpposite());
        BlockState relativeState = level.getBlockState(relativePos);
        if (relativeState.isFaceSturdy(level, thisPos, facing) && !relativePos.equals(alreadyBroken) && !relativeState.is(Blocks.LADDER)) {
            return true;
        }
        directionLoop:
        for (Direction direction : Direction.Plane.VERTICAL) {
            for (int offset = 0; offset < 7; offset++) {
                BlockPos pos = thisPos.relative(direction, offset);
                BlockState state = level.getBlockState(pos);
                if (state.is(Blocks.LADDER) && !pos.equals(alreadyBroken)) {
                    Direction offsetFacing = state.getValue(LadderBlock.FACING);
                    BlockPos.MutableBlockPos attachedPos = pos.mutable().move(offsetFacing.getOpposite());
                    BlockState attachedState = level.getBlockState(attachedPos);
                    if (attachedState.isFaceSturdy(level, pos, offsetFacing) && offsetFacing == facing && !attachedPos.equals(alreadyBroken) && !attachedState.is(Blocks.LADDER)) {
                        return true;
                    }
                } else if (offset != 0) {
                    continue directionLoop;
                }
            }
        }
        return false;
    }

    public static void onBreak(LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.is(Blocks.LADDER)) {
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);
            if (aboveState.is(Blocks.LADDER)) {
                if (!canSurvive(level, abovePos, aboveState.getValue(LadderBlock.FACING), pos)) {
                    level.destroyBlock(abovePos, true);
                    onBreak(level, abovePos, aboveState);
                }
            }
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.is(Blocks.LADDER)) {
                if (!canSurvive(level, belowPos, belowState.getValue(LadderBlock.FACING), pos)) {
                    level.destroyBlock(belowPos, true);
                    onBreak(level, belowPos, belowState);
                }
            }
        } else {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos adjacentPos = pos.relative(direction);
                BlockState adjacentState = level.getBlockState(adjacentPos);
                if (adjacentState.is(Blocks.LADDER)) {
                    if (adjacentState.getValue(LadderBlock.FACING) == direction && !canSurvive(level, adjacentPos, direction, pos)) {
                        level.destroyBlock(adjacentPos, true);
                        onBreak(level, adjacentPos, adjacentState);
                    }
                    updateAsChain(level, adjacentPos, Direction.DOWN, pos);
                    updateAsChain(level, adjacentPos, Direction.UP, pos);
                }
            }
        }
    }

    public static void updateAsChain(LevelAccessor level, BlockPos pos, Direction direction, BlockPos initialBreak) {
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.LADDER)) {
            if (!canSurvive(level, pos, state.getValue(LadderBlock.FACING), initialBreak)) {
                level.destroyBlock(pos, true);
            }
            updateAsChain(level, pos.relative(direction), direction, initialBreak);
        }
    }
}
