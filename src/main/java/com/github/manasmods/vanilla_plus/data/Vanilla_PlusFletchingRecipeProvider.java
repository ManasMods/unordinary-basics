package com.github.manasmods.vanilla_plus.data;

import com.github.manasmods.vanilla_plus.data.gen.FletchingRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.function.Consumer;

public class Vanilla_PlusFletchingRecipeProvider extends FletchingRecipeProvider {
    public Vanilla_PlusFletchingRecipeProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent.getGenerator());
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        fletch(Items.ARROW, 8)
            .pattern("F ")
            .pattern("S ")
            .pattern("A ")
            .define('F', Items.FLINT)
            .define('S', Tags.Items.RODS_WOODEN)
            .define('A', Tags.Items.FEATHERS)
            .save(pFinishedRecipeConsumer);
    }
}
