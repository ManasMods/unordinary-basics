package com.github.manasmods.unordinary_basics.client;

import com.github.manasmods.manascore.client.keybinding.KeybindingRegistry;
import com.github.manasmods.manascore.tab.InventoryTabRegistry;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.block.entity.Unordinary_BasicsBlockEntities;
import com.github.manasmods.unordinary_basics.client.block_entity_renderer.ItemSorterBlockEntityRenderer;
import com.github.manasmods.unordinary_basics.client.gui.*;
import com.github.manasmods.unordinary_basics.client.keybind.UBKeybindings;
import com.github.manasmods.unordinary_basics.integration.apotheosis.ApotheosisIntegrationClient;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.item.custom.SlimeCompassItem;
import com.github.manasmods.unordinary_basics.menu.Unordinary_BasicsMenuTypes;
import com.github.manasmods.unordinary_basics.tab.Unordinary_BasicsInventoryTab;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        e.enqueueWork(() -> InventoryTabRegistry.register(new Unordinary_BasicsInventoryTab(Component.literal("Unordinary Basics"))));
        e.enqueueWork(UBClient::registerEntityRenderer);
        e.enqueueWork(UBClient::registerMenuScreens);
        e.enqueueWork(UBClient::registerItemProperties);
        e.enqueueWork(UBClient::registerKeybinds);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Unordinary_BasicsBlockEntities.ITEM_SORTER_BLOCK_ENTITY,
                ItemSorterBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if(event.getAtlas().location() == Sheets.BANNER_SHEET) {
            for(BannerPattern pattern : BannerPattern.values()) {
                event.addSprite(new ResourceLocation(Unordinary_Basics.MOD_ID, "entity/banner/" + pattern.getFilename()));
            }
        }

        if (event.getAtlas().location() == InventoryMenu.BLOCK_ATLAS) {
            List<String> registerValues = List.of(
                    "waist",
                    "back_pack",
                    "back_quiver",
                    "back_wings",
                    "potion_belt"
            );

            for (String s : registerValues) {
                event.addSprite(new ResourceLocation(Unordinary_Basics.MOD_ID, "item/empty_slots/empty_armor_slot_" + s));
            }
        }
    }

    private static void registerKeybinds(){
        KeybindingRegistry.register(
                UBKeybindings.SET_PRONE,
                UBKeybindings.OPEN_UB_INV
        );
    }

    private static void registerEntityRenderer() {
//        EntityRenderers.register(UBEntityTypes.GROLAR_BEAR.get(), GrolarBearRenderer::new);
//        EntityRenderers.register(UBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearRenderer::new);
    }

    private static void registerMenuScreens() {
        MenuScreens.register(Unordinary_BasicsMenuTypes.FLETCHING_TABLE_MENU, FletchingTableScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.JUKE_BOX_MENU, JukeBoxScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.INVENTORY_MENU, Unordinary_BasicsInventoryScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.BUILDERS_GLOVE_MENU, BuildersGloveScreen::new);
        MenuScreens.register(Unordinary_BasicsMenuTypes.ITEM_SORTER_MENU, ItemSorterScreen::new);
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

    //It may be a good idea to make this its own class - taken straight from the vanilla compass
    private static void registerItemProperties(){
        ItemProperties.register(Unordinary_BasicsItems.SLIME_COMPASS, new ResourceLocation("angle"), new ClampedItemPropertyFunction() {
            private final CompassWobble wobble = new CompassWobble();
            private final CompassWobble wobbleRandom = new CompassWobble();

            public float unclampedCall(ItemStack stack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity pEntity, int pSeed) {
                Entity entity = (Entity)(pEntity != null ? pEntity : stack.getEntityRepresentation());
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (clientLevel == null && entity.level instanceof ClientLevel) {
                        clientLevel = (ClientLevel)entity.level;
                    }

                    BlockPos blockpos = this.getChunkPosition(entity,stack);
                    long i = clientLevel.getGameTime();
                    if (blockpos != null && !(entity.position().distanceToSqr((double)blockpos.getX() + 0.5D, entity.position().y(), (double)blockpos.getZ() + 0.5D) < (double)1.0E-5F)) {
                        boolean flag = pEntity instanceof Player && ((Player)pEntity).isLocalPlayer();
                        double d1 = 0.0D;
                        if (flag) {
                            d1 = (double)pEntity.getYRot();
                        } else if (entity instanceof ItemFrame) {
                            d1 = getFrameRotation((ItemFrame)entity);
                        } else if (entity instanceof ItemEntity) {
                            d1 = (double)(180.0F - ((ItemEntity)entity).getSpin(0.5F) / ((float)Math.PI * 2F) * 360.0F);
                        } else if (pEntity != null) {
                            d1 = (double)pEntity.yBodyRot;
                        }

                        d1 = Mth.positiveModulo(d1 / 360.0D, 1.0D);
                        double d2 = this.getAngleTo(Vec3.atCenterOf(blockpos), entity) / (double)((float)Math.PI * 2F);
                        double d3;
                        if (flag) {
                            if (this.wobble.shouldUpdate(i)) {
                                this.wobble.update(i, 0.5D - (d1 - 0.25D));
                            }

                            d3 = d2 + this.wobble.rotation;
                        } else {
                            d3 = 0.5D - (d1 - 0.25D - d2);
                        }

                        return Mth.positiveModulo((float)d3, 1.0F);
                    } else {
                        if (this.wobbleRandom.shouldUpdate(i)) {
                            this.wobbleRandom.update(i, Math.random());
                        }

                        double d0 = this.wobbleRandom.rotation + (double)((float)this.hash(pSeed) / 2.14748365E9F);
                        return Mth.positiveModulo((float)d0, 1.0F);
                    }
                }
            }

            private int hash(int p_174670_) {
                return p_174670_ * 1327217883;
            }

            @Nullable
            private BlockPos getChunkPosition(Entity pEntity, ItemStack pStack) {
                ChunkPos foundChunk = SlimeCompassItem.getClosestSlimeChunkPos(pEntity,pStack);

                if (foundChunk != null) {
                    pStack.getOrCreateTag().putBoolean("found",true);
                    return foundChunk.getMiddleBlockPosition(pEntity.getBlockY());
                } else pStack.getOrCreateTag().putBoolean("found",false);

                return null;
            }

            private double getAngleTo(Vec3 vec3, Entity entity) {
                return Math.atan2(vec3.z() - entity.getZ(), vec3.x() - entity.getX());
            }

            private double getFrameRotation(ItemFrame p_117914_) {
                Direction direction = p_117914_.getDirection();
                int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
                return (double)Mth.wrapDegrees(180 + direction.get2DDataValue() * 90 + p_117914_.getRotation() * 45 + i);
            }

            @OnlyIn(Dist.CLIENT)
            static class CompassWobble {
                double rotation;
                private double deltaRotation;
                private long lastUpdateTick;

                boolean shouldUpdate(long pGameTime) {
                    return this.lastUpdateTick != pGameTime;
                }

                void update(long pGameTime, double pWobbleAmount) {
                    this.lastUpdateTick = pGameTime;
                    double d0 = pWobbleAmount - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
                    this.deltaRotation += d0 * 0.1D;
                    this.deltaRotation *= 0.8D;
                    this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
                }
            }
        });

        ItemProperties.register(Unordinary_BasicsItems.QUIVER,new ResourceLocation("filled"),(stack,level,entity,seed) -> {
            AtomicBoolean isFilled = new AtomicBoolean();

            stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                for (int i = 0; i < handler.getSlots(); ++i){
                    if (!handler.getStackInSlot(i).isEmpty()) {
                        isFilled.set(true);
                        break;
                    }
                }
            });

            return isFilled.get() ? 1.0F : 0F;
        });
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
