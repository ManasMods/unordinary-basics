package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.data.gen.ItemModelProvider;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.item.Items;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Unordinary_BasicsItemModelProvider extends ItemModelProvider {
    public Unordinary_BasicsItemModelProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Unordinary_Basics.MOD_ID);
    }

    @Override
    protected void generate() {
        singleTexture(Unordinary_BasicsItems.ANIMAL_BAIT);
        singleTexture(Unordinary_BasicsItems.GOAT_MILK_BOTTLE);
        singleTexture(Unordinary_BasicsItems.GOAT_MILK_BUCKET);
        singleTexture(Unordinary_BasicsItems.MAP_BOOK);
        singleTexture(Unordinary_BasicsItems.MILK_BOTTLE);
        singleTexture(Unordinary_BasicsItems.POTION_BELT);
        singleTexture(Unordinary_BasicsItems.POUCH);
        singleTexture(Unordinary_BasicsItems.QUIVER);
        singleTexture(Unordinary_BasicsItems.RABBIT_BOOTS);
        singleTexture(Unordinary_BasicsItems.TECHNOBLADE_CROWN);

        singleTexture(Unordinary_BasicsItems.UNKNOWN_BLADE_FRAGMENT);
        singleTexture(Unordinary_BasicsItems.UNKNOWN_HANDLE_FRAGMENT);
        singleTexture(Unordinary_BasicsItems.UNKNOWN_HILT_FRAGMENT);
        singleTexture(Unordinary_BasicsItems.ZENITH);

        singleTexture(Unordinary_BasicsItems.MUSIC_DISC_QUEEN);

        singleTexture(Items.CHAINMAIL_HELMET);
        singleTexture(Items.CHAINMAIL_CHESTPLATE);
        singleTexture(Items.CHAINMAIL_LEGGINGS);
        singleTexture(Items.CHAINMAIL_BOOTS);
        singleTexture(Items.DIAMOND_HELMET);
        singleTexture(Items.DIAMOND_CHESTPLATE);
        singleTexture(Items.DIAMOND_LEGGINGS);
        singleTexture(Items.DIAMOND_BOOTS);
        singleTexture(Items.GOLDEN_HELMET);
        singleTexture(Items.GOLDEN_CHESTPLATE);
        singleTexture(Items.GOLDEN_LEGGINGS);
        singleTexture(Items.GOLDEN_BOOTS);
        singleTexture(Items.IRON_HELMET);
        singleTexture(Items.IRON_CHESTPLATE);
        singleTexture(Items.IRON_LEGGINGS);
        singleTexture(Items.IRON_BOOTS);
        singleTexture(Items.NETHERITE_HELMET);
        singleTexture(Items.NETHERITE_CHESTPLATE);
        singleTexture(Items.NETHERITE_LEGGINGS);
        singleTexture(Items.NETHERITE_BOOTS);
    }
}