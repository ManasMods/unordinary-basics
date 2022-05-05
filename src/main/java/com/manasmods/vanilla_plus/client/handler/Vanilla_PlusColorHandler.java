package com.manasmods.vanilla_plus.client.handler;

import com.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class Vanilla_PlusColorHandler {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(Vanilla_PlusColorHandler::onInitBlockColor);
        modEventBus.addListener(Vanilla_PlusColorHandler::onInitItemColor);
    }

    private static void onInitBlockColor(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) : GrassColor.get(0.5D, 1.0D),
            Vanilla_PlusBlocks.GRASS_BLOCK_SLAB, Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS);
    }

    private static void onInitItemColor(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem) pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, Vanilla_PlusBlocks.GRASS_BLOCK_SLAB, Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS);
    }
}
