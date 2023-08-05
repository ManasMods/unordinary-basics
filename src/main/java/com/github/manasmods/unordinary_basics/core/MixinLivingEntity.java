package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    LivingEntity entity = (LivingEntity)(Object)this;

    public MixinLivingEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Shadow protected int fallFlyTicks;

    @Inject(method = "updateFallFlying",at = @At("HEAD"),cancellable = true)
    private void updateFallFlying(CallbackInfo ci) {
        ci.cancel();
        boolean flag = super.getSharedFlag(7);
        if (flag && !this.onGround && !this.isPassenger() && !entity.hasEffect(MobEffects.LEVITATION)) {
            ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.CHEST);
            AtomicReference<ItemStack> stackAtomicReference = new AtomicReference<>();
            if (!itemstack.is(Items.ELYTRA)){
                if (entity instanceof Player player){
                    player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
                        stackAtomicReference.set(handler.getStackInSlot(CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.BACK)));
                    });
                }
                if(stackAtomicReference.get().is(Items.ELYTRA)){
                    itemstack = stackAtomicReference.get();
                }
            }
            flag = itemstack.canElytraFly(entity) && itemstack.elytraFlightTick(entity, this.fallFlyTicks);
            if (false) //Forge: Moved to ElytraItem
                if (itemstack.is(Items.ELYTRA) && ElytraItem.isFlyEnabled(itemstack)) {
                    flag = true;
                    int i = this.fallFlyTicks + 1;
                    if (!this.level.isClientSide && i % 10 == 0) {
                        int j = i / 10;
                        if (j % 2 == 0) {
                            itemstack.hurtAndBreak(1, entity, (p_147232_) -> {
                                p_147232_.broadcastBreakEvent(EquipmentSlot.CHEST);
                            });
                        }

                        this.gameEvent(GameEvent.ELYTRA_GLIDE);
                    }
                } else {
                    flag = false;
                }
        } else {
            flag = false;
        }

        if (!this.level.isClientSide) {
            this.setSharedFlag(7, flag);
        }
    }
}
