package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.entity.NonExplosiveWitherSkull;
import com.github.manasmods.unordinary_basics.item.UBToolTiers;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class ZenithItem extends SwordItem {
    public ZenithItem() {
        super(UBToolTiers.ZENITH, 15, -2.5F,
                new Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)
                        .durability(3000)
                        .fireResistant().rarity(Rarity.EPIC));
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(Component.translatable("item.unordinary_basics.zenith.desc"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        Vec3 rot = pPlayer.getLookAngle();
        Vec3 spawnPos = pPlayer.getEyePosition();
        NonExplosiveWitherSkull witherSkull = new NonExplosiveWitherSkull(pLevel,pPlayer,rot.x,rot.y,rot.z);

        witherSkull.setOwner(pPlayer);
        witherSkull.setPosRaw(spawnPos.x + rot.x,spawnPos.y - 0.2d,spawnPos.z + rot.z);

        int speed = 2;

        witherSkull.xPower = witherSkull.xPower * speed;
        witherSkull.yPower = witherSkull.yPower * speed;
        witherSkull.zPower = witherSkull.zPower * speed;

        pLevel.addFreshEntity(witherSkull);

        if (pLevel.isClientSide) {
            if (new Random().nextBoolean()) {
                pLevel.playSound(pPlayer,witherSkull.blockPosition(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS,0.5f,1f);
            } else {
                pLevel.playSound(pPlayer,witherSkull.blockPosition(), SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS,0.5f,1f);
            }
        }

        pPlayer.getCooldowns().addCooldown(this, 60);

        itemstack.getOrCreateTag().putInt("activated_timer",30);

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        int timer = pStack.getOrCreateTag().getInt("activated_timer");

        if (timer > 0){
            pStack.getOrCreateTag().putInt("activated_timer",timer - 2);
            System.out.println("timer ticked");
        }
    }
}



