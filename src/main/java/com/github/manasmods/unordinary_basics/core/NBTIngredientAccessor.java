package com.github.manasmods.unordinary_basics.core;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrictNBTIngredient.class)
public interface NBTIngredientAccessor {
    @Accessor(remap = false)
    ItemStack getStack();
}
