package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.enchantment.BreakingCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnordinaryBasics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CurseOfBreakingHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.BREAKING_CURSE, tool) > 0 && !player.isCreative()) {
            BreakingCurseEnchantment.damageItem(tool);
        }
    }

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (ItemStack stack : player.getArmorSlots()) {
                if (EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.BREAKING_CURSE, stack) > 0) {
                    BreakingCurseEnchantment.damageItem(stack);
                }
            }
        }
    }
}
