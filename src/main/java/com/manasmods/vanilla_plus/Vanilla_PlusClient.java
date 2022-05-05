package com.manasmods.vanilla_plus;

import com.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import com.manasmods.vanilla_plus.client.handler.Vanilla_PlusColorHandler;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;

public class Vanilla_PlusClient extends Vanilla_PlusCommon {
    @Override
    public void preInit(IEventBus modEventBus) {
        super.preInit(modEventBus);
        Vanilla_PlusColorHandler.register(modEventBus);
    }

    @Override
    public void clientInit() {
        cutoutMipped(Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS);
        cutoutMipped(Vanilla_PlusBlocks.GRASS_BLOCK_SLAB);
        cutout(Vanilla_PlusBlocks.GLASS_STAIRS);
        cutout(Vanilla_PlusBlocks.GLASS_SLAB);
        translucent(Vanilla_PlusBlocks.ICE_STAIRS);
        translucent(Vanilla_PlusBlocks.ICE_SLAB);
    }

    private void cutoutMipped(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
    }

    private void cutout(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
    }

    private void translucent(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucent());
    }
}