package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrowablePotionItem.class)
public abstract class MixinThrowablePotionItem extends PotionItem {

    ThrowablePotionItem potionItem = (ThrowablePotionItem) (Object)this;

    public MixinThrowablePotionItem(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "use", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"), cancellable = true)
    public void use(Level pLevel, Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {

        pPlayer.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
            if (handler instanceof UBInventoryItemStackHandler stackHandler) {
                if (!stackHandler.isItemEquipped(Unordinary_BasicsItems.POTION_BELT)) return;
                stackHandler.findFirstInstanceOf(Unordinary_BasicsItems.POTION_BELT).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                    for (int i = 0; i < itemHandler.getSlots(); i++) {
                        if (itemHandler.getStackInSlot(i).isEmpty() || !(itemHandler.getStackInSlot(i).getItem() instanceof ThrowablePotionItem)) continue;

                        pPlayer.getInventory().setItem(pPlayer.getInventory().selected, itemHandler.getStackInSlot(i));
                        itemHandler.extractItem(i, 1, false);
                        cir.setReturnValue(InteractionResultHolder.success(pPlayer.getItemInHand(pHand)));
                        break;
                    }
                });
            }
        });
    }

}
