package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(PotionItem.class)
public abstract class MixinPotionItem extends Item {
    public MixinPotionItem(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    public void finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, CallbackInfoReturnable<ItemStack> cir) {
        cir.cancel();
        Player player = pEntityLiving instanceof Player ? (Player)pEntityLiving : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, pStack);
        }

        if (!pLevel.isClientSide) {
            for(MobEffectInstance mobeffectinstance : PotionUtils.getMobEffects(pStack)) {
                if (mobeffectinstance.getEffect().isInstantenous()) {
                    mobeffectinstance.getEffect().applyInstantenousEffect(player, player, pEntityLiving, mobeffectinstance.getAmplifier(), 1.0D);
                } else {
                    pEntityLiving.addEffect(new MobEffectInstance(mobeffectinstance));
                }
            }
        }

        AtomicBoolean flag1 = new AtomicBoolean(false);

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
                   if (handler instanceof UBInventoryItemStackHandler stackHandler){
                       if (stackHandler.isItemEquipped(Unordinary_BasicsItems.POTION_BELT)){
                           stackHandler.findFirstInstanceOf(Unordinary_BasicsItems.POTION_BELT).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                               for (int i = 0; i < itemHandler.getSlots(); i++) {
                                   if (itemHandler.getStackInSlot(i).isEmpty() || itemHandler.getStackInSlot(i).is(UBTags.Items.POTION_BELT_ITEMS) || itemHandler.getStackInSlot(i).getItem() instanceof ThrowablePotionItem) continue;
                                   player.getInventory().setItem(player.getInventory().selected,itemHandler.getStackInSlot(i));
                                   itemHandler.extractItem(i,1,false);
                                   itemHandler.insertItem(i,new ItemStack(Items.GLASS_BOTTLE),false);
                                   flag1.set(true);
                                   break;
                               }
                               pStack.shrink(1);
                           });
                       } else {
                           pStack.shrink(1);
                       }
                   }
                });
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (pStack.isEmpty()) {
                cir.setReturnValue(new ItemStack(Items.GLASS_BOTTLE));
            }

            if (player != null && !flag1.get()) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        pLevel.gameEvent(pEntityLiving, GameEvent.DRINKING_FINISH, pEntityLiving.eyeBlockPosition());
        cir.setReturnValue(pStack);
    }

}
