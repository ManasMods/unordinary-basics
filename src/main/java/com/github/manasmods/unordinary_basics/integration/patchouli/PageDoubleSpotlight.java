package com.github.manasmods.unordinary_basics.integration.patchouli;

import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.client.book.BookContentsBuilder;
import vazkii.patchouli.client.book.BookEntry;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.client.book.page.abstr.PageWithText;

public class PageDoubleSpotlight extends PageWithText {

    IVariable item1;
    IVariable item2;
    String title;
    @SerializedName("link_recipe") boolean linkRecipe;
    transient Ingredient ingredient1;
    transient Ingredient ingredient2;

    @Override
    public void build(BookEntry entry, BookContentsBuilder builder, int pageNum) {
        super.build(entry, builder, pageNum);
        ingredient1 = item1.as(Ingredient.class);
        ingredient2 = item2.as(Ingredient.class);

        if (linkRecipe) {
            for (ItemStack stack : ingredient1.getItems()) {
                entry.addRelevantStack(builder, stack, pageNum);
            }
            for (ItemStack stack : ingredient2.getItems()) {
                entry.addRelevantStack(builder, stack, pageNum);
            }
        }
    }

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float ticks) {
        int w = 66;
        int h = 26;

        RenderSystem.setShaderTexture(0, book.craftingTexture);
        RenderSystem.enableBlend();
        GuiComponent.blit(ms, GuiBook.PAGE_WIDTH / 2 - w / 2, 10, 0, 128 - h, w, h, 128, 256);
        GuiComponent.blit(ms, GuiBook.PAGE_WIDTH / 2 - w / 2, 35, 0, 128 - h, w, h, 128, 256);

        // Title
        Component toDraw;
        if (title != null && !title.isEmpty()) {
            toDraw = i18nText(title);
        } else {
            toDraw = ingredient1.getItems()[0].getHoverName();
        }
        parent.drawCenteredStringNoShadow(ms, toDraw.getVisualOrderText(), GuiBook.PAGE_WIDTH / 2, 0, book.headerColor);

        // Items
        parent.renderIngredient(ms, GuiBook.PAGE_WIDTH / 2 - 8, 15, mouseX, mouseY, ingredient1);
        parent.renderIngredient(ms, GuiBook.PAGE_WIDTH / 2 - 8, 40, mouseX, mouseY, ingredient2);

        super.render(ms, mouseX, mouseY, ticks);
    }

    @Override
    public int getTextHeight() {
        return 65;
    }
}
