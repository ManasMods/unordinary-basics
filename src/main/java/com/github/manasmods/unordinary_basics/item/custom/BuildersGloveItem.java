package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.capability.ItemStackHandlerCapabilityProvider;
import com.github.manasmods.unordinary_basics.menu.BuildersGloveMenu;
import com.github.manasmods.unordinary_basics.utils.BlockBreakHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BuildersGloveItem extends Item {

    public BuildersGloveItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        AtomicReference<InteractionResult> result = new AtomicReference<>(InteractionResult.FAIL);
        Level level = pContext.getLevel();

        //Block Replace Function

        if (pContext.getPlayer().getInventory().offhand.get(0).getItem() instanceof BlockItem blockItem) {
            if (level.isClientSide) return result.get();

            if (pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() != Blocks.AIR){
                Block clickedOn = level.getBlockState(pContext.getClickedPos()).getBlock();
                BlockState clickedOnState = level.getBlockState(pContext.getClickedPos());

                boolean canBreakBlock = false;
                List<DiggerItem> diggerItems = getDiggerItems(pContext);
                DiggerItem correctItem = null;

                for (DiggerItem diggerItem : diggerItems) {
                    if (diggerItem.isCorrectToolForDrops(new ItemStack(diggerItem), clickedOnState)) {
                        canBreakBlock = true;
                        correctItem = diggerItem;
                        break;
                    }
                }

                //Check if the player has the right item to be able to break the block, or if the block is really easy to break
                if (canBreakBlock || clickedOnState.getDestroySpeed(pContext.getLevel(),pContext.getClickedPos()) <= 1 || pContext.getPlayer().getAbilities().instabuild){

                    if (clickedOn != blockItem.getBlock()) {

                        //Break the block
                        ItemStack tool = new ItemStack(correctItem);
                        List<ItemStack> drops = BlockBreakHelper.breakBlockAndReturnDrops(pContext.getClickedPos(),level,pContext.getPlayer().getOnPos().above(),
                                pContext.getClickedPos(),tool,pContext.getPlayer(),true);
                        level.destroyBlock(pContext.getClickedPos(), false);

                        //Place the new block
                        level.setBlockAndUpdate(pContext.getClickedPos(), blockItem.getBlock().defaultBlockState());
                        pContext.getPlayer().getInventory().offhand.get(0).shrink(1);

                        //No point spawning blocks in creative

                        if (!pContext.getPlayer().getAbilities().instabuild) {

                            BlockPos spawnPos = pContext.getPlayer().getOnPos().above();
                            drops.forEach(itemStack -> {
                                ItemEntity entity = new ItemEntity(level,spawnPos.getX(),spawnPos.getY(),spawnPos.getZ(),itemStack);
                                level.addFreshEntity(entity);
                            });
                        }

                        pContext.getPlayer().swing(pContext.getHand());

                        //RETURNING AN INTERACTION RESULT BREAKS THIS, FOR WHATEVER REASON, MAYBE FIX IT USING BlockItem's NATIVE METHOD?
                    }
                } else {
                    pContext.getPlayer().displayClientMessage(Component.translatable("warning.unordinary_basics.tool_not_strong_enough"),true);
                }
            }
        } else {
            //Block Place Function
            if (level.isClientSide) return result.get();

            ItemStack gloveItem = pContext.getItemInHand();
            gloveItem.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                ((ItemStackHandler) handler).deserializeNBT(gloveItem.getOrCreateTag().getCompound("inventory"));
                List<Integer> slotList = new ArrayList<>();
                for (int i = 0; i < handler.getSlots(); ++i) {
                    if (handler.getStackInSlot(i).getItem() instanceof BlockItem) {
                        slotList.add(i);
                    }
                }
                if (slotList.size() > 0) {
                    int toPlace = (int) (Math.random() * (slotList.size()));

                    //Part that actually places the block
                    placeBlockNativeMethod(pContext,handler.getStackInSlot(slotList.get(toPlace)),result);
                    refillItems(gloveItem,pContext.getPlayer());
                    gloveItem.getOrCreateTag().put("inventory", ((ItemStackHandler) handler).serializeNBT());
                }
            });
        }

        return result.get();
    }

    /**
     *Get all DiggerItems in the player's inventory by comparing their speed and removing the ones that are inferior in speed and are of the same type
     */
    private List<DiggerItem> getDiggerItems(UseOnContext pContext){
        List<DiggerItem> diggerItems = new ArrayList<>();
        Inventory inv = pContext.getPlayer().getInventory();
        for (int i = 0; inv.getContainerSize() > i; ++i){
            if (inv.getItem(i).getItem() instanceof DiggerItem){
                diggerItems.add((DiggerItem) inv.getItem(i).getItem());
            }
        }

        for (int i = 0; diggerItems.size() > i; ++i){
            DiggerItem compareAgainst = diggerItems.get(i);
            for (int j = 0; diggerItems.size() > j; ++j){
                DiggerItem compareTo = diggerItems.get(j);
                if (i != j) {
                    if (compareTo.getClass().equals(compareAgainst.getClass()) && compareAgainst.getTier().getSpeed() >= compareTo.getTier().getSpeed()) {
                        diggerItems.remove(j);
                    } else if (compareTo.getClass().equals(compareAgainst.getClass()) && compareAgainst.getTier().getSpeed() < compareTo.getTier().getSpeed()) {
                        diggerItems.remove(i);
                        break;
                    }
                }
            }
        }
        return diggerItems;
    }

    private void placeBlockNativeMethod(UseOnContext pContext, ItemStack blockStack, AtomicReference<InteractionResult> result){
        BlockHitResult hitResult = null;
        try {
            hitResult = (BlockHitResult) ObfuscationReflectionHelper.findMethod(pContext.getClass(), "m_43718_").invoke(pContext);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (hitResult != null) {
            UseOnContext customContext = new UseOnContext(pContext.getLevel(), pContext.getPlayer(), pContext.getHand(), blockStack, hitResult);
            result.set(blockStack.getItem().useOn(customContext));
        } else {
            UnordinaryBasics.getLogger().error("Couldn't get hit result! Unable to place block.");
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.unordinary_basics.builders_glove"));
    }

    private void refillItems(ItemStack pStack, Entity pEntity) {
        pStack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); ++i){
                if (handler.getStackInSlot(i).getItem() instanceof BlockItem && pEntity instanceof Player && ((Player) pEntity).getInventory().contains(handler.getStackInSlot(i))) {
                    ItemStack stack = handler.getStackInSlot(i);
                    if (((Player) pEntity).getInventory().findSlotMatchingItem(stack) >= 0) {
                        ItemStack playerStack = ((Player) pEntity).getInventory().getItem(((Player) pEntity).getInventory().findSlotMatchingItem(stack));
                        while (playerStack.getCount() > 0 && stack.getCount() < 64) {
                            playerStack.shrink(1);
                            stack.grow(1);
                        }
                    }
                }
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && !(pPlayer.getInventory().offhand.get(0).getItem() instanceof BlockItem) && pPlayer.isCrouching()) {
            pPlayer.getItemInHand(pUsedHand).getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> ((ItemStackHandler) handler).deserializeNBT(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getCompound("inventory")));
            NetworkHooks.openScreen((ServerPlayer) pPlayer, new SimpleMenuProvider((id, inv, p) -> new BuildersGloveMenu(id, inv, pPlayer.getInventory().selected), Component.translatable("item.unordinary_basics.builders_glove")),
                    buf -> buf.writeVarInt(pPlayer.getInventory().selected));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ItemStackHandlerCapabilityProvider(18);
    }

    //this doesn't work when a menu is open :(
    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return !(player.containerMenu instanceof BuildersGloveMenu);
    }
}
