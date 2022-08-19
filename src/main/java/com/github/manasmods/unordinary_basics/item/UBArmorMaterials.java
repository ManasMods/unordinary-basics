package com.github.manasmods.unordinary_basics.item;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

@RequiredArgsConstructor
public enum UBArmorMaterials implements ArmorMaterial {
    RABBIT("rabbit", 6, new int[]{1, 2, 3, 1},new int[]{13, 15, 16, 11}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, new LazyLoadedValue<>(() -> Ingredient.of(Items.RABBIT_HIDE))),
    TECHNOBLADE_CROWN("technoblade_crown", 37, new int[]{3, 6, 8, 3},new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.2F, new LazyLoadedValue<>(() -> Ingredient.of(Items.GOLD_BLOCK)));

    @Getter
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections, healthPerSlot;
    @Getter
    private final int enchantmentValue;
    @Getter
    private final SoundEvent equipSound;
    @Getter
    private final float toughness;
    @Getter
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return this.healthPerSlot[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public String getName() {
        return Unordinary_Basics.MOD_ID + ":" + this.name;
    }
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
