package com.github.manasmods.unordinary_basics.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FrostWalkerEnchantment.class)
public class MixinFrostWalker {

    @Inject(method = "onEntityMoved(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;I)V", at = @At("HEAD"))
    private static void onEntityMoved(LivingEntity livingEntity, Level level, BlockPos pos, int enchantmentLevel, CallbackInfo callback) {
        if (livingEntity.isOnGround()) {
            BlockPos targetPos = livingEntity.blockPosition().below();
            if (level.getBlockState(targetPos).is(Blocks.LAVA)) {
                level.setBlock(targetPos, Blocks.BASALT.defaultBlockState(), 3);
            }
            if (enchantmentLevel == 2) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos surroundingPos = targetPos.relative(direction);
                    if (level.getBlockState(surroundingPos).is(Blocks.LAVA)) {
                        level.setBlock(surroundingPos, Blocks.BASALT.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}
