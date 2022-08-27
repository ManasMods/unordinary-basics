package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VeinMinerHandler {

    // TODO: Common utility methods between Vein Miner, Master Miner, Tree Feller
    // TODO: When mining ores, drop xp too

    private static int recursionCounter = 0;
    private static final Set<BlockPos> toBreak = new HashSet<>();

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos pos = event.getPos();
        if (EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.VEIN_MINER, tool) > 0 && tool.getTag().getBoolean("veinMinerOn") && event.getWorld().getBlockState(pos).is(Tags.Blocks.ORES)) {
            toBreak.add(pos);
            executeBreak(event);
        }
    }

    private static void executeBreak(BlockEvent.BreakEvent event) {
        LevelAccessor level = event.getWorld();
        Player player = event.getPlayer();
        BlockPos adjacentPos = getIdenticalAdjacent(level, event.getPos());
        if (adjacentPos != null && recursionCounter < 63) {
            recursionCounter++;
            toBreak.add(adjacentPos);
            executeBreak(new BlockEvent.BreakEvent((Level) level, adjacentPos, level.getBlockState(adjacentPos), player));
        } else {
            recursionCounter = 0;
            toBreak.forEach(pos -> level.destroyBlock(pos, !player.isCreative(), player));
            if (!player.isCreative())
                player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(toBreak.size() - 1, player, s -> {});
            toBreak.clear();
        }
    }

    @Nullable
    private static BlockPos getIdenticalAdjacent(LevelAccessor level, BlockPos pos) {
        Block block = level.getBlockState(pos).getBlock();
        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = pos.relative(direction);
            if (level.getBlockState(adjacentPos).is(block) && !toBreak.contains(adjacentPos)) {
                return adjacentPos;
            }
        }
        return null;
    }

    @SubscribeEvent
    public static void onItemRightClick(final PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getPlayer();
        int maxLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.VEIN_MINER, stack);
        CompoundTag tag = stack.getTag();
        if (maxLevel > 0 && tag != null && player.isShiftKeyDown()) {
            boolean isOn = tag.getBoolean("veinMinerOn");
            tag.putBoolean("veinMinerOn", !isOn);
            player.displayClientMessage(new TextComponent("Turned Vein Miner " + (isOn ? "off" : "on")), true);
        }
    }
}
