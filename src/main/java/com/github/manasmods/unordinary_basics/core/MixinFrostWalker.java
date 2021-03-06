package com.github.manasmods.unordinary_basics.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
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
            tryConvertBlock(level, targetPos);
            if (enchantmentLevel == 2) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    tryConvertBlock(level, targetPos.relative(direction));
                }
            }
        }
    }

    private static void tryConvertBlock(Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(Blocks.LAVA) && !level.getFluidState(pos).is(Fluids.FLOWING_LAVA)) {
            level.setBlock(pos, Blocks.BASALT.defaultBlockState(), 3);
        }
    }
}
