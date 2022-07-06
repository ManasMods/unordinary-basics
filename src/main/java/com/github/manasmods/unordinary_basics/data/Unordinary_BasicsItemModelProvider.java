package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.data.gen.ItemModelProvider;
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
        singleTexture(Unordinary_BasicsItems.TECHNOBLADE_CROWN);
    }
}