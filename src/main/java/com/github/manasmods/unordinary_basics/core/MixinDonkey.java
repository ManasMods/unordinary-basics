package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Donkey.class)
public abstract class MixinDonkey extends Animal {

    protected MixinDonkey(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        System.out.println("Called mixin method");
        if (player.level.isClientSide && player.getItemInHand(hand).is(Unordinary_BasicsItems.EQUINE_TRACKER)) {
            System.out.println("Client side and holding equine tracker");
            Component nameComponent = this.getCustomName();
            String name = nameComponent == null ? "No name" : nameComponent.getString();
            double maxHealth = this.getAttribute(Attributes.MAX_HEALTH).getValue();
            double speed = this.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * 42.17d;
            double jumpStrength = this.getAttribute(Attributes.JUMP_STRENGTH).getValue();
            jumpStrength = 7.38 * jumpStrength - 2.13;
            player.sendSystemMessage(Component.literal("%s - %.0fhp - %.2fm/s - %.2f blocks".formatted(name, maxHealth, speed, jumpStrength)));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
