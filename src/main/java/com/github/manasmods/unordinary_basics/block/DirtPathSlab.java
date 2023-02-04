package com.github.manasmods.unordinary_basics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DirtPathSlab extends DirtWiltableSlabBlock {

    protected static final VoxelShape FULL_BLOCK = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public DirtPathSlab(Properties pProperties) {
        super(pProperties, () -> Unordinary_BasicsBlocks.DIRT_SLAB, null);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        SlabType slabtype = pState.getValue(TYPE);
        switch (slabtype) {
            case DOUBLE:
                return FULL_BLOCK;
            case TOP:
                return TOP_AABB;
            default:
                return BOTTOM_AABB;
        }
    }
}
