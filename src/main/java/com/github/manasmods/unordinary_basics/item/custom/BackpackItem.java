package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.ItemStackHandlerCapabilityProvider;
import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsItemInventoryScreen;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.menu.UBItemInventoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"ConstantConditions"})
public class BackpackItem extends Item implements IUBInventoryItem {
    public static final ResourceLocation TEXTURE = new ResourceLocation(UnordinaryBasics.MOD_ID, "textures/gui/backpack_slots.png");
    public BackpackItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public CapabilityUBInventory.UBSlot getSlot() {
        return CapabilityUBInventory.UBSlot.BACK;
    }

    @Override
    public int getSlotCount() {
        return 24;
    }

    @Override
    public void renderUsed(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, Unordinary_BasicsItemInventoryScreen screen) {
        int imageWidth = 65;
        int imageHeight = 166;

        int x = (screen.width - 176) / 2;
        int y = (screen.height - 166) / 2;

        RenderSystem.setShaderTexture(0,TEXTURE);
        screen.blit(pPoseStack,x - imageWidth + 4,y,0,0,imageWidth,imageHeight);
    }

    @Override
    public void addSlots(int windowId, Inventory inventory, Player player, UBItemInventoryMenu menu, ItemStack stack) {
        stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            if (handler instanceof ItemStackHandler){
                ItemStackHandler stackHandler = (ItemStackHandler) handler;
                int index = 0;
                for (int col = 0; col < 3; ++col){
                    for (int row = 0; row < 8; ++row){
                        menu.addSlotEx(new SlotItemHandler(stackHandler,index,8 + col * 18 - 64 + 3,12 + row * 18){
                            @Override
                            public boolean mayPlace(@NotNull ItemStack stack) {
                                if (stack.getItem().equals(Unordinary_BasicsItems.CHEST_BACKPACK) || stack.getItem().equals(Unordinary_BasicsItems.BARREL_BACKPACK)) return false;
                                return super.mayPlace(stack);
                            }
                        });
                        ++index;
                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ItemStackHandlerCapabilityProvider(new ItemStackHandler(24){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return !stack.getItem().equals(Unordinary_BasicsItems.BARREL_BACKPACK) && !stack.getItem().equals(Unordinary_BasicsItems.CHEST_BACKPACK);
            }
        });
    }
}
