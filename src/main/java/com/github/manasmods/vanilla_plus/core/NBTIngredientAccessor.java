package com.github.manasmods.vanilla_plus.core;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.NBTIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NBTIngredient.class)
public interface NBTIngredientAccessor {
    @Accessor
    ItemStack getStack();
}
