package com.github.manasmods.unordinary_basics.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class PrismarineSmokerMenu extends AbstractFurnaceMenu {

    public PrismarineSmokerMenu(int pContainerId, Inventory pPlayerInventory, ContainerData extraData) {
        super(MenuType.SMOKER, RecipeType.SMOKING, RecipeBookType.SMOKER, pContainerId, pPlayerInventory);
    }
}
