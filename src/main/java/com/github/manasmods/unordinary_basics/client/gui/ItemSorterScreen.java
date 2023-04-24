package com.github.manasmods.unordinary_basics.client.gui;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.ItemSorterMenu;
import com.github.manasmods.unordinary_basics.menu.slot.GhostSlot;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class ItemSorterScreen extends AbstractContainerScreen<ItemSorterMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Unordinary_Basics.MOD_ID, "textures/gui/item_sorter.png");
    private EditBox text;

    public ItemSorterScreen(ItemSorterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.subInit();
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.text.tick();
    }

    protected void subInit() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.text = new EditBox(this.font, i + 10, j + 20, 88, 10, new TranslatableComponent("unordinary_basics.menu.item_sorter.editbox"));
        this.text.setCanLoseFocus(false);
        this.text.setTextColor(-1);
        this.text.setTextColorUneditable(-1);
        this.text.setBordered(false);
        this.text.setMaxLength(35);
        this.text.setResponder(this::onTextChanged);
        this.text.setValue("");
        this.addWidget(this.text);
        this.setInitialFocus(this.text);
        this.text.setEditable(true);
        this.text.insertText(this.menu.blockEntity.getMessage(2,false).getString());
    }

    private void onTextChanged(String s) {
        if (!s.isEmpty()) {
            this.menu.blockEntity.setMessage(2, new TextComponent(s));
            this.menu.blockEntity.setChanged();
        }
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
        this.font.draw(pPoseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY - 33, 4210752);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack,pMouseX,pMouseY);
        renderGhostItems(x,y);
        RenderSystem.disableBlend();
        renderFg(pPoseStack,pMouseX,pMouseY,pPartialTick);
    }

    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == 256) {
            this.minecraft.player.closeContainer();
        }

        return !this.text.keyPressed(pKeyCode, pScanCode, pModifiers) && !this.text.canConsumeInput() ? super.keyPressed(pKeyCode, pScanCode, pModifiers) : true;
    }

    public void renderFg(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.text.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void renderGhostItems(int pX, int pY){
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        for (int i = 0 ; i < 2; ++i){
            for (int k = 0 ; k < 3; ++k){
                ItemStack stack = new ItemStack(((GhostSlot) this.menu.getSlot(i * 3 + k)).currentItem);
                itemRenderer.renderAndDecorateFakeItem(stack,114 + k * 18 + pX, 8 + i * 18 + pY);
            }
        }
    }
}
