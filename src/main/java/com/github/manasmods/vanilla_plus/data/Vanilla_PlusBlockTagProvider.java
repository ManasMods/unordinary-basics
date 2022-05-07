package com.github.manasmods.vanilla_plus.data;

import com.github.manasmods.manascore.data.gen.BlockTagProvider;
import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Vanilla_PlusBlockTagProvider extends BlockTagProvider {
    public Vanilla_PlusBlockTagProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Vanilla_Plus.MOD_ID);
    }

    @Override
    protected void generate() {

    }
}
