package com.github.manasmods.unordinary_basics.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GliderItem extends ElytraItem {

    public GliderItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return false;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType == EquipmentSlot.CHEST;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chestStack.isEmpty()) {
            player.setItemSlot(EquipmentSlot.CHEST, handStack.copy());
            if (!level.isClientSide()) {
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            handStack.setCount(0);
            return InteractionResultHolder.sidedSuccess(handStack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(handStack);
        }
    }
}
