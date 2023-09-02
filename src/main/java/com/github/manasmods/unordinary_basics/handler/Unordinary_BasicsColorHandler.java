package com.github.manasmods.unordinary_basics.handler;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnordinaryBasics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Unordinary_BasicsColorHandler {
    @SubscribeEvent
    public static void onInitBlockColor(final RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) : GrassColor.get(0.5D, 1.0D),
            Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
    }

    @SubscribeEvent
    public static void onInitItemColor(final RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem) pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
    }
}
