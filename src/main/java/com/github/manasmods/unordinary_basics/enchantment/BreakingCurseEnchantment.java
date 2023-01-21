package com.github.manasmods.unordinary_basics.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BreakingCurseEnchantment extends Enchantment {

    public BreakingCurseEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    public static void damageItem(ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue() + 1);
    }
}
