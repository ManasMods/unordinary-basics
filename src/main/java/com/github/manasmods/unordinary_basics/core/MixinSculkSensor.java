package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkSensorBlock.class)
public class MixinSculkSensor {

    @Inject(method = "stepOn", at = @At("RETURN"), cancellable = true)
    public void canTargetNewWarden(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity, CallbackInfo ci) {
        if (pEntity.getType() == UBEntityTypes.MASTER_SWORD_WARDEN.get()) {
            ci.cancel();
        }
    }
}
