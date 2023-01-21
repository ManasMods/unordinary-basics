package com.github.manasmods.unordinary_basics.item.capability;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Capability holder used for the inventory inside the Builder's Glove
 */
public class BuildersGloveCapability implements ICapabilityProvider {

    public ItemStackHandler stackHandler = new ItemStackHandler(18);

    private final LazyOptional<IItemHandler> optional = LazyOptional.of(() -> stackHandler);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return optional.cast();
        }
            return LazyOptional.empty();
    }
}
