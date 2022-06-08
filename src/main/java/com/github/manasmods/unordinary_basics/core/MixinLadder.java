package com.github.manasmods.unordinary_basics.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LadderBlock.class)
public class MixinLadder extends Block {

    public MixinLadder(Properties properties) {
        super(properties);
    }

    @Inject(method = "canSurvive(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(true);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = defaultBlockState();
        BlockPos clickedPos = context.getClickedPos();
        BlockPos belowPos = clickedPos.below();
        Level level = context.getLevel();
        BlockState belowState = level.getBlockState(belowPos);
        if (belowState.is(Blocks.LADDER)) {
            int distance = getDistance(level, clickedPos);
            if (distance == 7) {
                return null;
            }
            return state
                    .setValue(LadderBlock.WATERLOGGED, false)
                    .setValue(LadderBlock.FACING, belowState.getValue(LadderBlock.FACING));
        } else {
            FluidState fluidState = level.getFluidState(clickedPos);
            for (Direction direction : context.getNearestLookingDirections()) {
                if (direction.getAxis().isHorizontal()) {
                    BlockPos attachedPos = clickedPos.relative(direction);
                    BlockState attachedState = level.getBlockState(attachedPos);
                    if (attachedState.isFaceSturdy(level, attachedPos, direction.getOpposite())) {
                        return state
                                .setValue(LadderBlock.FACING, direction.getOpposite())
                                .setValue(LadderBlock.WATERLOGGED, fluidState.getType() == Fluids.WATER);
                    }
                }
            }
            return null;
        }
    }

    private static int getDistance(BlockGetter level, BlockPos thisPos) {
        for (Direction direction : Direction.Plane.VERTICAL) {
            for (int offset = 1; offset <= 7; offset++) {
                BlockPos pos = thisPos.relative(direction, offset);
                BlockState state = level.getBlockState(pos);
                if (state.is(Blocks.LADDER)) {
                    Direction facing1 = state.getValue(LadderBlock.FACING);
                    BlockPos.MutableBlockPos attachedPos1 = pos.mutable().move(facing1.getOpposite());
                    BlockState attachedState1 = level.getBlockState(attachedPos1);
                    if (attachedState1.isFaceSturdy(level, pos, facing1)) {
                        return offset;
                    }
                }
            }
        }
        return 7;
    }
}
