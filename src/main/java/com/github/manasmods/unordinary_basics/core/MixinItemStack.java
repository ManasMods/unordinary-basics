package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.utils.UBUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @Inject(method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", at = @At("HEAD"), cancellable = true)
    public void onUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> callback) {
        if (!player.isCrouching()) {
            ItemStack stack = player.getItemInHand(hand);
            Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(stack);
            if (stack.getItem().canBeDepleted() && player.experienceLevel >= 1 && enchantmentMap.containsKey(Enchantments.MENDING) && stack.getDamageValue() > 0) {
                int xp = UBUtils.getXpPointsForLevel(player.experienceLevel);
                int damageValue = stack.getDamageValue();
                int toTake = Math.min(xp * 2, damageValue);
                stack.setDamageValue(damageValue - toTake);
                player.experienceLevel--;
                callback.setReturnValue(InteractionResultHolder.consume(stack));
            }
        }
    }
}
