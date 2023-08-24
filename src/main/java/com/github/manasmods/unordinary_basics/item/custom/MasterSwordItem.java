package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.item.UBToolTiers;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class MasterSwordItem extends SwordItem {
    public MasterSwordItem() {
        super(UBToolTiers.MASTER, 11, -2.4F,
                new Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)
                        .fireResistant().rarity(Rarity.RARE));
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (pStack.isDamaged()) {
            CompoundTag tag = pStack.getOrCreateTag();
            int heal = tag.getInt("durabilityHeal");

            if (heal < 200) {
                tag.putInt("durabilityHeal", heal + 1);
            } else {
                pStack.setDamageValue(pStack.getDamageValue() - 1);
                tag.putInt("durabilityHeal", 0);
            }
        }
    }
}



