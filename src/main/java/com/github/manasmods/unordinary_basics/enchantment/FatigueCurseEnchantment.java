package com.github.manasmods.unordinary_basics.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FatigueCurseEnchantment extends Enchantment {

    private static final MobEffectInstance SLOWNESS = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0);
    private static final MobEffectInstance MINING_FATIGUE = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 0);

    public FatigueCurseEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    public static void applyEffects(Player player) {
        player.addEffect(SLOWNESS);
        player.addEffect(MINING_FATIGUE);
    }
}
