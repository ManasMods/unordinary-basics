package com.github.manasmods.unordinary_basics.integration.patchouli;

import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.client.book.BookContentsBuilder;
import vazkii.patchouli.client.book.BookEntry;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.client.book.page.abstr.PageWithText;

import java.util.ArrayList;
import java.util.List;

public class PageMultiSpotlight extends PageWithText {

    List<IVariable> items;
    String title;
    @SerializedName("link_recipe") boolean linkRecipe;
    transient List<Ingredient> ingredients = new ArrayList<>();

    @Override
    public void build(BookEntry entry, BookContentsBuilder builder, int pageNum) {
        super.build(entry, builder, pageNum);
        items.forEach(item -> ingredients.add(item.as(Ingredient.class)));

        if (linkRecipe) {
            for (Ingredient ingredient : ingredients) {
                for (ItemStack stack : ingredient.getItems()) {
                    entry.addRelevantStack(builder, stack, pageNum);
                }
            }
        }
    }

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float ticks) {
        // Title
        Component toDraw;
        if (title != null && !title.isEmpty()) {
            toDraw = i18nText(title);
        } else {
            toDraw = ingredients.get(0).getItems()[0].getHoverName();
        }
        parent.drawCenteredStringNoShadow(ms, toDraw.getVisualOrderText(), GuiBook.PAGE_WIDTH / 2, 0, book.headerColor);

        // Items
        for (int done = 0, row = 0; done < ingredients.size(); done += 7, row++) {
            int todo = Math.min(ingredients.size() - done, 7);
            for (int i = 0; i < todo; i++) {
                int x = GuiBook.PAGE_WIDTH / 2 - todo * 8 + i * 16;
                int y = 15 + row * 16;
                parent.renderIngredient(ms, x, y, mouseX, mouseY, ingredients.get(done + i));
            }
        }

        // Text
        super.render(ms, mouseX, mouseY, ticks);
    }

    @Override
    public int getTextHeight() {
        int numRows = (int) Math.ceil(ingredients.size() / 5f);
        return 20 + numRows * 16;
    }
}
