package com.github.manasmods.unordinary_basics.menu.slot;

import com.github.manasmods.unordinary_basics.menu.container.FletchingContainer;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRecipeTypeRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;

public class FletchingResultSlot extends ResultSlot {
    private final FletchingContainer container;
    private final Player player;

    public FletchingResultSlot(Player player, FletchingContainer container, Container playerInventory, int index,
                               int x, int y) {
        super(player, container, playerInventory, index, x, y);
        this.container = container;
        this.player = player;
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(pPlayer);
        NonNullList<ItemStack> remainingItems = pPlayer.level.getRecipeManager().getRemainingItemsFor(Unordinary_BasicsRecipeTypeRegistry.FLETCHING_RECIPE.get(), this.container, pPlayer.level);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < remainingItems.size(); i++) {
            container.setItem(i, remainingItems.get(i));
        }
    }

    @Override
    protected void onQuickCraft(ItemStack pStack, int pAmount) {
        if (pStack.isEmpty()) return;
        pStack.setCount(pAmount);
        ItemStack craftResult = pStack.copy();

        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(player);
        NonNullList<ItemStack> remainingItems = player.level.getRecipeManager().getRemainingItemsFor(Unordinary_BasicsRecipeTypeRegistry.FLETCHING_RECIPE.get(), this.container, player.level);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < remainingItems.size(); i++) {
            container.setItem(i, remainingItems.get(i));
        }

        while (player.level.getRecipeManager().getRecipeFor(Unordinary_BasicsRecipeTypeRegistry.FLETCHING_RECIPE.get(), container, player.level).isPresent() &&
            player.getInventory().add(craftResult.copy()) && pStack.getCount() + pAmount < 65) {

            net.minecraftforge.common.ForgeHooks.setCraftingPlayer(player);
            remainingItems = player.level.getRecipeManager().getRemainingItemsFor(Unordinary_BasicsRecipeTypeRegistry.FLETCHING_RECIPE.get(), this.container, player.level);
            net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

            for (int i = 0; i < remainingItems.size(); i++) {
                container.setItem(i, remainingItems.get(i));
            }

            pStack.grow(pAmount);
        }

        pStack.shrink(pAmount);
    }
}
