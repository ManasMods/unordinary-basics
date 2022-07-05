package com.github.manasmods.unordinary_basics.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.Random;

public class HallucinationCurseEnchantment extends Enchantment {

    private static final Random RANDOM = new Random();

    public HallucinationCurseEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public static void hallucinate(Player player) {
        if (RANDOM.nextFloat() > 0.999) {
            player.playNotifySound(getSoundEvent(), SoundSource.HOSTILE, 1f, 1f);
        }
    }

    private static SoundEvent getSoundEvent() {
        float n = RANDOM.nextFloat();
        if (n > 0.75) {
            return SoundEvents.CREEPER_PRIMED;
        } else if (n > 0.5) {
            return SoundEvents.ZOMBIE_AMBIENT;
        } else if (n > 0.25) {
            return SoundEvents.SKELETON_AMBIENT;
        } else {
            return SoundEvents.SPIDER_AMBIENT;
        }
    }
}
