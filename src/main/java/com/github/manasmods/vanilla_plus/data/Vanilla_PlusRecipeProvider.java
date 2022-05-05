package com.github.manasmods.vanilla_plus.data;

import com.github.manasmods.manascore.data.gen.RecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.function.Consumer;

public class Vanilla_PlusRecipeProvider extends RecipeProvider {

    public Vanilla_PlusRecipeProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent);
    }

    @Override
    protected void generate(Consumer<FinishedRecipe> consumer) {

    }
}
