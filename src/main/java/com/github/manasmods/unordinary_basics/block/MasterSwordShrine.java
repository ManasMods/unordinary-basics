package com.github.manasmods.unordinary_basics.block;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class MasterSwordShrine extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public MasterSwordShrine(Properties properties) {
        super(properties);
    }

    public VoxelShape makeShapeNS(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.create(0.25, 0.0625, 0.25, 0.75, 0.125, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.125, 0, 0.125, 0.875, 0.0625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.1875, 0.125, 0.46875, 0.8125, 1.25, 0.53125), BooleanOp.OR);

        return shape;
    }

    public VoxelShape makeShapeEW(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.create(0.25, 0.0625, 0.25, 0.75, 0.125, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.125, 0, 0.125, 0.875, 0.0625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.46875, 0.125, 0.1875, 0.53125, 1.25, 0.8125), BooleanOp.OR);

        return shape;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case EAST, WEST -> {
                return makeShapeEW();
            }
            case NORTH, SOUTH -> {
                return makeShapeNS();
            }
        }
        return makeShapeNS();
    }
    /* FACING */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.getAdvancements().getOrStartProgress(pLevel.getServer().getAdvancements().getAdvancement(new ResourceLocation("minecraft","end/kill_dragon"))).isDone()) {
                ItemEntity itemEntity = new ItemEntity(pLevel,pPos.getX(),pPos.getY() + 3,pPos.getZ(),new ItemStack(Unordinary_BasicsItems.MASTER_SWORD));
                pLevel.addFreshEntity(itemEntity);
                pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(),1);
                System.out.println();
                return InteractionResult.SUCCESS;
            } else {
                pPlayer.displayClientMessage(Component.translatable("unordinary_basics.message.master_sword_deny"),true);
                System.out.println("loser");
            }
        }

        return InteractionResult.FAIL;
    }
}
