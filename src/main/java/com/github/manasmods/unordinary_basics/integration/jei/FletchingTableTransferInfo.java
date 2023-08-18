package com.github.manasmods.unordinary_basics.integration.jei;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.github.manasmods.unordinary_basics.menu.Unordinary_BasicsMenuTypes;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

import java.util.List;
import java.util.Optional;

public class FletchingTableTransferInfo implements IRecipeTransferInfo<FletchingTableMenu, FletchingRecipe> {
    @Override
    public Class<FletchingTableMenu> getContainerClass() {
        return FletchingTableMenu.class;
    }

    @Override
    public Optional<MenuType<FletchingTableMenu>> getMenuType() {
        return Optional.of(Unordinary_BasicsMenuTypes.FLETCHING_TABLE_MENU);
    }

    @Override
    public RecipeType<FletchingRecipe> getRecipeType() {
        return RecipeType.create(Unordinary_Basics.MOD_ID,"fletching",FletchingRecipe.class);
    }

    @Override
    public boolean canHandle(FletchingTableMenu container, FletchingRecipe recipe) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(FletchingTableMenu container, FletchingRecipe recipe) {
        return container.getInputSlots();
    }

    @Override
    public List<Slot> getInventorySlots(FletchingTableMenu container, FletchingRecipe recipe) {
        return container.getInventorySlots();
    }


}
