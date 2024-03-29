package com.github.manasmods.unordinary_basics.integration.apotheosis;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.core.FletchingContainerAccessor;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestMenuChange;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import shadows.apotheosis.village.fletching.ApothFletchingBlock;
import shadows.apotheosis.village.fletching.FletchingContainer;

public class ApotheosisIntegration {
    
    public ApotheosisIntegration() {
        UnordinaryBasics.getLogger().info("Apotheosis integration automatically enabled!");
    }

    public void requestApotheosisFletchingMenu() {
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestMenuChange(RequestMenuChange.TargetMenu.APOTHEOSIS_FLETCHING));
    }

    public void requestUnordinaryBasicsFletchingMenu() {
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestMenuChange(RequestMenuChange.TargetMenu.UB_FLETCHING));
    }

    public void openApotheosisFletchingMenu(ServerPlayer player) {
        if (!(player.containerMenu instanceof FletchingTableMenu fletchingTableMenu)) {
            UnordinaryBasics.getLogger()
                    .error("Player {} tried to open Apotheosis Fletching Menu using MenuChange Packet but he wasn't able to send that packet. He might Cheat!", player.getStringUUID());
            return;
        }

        BlockState blockState = player.level.getBlockState(fletchingTableMenu.getPos());
        if (!(blockState.getBlock() instanceof ApothFletchingBlock fletchingBlock)) return;
        NetworkHooks.openScreen(player, fletchingBlock.getMenuProvider(blockState, player.level, fletchingTableMenu.getPos()));
    }

    public void openUnordinaryBasicsFletchingMenu(ServerPlayer player) {
        if (!(player.containerMenu instanceof FletchingContainer fletchingContainer)) {
            UnordinaryBasics.getLogger().error("Player {} tried to open UB Fletching Menu using MenuChange Packet but he wasn't able to send that packet. He might Cheat!", player.getStringUUID());
            return;
        }

        BlockPos pos = ((FletchingContainerAccessor) fletchingContainer).getPos();
        NetworkHooks.openScreen(player, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) -> new FletchingTableMenu(pContainerId, ContainerLevelAccess.create(player.level, pos), pPlayer1.getInventory(), pos),
                Component.translatable(UnordinaryBasics.MOD_ID + ".menu.fletching_table.title")), buffer -> {
            buffer.writeBlockPos(pos);
            buffer.writeResourceLocation(player.level.dimension().location());
        });
    }
}
