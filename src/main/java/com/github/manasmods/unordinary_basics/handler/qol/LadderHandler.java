package com.github.manasmods.unordinary_basics.handler.qol;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.utils.LadderHelper;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnordinaryBasics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LadderHandler {
    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        LadderHelper.onBreak(event.getLevel(), event.getPos(), event.getState());
    }
}
