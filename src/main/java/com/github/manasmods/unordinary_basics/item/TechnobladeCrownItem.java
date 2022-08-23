package com.github.manasmods.unordinary_basics.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class TechnobladeCrownItem extends ArmorItem {

    private static final MobEffectInstance STRENGTH = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false);
    private static final MobEffectInstance SPEED = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, false, false);

    public TechnobladeCrownItem(Item.Properties properties) {
        super(UBArmorMaterials.TECHNOBLADE_CROWN, EquipmentSlot.HEAD, properties);
    }

    @Nullable
    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType == getEquipmentSlot(stack);
    }

    @Nullable
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_GOLD;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("tooltip.unordinary_basics.technoblade_crown"));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        player.addEffect(STRENGTH);
        player.addEffect(SPEED);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }
}
