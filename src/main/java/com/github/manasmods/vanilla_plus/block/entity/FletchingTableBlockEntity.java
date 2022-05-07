package com.github.manasmods.vanilla_plus.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.world.Clearable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FletchingTableBlockEntity extends BlockEntity implements Clearable {
    public FletchingTableBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(Vanilla_PlusBlockEntities.FLETCHING_TABLE_BLOCK_ENTITY, pWorldPosition, pBlockState);

    }

    @Override
    public void clearContent() {

    }
}