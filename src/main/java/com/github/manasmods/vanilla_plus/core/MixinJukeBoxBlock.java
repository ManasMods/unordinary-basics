package com.github.manasmods.vanilla_plus.core;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import com.github.manasmods.vanilla_plus.block.entity.JukeboxBlockEntity;
import com.github.manasmods.vanilla_plus.menu.JukeBoxMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JukeboxBlock.class)
public abstract class MixinJukeBoxBlock extends BaseEntityBlock {
    protected MixinJukeBoxBlock(Properties properties) {
        super(properties);
    }

    @Inject(method = "setPlacedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    public void onSetPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack, CallbackInfo ci) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        CompoundTag tag = BlockItem.getBlockEntityData(pStack);
        if (tag == null) {
            tag = new JukeboxBlockEntity(pPos, pState).saveWithFullMetadata();
        }

        if (tag.contains("isPlaying")) {
            pLevel.setBlock(pPos, pState.setValue(JukeboxBlock.HAS_RECORD, tag.getBoolean("isPlaying")), 2);
        } else {
            Vanilla_Plus.getLogger().error("Missing nbt data on {}. No isPlaying value found.", pStack);
        }
        ci.cancel();
    }

    @Inject(method = "use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;", at = @At("HEAD"), cancellable = true)
    public void onUse(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        if (!pLevel.isClientSide()) {
            NetworkHooks.openGui((ServerPlayer) pPlayer, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) -> {
                JukeboxBlockEntity blockEntity = (JukeboxBlockEntity) pLevel.getBlockEntity(pPos);
                return new JukeBoxMenu(pContainerId, pPlayer1.getInventory(), blockEntity.getContainer(), ContainerLevelAccess.create(pLevel, pPos));
            }, new TranslatableComponent(Vanilla_Plus.MOD_ID + ".menu.jukebox.title")), buffer -> {
                buffer.writeBlockPos(pPos);
                buffer.writeResourceLocation(pLevel.dimension().getRegistryName());
            });
        }

        cir.setReturnValue(InteractionResult.SUCCESS);
        cir.cancel();
    }

    @Inject(method = "onRemove", at = @At("HEAD"))
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving, CallbackInfo ci) {
        if (!pState.is(pNewState.getBlock())) {
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Inject(method = "newBlockEntity(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/entity/BlockEntity;", at = @At("RETURN"), cancellable = true)
    public void onNewBlockEntity(BlockPos pPos, BlockState pState, CallbackInfoReturnable<BlockEntity> cir) {
        cir.setReturnValue(new JukeboxBlockEntity(pPos, pState));
    }
}
