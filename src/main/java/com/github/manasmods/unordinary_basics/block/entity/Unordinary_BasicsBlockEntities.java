package com.github.manasmods.unordinary_basics.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class Unordinary_BasicsBlockEntities {
    @ObjectHolder(registryName = "block_entity_type", value = "unordinary_basics:jukebox_block_entity")
    public static final BlockEntityType<JukeboxBlockEntity> JUKEBOX_BLOCK_ENTITY = null;
    @ObjectHolder(registryName = "block_entity_type", value = "unordinary_basics:item_sorter_block_entity")
    public static final BlockEntityType<ItemSorterBlockEntity> ITEM_SORTER_BLOCK_ENTITY = null;
    @ObjectHolder(registryName = "block_entity_type", value = "unordinary_basics:prismarine_smoker_block_entity")
    public static final BlockEntityType<PrismarineSmokerBlockEntity> PRISMARINE_SMOKER_BLOCK_ENTITY = null;
}
