package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FletchingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import shadows.apotheosis.village.fletching.ApothFletchingBlock;

@Pseudo
@Mixin(ApothFletchingBlock.class)
public class MixinApothFletchingBlock extends FletchingTableBlock {
    public MixinApothFletchingBlock(Properties p_53499_) {
        super(p_53499_);
    }

    @Inject(method = "use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;openMenu(Lnet/minecraft/world/MenuProvider;)Ljava/util/OptionalInt;"), cancellable = true)
    private void onGetMenuProvider(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) -> new FletchingTableMenu(pContainerId, ContainerLevelAccess.create(worldIn, pos), pPlayer1.getInventory(),pos), Component.translatable(Unordinary_Basics.MOD_ID + ".menu.fletching_table.title")), buffer -> {
            buffer.writeBlockPos(pos);
            buffer.writeResourceLocation(worldIn.dimension().location());
        });
        cir.setReturnValue(InteractionResult.CONSUME);
    }
}
