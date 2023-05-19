package com.github.manasmods.unordinary_basics.core;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.awt.event.ContainerListener;

@Mixin(AbstractHorse.class)
public abstract class MixinAbstractHorse extends Animal implements ContainerListener, PlayerRideableJumping, Saddleable {
    protected MixinAbstractHorse(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    protected int gallopSoundCounter;

    @Shadow
    protected float playerJumpPendingScale;

    @Shadow
    private boolean allowStandSliding;

    @Override
    public boolean rideableUnderWater() {
        return true;
    }

    @Shadow
    public abstract boolean isStanding();

    @Shadow
    public abstract double getCustomJump();

    @Shadow
    public abstract void setIsJumping(boolean pJumping);

    @Shadow
    @Nullable
    public abstract Entity getControllingPassenger();

    @Shadow
    public abstract Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity);

    @Inject(method = "travel(Lnet/minecraft/world/phys/Vec3;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;getSpeed()F"))
    public void travel(Vec3 pTravelVector, CallbackInfo info) {
        LivingEntity livingentity = (LivingEntity) getControllingPassenger();
        float f1 = livingentity.zza;
        if (f1 <= 0.0F) {
            f1 *= 0.25F;
            this.gallopSoundCounter = 0;
        }

        if (this.onGround && this.playerJumpPendingScale == 0.0F && this.isStanding() && !this.allowStandSliding) {
            f1 = 0.0F;
        }

        // Dismount player if horse is now drowning
        if (this.isUnderWater()) {
            Vec3 location = this.getDismountLocationForPassenger(livingentity);
            livingentity.dismountTo(location.x, location.y, location.z);
            return;
        }

        // If the horse is in the water and we are moving forward or jumping then jump out of the water
        if (this.isInWater() && !this.onGround) {
            Vec3 vec3 = this.getDeltaMovement();

            if (this.playerJumpPendingScale > 0.0F) {
                double d0 = this.getCustomJump() * (double) this.playerJumpPendingScale * (double) this.getBlockJumpFactor();
                double d1 = d0 + this.getJumpBoostPower();

                this.setDeltaMovement(vec3.x, d1, vec3.z);
                this.setIsJumping(true);
                this.hasImpulse = true;
                net.minecraftforge.common.ForgeHooks.onLivingJump(this);
            }

            if (f1 > 0.0F) {
                float f2 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F));
                float f3 = Mth.cos(this.getYRot() * ((float) Math.PI / 180F));
                this.setDeltaMovement(this.getDeltaMovement().add(-0.4F * f2 * this.playerJumpPendingScale, 0.02D, 0.4F * f3 * this.playerJumpPendingScale));
            }

            this.playerJumpPendingScale = 0.0F;
        }
    }

    @Inject(method = "positionRider(Lnet/minecraft/world/entity/Entity;)V", at = @At(value = "JUMP", ordinal = 0))
    public void positionRider(Entity pPassenger, CallbackInfo info) {
        double d0 = this.getY() + this.getPassengersRidingOffset() + pPassenger.getMyRidingOffset();
        int i = this.getPassengers().indexOf(pPassenger);

        if (i > 0) {
            float f3 = Mth.sin(this.yBodyRot * ((float) Math.PI / 180F));
            float f = Mth.cos(this.yBodyRot * ((float) Math.PI / 180F));

            pPassenger.setPos(this.getX() + (double) (0.5 * f3), d0, this.getZ() - (double) (0.5 * f));
        }
    }

    @Override
    public boolean canAddPassenger(Entity other) {
        return this.getPassengers().size() < 2;
    }

}
