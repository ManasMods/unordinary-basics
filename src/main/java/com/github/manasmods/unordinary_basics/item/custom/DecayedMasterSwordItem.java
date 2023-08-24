package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.item.UBToolTiers;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class DecayedMasterSwordItem extends SwordItem {
    public DecayedMasterSwordItem() {
        super(UBToolTiers.MASTER, 0, -2.4F,
                new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)
                        .fireResistant().rarity(Rarity.RARE));
    }

    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        CompoundTag tag = pStack.getOrCreateTag();

        if (pStack.isDamaged()) {
            int heal = tag.getInt("durabilityHeal");
            if (heal < 200) {
                tag.putInt("durabilityHeal", heal + 1);
            } else {
                pStack.setDamageValue(pStack.getDamageValue() - 1);
                tag.putInt("durabilityHeal", 0);
            }
        }

        int formTick = tag.getInt("formHeal");
        if (formTick < 12000) {
            tag.putInt("formHeal",formTick + 1);
        } else {
            tag.remove("formHeal");
            
            ItemStack evolvingItem = new ItemStack(Unordinary_BasicsItems.MASTER_SWORD);
            evolvingItem.setTag(tag.copy()); //copy every tag from old sword, so enchantments and stuff gets reserved
            pEntity.getSlot(pSlotId).set(evolvingItem);

            pEntity.getLevel().playSound(null, pEntity.blockPosition(),
                    SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 10f); //I just like sound effects
        }
    }
}



