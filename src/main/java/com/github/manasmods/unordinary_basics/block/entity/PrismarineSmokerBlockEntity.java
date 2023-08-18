package com.github.manasmods.unordinary_basics.block.entity;

import com.github.manasmods.unordinary_basics.menu.PrismarineSmokerMenu;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRecipeTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PrismarineSmokerBlockEntity extends AbstractFurnaceBlockEntity {

    public PrismarineSmokerBlockEntity(BlockPos pos, BlockState state) {
        super(Unordinary_BasicsBlockEntities.PRISMARINE_SMOKER_BLOCK_ENTITY, pos, state, Unordinary_BasicsRecipeTypeRegistry.PRISMARINE_SMOKING_RECIPE.get());
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.unordinary_basics.prismarine_smoker");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new PrismarineSmokerMenu(containerId, inventory, this.dataAccess);
    }
}
