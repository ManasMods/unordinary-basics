package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.unordinary_basics.data.gen.FletchingRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Unordinary_BasicsFletchingRecipeProvider extends FletchingRecipeProvider {
    public Unordinary_BasicsFletchingRecipeProvider(GatherDataEvent gatherDataEvent) {
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

        fletch(Items.SPECTRAL_ARROW, 8)
            .pattern("  ")
            .pattern(" A")
            .pattern(" G")
            .define('A', StrictNBTIngredient.of(new ItemStack(Items.ARROW, 8)))
            .define('G', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
            .save(pFinishedRecipeConsumer);

        ForgeRegistries.POTIONS.getValues().forEach(potion -> {
            if (potion.equals(Potions.EMPTY)) return;

            ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
            stack.setCount(32);
            PotionUtils.setPotion(stack, potion);

            ItemStack potionStack = new ItemStack(Items.LINGERING_POTION);
            PotionUtils.setPotion(potionStack, potion);

            fletch(stack)
                .pattern(" P")
                .pattern(" A")
                .pattern("  ")
                .define('P', StrictNBTIngredient.of(potionStack))
                .define('A', StrictNBTIngredient.of(new ItemStack(Items.ARROW, 32)))
                .save(pFinishedRecipeConsumer, new ResourceLocation("minecraft", "tipped_arrow_" + potion.getRegistryName().getPath()));
        });
    }
}
