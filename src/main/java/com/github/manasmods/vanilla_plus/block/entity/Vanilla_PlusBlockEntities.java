package com.github.manasmods.vanilla_plus.block.entity;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Vanilla_Plus.MOD_ID)
public class Vanilla_PlusBlockEntities {
    @ObjectHolder("jukebox_block_entity")
    public static final BlockEntityType<JukeboxBlockEntity> JUKEBOX_BLOCK_ENTITY = null;
    @ObjectHolder("fletching_table_block_entity")
    public static final BlockEntityType<?> FLETCHING_TABLE_BLOCK_ENTITY = null;
}
