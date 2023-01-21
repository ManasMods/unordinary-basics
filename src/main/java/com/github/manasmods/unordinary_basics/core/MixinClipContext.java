package com.github.manasmods.unordinary_basics.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClipContext.class)
public abstract class MixinClipContext {

    private static boolean handling = false;

    @Inject(method = "getBlockShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;", at = @At("HEAD"), cancellable = true)
    public void getBlockShape(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<VoxelShape> callback) {
        if (handling) return;
        handling = true;
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        if (state.getCollisionShape(level, pos).isEmpty()) {
            Vec3 eyePosition = player.getEyePosition();
            double range = Math.max(0, player.getAttackRange());
            Vec3 view = player.getViewVector(1.0F);
            Vec3 plusRange = eyePosition.add(view.x * range, view.y * range, view.z * range);
            AABB box = player.getBoundingBox().expandTowards(view.scale(range)).inflate(1.0D, 1.0D, 1.0D);
            double squaredDistance = range * range;
            if (ProjectileUtil.getEntityHitResult(player, eyePosition, plusRange, box, e -> true, squaredDistance) != null) {
                callback.setReturnValue(Shapes.empty());
            }
        }
        handling = false;
    }
}
