package com.github.manasmods.unordinary_basics.integration.jei;

import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;

import java.util.List;

public class FletchingTableTransferInfo implements IRecipeTransferInfo<FletchingTableMenu, FletchingRecipe> {
    @Override
    public Class<FletchingTableMenu> getContainerClass() {
        return FletchingTableMenu.class;
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

    @Override
    public Class<FletchingRecipe> getRecipeClass() {
        return FletchingRecipe.class;
    }

    @Override
    public ResourceLocation getRecipeCategoryUid() {
        return FletchingTableRecipeCategory.UID;
    }
}
