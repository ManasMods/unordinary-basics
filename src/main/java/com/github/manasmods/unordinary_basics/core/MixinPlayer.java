package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity implements net.minecraftforge.common.extensions.IForgePlayer {
    protected MixinPlayer(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    Player player = (Player)(Object)this;

    @Inject(method = "getProjectile", at = @At("HEAD"), cancellable = true)
    public void getProjectile(ItemStack pShootable, CallbackInfoReturnable<ItemStack> cir) {
        if (!(pShootable.getItem() instanceof BowItem)) return;
        AtomicReference<ItemStack> returnValue = new AtomicReference<>();
        player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
            if (!(handler instanceof UBInventoryItemStackHandler)) return;
            UBInventoryItemStackHandler stackHandler = (UBInventoryItemStackHandler)handler;
            if (!stackHandler.isItemEquipped(Unordinary_BasicsItems.QUIVER)) return;

            ItemStack quiver = stackHandler.findFirstInstanceOf(Unordinary_BasicsItems.QUIVER);

            quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                for (int i = 0; i < itemHandler.getSlots(); ++i){
                    if (!itemHandler.getStackInSlot(i).isEmpty()) {
                        returnValue.set(itemHandler.getStackInSlot(i));
                        break;
                    }
                }
            });
        });
        if (returnValue.get() != null){
            cir.setReturnValue(net.minecraftforge.common.ForgeHooks.getProjectile(this, pShootable, returnValue.get()));
        }
    }

}
