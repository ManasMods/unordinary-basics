package com.github.manasmods.unordinary_basics.block.entity;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

public class Unordinary_BasicsBlockEntities {
    @ObjectHolder(registryName = "block_entity_type", value = "unordinary_basics:jukebox_block_entity")
    public static final BlockEntityType<JukeboxBlockEntity> JUKEBOX_BLOCK_ENTITY = null;
    @ObjectHolder(registryName = "block_entity_type", value = "unordinary_basics:item_sorter_block_entity")
    public static final BlockEntityType<ItemSorterBlockEntity> ITEM_SORTER_BLOCK_ENTITY = null;
}
