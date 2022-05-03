package com.manasmods.vanilla_plus.item.templates;


import com.manasmods.vanilla_plus.item.Vanilla_PlusCreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class SimpleBlockItem extends BlockItem {
    /**
     * Default {@link BlockItem}.
     * This will be used for all registered {@link Block} objects if there is no {@link BlockItem} already registered.
     *
     * @param pBlock Parent Block
     */
    public SimpleBlockItem(Block pBlock) {
        super(pBlock, new Properties().tab(Vanilla_PlusCreativeTab.BLOCKS));
    }
}
