package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.client.gui.FletchingTableScreen;
import com.github.manasmods.unordinary_basics.client.gui.JukeBoxScreen;
import com.github.manasmods.unordinary_basics.client.handler.Unordinary_BasicsColorHandler;
import com.github.manasmods.unordinary_basics.menu.Vanilla_AdditionsMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Unordinary_BasicsClient extends Unordinary_BasicsCommon {
    @Override
    public void preInit(IEventBus modEventBus) {
        super.preInit(modEventBus);
        Unordinary_BasicsColorHandler.register(modEventBus);
    }

    @Override
    public void clientInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> cutoutMipped(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS));
        event.enqueueWork(() -> cutoutMipped(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB));
        event.enqueueWork(() ->cutout(Unordinary_BasicsBlocks.GLASS_STAIRS));
        event.enqueueWork(() ->cutout(Unordinary_BasicsBlocks.GLASS_SLAB));
        event.enqueueWork(() ->translucent(Unordinary_BasicsBlocks.ICE_STAIRS));
        event.enqueueWork(() ->translucent(Unordinary_BasicsBlocks.ICE_SLAB));

        event.enqueueWork(() ->MenuScreens.register(Vanilla_AdditionsMenuTypes.JUKE_BOX_MENU, JukeBoxScreen::new));
        event.enqueueWork(() ->MenuScreens.register(Vanilla_AdditionsMenuTypes.FLETCHING_TABLE_MENU, FletchingTableScreen::new));
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