package com.github.manasmods.unordinary_basics.core;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CrossbowItem.class, priority = 1000)
public class MixinCrossbow {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void onCrossbowUse(Level world, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> info) {
        ItemStack crossbowStack = player.getItemInHand(hand);

        if (!world.isClientSide && CrossbowItem.isCharged(crossbowStack) && player.isCrouching()) {
            CompoundTag crossbowTag = crossbowStack.getOrCreateTag();
            ListTag chargedProjectiles = crossbowTag.getList("ChargedProjectiles", 10);

            if (!chargedProjectiles.isEmpty()) {
                CompoundTag projectileTag = chargedProjectiles.getCompound(0);
                ItemStack projectileStack = ItemStack.of(projectileTag);

                if (!player.getInventory().add(projectileStack)) {
                    player.drop(projectileStack, false);
                }

                chargedProjectiles.remove(0);
                crossbowTag.put("ChargedProjectiles", chargedProjectiles);
                if (chargedProjectiles.isEmpty()) {
                    crossbowTag.remove("Charged");
                }
                crossbowStack.setTag(crossbowTag);

                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);

                info.setReturnValue(InteractionResultHolder.success(crossbowStack));
                info.cancel();
            }
        }
    }
}


