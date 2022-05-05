package com.manasmods.vanilla_plus;

import com.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import com.manasmods.vanilla_plus.client.handler.Vanilla_PlusColorHandler;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;

public class Vanilla_PlusClient extends Vanilla_PlusCommon {
    @Override
    public void preInit(IEventBus modEventBus) {
        super.preInit(modEventBus);
        Vanilla_PlusColorHandler.register(modEventBus);
    }

    @Override
    public void clientInit() {
        ItemBlockRenderTypes.setRenderLayer(Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(Vanilla_PlusBlocks.GRASS_BLOCK_SLAB, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(Vanilla_PlusBlocks.GLASS_STAIRS, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(Vanilla_PlusBlocks.GLASS_SLAB, RenderType.cutoutMipped());
    }
}