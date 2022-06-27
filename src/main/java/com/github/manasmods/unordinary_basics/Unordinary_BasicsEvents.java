package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.utils.MixinLadderHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class Unordinary_BasicsEvents {

    private static final Set<Item> CHAINMAIL_ARMOR;
    static {
        CHAINMAIL_ARMOR = new HashSet<>();
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_HELMET);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_CHESTPLATE);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_LEGGINGS);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_BOOTS);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        MixinLadderHelper.onBreak(event.getWorld(), event.getPos(), event.getState());
    }

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            boolean allChainmail = StreamSupport.stream(player.getArmorSlots().spliterator(), false).allMatch(o -> CHAINMAIL_ARMOR.contains(o.getItem()));
            if (allChainmail) {
                event.setAmount(event.getAmount() * 0.75f);
            }
        }
    }
}
