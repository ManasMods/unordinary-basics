package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.FletchingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FletchingTableBlock.class)
public abstract class MixinFletchingTableBlock extends CraftingTableBlock {
    protected MixinFletchingTableBlock(Properties properties) {
        super(properties);
    }

    @Inject(method = "Lnet/minecraft/world/level/block/FletchingTableBlock;use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;", at = @At("RETURN"),cancellable = true)
    private void onUse(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        if (pLevel.isClientSide) {
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else {
            NetworkHooks.openGui((ServerPlayer) pPlayer, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) -> {
                return new FletchingTableMenu(pContainerId, ContainerLevelAccess.create(pLevel, pPos), pPlayer1.getInventory());
            }, new TranslatableComponent(Unordinary_Basics.MOD_ID + ".menu.fletching_table.title")), buffer -> {
                buffer.writeBlockPos(pPos);
                buffer.writeResourceLocation(pLevel.dimension().location());});
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }
}