package com.github.manasmods.vanilla_plus;

import com.github.manasmods.vanilla_plus.block.Vanilla_PlusBlocks;
import com.github.manasmods.vanilla_plus.client.gui.JukeBoxScreen;
import com.github.manasmods.vanilla_plus.client.handler.Vanilla_PlusColorHandler;
import com.github.manasmods.vanilla_plus.menu.Vanilla_PlusMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Vanilla_PlusClient extends Vanilla_PlusCommon {
    @Override
    public void preInit(IEventBus modEventBus) {
        super.preInit(modEventBus);
        Vanilla_PlusColorHandler.register(modEventBus);
    }

    @Override
    public void clientInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> cutoutMipped(Vanilla_PlusBlocks.GRASS_BLOCK_STAIRS));
        event.enqueueWork(() -> cutoutMipped(Vanilla_PlusBlocks.GRASS_BLOCK_SLAB));
        event.enqueueWork(() ->cutout(Vanilla_PlusBlocks.GLASS_STAIRS));
        event.enqueueWork(() ->cutout(Vanilla_PlusBlocks.GLASS_SLAB));
        event.enqueueWork(() ->translucent(Vanilla_PlusBlocks.ICE_STAIRS));
        event.enqueueWork(() ->translucent(Vanilla_PlusBlocks.ICE_SLAB));

        event.enqueueWork(() ->MenuScreens.register(Vanilla_PlusMenuTypes.JUKE_BOX_MENU, JukeBoxScreen::new));
    }

    @Override
    public Level getLevelOrOverworld() {
        return Minecraft.getInstance().level;
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