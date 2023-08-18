package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.ItemStackHandlerCapabilityProvider;
import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsInventoryScreen;
import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsItemInventoryScreen;
import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.menu.UBItemInventoryMenu;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PotionBeltItem extends Item implements IUBInventoryItem {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/potion_belt_slots.png");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_POTION_BELT = new ResourceLocation(Unordinary_Basics.MOD_ID,"item/empty_slots/empty_armor_slot_potion_belt");

    public PotionBeltItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public CapabilityUBInventory.UBSlot getSlot() {
        return CapabilityUBInventory.UBSlot.WAIST;
    }

    @Override
    public int getSlotCount() {
        return 7;
    }

    @Override
    public void renderUsed(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, Unordinary_BasicsItemInventoryScreen screen) {
        int imageWidth = 140;
        int imageHeight = 25;

        int x = (screen.width - 176) / 2;
        int y = (screen.height - 166) / 2;

        RenderSystem.setShaderTexture(0,TEXTURE);
        screen.blit(pPoseStack,x + 18,y + 160,0,0,imageWidth,imageHeight);
    }

    @Override
    public void addSlots(int windowId, Inventory inventory, Player player, UBItemInventoryMenu menu, ItemStack stack) {
        stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            if (handler instanceof ItemStackHandler){
                ItemStackHandler stackHandler = (ItemStackHandler) handler;
                int index = 0;
                for (int col = 0; col < 7; ++col){
                        menu.addSlotEx(new SlotItemHandler(stackHandler,index,8 + 18 + col * 18,161){
                            @Override
                            public boolean mayPlace(@NotNull ItemStack stack) {
                                if (stack.getItem() instanceof PotionItem || stack.is(UBTags.Items.POTION_BELT_ITEMS)){
                                    return super.mayPlace(stack);
                                }
                                return false;
                            }

                            @Nullable
                            @Override
                            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                                return new Pair<>(InventoryMenu.BLOCK_ATLAS,EMPTY_ARMOR_SLOT_POTION_BELT);
                            }
                        });
                        ++index;
                    }
                }
            });
        }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ItemStackHandlerCapabilityProvider(new ItemStackHandler(7){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                if (stack.getItem() instanceof PotionItem || stack.is(UBTags.Items.POTION_BELT_ITEMS)){
                    return super.isItemValid(slot,stack);
                }
                return false;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 16;
            }
        });
    }
}
