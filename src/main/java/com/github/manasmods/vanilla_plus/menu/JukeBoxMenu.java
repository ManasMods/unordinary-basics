package com.github.manasmods.vanilla_plus.menu;

import com.github.manasmods.vanilla_plus.menu.container.JukeboxContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class JukeBoxMenu extends AbstractContainerMenu {
    public JukeBoxMenu(int containerId, Inventory inventory, JukeboxContainer jukeBoxContainer) {
        super(Vanilla_PlusMenuTypes.JUKE_BOX_MENU, containerId);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
