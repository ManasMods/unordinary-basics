package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.item.UBArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RabbitBootsItem extends ArmorItem {

    private static final MobEffectInstance JUMP_BOOST = new MobEffectInstance(MobEffects.JUMP, 1, 1, false, false);
    private static final MobEffectInstance LUCK = new MobEffectInstance(MobEffects.LUCK, 1, 0, false, false);


    public RabbitBootsItem(Properties properties) {
        super(UBArmorMaterials.RABBIT, EquipmentSlot.FEET, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        player.addEffect(JUMP_BOOST);
        player.addEffect(LUCK);
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}
