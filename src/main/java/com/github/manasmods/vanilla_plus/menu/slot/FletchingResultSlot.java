package com.github.manasmods.vanilla_plus.menu.slot;

import com.github.manasmods.vanilla_plus.menu.container.FletchingContainer;
import com.github.manasmods.vanilla_plus.registry.Vanilla_PlusRecipeTypeRegistry;
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
        NonNullList<ItemStack> remainingItems = pPlayer.level.getRecipeManager().getRemainingItemsFor(Vanilla_PlusRecipeTypeRegistry.FLETCHING_RECIPE.get(), this.container, pPlayer.level);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < remainingItems.size(); i++) {
            container.setItem(i, remainingItems.get(i));
        }
    }
}
