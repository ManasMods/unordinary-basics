package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.ItemModelProvider;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
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
    }
}