package com.github.manasmods.unordinary_basics.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PathableStairBlock extends StairBlock {

    public final Supplier<Block> pathVariant;

    public PathableStairBlock(Supplier<BlockState> pBlockType, Properties pProperties, Supplier<Block> pPathVariant) {
        super(pBlockType, pProperties);

        this.pathVariant = pPathVariant;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (simulate) return null;
        if (!toolAction.equals(ToolActions.SHOVEL_FLATTEN)) return null;
        if (pathVariant == null) return null;

        BlockState currentState = state;

        return pathVariant.get().defaultBlockState()
                .setValue(StairBlock.WATERLOGGED,currentState.getValue(StairBlock.WATERLOGGED)).setValue(StairBlock.FACING,currentState.getValue(StairBlock.FACING))
                .setValue(StairBlock.SHAPE,currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.HALF,currentState.getValue(StairBlock.HALF));
    }
}
