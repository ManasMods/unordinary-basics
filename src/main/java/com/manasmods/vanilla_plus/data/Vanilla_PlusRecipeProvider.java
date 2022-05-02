package com.manasmods.vanilla_plus.data;

import com.github.manasmods.manascore.data.gen.RecipeProvider;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public abstract class Vanilla_PlusRecipeProvider extends RecipeProvider {

    public Vanilla_PlusRecipeProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent);
    }
}
