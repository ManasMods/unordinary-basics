package com.github.manasmods.vanilla_plus.data.gen;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
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
    public void run(HashCache pCache) {
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

    private static void saveRecipe(HashCache pCache, JsonObject pRecipeJson, Path pPath) {
        try {
            String s = GSON.toJson((JsonElement) pRecipeJson);
            String s1 = SHA1.hashUnencodedChars(s).toString();
            if (!Objects.equals(pCache.getHash(pPath), s1) || !Files.exists(pPath)) {
                Files.createDirectories(pPath.getParent());
                BufferedWriter bufferedwriter = Files.newBufferedWriter(pPath);

                try {
                    bufferedwriter.write(s);
                } catch (Throwable throwable1) {
                    if (bufferedwriter != null) {
                        try {
                            bufferedwriter.close();
                        } catch (Throwable throwable) {
                            throwable1.addSuppressed(throwable);
                        }
                    }

                    throw throwable1;
                }

                if (bufferedwriter != null) {
                    bufferedwriter.close();
                }
            }

            pCache.putNew(pPath, s1);
        } catch (IOException ioexception) {
            Vanilla_Plus.getLogger().error("Couldn't save recipe {}", pPath, ioexception);
        }

    }

    protected abstract void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer);

    @Override
    public String getName() {
        return Vanilla_Plus.MOD_ID + ":fletching";
    }

    protected FletchingRecipeBuilder fletch(ItemLike result, int amount) {
        return FletchingRecipeBuilder.create(result, amount);
    }

    protected FletchingRecipeBuilder fletch(ItemStack stack) {
        return FletchingRecipeBuilder.create(stack);
    }
}
