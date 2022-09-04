package com.github.manasmods.unordinary_basics.utils;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class UBTags {public static class Blocks {

    static TagKey<Block> modTag(String name) {
        return BlockTags.create(new ResourceLocation(Unordinary_Basics.MOD_ID, name));
    }

    static TagKey<Block> forgeTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }
}

    public static class Items {
        public static TagKey<Item> MILK_BOTTLE = forgeTag("items/milk_bottle");
        public static TagKey<Item> BEAR_FOOD = forgeTag("items/milk_bottle");
        public static TagKey<Item> TAME_BEAR_FOOD = forgeTag("items/milk_bottle");

        static TagKey<Item> modTag(String name) {
            return ItemTags.create(new ResourceLocation(Unordinary_Basics.MOD_ID, name));
        }

        static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
