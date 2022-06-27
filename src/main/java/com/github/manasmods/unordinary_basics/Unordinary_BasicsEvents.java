package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.utils.MixinLadderHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class Unordinary_BasicsEvents {

    private static final Set<Item> CHAINMAIL_ARMOR;
    private static final Set<Item> NETHERITE_ARMOR;
    static {
        CHAINMAIL_ARMOR = new HashSet<>();
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_HELMET);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_CHESTPLATE);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_LEGGINGS);
        CHAINMAIL_ARMOR.add(Items.CHAINMAIL_BOOTS);
        NETHERITE_ARMOR = new HashSet<>();
        NETHERITE_ARMOR.add(Items.NETHERITE_HELMET);
        NETHERITE_ARMOR.add(Items.NETHERITE_CHESTPLATE);
        NETHERITE_ARMOR.add(Items.NETHERITE_LEGGINGS);
        NETHERITE_ARMOR.add(Items.NETHERITE_BOOTS);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        MixinLadderHelper.onBreak(event.getWorld(), event.getPos(), event.getState());
    }

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            if (fullArmorSet(player, CHAINMAIL_ARMOR) && event.getSource().msgId.equals("arrow")) {
                event.setAmount(event.getAmount() * 0.75f);
            }
            if (fullArmorSet(player, NETHERITE_ARMOR) && (event.getSource() == DamageSource.LAVA || event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.ON_FIRE)) {
                event.setAmount(event.getAmount() * 0.7f);
            }
        }
    }

    private static boolean fullArmorSet(Player player, Set<Item> armorSet) {
        for (ItemStack stack : player.getArmorSlots()) {
            if (!armorSet.contains(stack.getItem())) {
                return false;
            }
        }
        return true;
    }
}
