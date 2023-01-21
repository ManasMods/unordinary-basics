package com.github.manasmods.unordinary_basics.menu;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class BuildersGloveMenu extends AbstractContainerMenu {

    private final ItemStack backpackItem;

    public BuildersGloveMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId,inv, extraData.readVarInt());
    }

    public BuildersGloveMenu(int pContainerId, Inventory inv, int selectedSlot) {
        super(Unordinary_BasicsMenuTypes.BUILDERS_GLOVE_MENU, pContainerId);
        checkContainerSize(inv,18);

        backpackItem = inv.getItem(selectedSlot);

        backpackItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {

            for (int i = 0 ; i < 2; ++i){
                for (int k = 0 ; k < 9; ++k){
                    this.addSlot(new SlotItemHandler(handler, i * 9 + k , 8 + k * 18, 18 + i * 18){
                        @Override
                        public boolean mayPlace(@NotNull ItemStack stack) {
                            return stack.getItem() instanceof BlockItem;
                        }
                    });
                }
            }

        });

        addPlayerHotbar(inv);
        addPlayerInventory(inv);
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        backpackItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            CompoundTag tag = backpackItem.getOrCreateTag();
            ItemStackHandler stackHandler = (ItemStackHandler) handler;
            tag.put("inventory", stackHandler.serializeNBT());
        });
    }



    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 18;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            final ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < TE_INVENTORY_SLOT_COUNT) {
                if (!moveItemStackTo(itemstack1, TE_INVENTORY_SLOT_COUNT, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(itemstack1, 0, TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 68 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 126));
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
