package com.github.manasmods.unordinary_basics.item.templates;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Normal item that is able to take a custom tooltip
 */
public class TooltipItem extends Item {

    private final Component tooltip;

    public TooltipItem(Properties pProperties, Component tooltip) {
        super(pProperties);

        this.tooltip = tooltip;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(tooltip);
    }
}
