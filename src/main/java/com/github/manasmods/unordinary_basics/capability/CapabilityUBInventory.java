package com.github.manasmods.unordinary_basics.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.HashMap;
import java.util.Map;

public class CapabilityUBInventory {
    public static final Capability<IUBInventoryHandler> UB_INVENTORY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    public static final Map<UBSlot,Integer> SLOT_INDEX = Map.of(UBSlot.BACK,1,UBSlot.WAIST,2);

    enum UBSlot{
        BACK,
        WAIST
    }
}


