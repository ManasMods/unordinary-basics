package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.enchantment.BreakingCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = UnordinaryBasics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MasterMinerHandler {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();
        LevelAccessor level = event.getLevel();
        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        int radius;
        CompoundTag tag = tool.getTag();
        if (tag != null) {
            radius = tag.getInt("masterMinerLevel");
        } else {
            radius = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.MASTER_MINER, tool);
        }
        if (radius > 0) {
            Direction.Axis playerAxis = getFacingDirection(player);
            Direction.Axis[] axes = Arrays.stream(Direction.Axis.values()).filter(a -> a != playerAxis).toArray(Direction.Axis[]::new);
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    if (x != 0 || y != 0) {
                        BlockPos newPos = pos.relative(axes[0], x).relative(axes[1], y);
                        BlockState state = level.getBlockState(newPos);
                        if ((state.getBlock().defaultDestroyTime() != -1 || player.isCreative()) && level.getFluidState(newPos) == Fluids.EMPTY.defaultFluidState() && !level.getBlockState(newPos).is(
                            Blocks.AIR)) {
                            level.setBlock(newPos, Blocks.AIR.defaultBlockState(), 3);
                            if (!player.isCreative() && state.canHarvestBlock(level, newPos, player)) {
                                Block.dropResources(state, (Level) level, newPos, level.getBlockEntity(newPos), player, tool);
                            }
                            BreakingCurseEnchantment.damageItem(tool);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemRightClick(final PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        int maxLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.MASTER_MINER, stack);
        CompoundTag tag = stack.getTag();
        if (maxLevel > 0 && tag != null && player.isCrouching()) {
            int currentLevel = tag.getInt("masterMinerLevel");
            int newLevel = currentLevel == maxLevel ? 0 : currentLevel + 1;
            tag.putInt("masterMinerLevel", newLevel);
            player.displayClientMessage(Component.translatable("Using Master Miner level " + newLevel), true);
        }
    }

    private static Direction.Axis getFacingDirection(Entity entity) {
        if (entity.getXRot() > -45 && entity.getXRot() < 45) {
            return entity.getDirection().getAxis();
        }
        return Direction.Axis.Y;
    }
}
