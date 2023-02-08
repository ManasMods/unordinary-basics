package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.ItemStackHandlerCapabilityProvider;
import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsInventoryScreen;
import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.menu.slot.IUBItemSlot;
import com.github.manasmods.unordinary_basics.menu.slot.UBItemSlot;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class BackpackItem extends Item implements IUBInventoryItem {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/backpack_slots.png");
    public BackpackItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public CapabilityUBInventory.UBSlot getSlot() {
        return CapabilityUBInventory.UBSlot.BACK;
    }

    @Override
    public void renderUsed(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, Unordinary_BasicsInventoryScreen screen) {
        int imageWidth = 64;
        int imageHeight = 165;

        int x = (screen.width - 176) / 2;
        int y = (screen.height - 166) / 2;
        RenderSystem.setShaderTexture(0,TEXTURE);
        screen.blit(pPoseStack,x - 64,y,0,0,imageWidth,imageHeight);
    }

    @Override
    public void addSlots(int windowId, Inventory inventory, Player player, UBInventoryMenu menu, ItemStack stack) {
        stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            if (handler instanceof ItemStackHandler){
                ItemStackHandler stackHandler = (ItemStackHandler) handler;
                int index = 0;
                for (int col = 0; col < 3; ++col){
                    for (int row = 0; row < 8; ++row){
                        menu.addSlotEx(new UBItemSlot(stackHandler,index,8 + col * 18 - 64,12 + row * 18));
                        ++index;
                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ItemStackHandlerCapabilityProvider(new ItemStackHandler(24)/*{
            @Override
            protected void onContentsChanged(int slot) {
                stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    if (handler instanceof ItemStackHandler) {
                        stack.getOrCreateTag().put("inventory", ((ItemStackHandler)handler).serializeNBT());
                    }
                });
            }
        }*/);
    }
}
