package com.github.manasmods.unordinary_basics.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class TrimmerEnchantment extends Enchantment {

    public TrimmerEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return super.canEnchant(stack)
                && EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.MASTER_MINER, stack) == 0
                && (stack.getItem() instanceof ShearsItem);
    }
}