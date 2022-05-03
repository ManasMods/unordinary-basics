package com.manasmods.vanilla_plus.data;

import com.github.manasmods.manascore.data.gen.ItemModelProvider;
import com.manasmods.vanilla_plus.Vanilla_Plus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class Vanilla_PlusItemModelProvider extends ItemModelProvider {
    public Vanilla_PlusItemModelProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Vanilla_Plus.MOD_ID);
    }

    @Override
    protected void generate() {

    }

    @Override
    protected void registerModels() {

    }
}