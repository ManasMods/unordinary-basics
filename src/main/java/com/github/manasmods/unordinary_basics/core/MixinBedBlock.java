package com.github.manasmods.unordinary_basics.core;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.manasmods.unordinary_basics.block.IFromItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(BedBlock.class)
public abstract class MixinBedBlock extends HorizontalDirectionalBlock implements EntityBlock
{

    protected MixinBedBlock(Properties p_54120_)
    {
        super(p_54120_);
    }
    
    @Inject(method = "use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;", 
            at = @At(value = "HEAD"),
            cancellable = true)
    public void use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
            BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir)
    {
        if(Block.byItem(pPlayer.getItemInHand(pHand).getItem()) instanceof BannerBlock) {
            if(pLevel.getBlockEntity(pPos) != null && pLevel.getBlockEntity(pPos) instanceof BedBlockEntity) {
                BedBlockEntity entity = (BedBlockEntity) pLevel.getBlockEntity(pPos);
                
                if(!pLevel.isClientSide) {
                    if(entity instanceof IFromItem) {
                        IFromItem entityItem = (IFromItem) entity;
                        
                        entityItem.fromItem(pPlayer.getPose() == Pose.CROUCHING ? null : pPlayer.getItemInHand(pHand));
                        
                        switch(pLevel.getBlockState(pPos).getValue(BedBlock.PART)) {
                        case FOOT:
                            //using additional {} so i dont get conflicting names
                            {
                                BlockPos pos = pPos.relative(pState.getValue(FACING));
                                if(pLevel.getBlockEntity(pPos) instanceof BedBlockEntity) {
                                    entity = (BedBlockEntity) pLevel.getBlockEntity(pos);
                                    
                                    if(entity instanceof IFromItem) {
                                        IFromItem fromItem = (IFromItem) entity;
                                        fromItem.fromItem(pPlayer.getPose() == Pose.CROUCHING ? null : pPlayer.getItemInHand(pHand));
                                    }
                                }
                            }
                            break;
                        case HEAD:
                            BlockPos pos = pPos.relative(pState.getValue(FACING).getOpposite());
                            if(pLevel.getBlockEntity(pPos) instanceof BedBlockEntity) {
                                entity = (BedBlockEntity) pLevel.getBlockEntity(pos);
                                
                                if(entity instanceof IFromItem) {
                                    IFromItem fromItem = (IFromItem) entity;
                                    fromItem.fromItem(pPlayer.getPose() == Pose.CROUCHING ? null : pPlayer.getItemInHand(pHand));
                                }
                            }
                            break;
                        }
                        
                        cir.setReturnValue(InteractionResult.SUCCESS);
                    }
                } else {
                    cir.setReturnValue(InteractionResult.sidedSuccess(pLevel.isClientSide));
                }
            }
        }
    }
    
    @Inject(method = "setPlacedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;blockUpdated(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V"))
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack, CallbackInfo info) {
        BlockPos pos = pPos.relative(pState.getValue(FACING));
        
        if(pLevel.getBlockEntity(pos) != null && pLevel.getBlockEntity(pos) instanceof BedBlockEntity) {
            CompoundTag tag = BlockItem.getBlockEntityData(pStack);
            
            if(tag != null) {
                BedBlockEntity entity = (BedBlockEntity) pLevel.getBlockEntity(pos);
                entity.load(tag);
                entity.setChanged();
            }
        }
    }
}
