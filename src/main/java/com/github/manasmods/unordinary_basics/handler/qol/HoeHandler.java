package com.github.manasmods.unordinary_basics.handler.qol;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HoeHandler {
    @SubscribeEvent
    public static void onHarvest(final BlockEvent.BreakEvent e) {
        BlockState blockState = e.getState();

        if (!blockState.is(BlockTags.CROPS)) return;
        if (!(blockState.getBlock() instanceof CropBlock plant)) return;
        if (blockState.getValue(CropBlock.AGE) < plant.getMaxAge()) return;
        Player player = e.getPlayer();

        if (!(player.getLevel() instanceof ServerLevel level)) return;
        ItemStack usedTool = player.getMainHandItem();

        if (!(usedTool.getItem() instanceof HoeItem)) return;
        BlockPos pos = e.getPos();
        List<ItemStack> drops = Block.getDrops(blockState, level, pos, null);
        Item seedItem = plant.asItem();

        boolean foundSeed = false;
        for (int i = 0; i < drops.size(); i++) {
            ItemStack dropStack = drops.get(i);
            if (dropStack.getItem().equals(seedItem)) {
                //Remove 1 seed
                dropStack.shrink(1);
                foundSeed = true;
                if (dropStack.isEmpty()) {
                    drops.remove(i);
                } else {
                    drops.set(i, dropStack);
                }
            }
        }

        if (!foundSeed) {
            ItemStack seedStack = findSeeds(player, seedItem);
            if (!seedStack.isEmpty()) {
                seedStack.shrink(1);
                foundSeed = true;
            }
        }

        final boolean finalFoundSeed = foundSeed;
        level.getServer().execute(() -> {
            if (finalFoundSeed) {
                level.setBlockAndUpdate(pos, plant.defaultBlockState());
            }

            drops.forEach(stack -> {
                ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY() + 0.3, pos.getZ(), stack);
                level.addFreshEntity(itemEntity);
            });
        });
    }

    private static ItemStack findSeeds(Player player, Item item) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
