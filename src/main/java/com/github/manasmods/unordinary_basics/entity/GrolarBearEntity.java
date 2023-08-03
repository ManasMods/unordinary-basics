package com.github.manasmods.unordinary_basics.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

@Deprecated(forRemoval = true)
public class GrolarBearEntity extends TamableAnimal implements NeutralMob {
    private static final EntityDataAccessor<Boolean> DATA_STANDING_ID = SynchedEntityData.defineId(GrolarBearEntity.class, EntityDataSerializers.BOOLEAN);
    private float clientSideStandAnimationO;
    private float clientSideStandAnimation;
    private int warningSoundTicks;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;
    public GrolarBearEntity(EntityType<? extends TamableAnimal> pType, Level pLevel) {
        super(pType, pLevel);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new GrolarBearEntity.grolarBearEntityMeleeAttackGoal());
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.addGoal(3, new GrolarBearEntity.grolarBearEntityPanicGoal());
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Fox.class, 10, true, true, (Predicate<LivingEntity>)null));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new GrolarBearEntity.grolarBearEntityHurtByTargetGoal());
        this.targetSelector.addGoal(2, new GrolarBearEntity.grolarBearEntityAttackPlayersGoal());
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D);
    }

    /*public static boolean checkGrolarBearEntitySpawnRules(EntityType<GrolarBearEntity> p_29550_, LevelAccessor p_29551_, MobSpawnType p_29552_, BlockPos p_29553_, Random p_29554_) {
        Holder<Biome> holder = p_29551_.getBiome(p_29553_);
        if (holder.is(Biomes.PLAINS) || holder.is(Biomes.FOREST) ||
                holder.is(Biomes.TAIGA) || holder.is(Biomes.STONY_PEAKS)){
            return checkAnimalSpawnRules(p_29550_, p_29551_, p_29552_, p_29553_, p_29554_);
        }
        return false;
    }*/

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level, pCompound);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    protected SoundEvent getAmbientSound() {
        return this.isBaby() ? SoundEvents.POLAR_BEAR_AMBIENT_BABY : SoundEvents.POLAR_BEAR_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.POLAR_BEAR_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.POLAR_BEAR_DEATH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.POLAR_BEAR_STEP, 0.15F, 1.0F);
    }

    protected void playWarningSound() {
        if (this.warningSoundTicks <= 0) {
            this.playSound(SoundEvents.POLAR_BEAR_WARNING, 1.0F, this.getVoicePitch());
            this.warningSoundTicks = 40;
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STANDING_ID, false);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.clientSideStandAnimation != this.clientSideStandAnimationO) {
                this.refreshDimensions();
            }
            this.clientSideStandAnimationO = this.clientSideStandAnimation;
            if (this.isStanding()) {
                this.clientSideStandAnimation = Mth.clamp(this.clientSideStandAnimation + 1.0F, 0.0F, 6.0F);
            } else {
                this.clientSideStandAnimation = Mth.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
            }
        }
        if (this.warningSoundTicks > 0) {
            --this.warningSoundTicks;
        }
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }
    }

    public EntityDimensions getDimensions(Pose pPose) {
        if (this.clientSideStandAnimation > 0.0F) {
            float f = this.clientSideStandAnimation / 6.0F;
            float f1 = 1.0F + f;
            return super.getDimensions(pPose).scale(1.0F, f1);
        } else {
            return super.getDimensions(pPose);
        }
    }

    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
        }
        return flag;
    }

    public boolean isStanding() {return this.entityData.get(DATA_STANDING_ID);}
    public void setStanding(boolean pStanding) {this.entityData.set(DATA_STANDING_ID, pStanding);}
    public float getStandingAnimationScale(float p_29570_) {
        return Mth.lerp(p_29570_, this.clientSideStandAnimationO, this.clientSideStandAnimation) / 6.0F;
    }
    protected float getWaterSlowDown() {
        return 0.8F;
    }
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (pSpawnData == null) {
            pSpawnData = new AgeableMobGroupData(1.0F);
        }
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {return stack.is(Items.SALMON);}
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(Items.SALMON) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.isCreative()) {
                        itemstack.shrink(1);
                    }
                    FoodProperties food = item.getFoodProperties();
                    if (food != null) {
                        this.heal((float) food.getNutrition());
                        return InteractionResult.SUCCESS;
                    }
                }
                InteractionResult actionresulttype = super.mobInteract(player, hand);
                if ((!actionresulttype.consumesAction()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    if (this.isOrderedToSit()) {
                        player.displayClientMessage(Component.translatable("unordinary_basics.message.pet.sit", this.getDisplayName()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN).withBold(true)), false);
                    } else {
                        player.displayClientMessage(Component.translatable("unordinary_basics.message.pet.follow", this.getDisplayName()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN).withBold(true)), false);
                    }
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                }
                return actionresulttype;
            } else if (itemstack.is(Items.SALMON)) {
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }
                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            }
            return super.mobInteract(player, hand);
        }
    }
    class grolarBearEntityAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
        public grolarBearEntityAttackPlayersGoal() {
            super(GrolarBearEntity.this, Player.class, 20, true, true, (Predicate<LivingEntity>)null);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (GrolarBearEntity.this.isBaby()) {
                return false;
            } else {
                if (super.canUse()) {
                    for(GrolarBearEntity GrolarBearEntity : GrolarBearEntity.this.level.getEntitiesOfClass(GrolarBearEntity.class, GrolarBearEntity.this.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
                        if (GrolarBearEntity.isBaby()) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        protected double getFollowDistance() {
            return super.getFollowDistance() * 0.5D;
        }
    }

    class grolarBearEntityHurtByTargetGoal extends HurtByTargetGoal {
        public grolarBearEntityHurtByTargetGoal() {
            super(GrolarBearEntity.this);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            super.start();
            if (GrolarBearEntity.this.isBaby()) {
                this.alertOthers();
                this.stop();
            }

        }

        protected void alertOther(Mob pMob, LivingEntity pTarget) {
            if (pMob instanceof GrolarBearEntity && !pMob.isBaby()) {
                super.alertOther(pMob, pTarget);
            }

        }
    }

    class grolarBearEntityMeleeAttackGoal extends MeleeAttackGoal {
        public grolarBearEntityMeleeAttackGoal() {
            super(GrolarBearEntity.this, 1.25D, true);
        }

        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            double d0 = this.getAttackReachSqr(pEnemy);
            if (pDistToEnemySqr <= d0 && this.isTimeToAttack()) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(pEnemy);
                GrolarBearEntity.this.setStanding(false);
            } else if (pDistToEnemySqr <= d0 * 2.0D) {
                if (this.isTimeToAttack()) {
                    GrolarBearEntity.this.setStanding(false);
                    this.resetAttackCooldown();
                }

                if (this.getTicksUntilNextAttack() <= 10) {
                    GrolarBearEntity.this.setStanding(true);
                    GrolarBearEntity.this.playWarningSound();
                }
            } else {
                this.resetAttackCooldown();
                GrolarBearEntity.this.setStanding(false);
            }

        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            GrolarBearEntity.this.setStanding(false);
            super.stop();
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double)(4.0F + pAttackTarget.getBbWidth());
        }
    }

    class grolarBearEntityPanicGoal extends PanicGoal {
        public grolarBearEntityPanicGoal() {
            super(GrolarBearEntity.this, 2.0D);
        }

        protected boolean shouldPanic() {
            return this.mob.getLastHurtByMob() != null && this.mob.isBaby() || this.mob.isOnFire();
        }
    }
}
