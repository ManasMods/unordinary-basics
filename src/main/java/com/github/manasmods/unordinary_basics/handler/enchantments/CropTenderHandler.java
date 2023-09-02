package com.github.manasmods.unordinary_basics.handler.enchantments;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import com.github.manasmods.unordinary_basics.utils.BlockBreakHelper;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CropTenderHandler {

    //If this isn't working, try rerunning data, as the tags may not be present
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos pos = event.getPos();
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.CROP_TENDER, tool);
        if (enchantLevel > 0 && tool.getTag().getBoolean("cropTenderOn") && event.getLevel().getBlockState(pos).is(UBTags.Blocks.CROP_TENDER_VALID)) {
            tool.getOrCreateTag().putBoolean("cropTenderOn",false);
            BlockBreakHelper.floodMineOnBlock(128 ,pos,player.getLevel(),player.getOnPos().above(),tool,player,event.getLevel().getBlockState(pos).getBlock(),true);
            tool.getOrCreateTag().putBoolean("cropTenderOn",true);
        }
    }

    @SubscribeEvent
    public static void onBlockTill(BlockEvent.BlockToolModificationEvent event) {
        if (!event.getToolAction().equals(ToolActions.HOE_TILL)) return;
        Player player = event.getPlayer();
        if (player == null) return;

        ItemStack tool = player.getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos pos = event.getPos();
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.CROP_TENDER, tool);

        boolean shouldTill = enchantLevel > 0 && tool.getTag().getBoolean("cropTenderOn")
                && event.getLevel().getBlockState(pos).is(UBTags.Blocks.CROP_TENDER_TILL_VALID);
        if (!shouldTill) return;

        tool.getOrCreateTag().putBoolean("cropTenderOn",false);
        BlockBreakHelper.floodTillOnBlock(400 , pos, event.getContext(), event.getLevel().getBlockState(pos).getBlock());
        tool.getOrCreateTag().putBoolean("cropTenderOn",true);
    }

    @SubscribeEvent
    public static void onItemRightClick(final PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        int maxLevel = EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.CROP_TENDER, stack);
        CompoundTag tag = stack.getTag();
        if (maxLevel > 0 && tag != null && player.isCrouching()) {
            boolean isOn = tag.getBoolean("cropTenderOn");
            tag.putBoolean("cropTenderOn", !isOn);
            player.displayClientMessage((Component.translatable("unordinary_basics.message.crop_tender_toggle",isOn ?
                    Component.translatable("unordinary_basics.message.off") : Component.translatable("unordinary_basics.message.on"))), true);
        }
    }
}
