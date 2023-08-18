package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.enchantment.BreakingCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.CropTenderEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.FatigueCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.HallucinationCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.MasterMinerEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.TreeFellerEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.TrimmerEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.VeinMinerEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;

import static net.minecraft.world.entity.EquipmentSlot.CHEST;
import static net.minecraft.world.entity.EquipmentSlot.FEET;
import static net.minecraft.world.entity.EquipmentSlot.HEAD;
import static net.minecraft.world.entity.EquipmentSlot.LEGS;
import static net.minecraft.world.entity.EquipmentSlot.MAINHAND;
import static net.minecraft.world.entity.EquipmentSlot.OFFHAND;

public class EnchantmentsRegistry {

    public static void register(DeferredRegister<Enchantment> registry) {
        registry.register("master_miner", () -> new MasterMinerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER));
        registry.register("breaking_curse", () -> new BreakingCurseEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE));
        registry.register("fatigue_curse", () -> new FatigueCurseEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, HEAD, CHEST, LEGS, FEET, MAINHAND, OFFHAND));
        registry.register("hallucination_curse", () -> new HallucinationCurseEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, HEAD));
        registry.register("vein_miner", () -> new VeinMinerEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER));
        registry.register("tree_feller", () -> new TreeFellerEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER));
        registry.register("trimmer", () -> new TrimmerEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER));
        registry.register("crop_tender", () -> new CropTenderEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER));
    }
}
