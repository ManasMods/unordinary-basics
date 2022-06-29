package com.github.manasmods.unordinary_basics.block;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;

public interface IPatternable
{

    /**
     * @return the patterns for this object.
     */
    public List<Pair<BannerPattern, DyeColor>> getPatterns();
    
    public ListTag getItemPatterns();
    
}
