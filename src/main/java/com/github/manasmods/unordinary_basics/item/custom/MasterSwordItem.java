package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.entity.MasterSwordBeam;
import com.github.manasmods.unordinary_basics.item.UBToolTiers;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

public class MasterSwordItem extends SwordItem {
    public MasterSwordItem() {
        super(UBToolTiers.MASTER, 11, -2.4F,
                new Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).fireResistant().rarity(Rarity.RARE));
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public  <T extends LivingEntity> int damageItem(ItemStack pStack, int amount, T pEntity, Consumer<T> onBroken) {
        if (pStack.getDamageValue() + amount >= pStack.getMaxDamage()) {
            ItemStack decay = new ItemStack(Unordinary_BasicsItems.DECAYED_MASTER_SWORD);
            decay.setTag(pStack.getOrCreateTag().copy());

            if (pEntity.getMainHandItem().is(pStack.getItem())) {
                pEntity.setItemInHand(InteractionHand.MAIN_HAND, decay);
            } else if (pEntity.getOffhandItem().is(pStack.getItem())) {
                pEntity.setItemInHand(InteractionHand.OFF_HAND, decay);
            }

            pEntity.getLevel().playSound(null, pEntity.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 10f);
            return 0;
        }
        return amount;
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (pStack.isDamaged()) {
            if (pStack.getDamageValue() >= pStack.getMaxDamage() - 1) {
                ItemStack decay = new ItemStack(Unordinary_BasicsItems.DECAYED_MASTER_SWORD);
                decay.setTag(pStack.getOrCreateTag().copy());
                pEntity.getSlot(pSlotId).set(decay);

                pEntity.getLevel().playSound(null, pEntity.blockPosition(),
                        SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 10f);
                return;
            }

            CompoundTag tag = pStack.getOrCreateTag();
            int heal = tag.getInt("durabilityHeal");

            if (heal < 200) {
                tag.putInt("durabilityHeal", heal + 1);
            } else {
                pStack.setDamageValue(pStack.getDamageValue() - 1);
                tag.putInt("durabilityHeal", 0);
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer.getMaxHealth() != pPlayer.getHealth()) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        Vec3 rot = pPlayer.getLookAngle();
        Vec3 spawnPos = pPlayer.getEyePosition();
        MasterSwordBeam swordBeam = new MasterSwordBeam(pLevel,pPlayer,rot.x,rot.y,rot.z);

        swordBeam.setOwner(pPlayer);
        swordBeam.setPosRaw(spawnPos.x + rot.x,spawnPos.y - 0.2d,spawnPos.z + rot.z);

        int speed = 2;

        swordBeam.xPower = swordBeam.xPower * speed;
        swordBeam.yPower = swordBeam.yPower * speed;
        swordBeam.zPower = swordBeam.zPower * speed;

        pLevel.addFreshEntity(swordBeam);

        /*if (pLevel.isClientSide) {
            if (new Random().nextBoolean()) {
                pLevel.playSound(pPlayer,swordBeam.blockPosition(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS,0.5f,1f);
            } else {
                pLevel.playSound(pPlayer,swordBeam.blockPosition(), SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS,0.5f,1f);
            }
        }*/
        //TODO: add sounds

        pPlayer.getCooldowns().addCooldown(this, 60);

        //itemstack.getOrCreateTag().putInt("activated_timer",30);

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}



