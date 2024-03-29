package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.menu.container.FletchingContainer;
import com.github.manasmods.unordinary_basics.menu.slot.FilteredSlot;
import com.github.manasmods.unordinary_basics.menu.slot.FletchingResultSlot;
import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import com.github.manasmods.unordinary_basics.registry.UBRecipeTypeRegistry;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FletchingTableMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess levelAccess;
    private final InvWrapper playerInvWrapper;
    private final FletchingContainer craftingContainer = new FletchingContainer(this);
    private final ResultContainer resultContainer = new ResultContainer();
    private int lastHotBarIndex, lastInventoryIndex, lastCraftingIndex, resultIndex;
    private final Player player;
    @Getter
    private final BlockPos pos;


    public FletchingTableMenu(int pContainerId, ContainerLevelAccess levelAccess, Inventory inventory, BlockPos pos) {
        super(Unordinary_BasicsMenuTypes.FLETCHING_TABLE_MENU, pContainerId);
        this.levelAccess = levelAccess;
        this.playerInvWrapper = new InvWrapper(inventory);
        this.player = inventory.player;
        this.pos = pos;

        setupFletchingTableSlots();
        setupPlayerSlots();
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack slotItemStack = slots.get(pIndex).getItem();
        if (slotItemStack.isEmpty()) return ItemStack.EMPTY;

        if (pIndex < resultIndex) {
            //Handle Fletching Table Inventory Shift-Click
            slotItemStack = moveToPlayerInventory(slotItemStack);
        } else {
            if (pIndex == resultIndex) {
                slots.get(pIndex).onQuickCraft(ItemStack.EMPTY, slotItemStack);
                return ItemStack.EMPTY;
            } else {
                //Handle Inventory Shift-Click
                slotItemStack = moveToFletchingTableInventory(slotItemStack);
            }
        }

        Slot slot = slots.get(pIndex);
        slot.set(slotItemStack);
        slot.setChanged();
        return ItemStack.EMPTY;
    }

    private void setupFletchingTableSlots() {
        int slotIndex = 0;
        int baseY = 21;

        //left slots
        for (int i = 0; i < 3; i++) {
            addSlot(new FilteredSlot(this.craftingContainer, slotIndex++, 29, baseY + 21 * i));
        }

        //right slots
        for (int i = 0; i < 3; i++) {
            addSlot(new FilteredSlot(this.craftingContainer, slotIndex++, 50, baseY + 21 * i));
        }

        lastCraftingIndex = slots.size();

        //result
        Slot slot = this.addSlot(new FletchingResultSlot(player, craftingContainer, this.resultContainer, slotIndex, 111, 41));
        resultIndex = slot.index;
    }

    private void setupPlayerSlots() {
        int index = 0;

        //Hot bar
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(this.playerInvWrapper, index++, 8 + 18 * col, 100 + 18 * 3));
        }
        lastHotBarIndex = slots.size();

        //Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new SlotItemHandler(this.playerInvWrapper, index++, 8 + 18 * col, 96 + 18 * row));
            }
        }
        lastInventoryIndex = slots.size();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, Blocks.FLETCHING_TABLE);
    }

    private ItemStack moveToPlayerInventory(int slotIndex, ItemStack itemStack) {
        Slot currentCurrentSlot = getSlot(slotIndex);
        //Check if slot exist
        if (currentCurrentSlot == null) return itemStack;
        //Try to place Item into the Player Slot
        if (currentCurrentSlot.mayPlace(itemStack)) {
            itemStack = currentCurrentSlot.safeInsert(itemStack);
            currentCurrentSlot.setChanged();
        }

        return itemStack;
    }

    private ItemStack moveToPlayerInventory(ItemStack stack) {
        //Check Space in hot-bar
        for (int i = resultIndex; i < lastHotBarIndex; i++) {
            stack = moveToPlayerInventory(i, stack);
            //Check if there are no items left
            if (stack.isEmpty()) {
                break;
            }
        }

        //Check Space in Inventory
        if (!stack.isEmpty()) {
            for (int i = lastHotBarIndex; i < lastInventoryIndex; i++) {
                stack = moveToPlayerInventory(i, stack);
                //Check if there are no items left
                if (stack.isEmpty()) {
                    break;
                }
            }
        }

        return stack;
    }

    private ItemStack moveToFletchingTableInventory(ItemStack stack) {
        //Handle Player Inventory Shift-Click
        for (int i = 0; i < lastCraftingIndex; i++) {
            Slot currentCurrentSlot = getSlot(i);
            //Check if slot exist
            if (currentCurrentSlot == null) break;

            //Try to place Item into the Player Slot
            if (currentCurrentSlot.mayPlace(stack)) {
                stack = currentCurrentSlot.safeInsert(stack);
                currentCurrentSlot.setChanged();

            }
        }
        return stack;
    }

    @Override
    public void slotsChanged(Container pInventory) {
        levelAccess.execute((level, pos) -> slotChangedCraftingGrid(this, level, this.player, craftingContainer, resultContainer));
        super.slotsChanged(pInventory);
    }

    protected static void slotChangedCraftingGrid(FletchingTableMenu menu, Level level, Player player, FletchingContainer craftingContainer, ResultContainer resultContainer) {
        if (player instanceof ServerPlayer serverPlayer) {
            ItemStack itemstack = ItemStack.EMPTY;

            Optional<FletchingRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(UBRecipeTypeRegistry.FLETCHING_RECIPE.get(), craftingContainer, level);
            if (optional.isPresent()) {
                FletchingRecipe fletchingRecipe = optional.get();
                if (resultContainer.setRecipeUsed(level, serverPlayer, fletchingRecipe)) {
                    itemstack = fletchingRecipe.assemble(craftingContainer);
                }
            }

            resultContainer.setItem(0, itemstack);
            menu.setRemoteSlot(0, itemstack);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), menu.resultIndex, itemstack));
        }
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.levelAccess.execute((level, pos) -> this.clearContainer(pPlayer, craftingContainer));
    }

    public List<Slot> getInputSlots() {
        List<Slot> inputSlots = new ArrayList<>();
        for (int i = 0; i < lastCraftingIndex; i++) {
            inputSlots.add(slots.get(i));
        }

        return inputSlots;
    }

    public List<Slot> getInventorySlots() {
        List<Slot> invSlots = new ArrayList<>();
        for (int i = resultIndex + 1; i < lastInventoryIndex; i++) {
            invSlots.add(slots.get(i));
        }

        return invSlots;
    }
}

