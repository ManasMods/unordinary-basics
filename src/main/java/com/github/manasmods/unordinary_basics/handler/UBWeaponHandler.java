package com.github.manasmods.unordinary_basics.handler;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class UBWeaponHandler {

    @SubscribeEvent
    public static void onDamageEntity(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getType().is(UBTags.EntityTypes.SCULK_ENEMIES)) {
            event.setAmount(event.getAmount() * 1.5F);
        }
    }
}
