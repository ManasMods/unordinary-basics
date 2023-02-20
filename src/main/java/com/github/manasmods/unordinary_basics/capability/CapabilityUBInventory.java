package com.github.manasmods.unordinary_basics.capability;

import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.Map;

public class CapabilityUBInventory {
    public static final Capability<IUBInventoryHandler> UB_INVENTORY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    /**
     * Used to obtain the indexes that are mapped to the BACK slot and WAIST slot in the {@link UBInventoryMenu}
     */
    public static final Map<UBSlot,Integer> SLOT_INDEX = Map.of(UBSlot.BACK,0,UBSlot.WAIST,1);
    public static final Map<TagKey<Item>,Integer> SLOT_INDEX_TAG = Map.of(UBTags.Items.UB_SLOT_BACK,0,UBTags.Items.UB_SLOT_WAIST,1);

    public enum UBSlot{
        BACK,
        WAIST
    }
}


