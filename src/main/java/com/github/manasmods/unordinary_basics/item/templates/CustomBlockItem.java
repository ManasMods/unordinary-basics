package com.github.manasmods.unordinary_basics.item.templates;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CustomBlockItem extends BlockItem {
    /**
     * Default {@link BlockItem}.
     * This will be used for all registered {@link Block} objects if there is no {@link BlockItem} already registered.
     *
     * @param pBlock Parent Block
     */
    public CustomBlockItem(Block pBlock) {
        super(pBlock, new Properties().tab(Unordinary_BasicsCreativeTab.BLOCKS));
    }
}
