package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.data.Unordinary_BasicsBlockTagProvider;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import com.github.manasmods.unordinary_basics.utils.BlockBreakHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TreeFellerHandler {

    //If this isn't working, try rerunning data, as the tags may not be present
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos pos = event.getPos();
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.TREE_FELLER, tool);
        if (enchantLevel > 0 && tool.getTag().getBoolean("treeFellerOn") && event.getWorld().getBlockState(pos).is(Unordinary_BasicsBlockTagProvider.TREE_FELLER_VALID)) {
            tool.getOrCreateTag().putBoolean("treeFellerOn",false);
            BlockBreakHelper.floodMineOnBlock(27 ,pos,player.getLevel(),player.getOnPos().above(),tool,player,event.getWorld().getBlockState(pos).getBlock());
            tool.getOrCreateTag().putBoolean("treeFellerOn",true);
        }
    }


    @SubscribeEvent
    public static void onItemRightClick(final PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getPlayer();
        int maxLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.TREE_FELLER, stack);
        CompoundTag tag = stack.getTag();
        if (maxLevel > 0 && tag != null && player.isShiftKeyDown()) {
            boolean isOn = tag.getBoolean("treeFellerOn");
            tag.putBoolean("treeFellerOn", !isOn);
            player.displayClientMessage(new TranslatableComponent("unordinary_basics.message.tree_feller_toggle",(isOn ? new TranslatableComponent("unordinary_basics.message.off") : new TranslatableComponent("unordinary_basics.message.on"))), true);
        }
    }
}
