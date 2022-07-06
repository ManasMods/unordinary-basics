package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.enchantment.HallucinationCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HallucinationCurseHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (EnchantmentHelper.getEnchantmentLevel(UnordinaryBasicsEnchantments.HALLUCINATION_CURSE, player) > 0) {
            HallucinationCurseEnchantment.hallucinate(player);
        }
    }
}
