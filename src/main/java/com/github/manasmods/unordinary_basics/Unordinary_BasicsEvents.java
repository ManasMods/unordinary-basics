package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.enchantment.BreakingCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.FatigueCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.HallucinationCurseEnchantment;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import com.github.manasmods.unordinary_basics.utils.MixinLadderHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class Unordinary_BasicsEvents {

    private static final Set<Item> CHAINMAIL_ARMOR;
    private static final Set<Item> NETHERITE_ARMOR;
    static {
        CHAINMAIL_ARMOR = new HashSet<>();
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_HELMET);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_CHESTPLATE);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_LEGGINGS);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_BOOTS);
        NETHERITE_ARMOR = new HashSet<>();
        NETHERITE_ARMOR.add(Items.NETHERITE_HELMET);
        NETHERITE_ARMOR.add(Items.NETHERITE_CHESTPLATE);
        NETHERITE_ARMOR.add(Items.NETHERITE_LEGGINGS);
        NETHERITE_ARMOR.add(Items.NETHERITE_BOOTS);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();
        LevelAccessor level = event.getWorld();
        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        // Ladders
        MixinLadderHelper.onBreak(event.getWorld(), pos, event.getState());
        // Master miner enchantment
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
                        if ((state.getBlock().defaultDestroyTime() != -1 || player.isCreative()) && level.getFluidState(newPos) == Fluids.EMPTY.defaultFluidState() && !level.getBlockState(newPos).is(Blocks.AIR)) {
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
        // Curse of Breaking
        if (EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.BREAKING_CURSE, tool) > 0 && !player.isCreative()) {
            BreakingCurseEnchantment.damageItem(tool);
        }
    }

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        int maxLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.MASTER_MINER, stack);
        CompoundTag tag = stack.getTag();
        if (maxLevel > 0 && tag != null) {
            int currentLevel = tag.getInt("masterMinerLevel");
            int newLevel = currentLevel == maxLevel ? 0 : currentLevel + 1;
            tag.putInt("masterMinerLevel", newLevel);
            event.getPlayer().displayClientMessage(new TextComponent("Using Master Miner level " + newLevel), true);
        }
    }

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            // Full armor effects
            if (hasFullArmorSet(player, CHAINMAIL_ARMOR) && event.getSource().msgId.equals("arrow")) {
                event.setAmount(event.getAmount() * 0.75f);
            }
            if (hasFullArmorSet(player, NETHERITE_ARMOR) && (event.getSource() == DamageSource.LAVA || event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.ON_FIRE)) {
                event.setAmount(event.getAmount() * 0.7f);
            }
            // Curse of Breaking
            for (ItemStack stack : player.getArmorSlots()) {
                if (EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.BREAKING_CURSE, stack) > 0) {
                    BreakingCurseEnchantment.damageItem(stack);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        // Fatigue curse
        if (EnchantmentHelper.getEnchantmentLevel(UnordinaryBasicsEnchantments.FATIGUE_CURSE, player) > 0) {
            FatigueCurseEnchantment.applyEffects(player);
        }
        // Hallucination curse
        if (EnchantmentHelper.getEnchantmentLevel(UnordinaryBasicsEnchantments.HALLUCINATION_CURSE, player) > 0) {
            HallucinationCurseEnchantment.hallucinate(player);
        }
    }

    private static boolean hasFullArmorSet(Player player, Set<Item> armorSet) {
        for (ItemStack stack : player.getArmorSlots()) {
            if (!armorSet.contains(stack.getItem())) {
                return false;
            }
        }
        return true;
    }

    private static Direction.Axis getFacingDirection(Entity entity) {
        if (entity.getXRot() > -45 && entity.getXRot() < 45) {
            return entity.getDirection().getAxis();
        }
        return Direction.Axis.Y;
    }
}
