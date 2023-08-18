package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.utils.LadderHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
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
        callback.setReturnValue(LadderHelper.canSurvive(level, pos, state.getValue(LadderBlock.FACING), null));
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // Attempt to place ladder above or below depending on the player's pitch
        ItemStack stackInHand = player.getItemInHand(hand);
        if (stackInHand.getItem() == Items.LADDER) {
            BlockPos abovePos = pos.above();
            BlockPos belowPos = pos.below();
            boolean canPlaceAbove = level.getBlockState(abovePos).is(Blocks.AIR) && LadderHelper.canSurvive(level, abovePos, state.getValue(LadderBlock.FACING), null);
            boolean canPlaceBelow = level.getBlockState(belowPos).is(Blocks.AIR) && LadderHelper.canSurvive(level, belowPos, state.getValue(LadderBlock.FACING), null);
            BlockPos toPlaceAt;
            if (canPlaceAbove && canPlaceBelow) {
                toPlaceAt = player.getViewXRot(0) < 0 ? abovePos : belowPos;
            } else if (canPlaceAbove || canPlaceBelow) {
                toPlaceAt = canPlaceAbove ? abovePos : belowPos;
            } else {
                return InteractionResult.PASS;
            }
            level.setBlock(toPlaceAt,
                Blocks.LADDER.defaultBlockState().setValue(LadderBlock.FACING, state.getValue(LadderBlock.FACING)).setValue(LadderBlock.WATERLOGGED, level.getFluidState(toPlaceAt).is(Fluids.WATER)),
                3);
            if (!player.isCreative()) {
                stackInHand.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
