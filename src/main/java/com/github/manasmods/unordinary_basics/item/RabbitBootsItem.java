package com.github.manasmods.unordinary_basics.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RabbitBootsItem extends ArmorItem {

    private static final MobEffectInstance SPEED = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0);
    private static final MobEffectInstance JUMP_BOOST = new MobEffectInstance(MobEffects.JUMP, 200, 1);

    public RabbitBootsItem(Properties properties) {
        super(UBArmorMaterials.RABBIT, EquipmentSlot.FEET, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        player.addEffect(SPEED);
        player.addEffect(JUMP_BOOST);
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}
