package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.enchantment.MasterMinerEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;

public class EnchantmentsRegistry {

    public static void register(DeferredRegister<Enchantment> registry) {
        registry.register("master_miner", () -> new MasterMinerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER));
    }
}
