package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.capability.RedstonePouchCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.items.CapabilityItemHandler;
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
        pouchItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            ((ItemStackHandler) handler).deserializeNBT(pouchItem.getOrCreateTag().getCompound("inventory"));

                BlockHitResult hitResult = null;
                try {
                    hitResult = (BlockHitResult) ObfuscationReflectionHelper.findMethod(pContext.getClass(),"getHitResult").invoke(pContext);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                if (hitResult != null) {
                    ItemStack blockStack = handler.getStackInSlot(RedstonePouchCapability.getFirstNonEmptySlot(handler));
                    UseOnContext customContext = new UseOnContext(pContext.getLevel(), pContext.getPlayer(), pContext.getHand(), blockStack, hitResult);
                    result.set(blockStack.getItem().useOn(customContext));

                    if (result.get() != InteractionResult.FAIL) handler.extractItem(RedstonePouchCapability.getFirstNonEmptySlot(handler),1,false);

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
                pClicked.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    ((ItemStackHandler)handler).deserializeNBT(pClicked.getOrCreateTag().getCompound("inventory"));

                    if (pAction.equals(ClickAction.PRIMARY)) {
                        returnValue.set(RedstonePouchCapability.dumpItemStack(pClickedWith, handler));
                    } else if (pAction.equals(ClickAction.SECONDARY)){
                        returnValue.set(RedstonePouchCapability.addOneItem(pClickedWith,handler));
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
            pClickedWith.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                ((ItemStackHandler)handler).deserializeNBT(pClickedWith.getOrCreateTag().getCompound("inventory"));

                if (pAction.equals(ClickAction.PRIMARY)) {
                    returnValue.set(RedstonePouchCapability.dumpItemStack(pClicked, handler));
                } else if (pAction.equals(ClickAction.SECONDARY) && pSlot.getItem().getCount() < 64){
                    returnValue.set(RedstonePouchCapability.removeOneItem(handler,pSlot,Items.REDSTONE));
                }

                pClickedWith.getOrCreateTag().put("inventory",((ItemStackHandler)handler).serializeNBT());
            });
            return returnValue.get();
        } else if (pClicked.getItem().equals(Items.AIR) && pAction.equals(ClickAction.SECONDARY)){
            pClickedWith.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                ((ItemStackHandler)handler).deserializeNBT(pClickedWith.getOrCreateTag().getCompound("inventory"));

                returnValue.set(RedstonePouchCapability.removeOneItem(handler,pSlot,Items.REDSTONE));

                pClickedWith.getOrCreateTag().put("inventory",((ItemStackHandler)handler).serializeNBT());
            });
            return returnValue.get();
        }

        return false;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new RedstonePouchCapability();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        AtomicInteger itemCount = new AtomicInteger();

        pStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> itemCount.set(RedstonePouchCapability.getItemAmount(handler)));

        pTooltipComponents.add(new TextComponent("Redstone: " + itemCount.get()));
    }
}
