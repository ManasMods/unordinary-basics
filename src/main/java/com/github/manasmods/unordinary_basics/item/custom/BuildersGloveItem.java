package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.capability.ItemStackHandlerCapabilityProvider;
import com.github.manasmods.unordinary_basics.menu.BuildersGloveMenu;
import com.github.manasmods.unordinary_basics.utils.BlockBreakHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.items.CapabilityItemHandler;
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
        if (pContext.getPlayer().getInventory().offhand.get(0).getItem() instanceof BlockItem) {
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
                if (level.isClientSide) return result.get();
                if (canBreakBlock || clickedOnState.getDestroySpeed(pContext.getLevel(),pContext.getClickedPos()) <= 1 || pContext.getPlayer().getAbilities().instabuild){
                    if (clickedOn != ((BlockItem) pContext.getPlayer().getInventory().offhand.get(0).getItem()).getBlock()) {

                        //Break the block
                        ItemStack tool = new ItemStack(correctItem);
                        List<ItemStack> drops = BlockBreakHelper.breakBlockAndReturnDrops(pContext.getClickedPos(),level,pContext.getPlayer().getOnPos().above(),
                                pContext.getClickedPos(),tool,pContext.getPlayer());

                        //Place the new block
                        level.setBlockAndUpdate(pContext.getClickedPos(), ((BlockItem) pContext.getPlayer().getInventory().offhand.get(0).getItem()).getBlock().defaultBlockState());
                        level.updateNeighborsAt(pContext.getClickedPos(), ((BlockItem) pContext.getPlayer().getInventory().offhand.get(0).getItem()).getBlock());

                        //No point spawning blocks in creative

                        if (!pContext.getPlayer().getAbilities().instabuild) {

                            BlockPos spawnPos = pContext.getPlayer().getOnPos().above();
                            drops.forEach(itemStack -> {
                                ItemEntity entity = new ItemEntity(level,spawnPos.getX(),spawnPos.getY(),spawnPos.getZ(),itemStack);
                                level.addFreshEntity(entity);
                            });

                            pContext.getPlayer().getInventory().offhand.get(0).shrink(1);
                        }

                        pContext.getPlayer().swing(pContext.getHand());

                        //RETURNING AN INTERACTION RESULT BREAKS THIS, FOR WHATEVER REASON, MAYBE FIX IT USING BlockItem's NATIVE METHOD?
                    }
                } else {
                    pContext.getPlayer().displayClientMessage(new TranslatableComponent("warning.unordinary_basics.tool_not_strong_enough"),true);
                }
            }
        } else {
            //Block Place Function
            if (level.isClientSide) return result.get();

            ItemStack gloveItem = pContext.getItemInHand();
            gloveItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                ((ItemStackHandler) handler).deserializeNBT(gloveItem.getOrCreateTag().getCompound("inventory"));
                List<Integer> slotList = new ArrayList<>();
                for (int i = 0; i < handler.getSlots(); ++i) {
                    if (handler.getStackInSlot(i).getItem() instanceof BlockItem) {
                        slotList.add(i);
                    }
                }
                if (slotList.size() > 0) {
                    int toPlace = (int) (Math.random() * (slotList.size()));
                    BlockHitResult hitResult = null;
                    try {
                        hitResult = (BlockHitResult) ObfuscationReflectionHelper.findMethod(pContext.getClass(), "getHitResult").invoke(pContext);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    if (hitResult != null) {
                        ItemStack blockStack = handler.getStackInSlot(slotList.get(toPlace));
                        UseOnContext customContext = new UseOnContext(pContext.getLevel(), pContext.getPlayer(), pContext.getHand(), blockStack, hitResult);
                        result.set(blockStack.getItem().useOn(customContext));
                        gloveItem.getOrCreateTag().put("inventory", ((ItemStackHandler) handler).serializeNBT());
                    } else {
                        Unordinary_Basics.getLogger().error("Couldn't get hit result! Unable to place block.");
                    }
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
            for (int j = 0; diggerItems.size() > i; ++i){
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("tooltip.unordinary_basics.builders_glove"));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        pStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); ++i){
                if (handler.getStackInSlot(i).getItem() instanceof BlockItem && pEntity instanceof Player && ((Player) pEntity).getInventory().contains(handler.getStackInSlot(i))) {
                    ((ItemStackHandler) handler).deserializeNBT(pStack.getOrCreateTag().getCompound("inventory"));
                    ItemStack stack = handler.getStackInSlot(i);
                    if (((Player) pEntity).getInventory().findSlotMatchingItem(stack) >= 0) {
                        ItemStack playerStack = ((Player) pEntity).getInventory().getItem(((Player) pEntity).getInventory().findSlotMatchingItem(stack));
                        while (playerStack.getCount() > 0 && stack.getCount() < 64) {
                            playerStack.shrink(1);
                            stack.grow(1);
                            pStack.getOrCreateTag().put("inventory", ((ItemStackHandler) handler).serializeNBT());
                        }
                    }
                }
            }

        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && !(pPlayer.getInventory().offhand.get(0).getItem() instanceof BlockItem) && Screen.hasShiftDown()) {
            pPlayer.getItemInHand(pUsedHand).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> ((ItemStackHandler) handler).deserializeNBT(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getCompound("inventory")));
            NetworkHooks.openGui((ServerPlayer) pPlayer, new SimpleMenuProvider((id, inv, p) -> new BuildersGloveMenu(id, inv, pPlayer.getInventory().selected), new TranslatableComponent("item.unordinary_basics.builders_glove")),
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
