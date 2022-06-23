package com.github.manasmods.unordinary_basics.core;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

@Mixin(Horse.class)
public abstract class MixinHorse extends AbstractHorse
{

    protected MixinHorse(EntityType<? extends AbstractHorse> p_30531_, Level p_30532_)
    {
        super(p_30531_, p_30532_);
    }
    
    @Overwrite
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!this.isBaby()) {
           if (this.isTamed() && pPlayer.isSecondaryUseActive()) {
              this.openInventory(pPlayer);
              return InteractionResult.sidedSuccess(this.level.isClientSide);
           }
        }

        if (!itemstack.isEmpty()) {
           if (this.isFood(itemstack)) {
              return this.fedFood(pPlayer, itemstack);
           }

           InteractionResult interactionresult = itemstack.interactLivingEntity(pPlayer, this, pHand);
           if (interactionresult.consumesAction()) {
              return interactionresult;
           }

           if (!this.isTamed()) {
              this.makeMad();
              return InteractionResult.sidedSuccess(this.level.isClientSide);
           }

           boolean flag = !this.isBaby() && !this.isSaddled() && itemstack.is(Items.SADDLE);
           if (this.isArmor(itemstack) || flag) {
              this.openInventory(pPlayer);
              return InteractionResult.sidedSuccess(this.level.isClientSide);
           }
        }

        if (this.isBaby() || this.getPassengers().size() >= 2) {
           return super.mobInteract(pPlayer, pHand);
        } else {
           this.doPlayerRide(pPlayer);
           return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
     }

}
