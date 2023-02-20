package com.github.manasmods.unordinary_basics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PathableSlabBlock extends SlabBlock {

    public final Supplier<Block> pathVariant;

    public PathableSlabBlock(Properties pProperties, Supplier<Block> pPathVariant) {
        super(pProperties);

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
                .setValue(SlabBlock.WATERLOGGED,currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE,currentState.getValue(SlabBlock.TYPE));
    }
}
