package com.github.manasmods.unordinary_basics.handler.qol;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.utils.UBUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = UnordinaryBasics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NetheriteArmorHandler {
    private static final Set<Item> NETHERITE_ARMOR = Set.of(Items.NETHERITE_HELMET,Items.NETHERITE_CHESTPLATE,Items.NETHERITE_LEGGINGS,Items.NETHERITE_BOOTS);

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (UBUtils.hasFullArmorSet(player, NETHERITE_ARMOR) && (event.getSource() == DamageSource.LAVA || event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.ON_FIRE)) {
                event.setAmount(event.getAmount() * 0.7f);
            }
        }
    }
}
