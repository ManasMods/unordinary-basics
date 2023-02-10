package com.github.manasmods.unordinary_basics.handler;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class UBEffectsHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        int amplifier = -1;
        if (player.getItemBySlot(EquipmentSlot.FEET).is(Unordinary_BasicsItems.RABBIT_BOOTS)) amplifier++;
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(Unordinary_BasicsItems.TECHNOBLADE_CROWN)) amplifier++;
        if (amplifier != -1) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, amplifier, false, false));
        }
    }
}
