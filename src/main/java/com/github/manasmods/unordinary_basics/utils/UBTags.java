package com.github.manasmods.unordinary_basics.utils;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class UBTags {

    //There is no need to prefix entries with 'items/' or 'blocks/', that is done automatically, and is not how tags are formatted

    public static class Blocks {
        public static TagKey<Block> VEIN_MINER_VALID = modTag("vein_miner_valid");
        public static TagKey<Block> TREE_FELLER_VALID = modTag("tree_feller_valid");
        public static TagKey<Block> CROP_TENDER_VALID = modTag("crop_tender_valid");
        public static TagKey<Block> CROP_TENDER_TILL_VALID = modTag("crop_tender_valid");
        public static TagKey<Block> TRIMMER_VALID = modTag("trimmer_valid");

        public static TagKey<Block> GRASS_BLOCKS = forgeTag("grass_block");
        public static TagKey<Block> MYCELIUM_BLOCKS = forgeTag("mycelium");
        public static TagKey<Block> PODZOL_BLOCKS = forgeTag("podzol");


        static TagKey<Block> modTag(String name) {
            return BlockTags.create(new ResourceLocation(UnordinaryBasics.MOD_ID, name));
        }

        static TagKey<Block> forgeTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }
}

    public static class Items {
        public static TagKey<Item> POTION_BELT_ITEMS = modTag("potion_belt_items");
        public static TagKey<Item> UB_SLOT_BACK = modTag("ub_slot_back");
        public static TagKey<Item> UB_SLOT_WAIST = modTag("ub_slot_waist");
        public static TagKey<Item> STONE = modTag("stone");
        public static TagKey<Item> BLACK_DYE_RESOURCES = modTag("black_dye_resources");

        public static TagKey<Item> MILK_BOTTLE = forgeTag("milk_bottle");
        public static TagKey<Item> BEAR_FOOD = forgeTag("bear_food");
        public static TagKey<Item> TAME_BEAR_FOOD = forgeTag("tame_bear_food");
        public static TagKey<Item> STORAGE_WOODEN = forgeTag("storage_wooden");



        static TagKey<Item> modTag(String name) {
            return ItemTags.create(new ResourceLocation(UnordinaryBasics.MOD_ID, name));
        }

        static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class EntityTypes {
        public static TagKey<EntityType<?>> SCULK_ENEMIES = forgeTag("sculk_enemies");
        static TagKey<EntityType<?>> forgeTag(String name) {
            return create(new ResourceLocation("forge", name));
        }
        static TagKey<EntityType<?>> create(final ResourceLocation name) {
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, name);
        }
    }

    public static class Structures {

        public static TagKey<Structure> MASTER_SWORD_SHRINE = create(new ResourceLocation(UnordinaryBasics.MOD_ID,"master_sword_shrine"));

        static TagKey<Structure> forgeTag(String name) {
            return create(new ResourceLocation("forge", name));
        }
        static TagKey<Structure> create(final ResourceLocation name) {
            return TagKey.create(Registry.STRUCTURE_REGISTRY, name);
        }
    }
}
