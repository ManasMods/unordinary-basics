package com.github.manasmods.unordinary_basics.utils;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class UBTags {

    //There is no need to prefix entries with 'items/' or 'blocks/', that is done automatically, and is not how tags are formatted

    public static class Blocks {
        public static TagKey<Block> VEIN_MINER_VALID = modTag("vein_miner_valid");
        public static TagKey<Block> TREE_FELLER_VALID = modTag("tree_feller_valid");

        public static TagKey<Block> GRASS_BLOCKS = forgeTag("grass_block");
        public static TagKey<Block> MYCELIUM_BLOCKS = forgeTag("mycelium");
        public static TagKey<Block> PODZOL_BLOCKS = forgeTag("podzol");


        static TagKey<Block> modTag(String name) {
            return BlockTags.create(new ResourceLocation(Unordinary_Basics.MOD_ID, name));
        }

        static TagKey<Block> forgeTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }
}

    public static class Items {
        public static TagKey<Item> MILK_BOTTLE = forgeTag("milk_bottle");
        public static TagKey<Item> BEAR_FOOD = forgeTag("bear_food");
        public static TagKey<Item> TAME_BEAR_FOOD = forgeTag("tame_bear_food");
        public static TagKey<Item> STONE = forgeTag("stone");
        public static TagKey<Item> BLACK_DYES = forgeTag("black_dyes");


        static TagKey<Item> modTag(String name) {
            return ItemTags.create(new ResourceLocation(Unordinary_Basics.MOD_ID, name));
        }

        static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
