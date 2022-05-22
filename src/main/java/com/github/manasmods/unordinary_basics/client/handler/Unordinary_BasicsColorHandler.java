package com.github.manasmods.unordinary_basics.client.handler;

import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class Unordinary_BasicsColorHandler {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(Unordinary_BasicsColorHandler::onInitBlockColor);
        modEventBus.addListener(Unordinary_BasicsColorHandler::onInitItemColor);
    }

    private static void onInitBlockColor(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) : GrassColor.get(0.5D, 1.0D),
            Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
    }

    private static void onInitItemColor(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem) pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
    }

}
