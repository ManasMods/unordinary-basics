package com.github.manasmods.vanilla_plus.menu;

import com.github.manasmods.vanilla_plus.menu.container.JukeboxContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.Blocks;

public class JukeBoxMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess levelAccess;

    public JukeBoxMenu(int containerId, Inventory inventory, JukeboxContainer jukeBoxContainer, ContainerLevelAccess levelAccess) {
        super(Vanilla_PlusMenuTypes.JUKE_BOX_MENU, containerId);
        this.levelAccess = levelAccess;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, Blocks.JUKEBOX);
    }
}
