package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.data.gen.BlockTagProvider;
import com.github.manasmods.manascore.data.gen.ItemTagProvider;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Unordinary_BasicsItemTagProvider extends ItemTagProvider {

    public Unordinary_BasicsItemTagProvider(GatherDataEvent gatherDataEvent, BlockTagProvider blockTagProvider) {
        super(gatherDataEvent, Unordinary_Basics.MOD_ID, blockTagProvider);
    }

    @Override
    protected void generate() {
        tag(UBTags.Items.MILK_BOTTLE)
                .add(Unordinary_BasicsItems.MILK_BOTTLE, Unordinary_BasicsItems.GOAT_MILK_BOTTLE);
    }
}
