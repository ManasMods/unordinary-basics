package com.github.manasmods.unordinary_basics.recipe.serializer;

import com.github.manasmods.unordinary_basics.recipe.PrismarineSmokingRecipe;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;

public class PrismarineSmokingRecipeSerializer extends SimpleCookingSerializer<PrismarineSmokingRecipe> {

    public PrismarineSmokingRecipeSerializer() {
        super(PrismarineSmokingRecipe::new, 50);
    }
}
