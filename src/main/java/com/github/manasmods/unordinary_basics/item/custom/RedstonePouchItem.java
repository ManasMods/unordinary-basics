package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.capability.RedstonePouchCapabilityProvider;
import com.google.common.util.concurrent.AtomicDouble;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RedstonePouchItem extends Item {
    public RedstonePouchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        AtomicReference<InteractionResult> result = new AtomicReference<>(InteractionResult.FAIL);

        //Block Place Function - from builder's glove
        ItemStack pouchItem = pContext.getItemInHand();
        pouchItem.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            ((ItemStackHandler) handler).deserializeNBT(pouchItem.getOrCreateTag().getCompound("inventory"));

                BlockHitResult hitResult = null;
                try {
                    hitResult = (BlockHitResult) ObfuscationReflectionHelper.findMethod(pContext.getClass(),"getHitResult").invoke(pContext);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                if (hitResult != null && RedstonePouchCapabilityProvider.getFirstNonEmptySlot(handler) != -8) {
                    ItemStack blockStack = handler.getStackInSlot(RedstonePouchCapabilityProvider.getFirstNonEmptySlot(handler));
                    UseOnContext customContext = new UseOnContext(pContext.getLevel(), pContext.getPlayer(), pContext.getHand(), blockStack, hitResult);
                    result.set(blockStack.getItem().useOn(customContext));

                    if (result.get() != InteractionResult.FAIL) handler.extractItem(RedstonePouchCapabilityProvider.getFirstNonEmptySlot(handler),1,false);

                    pouchItem.getOrCreateTag().put("inventory", ((ItemStackHandler) handler).serializeNBT());
                } else {
                    Unordinary_Basics.getLogger().error("Couldn't get hit result! Unable to place block.");
                }
            });
            return result.get();
        }

    //Take the whole stack if left-clicked, take one if right-clicked
    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pClicked, ItemStack pClickedWith, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {

        AtomicBoolean returnValue = new AtomicBoolean(false);

        if (!pSlot.allowModification(pPlayer)) return false;

            if (pClickedWith.getItem().equals(Items.REDSTONE)) {
                pClicked.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                    ((ItemStackHandler)handler).deserializeNBT(pClicked.getOrCreateTag().getCompound("inventory"));

                    if (pAction.equals(ClickAction.PRIMARY)) {
                        returnValue.set(RedstonePouchCapabilityProvider.dumpItemStack(pClickedWith, handler));
                    } else if (pAction.equals(ClickAction.SECONDARY)){
                        returnValue.set(RedstonePouchCapabilityProvider.addOneItem(pClickedWith,handler));
                    }

                    pClicked.getOrCreateTag().put("inventory",((ItemStackHandler)handler).serializeNBT());
                });
                return returnValue.get();
            }

        return false;
    }

    //Take items if left-clicked on a full slot, put down items if right-clicked and compatible
    @Override
    public boolean overrideStackedOnOther(ItemStack pClickedWith, Slot pSlot, ClickAction pAction, Player pPlayer) {

        AtomicBoolean returnValue = new AtomicBoolean(false);

        ItemStack pClicked = pSlot.getItem();

        if (pClicked.getItem().equals(Items.REDSTONE)) {
            pClickedWith.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                ((ItemStackHandler)handler).deserializeNBT(pClickedWith.getOrCreateTag().getCompound("inventory"));

                if (pAction.equals(ClickAction.PRIMARY)) {
                    returnValue.set(RedstonePouchCapabilityProvider.dumpItemStack(pClicked, handler));
                } else if (pAction.equals(ClickAction.SECONDARY) && pSlot.getItem().getCount() < 64){
                    returnValue.set(RedstonePouchCapabilityProvider.removeOneItem(handler,pSlot,Items.REDSTONE));
                }

                pClickedWith.getOrCreateTag().put("inventory",((ItemStackHandler)handler).serializeNBT());
            });
            return returnValue.get();
        } else if (pClicked.getItem().equals(Items.AIR) && pAction.equals(ClickAction.SECONDARY)){
            pClickedWith.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                ((ItemStackHandler)handler).deserializeNBT(pClickedWith.getOrCreateTag().getCompound("inventory"));

                returnValue.set(RedstonePouchCapabilityProvider.removeOneItem(handler,pSlot,Items.REDSTONE));

                pClickedWith.getOrCreateTag().put("inventory",((ItemStackHandler)handler).serializeNBT());
            });
            return returnValue.get();
        }

        return false;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new RedstonePouchCapabilityProvider();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        AtomicInteger itemCount = new AtomicInteger();

        pStack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            ((ItemStackHandler)handler).deserializeNBT(pStack.getOrCreateTag().getCompound("inventory"));
            itemCount.set(RedstonePouchCapabilityProvider.getItemAmount(handler));
        });

        pTooltipComponents.add(Component.translatable("tooltip.unordinary_basics.redstone_pouch",itemCount));
    }

    /*@Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem().equals(newStack.getItem());
    }*/

    @Override
    public int getBarWidth(ItemStack pStack) {
        AtomicInteger width = new AtomicInteger(0);
        pStack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            ((ItemStackHandler)handler).deserializeNBT(pStack.getOrCreateTag().getCompound("inventory"));
            width.set(Math.round((13.0F * RedstonePouchCapabilityProvider.getItemAmount(handler)) / (handler.getSlots() * 64)));
        });
        return width.get();
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        AtomicDouble color = new AtomicDouble();
        pStack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            ((ItemStackHandler)handler).deserializeNBT(pStack.getOrCreateTag().getCompound("inventory"));
            color.set(Math.max(0.0F, ((float) (handler.getSlots() * 64) - ((handler.getSlots() * 64) - RedstonePouchCapabilityProvider.getItemAmount(handler))) / (handler.getSlots() * 64)));
        });
        return Mth.hsvToRgb((float) (color.get() / 3.0F), 1.0F, 1.0F);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }
}
