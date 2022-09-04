package com.github.manasmods.unordinary_basics.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

public class GliderItem extends Item {

    private static final MobEffectInstance SLOW_FALLING = new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, false, false);

    public GliderItem(Properties properties) {
        super(properties);
    }



}