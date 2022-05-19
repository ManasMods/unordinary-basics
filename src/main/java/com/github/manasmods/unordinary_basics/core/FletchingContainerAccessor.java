package com.github.manasmods.unordinary_basics.core;

import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;
import shadows.apotheosis.village.fletching.FletchingContainer;

@Pseudo
@Mixin(FletchingContainer.class)
public interface FletchingContainerAccessor {
    @Accessor
    BlockPos getPos();
}
