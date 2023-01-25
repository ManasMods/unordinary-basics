package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.enchantment.UnordinaryBasicsEnchantments;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DiggerItem.class)
public class MixinDestroySpeed {

    @Final @Shadow private TagKey<Block> blocks;
    @Final @Shadow protected float speed;

    @Inject(method = "getDestroySpeed(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;)F", at = @At("HEAD"), cancellable = true)
    public void getDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> callback) {
        float normal = state.is(this.blocks) ? this.speed : 1.0F;
        if (EnchantmentHelper.getItemEnchantmentLevel(UnordinaryBasicsEnchantments.TREE_FELLER, stack) > 0 && stack.getTag().getBoolean("treeFellerOn") && stack.is(ItemTags.LOGS)) {
            callback.setReturnValue(normal / 4f);
        }
    }
}
