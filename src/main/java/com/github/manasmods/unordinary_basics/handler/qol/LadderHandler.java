package com.github.manasmods.unordinary_basics.handler.qol;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.utils.LadderHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LadderHandler {
    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        LadderHelper.onBreak(event.getWorld(), event.getPos(), event.getState());
    }
}
