package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.ItemStackHandlerCapabilityProvider;
import com.github.manasmods.unordinary_basics.client.gui.Unordinary_BasicsItemInventoryScreen;
import com.github.manasmods.unordinary_basics.menu.UBItemInventoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class QuiverItem extends Item implements IUBInventoryItem {
    public static final ResourceLocation TEXTURE = new ResourceLocation(UnordinaryBasics.MOD_ID, "textures/gui/quiver_slots.png");
    public QuiverItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public CapabilityUBInventory.UBSlot getSlot() {
        return CapabilityUBInventory.UBSlot.BACK;
    }

    @Override
    public int getSlotCount() {
        return 6;
    }

    @Override
    public void renderUsed(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, Unordinary_BasicsItemInventoryScreen screen) {
        int imageWidth = 46;
        int imageHeight = 67;

        int x = (screen.width - 176) / 2;
        int y = (screen.height - 166) / 2;

        RenderSystem.setShaderTexture(0,TEXTURE);
        screen.blit(pPoseStack,x - imageWidth + 6,y + 76,0,0,imageWidth,imageHeight);
    }

    @Override
    public void addSlots(int windowId, Inventory inventory, Player player, UBItemInventoryMenu menu, ItemStack stack) {
        stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            if (handler instanceof ItemStackHandler stackHandler){
                int index = 0;
                for (int col = 0; col < 2; ++col){
                    for (int row = 0; row < 3; ++row){
                        menu.addSlotEx(new SlotItemHandler(stackHandler,index,8 + col * 18 - 40,8 + row * 18 + 76){
                            @Override
                            public boolean mayPlace(@NotNull ItemStack stack) {
                                if (((ProjectileWeaponItem)Items.BOW).getSupportedHeldProjectiles().test(stack)) {
                                    return super.mayPlace(stack);
                                }
                                return false;
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
        return new ItemStackHandlerCapabilityProvider(new ItemStackHandler(6){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return ((ProjectileWeaponItem)Items.BOW).getSupportedHeldProjectiles().test(stack);
            }
        });
    }
}
