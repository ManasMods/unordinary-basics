package com.github.manasmods.unordinary_basics.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Capability holder used for the inventories that use ItemStackHandler - is persistent
 */
public class ItemStackHandlerCapabilityProvider implements ICapabilitySerializable<CompoundTag> {
    private ItemStackHandler stackHandler;

    public ItemStackHandlerCapabilityProvider(int size){
        stackHandler = new ItemStackHandler(size);
    }
    public ItemStackHandlerCapabilityProvider(ItemStackHandler stackHandler){
        this.stackHandler = stackHandler;
    }

    private final LazyOptional<IItemHandler> optional = LazyOptional.of(() -> stackHandler);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return stackHandler.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        stackHandler.deserializeNBT(nbt);
    }
}
