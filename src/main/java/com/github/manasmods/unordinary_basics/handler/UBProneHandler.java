package com.github.manasmods.unordinary_basics.handler;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= UnordinaryBasics.MOD_ID)
public class UBProneHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.isCrouching()) {
            if (!player.getLevel().getBlockState(player.getOnPos().above(2).relative(player.getDirection())).getCollisionShape(player.getLevel(), player.getOnPos().above(2).relative(player.getDirection())).isEmpty()
                    && player.getPose() != Pose.SWIMMING
                    && !player.getLevel().getBlockState(player.getOnPos().relative(player.getDirection())).getCollisionShape(player.getLevel(), player.getOnPos().relative(player.getDirection())).isEmpty() && player.getPose() != Pose.SWIMMING) {
                player.setPos(player.getDirection().getStepX() / 2f + player.getX(), player.getY(), player.getDirection().getStepZ() / 2f + player.getZ());
                player.setSwimming(true);
                player.setPose(Pose.SWIMMING);
            }
        }
    }
}
