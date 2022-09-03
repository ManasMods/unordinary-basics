package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.data.gen.BlockTagProvider;
import com.github.manasmods.manascore.data.gen.ItemTagProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Unordinary_BasicsItemTagProvider extends ItemTagProvider {

    public Unordinary_BasicsItemTagProvider(GatherDataEvent gatherDataEvent, String modId, BlockTagProvider blockTagProvider) {
        super(gatherDataEvent, modId, blockTagProvider);
    }

    @Override
    protected void generate() {
        addTags(ItemTags.MILK_BOTTLE);

    }
}
