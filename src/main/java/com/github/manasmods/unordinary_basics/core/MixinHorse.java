package com.github.manasmods.unordinary_basics.core;

import net.minecraft.world.entity.animal.horse.Horse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Horse.class)
public class MixinHorse {
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/Horse;isVehicle()Z"))
    public boolean onMobInteract(Horse instance) {
        return false;
    }
}
