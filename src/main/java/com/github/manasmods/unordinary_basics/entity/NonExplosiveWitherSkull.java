package com.github.manasmods.unordinary_basics.entity;

import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class NonExplosiveWitherSkull extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<Boolean> DATA_DANGEROUS = SynchedEntityData.defineId(NonExplosiveWitherSkull.class, EntityDataSerializers.BOOLEAN);

    public NonExplosiveWitherSkull(EntityType<NonExplosiveWitherSkull> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NonExplosiveWitherSkull(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(UBEntityTypes.NON_EXPLOSIVE_WITHER_SKULL.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }


    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getInertia() {
        return this.isDangerous() ? 0.73F : super.getInertia();
    }

    /**
     * Returns {@code true} if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isOnFire() {
        return false;
    }

    /**
     * Explosion resistance of a block relative to this entity
     */
    public float getBlockExplosionResistance(Explosion pExplosion, BlockGetter pLevel, BlockPos pPos, BlockState pBlockState, FluidState pFluidState, float pExplosionPower) {
        return this.isDangerous() && pBlockState.canEntityDestroy(pLevel, pPos, this) ? Math.min(0.8F, pExplosionPower) : pExplosionPower;
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            boolean flag;
            if (entity1 instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity1;
                flag = entity.hurt(damageSource(this, livingentity), 8.0F);
                if (flag) {
                    if (entity.isAlive()) {
                        this.doEnchantDamageEffects(livingentity, entity);
                    } else {
                        livingentity.heal(5.0F);
                    }
                }
            } else {
                flag = entity.hurt(DamageSource.MAGIC, 5.0F);
            }

            if (flag && entity instanceof LivingEntity) {
                int i = 0;
                if (this.level.getDifficulty() == Difficulty.NORMAL) {
                    i = 10;
                } else if (this.level.getDifficulty() == Difficulty.HARD) {
                    i = 40;
                }

                if (i > 0) {
                    ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * i, 1), this.getEffectSource());
                }
            }

        }
    }

    /**
     *  Called when this EntityFireball hits a block or entity.
     **/
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner()) ? Explosion.BlockInteraction.NONE : Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 0.0F, false, explosion$blockinteraction);
            this.discard();
        }

    }

    /**
     * Returns {@code true} if other Entities should be prevented from moving through this Entity.
     */
    public boolean isPickable() {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_DANGEROUS, false);
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public boolean isDangerous() {
        return this.entityData.get(DATA_DANGEROUS);
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setDangerous(boolean pInvulnerable) {
        this.entityData.set(DATA_DANGEROUS, pInvulnerable);
    }

    protected boolean shouldBurn() {
        return false;
    }

    public DamageSource damageSource(NonExplosiveWitherSkull pWitherSkull, Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("witherSkull", pWitherSkull, pIndirectEntity)).setProjectile();
    }
}
