package com.github.manasmods.unordinary_basics.proxy;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.gui.FletchingTableScreen;
import com.github.manasmods.unordinary_basics.gui.JukeBoxScreen;
import com.github.manasmods.unordinary_basics.handler.Unordinary_BasicsColorHandler;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisClientHandler;
import com.github.manasmods.unordinary_basics.menu.Vanilla_AdditionsMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class Unordinary_BasicsClient extends Unordinary_BasicsCommon {
    @Override
    public void preInit(IEventBus modEventBus) {
        super.preInit(modEventBus);
        Unordinary_BasicsColorHandler.register(modEventBus);
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        super.init(event);
        if(Unordinary_Basics.getInstance().getApotheosisIntegration().isPresent()){
            event.enqueueWork(() -> MinecraftForge.EVENT_BUS.addListener(ApotheosisClientHandler::onOpenApotheosisMenu));
        }
    }

    @Override
    public void clientInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> cutoutMipped(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS));
        event.enqueueWork(() -> cutoutMipped(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB));

        event.enqueueWork(() -> cutout(Unordinary_BasicsBlocks.GLASS_STAIRS, Unordinary_BasicsBlocks.GLASS_SLAB));

        event.enqueueWork(() -> translucent(
                //Stained Glass Stairs
                Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.LIME_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.PINK_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.RED_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_STAIRS,
                Unordinary_BasicsBlocks.TINTED_GLASS_STAIRS,
                //Stained Glass Slabs
                Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.LIME_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.PINK_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.RED_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_SLAB,
                Unordinary_BasicsBlocks.TINTED_GLASS_SLAB,
                //Ice
                Unordinary_BasicsBlocks.ICE_STAIRS,
                Unordinary_BasicsBlocks.ICE_SLAB
        ));

        event.enqueueWork(() -> MenuScreens.register(Vanilla_AdditionsMenuTypes.JUKE_BOX_MENU, JukeBoxScreen::new));
        event.enqueueWork(() -> MenuScreens.register(Vanilla_AdditionsMenuTypes.FLETCHING_TABLE_MENU, FletchingTableScreen::new));
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

    private void cutout(Block... blocks) {
        for (Block block : blocks) {
            cutout(block);
        }
    }

    private void translucent(Block... blocks) {
        for (Block block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucent());
        }
    }
}