package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.utils.MixinLadderHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class Unordinary_BasicsEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        MixinLadderHelper.onBreak(event.getWorld(), event.getPos(), event.getState());
    }
}
