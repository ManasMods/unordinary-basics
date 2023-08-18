package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Horse.class)
public abstract class MixinHorse extends LivingEntity {

    protected MixinHorse(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/Horse;isVehicle()Z"))
    public boolean onMobInteract(Horse instance) {
        return false;
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> callback) {
        if (player.level.isClientSide && player.getItemInHand(hand).is(Unordinary_BasicsItems.EQUINE_TRACKER)) {
            Component nameComponent = this.getCustomName();
            String name = nameComponent == null ? "No name" : nameComponent.getString();
            double maxHealth = this.getAttribute(Attributes.MAX_HEALTH).getValue();
            double speed = this.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * 42.17d;
            double jumpStrength = this.getAttribute(Attributes.JUMP_STRENGTH).getValue();
            jumpStrength = 7.38 * jumpStrength - 2.13;
            player.sendSystemMessage(Component.literal("%s - %.0fhp - %.2fm/s - %.2f blocks".formatted(name, maxHealth, speed, jumpStrength)).withStyle(ChatFormatting.DARK_PURPLE));
            callback.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
