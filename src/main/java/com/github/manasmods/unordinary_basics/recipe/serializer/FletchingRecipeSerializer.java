package com.github.manasmods.unordinary_basics.recipe.serializer;

import com.github.manasmods.unordinary_basics.recipe.FletchingRecipe;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public class FletchingRecipeSerializer implements RecipeSerializer<FletchingRecipe> {
    private ResourceLocation registryName;

    @Override
    public FletchingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        Map<String, Ingredient> ingredientMap = keyFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "key"));
        String[] patternArray = shrink(patternFromJson(GsonHelper.getAsJsonArray(pSerializedRecipe, "pattern")));
        NonNullList<Ingredient> dissolvePattern = dissolvePattern(patternArray, ingredientMap);
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

        return new FletchingRecipe(dissolvePattern, result, pRecipeId);
    }

    @Nullable
    @Override
    public FletchingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        int i = pBuffer.readVarInt();
        int j = pBuffer.readVarInt();
        NonNullList<Ingredient> dissolvePattern = NonNullList.withSize(i * j, Ingredient.EMPTY);

        for (int k = 0; k < dissolvePattern.size(); ++k) {
            dissolvePattern.set(k, Ingredient.fromNetwork(pBuffer));
        }

        ItemStack result = pBuffer.readItem();
        return new FletchingRecipe(dissolvePattern, result, pRecipeId);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, FletchingRecipe pRecipe) {
        pBuffer.writeVarInt(2);
        pBuffer.writeVarInt(3);

        NonNullList<Ingredient> ingredients = pRecipe.getIngredients();

        for (Ingredient ingredient : ingredients) {
            ingredient.toNetwork(pBuffer);
        }

        pBuffer.writeItem(pRecipe.getResultItem());
    }

    static Map<String, Ingredient> keyFromJson(JsonObject pKeyEntry) {
        Map<String, Ingredient> map = Maps.newHashMap();

        for (Map.Entry<String, JsonElement> entry : pKeyEntry.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    static String[] patternFromJson(JsonArray pPatternArray) {
        String[] astring = new String[pPatternArray.size()];
        if (astring.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + 3 + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for (int i = 0; i < astring.length; ++i) {
                String s = GsonHelper.convertToString(pPatternArray.get(i), "pattern[" + i + "]");
                if (s.length() > 2) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + 2 + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            return astring;
        }
    }

    static String[] shrink(String... pToShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for (int i1 = 0; i1 < pToShrink.length; ++i1) {
            String s = pToShrink[i1];
            i = Math.min(i, 0);
            int j1 = s.length() - 1;
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pToShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[pToShrink.length - l - k];

            for (int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = pToShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    static NonNullList<Ingredient> dissolvePattern(String[] pPattern, Map<String, Ingredient> pKeys) {
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(6, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(pKeys.keySet());
        set.remove(" ");

        for (int i = 0; i < pPattern.length; ++i) {
            for (int j = 0; j < pPattern[i].length(); ++j) {
                String s = pPattern[i].substring(j, j + 1);
                Ingredient ingredient;
                if (s.equals(" ")) {
                    ingredient = Ingredient.EMPTY;
                } else {
                    ingredient = pKeys.get(s);

                    if (ingredient == null) {
                        throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                    }

                    set.remove(s);
                }

                nonnulllist.set(i + 3 * j, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return nonnulllist;
        }
    }
}
