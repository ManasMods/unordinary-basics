package com.github.manasmods.unordinary_basics.block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.List;

public interface IPatternable {

    /**
     * @return the patterns for this object.
     */
    List<Pair<BannerPattern, DyeColor>> getPatterns();

    ListTag getItemPatterns();
}
