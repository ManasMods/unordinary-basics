package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.block.entity.ItemSorterBlockEntity;
import com.github.manasmods.unordinary_basics.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

class BlockEntityRegistry {

    public static void register(DeferredRegister<BlockEntityType<?>> registry) {
        registry.register("jukebox_block_entity", () -> BlockEntityType.Builder.of(JukeboxBlockEntity::new, Blocks.JUKEBOX).build(null));
        registry.register("item_sorter_block_entity", () -> BlockEntityType.Builder.of(ItemSorterBlockEntity::new, Unordinary_BasicsBlocks.ITEM_SORTER).build(null));
    }
}
