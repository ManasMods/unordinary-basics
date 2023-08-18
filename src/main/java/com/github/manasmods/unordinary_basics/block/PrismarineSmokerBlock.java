package com.github.manasmods.unordinary_basics.block;

import com.github.manasmods.unordinary_basics.block.entity.PrismarineSmokerBlockEntity;
import com.github.manasmods.unordinary_basics.block.entity.Unordinary_BasicsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PrismarineSmokerBlock extends AbstractFurnaceBlock {

    public PrismarineSmokerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof PrismarineSmokerBlockEntity) {
            player.openMenu((MenuProvider) blockEntity);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismarineSmokerBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, Unordinary_BasicsBlockEntities.PRISMARINE_SMOKER_BLOCK_ENTITY);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d0 = pos.getX() + 0.5;
            double d1 = pos.getY();
            double d2 = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                level.playLocalSound(d0, d1, d2, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1, 1, false);
            }

            level.addParticle(ParticleTypes.SMOKE, d0, d1 + 1.1, d2, 0, 0, 0);
        }
    }
}
