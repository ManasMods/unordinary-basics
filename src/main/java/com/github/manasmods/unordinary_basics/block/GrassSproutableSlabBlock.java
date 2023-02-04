package com.github.manasmods.unordinary_basics.block;

import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

import java.util.Random;
import java.util.function.Supplier;

public class GrassSproutableSlabBlock extends PathableSlabBlock {

    public final Supplier<Block> blockTypeGrass;
    public final Supplier<Block> blockTypeMycelium;
    public final Supplier<Block> blockTypePodzol;

    public GrassSproutableSlabBlock(Properties pProperties, Supplier<Block> pBlockTypeMycelium, Supplier<Block> pBlockTypeGrass, Supplier<Block> pBlockTypePodzol, Supplier<Block> pBlockTypePath) {
        super(pProperties,pBlockTypePath);

        this.blockTypeMycelium = pBlockTypeMycelium;
        this.blockTypeGrass = pBlockTypeGrass;
        this.blockTypePodzol = pBlockTypePodzol;
    }

    private void trySprout(Level pLevel, BlockPos pPos){

        if (!pLevel.getBlockState(pPos.above()).getCollisionShape(pLevel,pPos.above()).isEmpty()) return;

        boolean grassExists = false;
        boolean myceliumExists = false;
        boolean podzolExists = false;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .is(UBTags.Blocks.GRASS_BLOCKS)){
                        grassExists = true;
                    } else if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .is(UBTags.Blocks.MYCELIUM_BLOCKS)){
                        myceliumExists = true;
                        break;
                    } else if (pLevel.getBlockState(new BlockPos(pPos.getX() + x, pPos.getY() + y, pPos.getZ() + z))
                            .is(UBTags.Blocks.PODZOL_BLOCKS)){
                        podzolExists = true;
                    }
                }
            }
        }

        BlockState currentState = pLevel.getBlockState(pPos);

        if (myceliumExists){
            pLevel.setBlockAndUpdate(pPos,this.blockTypeMycelium.get().defaultBlockState()
                    .setValue(SlabBlock.WATERLOGGED,currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE,currentState.getValue(SlabBlock.TYPE)));
        } else if (podzolExists) {
            pLevel.setBlockAndUpdate(pPos,this.blockTypePodzol.get().defaultBlockState()
                    .setValue(SlabBlock.WATERLOGGED,currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE,currentState.getValue(SlabBlock.TYPE)));
        } else if (grassExists) {
            pLevel.setBlockAndUpdate(pPos,this.blockTypeGrass.get().defaultBlockState()
                    .setValue(SlabBlock.WATERLOGGED,currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE,currentState.getValue(SlabBlock.TYPE)));
        }

    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pRandom.nextBoolean()) {
            trySprout(pLevel, pPos);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
