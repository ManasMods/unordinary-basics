package com.github.manasmods.unordinary_basics.client.gui.overlay;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.client.ClientUBInventoryData;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class QuiverArrowHudOverlay extends GuiComponent {

    public static Tuple<Boolean,ItemStack> shouldDisplay(){
        AtomicBoolean returnValue = new AtomicBoolean(false);
        AtomicReference<ItemStack> returnStack = new AtomicReference<>();
        IUBInventoryHandler handler = ClientUBInventoryData.getHandler();
        if (handler == null) return new Tuple<>(false,ItemStack.EMPTY);

        if (UBInventoryItemStackHandler.isItemEquipped(Unordinary_BasicsItems.QUIVER,handler)){
            ItemStack quiver = handler.getStackInSlot(CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.BACK));
            quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                for (int i = 0; i < itemHandler.getSlots(); i++) {
                    if (!itemHandler.getStackInSlot(i).isEmpty()){
                        returnValue.set(true);
                        returnStack.set(itemHandler.getStackInSlot(i));
                        break;
                    }
                }
            });
        }
        return new Tuple<>(returnValue.get(),returnStack.get());
    }

    //TODO: whenever we make configs, the x and y should be made adjustable
    public void drawHUD(PoseStack poseStack) {
        Tuple<Boolean,ItemStack> displayData = shouldDisplay();
        if(!displayData.getA()) return;
        if(!(Minecraft.getInstance().player.getMainHandItem().getItem() instanceof BowItem)) return;

        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        renderer.renderAndDecorateFakeItem(displayData.getB(),419,417);

        RenderSystem.setShaderTexture(0,new ResourceLocation("textures/gui/widgets.png"));
        blit(poseStack,415,413,0,0,24,24,256,256);
        blit(poseStack,415,413,0,22,24,24,256,256);
    }

}
