package com.github.manasmods.unordinary_basics.data.gen;

//TODO: FIX THE CACHING PART OF THIS

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

@RequiredArgsConstructor
public abstract class FletchingRecipeProvider implements DataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    @Override
    public void run(CachedOutput pCache) {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        buildCraftingRecipes((finishedFletchingRecipe) -> {
            if (!set.add(finishedFletchingRecipe.getId())) {
                throw new IllegalStateException("Duplicate recipe " + finishedFletchingRecipe.getId());
            } else {
                saveRecipe(pCache, finishedFletchingRecipe.serializeRecipe(),
                    path.resolve("data/" + finishedFletchingRecipe.getId().getNamespace() + "/recipes/fletching/" + finishedFletchingRecipe.getId().getPath() + ".json"));
            }
        });
    }

    private static void saveRecipe(CachedOutput pCache, JsonObject pRecipeJson, Path pPath) {
        try {
            DataProvider.saveStable(pCache, pRecipeJson, pPath);
        } catch (IOException e) {
            Unordinary_Basics.getLogger().error("Couldn't save fletching recipe to {}", pPath, e);
        }
    }

    protected abstract void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer);

    @Override
    public String getName() {
        return Unordinary_Basics.MOD_ID + ":fletching";
    }

    protected FletchingRecipeBuilder fletch(ItemLike result, int amount) {
        return FletchingRecipeBuilder.create(result, amount);
    }

    protected FletchingRecipeBuilder fletch(ItemStack stack) {
        return FletchingRecipeBuilder.create(stack);
    }
}
