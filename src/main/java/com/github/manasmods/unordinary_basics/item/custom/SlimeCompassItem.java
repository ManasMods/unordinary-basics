package com.github.manasmods.unordinary_basics.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SlimeCompassItem extends Item {
    public SlimeCompassItem(Properties pProperties) {
        super(pProperties);
    }

    public static ChunkPos getClosestSlimeChunkPos(Entity entity, ItemStack pStack){

        if (entity != null && pStack.getOrCreateTag().contains("seed") && entity.getLevel().dimensionType().bedWorks()){
            ChunkPos entityPos = new ChunkPos(entity.getOnPos());

            long seed = pStack.getOrCreateTag().getLong("seed");

            if (WorldgenRandom.seedSlimeChunk(entityPos.x, entityPos.z, seed, 987234911L).nextInt(10) == 0){
                return entityPos;
            }

            return checkChunks(entityPos,75, seed,entity);

        }
        return null;
    }

    private static @Nullable ChunkPos checkChunks(ChunkPos pStartPos, int pLimit, long pSeed, Entity pEntity){
        int chunksChecked = 0;
        Queue<ChunkPos> positionQueue = new LinkedList<>();

        positionQueue.add(pStartPos);
        chunksChecked++;

        floodCheck:
            while (!positionQueue.isEmpty()) {
                ChunkPos startPos = positionQueue.poll();

                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && z == 0) {
                            continue;
                        }
                        if (chunksChecked >= pLimit) {
                            break floodCheck;
                        }
                        ChunkPos toProcess = new ChunkPos(startPos.x + x, startPos.z + z);
                        if (WorldgenRandom.seedSlimeChunk(toProcess.x, toProcess.z, pSeed, 987234911L).nextInt(10) == 0) {
                            return toProcess;
                        } else {
                            positionQueue.add(toProcess);
                            chunksChecked++;
                        }
                    }
                }
            }
        return null;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide){
            if (!pStack.getOrCreateTag().contains("seed")) {
                long seed = ((WorldGenLevel) pLevel).getSeed();
                pStack.getOrCreateTag().putLong("seed", seed);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (!pStack.getOrCreateTag().getBoolean("found")){
            pTooltipComponents.add(Component.translatable("warning.unordinary_basics.no_slime_chunk_found"));
        }
    }
}
