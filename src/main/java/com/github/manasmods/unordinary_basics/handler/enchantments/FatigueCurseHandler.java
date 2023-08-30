package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.enchantment.FatigueCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnordinaryBasics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FatigueCurseHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (EnchantmentHelper.getEnchantmentLevel(UnordinaryBasicsEnchantments.FATIGUE_CURSE, player) > 0) {
            FatigueCurseEnchantment.applyEffects(player);
        }
    }
}
