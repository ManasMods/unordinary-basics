package com.github.manasmods.vanilla_plus.registry;

import com.github.manasmods.vanilla_plus.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

class BlockEntityRegistry {

    public static void register(DeferredRegister<BlockEntityType<?>> registry) {
        registry.register("jukebox_block_entity", () -> BlockEntityType.Builder.of(JukeboxBlockEntity::new, Blocks.JUKEBOX).build(null));
    }
}
