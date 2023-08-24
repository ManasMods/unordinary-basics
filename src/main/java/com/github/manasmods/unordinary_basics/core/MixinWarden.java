package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public class MixinWarden {

    @Inject(method = "canTargetEntity", at = @At("RETURN"), cancellable = true)
    public void canTargetNewWarden(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType() == UBEntityTypes.MASTER_SWORD_WARDEN.get()) {
            cir.setReturnValue(false);
        }
    }
}
