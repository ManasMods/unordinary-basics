package com.github.manasmods.unordinary_basics.item;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class UBItemTags extends ItemTags {
    private UBItemTags() {

        public static final TagKey<Item> WOOL = bind("wool");

    }
}