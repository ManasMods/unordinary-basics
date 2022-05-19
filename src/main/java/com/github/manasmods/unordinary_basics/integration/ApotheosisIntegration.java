package com.github.manasmods.unordinary_basics.integration;

import com.github.manasmods.manascore.client.gui.widget.ImagePredicateButton;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.core.AbstractContainerScreenAccessor;
import com.github.manasmods.unordinary_basics.core.FletchingContainerAccessor;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestMenuChange;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.network.NetworkHooks;
import shadows.apotheosis.village.fletching.ApothFletchingBlock;
import shadows.apotheosis.village.fletching.FletchingContainer;
import shadows.apotheosis.village.fletching.FletchingScreen;

public class ApotheosisIntegration {
    private static final ResourceLocation UNORDINARY_BASICS_LOGO = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/apotheosis_logo.png");

    public ApotheosisIntegration() {
        Unordinary_Basics.getLogger().info("Apotheosis integration automatically enabled!");
    }

    public void requestApotheosisFletchingMenu() {
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestMenuChange(RequestMenuChange.TargetMenu.APOTHEOSIS_FLETCHING));
    }

    public void requestUnordinaryBasicsFletchingMenu() {
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestMenuChange(RequestMenuChange.TargetMenu.UB_FLETCHING));
    }

    public void openApotheosisFletchingMenu(ServerPlayer player) {
        if (!(player.containerMenu instanceof FletchingTableMenu fletchingTableMenu)) {
            Unordinary_Basics.getLogger()
                    .error("Player {} tried to open Apotheosis Fletching Menu using MenuChange Packet but he wasn't able to send that packet. He might Cheat!", player.getStringUUID());
            return;
        }

        BlockState blockState = player.level.getBlockState(fletchingTableMenu.getPos());
        if (!(blockState.getBlock() instanceof ApothFletchingBlock fletchingBlock)) return;
        NetworkHooks.openGui(player, fletchingBlock.getMenuProvider(blockState, player.level, fletchingTableMenu.getPos()));
    }

    public void openUnordinaryBasicsFletchingMenu(ServerPlayer player) {
        if (!(player.containerMenu instanceof FletchingContainer fletchingContainer)) {
            Unordinary_Basics.getLogger().error("Player {} tried to open UB Fletching Menu using MenuChange Packet but he wasn't able to send that packet. He might Cheat!", player.getStringUUID());
            return;
        }

        BlockPos pos = ((FletchingContainerAccessor) fletchingContainer).getPos();
        NetworkHooks.openGui(player, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) -> new FletchingTableMenu(pContainerId, ContainerLevelAccess.create(player.level, pos), pPlayer1.getInventory(), pos), new TranslatableComponent(Unordinary_Basics.MOD_ID + ".menu.fletching_table.title")), buffer -> {
            buffer.writeBlockPos(pos);
            buffer.writeResourceLocation(player.level.dimension().location());
        });
    }

    public void onOpenApotheosisMenu(final ScreenEvent.InitScreenEvent.Post e) {
        if (!(e.getScreen() instanceof FletchingScreen screen)) return;
        int imageWidth = ((AbstractContainerScreenAccessor) screen).getImageWidth();
        e.addListener(new ImagePredicateButton(screen.getGuiLeft() + imageWidth - 24 - 4, screen.getGuiTop() + 6, 24, 24, UNORDINARY_BASICS_LOGO, pButton -> {
            Unordinary_Basics.getInstance().getApotheosisIntegration().ifPresent(ApotheosisIntegration::requestUnordinaryBasicsFletchingMenu);
        }, (pButton, pPoseStack, pMouseX, pMouseY) -> {
            screen.renderTooltip(pPoseStack, new TranslatableComponent(Unordinary_Basics.MOD_ID + ".menu.fletching_table.switch.ub"), pMouseX, pMouseY);
        }, () -> Unordinary_Basics.getInstance().getApotheosisIntegration().isPresent()));
    }
}
