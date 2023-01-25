package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;

import static net.minecraft.world.entity.EquipmentSlot.*;

public class EnchantmentsRegistry {

    public static void register(DeferredRegister<Enchantment> registry) {
        registry.register("master_miner", () -> new MasterMinerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER));
        registry.register("breaking_curse", () -> new BreakingCurseEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE));
        registry.register("fatigue_curse", () -> new FatigueCurseEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, HEAD, CHEST, LEGS, FEET, MAINHAND, OFFHAND));
        registry.register("hallucination_curse", () -> new HallucinationCurseEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, HEAD));
        registry.register("vein_miner", () -> new VeinMinerEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER));
        registry.register("tree_feller", () -> new TreeFellerEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER));
    }
}
