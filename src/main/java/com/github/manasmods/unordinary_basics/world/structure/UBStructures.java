package com.github.manasmods.unordinary_basics.world.structure;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class UBStructures {
    public static ResourceKey<Structure> MASTER_SWORD_SHRINE = createKey(new ResourceLocation(UnordinaryBasics.MOD_ID,"master_sword_shrine"));

    private static ResourceKey<Structure> createKey(ResourceLocation location) {
        return ResourceKey.create(Registry.STRUCTURE_REGISTRY, location);
    }
}
