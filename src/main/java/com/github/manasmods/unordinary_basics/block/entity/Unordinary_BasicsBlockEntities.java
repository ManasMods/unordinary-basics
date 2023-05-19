package com.github.manasmods.unordinary_basics.block.entity;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Unordinary_Basics.MOD_ID)
public class Unordinary_BasicsBlockEntities {
    @ObjectHolder("jukebox_block_entity")
    public static final BlockEntityType<JukeboxBlockEntity> JUKEBOX_BLOCK_ENTITY = null;
    @ObjectHolder("item_sorter_block_entity")
    public static final BlockEntityType<ItemSorterBlockEntity> ITEM_SORTER_BLOCK_ENTITY = null;
}
