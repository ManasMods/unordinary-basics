package com.github.manasmods.unordinary_basics.core;

import java.awt.event.ContainerListener;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@Mixin(AbstractHorse.class)
public abstract class MixinAbstractHorse extends Animal implements ContainerListener, PlayerRideableJumping, Saddleable
{

    protected MixinAbstractHorse(EntityType<? extends Animal> p_27557_, Level p_27558_)
    {
        super(p_27557_, p_27558_);
    }
    
    @Shadow
    protected int gallopSoundCounter;
    
    @Shadow
    protected float playerJumpPendingScale;
    
    @Shadow
    private boolean allowStandSliding;
    
    @Shadow
    private float standAnimO;
    
    @Override
    public boolean rideableUnderWater() {
        return true;
    }
    
    @Shadow
    public abstract boolean isStanding();
    
    @Shadow
    public abstract boolean isJumping();
    
    @Shadow
    public abstract double getCustomJump();
    
    @Shadow
    public abstract void setIsJumping(boolean pJumping);
    
    @Overwrite
    public void travel(Vec3 pTravelVector) {
        if (this.isAlive()) {
           if (this.isVehicle() && this.canBeControlledByRider() && this.isSaddled()) {
              LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();
              this.setYRot(livingentity.getYRot());
              this.yRotO = this.getYRot();
              this.setXRot(livingentity.getXRot() * 0.5F);
              this.setRot(this.getYRot(), this.getXRot());
              this.yBodyRot = this.getYRot();
              this.yHeadRot = this.yBodyRot;
              float f = livingentity.xxa * 0.5F;
              float f1 = livingentity.zza;
              if (f1 <= 0.0F) {
                 f1 *= 0.25F;
                 this.gallopSoundCounter = 0;
              }

              if (this.onGround && this.playerJumpPendingScale == 0.0F && this.isStanding() && !this.allowStandSliding) {
                 f = 0.0F;
                 f1 = 0.0F;
              }

              if (this.playerJumpPendingScale > 0.0F && !this.isJumping() && this.onGround) {
                 double d0 = this.getCustomJump() * (double)this.playerJumpPendingScale * (double)this.getBlockJumpFactor();
                 double d1 = d0 + this.getJumpBoostPower();
                 Vec3 vec3 = this.getDeltaMovement();
                 this.setDeltaMovement(vec3.x, d1, vec3.z);
                 this.setIsJumping(true);
                 this.hasImpulse = true;
                 net.minecraftforge.common.ForgeHooks.onLivingJump(this);
                 if (f1 > 0.0F) {
                    float f2 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F));
                    float f3 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
                    this.setDeltaMovement(this.getDeltaMovement().add((double)(-0.4F * f2 * this.playerJumpPendingScale), 0.0D, (double)(0.4F * f3 * this.playerJumpPendingScale)));
                 }

                 this.playerJumpPendingScale = 0.0F;
              } else if(this.isUnderWater() && this.playerJumpPendingScale > 0.0F) {
                  double d0 = this.getCustomJump() * (double)this.playerJumpPendingScale * (double)this.getBlockJumpFactor();
                  double d1 = d0 + this.getJumpBoostPower();
                  Vec3 vec3 = this.getDeltaMovement();
                  this.setDeltaMovement(vec3.x, d1, vec3.z);
                  this.setIsJumping(true);
                  this.hasImpulse = true;
                  net.minecraftforge.common.ForgeHooks.onLivingJump(this);
                  if (f1 > 0.0F) {
                     float f2 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F));
                     float f3 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
                     this.setDeltaMovement(this.getDeltaMovement().add((double)(-0.4F * f2 * this.playerJumpPendingScale), 0.0D, (double)(0.4F * f3 * this.playerJumpPendingScale)));
                  }
                  
                  this.playerJumpPendingScale = 0.0F;
              }

              this.flyingSpeed = this.getSpeed() * 0.1F;
              if (this.isControlledByLocalInstance()) {
                 this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                 super.travel(new Vec3((double)f, pTravelVector.y, (double)f1));
              } else if (livingentity instanceof Player) {
                 this.setDeltaMovement(Vec3.ZERO);
              }

              if (this.onGround) {
                 this.playerJumpPendingScale = 0.0F;
                 this.setIsJumping(false);
              }

              this.calculateEntityAnimation(this, false);
              this.tryCheckInsideBlocks();
           } else {
              this.flyingSpeed = 0.02F;
              super.travel(pTravelVector);
           }
        }
     }
    
    @Overwrite
    public void positionRider(Entity pPassenger) {
        super.positionRider(pPassenger);
        
        double d0 = this.getY() + this.getPassengersRidingOffset() + pPassenger.getMyRidingOffset();
        int i = this.getPassengers().indexOf(pPassenger);
        
        if(i > 0) {
            float f3 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
            float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
            
            pPassenger.setPos(this.getX()+ (double)(0.5 * f3), d0, this.getZ() - (double)(0.5 * f));
        }
        
        if (pPassenger instanceof Mob) {
           Mob mob = (Mob)pPassenger;
           this.yBodyRot = mob.yBodyRot;
        }

        if (this.standAnimO > 0.0F) {
            float f3 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
            float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
            float f1 = 0.7F * this.standAnimO;
            float f2 = 0.15F * this.standAnimO;
            pPassenger.setPos(this.getX()+ (double)(f1 * f3), this.getY() + this.getPassengersRidingOffset() + pPassenger.getMyRidingOffset() + (double)f2, this.getZ() - (double)(f1 * f));
            if (pPassenger instanceof LivingEntity) {
               ((LivingEntity)pPassenger).yBodyRot = this.yBodyRot;
            }
        }

     }
    
    @Override
    public boolean canAddPassenger(Entity other) {
        return this.getPassengers().size() < 2;
    }

}
