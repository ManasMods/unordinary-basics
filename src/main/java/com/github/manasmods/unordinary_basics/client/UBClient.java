package com.github.manasmods.unordinary_basics.client;

import com.github.manasmods.manascore.tab.InventoryTabRegistry;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.client.gui.BuildersGloveScreen;
import com.github.manasmods.unordinary_basics.client.gui.FletchingTableScreen;
import com.github.manasmods.unordinary_basics.client.gui.JukeBoxScreen;
import com.github.manasmods.unordinary_basics.entity.client.GrizzlyBearRenderer;
import com.github.manasmods.unordinary_basics.entity.client.GrolarBearRenderer;
import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsInventoryScreen;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisIntegrationClient;
import com.github.manasmods.unordinary_basics.menu.Unordinary_BasicsMenuTypes;
import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import com.github.manasmods.unordinary_basics.tab.Unordinary_BasicsInventoryTab;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UBClient {
    @SubscribeEvent
    public void init(FMLCommonSetupEvent event) {
        Unordinary_Basics.getInstance().getApotheosisIntegration().ifPresent(apotheosisIntegration -> {
            event.enqueueWork(() -> MinecraftForge.EVENT_BUS.addListener(ApotheosisIntegrationClient::onOpenApotheosisMenu));
        });
    }

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent e) {
        e.enqueueWork(UBClient::registerBlockTransparencies);
        e.enqueueWork(() -> InventoryTabRegistry.register(new Unordinary_BasicsInventoryTab(new TextComponent("Unordinary Basics"))));
        e.enqueueWork(UBClient::registerEntityRenderer);
        e.enqueueWork(UBClient::registerMenuScreens);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if(event.getAtlas().location() == Sheets.BANNER_SHEET) {
            for(BannerPattern pattern : BannerPattern.values()) {
                event.addSprite(new ResourceLocation(Unordinary_Basics.MOD_ID, "entity/banner/" + pattern.getFilename()));
            }
        }
    }

    private static void registerEntityRenderer() {
        EntityRenderers.register(UBEntityTypes.GROLAR_BEAR.get(), GrolarBearRenderer::new);
        EntityRenderers.register(UBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
    }

    private static void registerMenuScreens() {
        MenuScreens.register(Unordinary_BasicsMenuTypes.FLETCHING_TABLE_MENU, FletchingTableScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.JUKE_BOX_MENU, JukeBoxScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.INVENTORY_MENU, Unordinary_BasicsInventoryScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.BUILDERS_GLOVE_MENU, BuildersGloveScreen::new);
    }

    private static void registerBlockTransparencies(){
        cutoutMipped(Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS);
        cutoutMipped(Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB);
        cutout(Unordinary_BasicsBlocks.GLASS_STAIRS, Unordinary_BasicsBlocks.GLASS_SLAB);

        translucent(
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
        );
    }

    private static void cutoutMipped(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
    }

    private static void cutout(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
    }

    private static void cutout(Block... blocks) {
        for (Block block : blocks) {
            cutout(block);
        }
    }

    private static void translucent(Block... blocks) {
        for (Block block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucent());
        }
    }
}
