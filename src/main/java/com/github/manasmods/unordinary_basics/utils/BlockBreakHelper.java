package com.github.manasmods.unordinary_basics.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BlockBreakHelper {

    /**
     * Breaks all blocks that are of the same type, near one specified block. <br>
     * Uses the "forest fire" algorithm - creating a square break area
     * @param pBlockLimit Limit of how many near blocks'll be broken
     * @param pPos Position where the flood mine will start
     * @param pLevel Level where the blocks are located
     * @param pSpawnPos Position where the drops of the broken blocks'll spawn
     * @param pTool Tool that that was used to break the blocks
     * @param pEntity Entity that is breaking the blocks
     * @param pBlock Type of the blocks being broken
     * @param doChecks Will check if the block being mined by the tool can actually be mined for the specific tool passed in - should usually be true
     */
    public static void floodMineOnBlock(int pBlockLimit, BlockPos pPos, Level pLevel, BlockPos pSpawnPos, ItemStack pTool, LivingEntity pEntity, Block pBlock, boolean doChecks){
        int blocksBroken = 0;
        Queue<BlockPos> positionQueue = new LinkedList<>();
        List<ItemStack> drops = new LinkedList<>();

        positionQueue.add(pPos);
        blocksBroken++;

        floodBreak:
        while (!positionQueue.isEmpty()){
            BlockPos startPos = positionQueue.poll();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0){continue;}
                        if (blocksBroken >= pBlockLimit){break floodBreak;}
                        BlockPos toProcess = startPos.offset(x,y,z);
                        if (pLevel.getBlockState(toProcess).getBlock() == pBlock){
                            drops.addAll(breakBlockAndReturnDrops(toProcess,pLevel,pSpawnPos,startPos,pTool,pEntity,doChecks));
                            positionQueue.add(toProcess);
                            blocksBroken++;
                        }
                    }
                }
            }
        }
        drops.forEach(itemStack -> {
            ItemEntity entity = new ItemEntity(pLevel,pSpawnPos.getX(),pSpawnPos.getY(),pSpawnPos.getZ(),itemStack);
            pLevel.addFreshEntity(entity);
        });
    }

    /**
     * breakBlockAndReturnDrops method by Melanx <br>
     * https://github.com/MelanX/MoreVanillaLib <br> <br>
     *
     * Under Apache 2.0 license <br>
     * https://www.apache.org/licenses/LICENSE-2.0.txt <br>
     */
    public static List<ItemStack> breakBlockAndReturnDrops(BlockPos blockPos, Level pLevel, BlockPos spawnPos, BlockPos originPos, ItemStack pTool, LivingEntity pEntity, boolean doChecks){
        Player player = (Player) pEntity;
        LootContext.Builder lootContextBuilder =
                (new LootContext.Builder((ServerLevel)pLevel)).withRandom(pLevel.random).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(spawnPos))
                        .withParameter(LootContextParams.TOOL, pTool).withOptionalParameter(LootContextParams.THIS_ENTITY, pEntity);
        List<ItemStack> list = pLevel.getBlockState(blockPos).getDrops(lootContextBuilder);
        BlockState state = pLevel.getBlockState(blockPos);
        if (!blockPos.equals(originPos)) {
            if (pTool.isCorrectToolForDrops(state) || !doChecks) {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                if (player.getAbilities().instabuild) {
                    if (state.onDestroyedByPlayer(pLevel, blockPos, player, true, state.getFluidState()))
                        state.getBlock().destroy(pLevel, blockPos, state);
                } else {
                    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(pLevel, blockPos, state, player);
                    MinecraftForge.EVENT_BUS.post(event);

                    if (event.isCanceled()) {
                        // Forge copy
                        serverPlayer.connection.send(new ClientboundBlockUpdatePacket(pLevel, blockPos));
                        BlockEntity tile = pLevel.getBlockEntity(blockPos);
                        if (tile != null) {
                            Packet<?> packet = tile.getUpdatePacket();
                            if (packet != null) {
                                serverPlayer.connection.send(packet);
                            }
                        }
                    } else {
                        pTool.getItem().mineBlock(pTool, pLevel, state, blockPos, player);
                        state.getBlock().destroy(pLevel, blockPos, state);
                        state.getBlock().popExperience((ServerLevel) pLevel, blockPos, event.getExpToDrop());

                        pLevel.removeBlock(blockPos, false);
                        pLevel.levelEvent(2001, blockPos, Block.getId(state));
                        serverPlayer.connection.send(new ClientboundBlockUpdatePacket(pLevel, blockPos));
                    }
                }
            }
        }
        return list;
    }

    public static void floodTillOnBlock(int pBlockLimit, BlockPos pPos, UseOnContext pContext, Block pBlock){
        int blocksTilled = 0;
        Level pLevel = pContext.getLevel();
        Queue<BlockPos> positionQueue = new LinkedList<>();

        positionQueue.add(pPos);
        blocksTilled++;

        floodBreak:
        while (!positionQueue.isEmpty()){
            BlockPos startPos = positionQueue.poll();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;
                        BlockPos toProcess = startPos.offset(x,y,z);
                        if (!pLevel.getBlockState(toProcess.above()).isAir()) continue;
                        if (blocksTilled >= pBlockLimit) break floodBreak;

                        if (pLevel.getBlockState(toProcess).getBlock() == pBlock){
                            tillBlock(toProcess, pContext, startPos);
                            positionQueue.add(toProcess);
                            blocksTilled++;
                        }
                    }
                }
            }
        }
    }

    public static void tillBlock(BlockPos blockpos, UseOnContext pContext, BlockPos originPos){
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockState state = level.getBlockState(blockpos);

        if (!blockpos.equals(originPos)) {
            BlockEvent.BlockToolModificationEvent event = new BlockEvent.BlockToolModificationEvent(state, pContext, ToolActions.HOE_TILL, false);
            MinecraftForge.EVENT_BUS.post(event);

            if (!event.isCanceled()) {
                BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(pContext, ToolActions.HOE_TILL, false);

                Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null :
                        Pair.of(ctx -> true, changeIntoState(toolModifiedState, blockpos));
                if (pair == null) return;

                Predicate<UseOnContext> predicate = pair.getFirst();
                Consumer<UseOnContext> consumer = pair.getSecond();
                if (predicate.test(pContext)) {
                    level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    consumer.accept(pContext);
                    if (player != null) {
                        pContext.getItemInHand().hurtAndBreak(1, player, (breakEvent) -> breakEvent.broadcastBreakEvent(pContext.getHand()));
                    }
                }
            }
        }
    }

    public static Consumer<UseOnContext> changeIntoState(BlockState pState, BlockPos pos) {
        return (context) -> {
            context.getLevel().setBlock(pos, pState, 11);
            context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(context.getPlayer(), pState));
        };
    }
}
