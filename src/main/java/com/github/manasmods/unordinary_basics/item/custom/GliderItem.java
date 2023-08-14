package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class GliderItem extends Item {

    /**
     * Changes the level of the slow falling effect, the higher this is, the slower the player will fall
     */
    private static int FALL_SPEED_MODIFIER = 8;

    private static final MobEffectInstance SLOW_FALLING = new MobEffectInstance(MobEffects.SLOW_FALLING, 1, FALL_SPEED_MODIFIER, false, false);

    public GliderItem(Properties properties) {
        super(properties);
    }

    public static boolean isGliding(Entity entity, ItemStack stack){
        if (stack.getItem() != Unordinary_BasicsItems.GLIDER) return false;

        Level level = entity.getLevel();

        return level.getBlockState(entity.getOnPos()).is(Blocks.AIR) && level.getBlockState(entity.getOnPos().below()).is(Blocks.AIR);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pIsSelected) return;
        if (!isGliding(pEntity,pStack)) return;
        if (!(pEntity instanceof LivingEntity)) return;

        LivingEntity livingEntity = (LivingEntity) pEntity;

        livingEntity.addEffect(SLOW_FALLING);
        System.out.println("Moved");

    }
}