package com.github.manasmods.unordinary_basics.item.templates;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class MusicDiscItem extends RecordItem {
    /**
     * Easy to use {@link RecordItem}.
     *
     * @param soundSupplier Supplier reference to the sound event triggered when playing the disc.
     * @param lengthInTicks The amount of time that the record lasts for, in ticks
     */
    public MusicDiscItem(Supplier<SoundEvent> soundSupplier, int lengthInTicks) {
        this(1, soundSupplier,lengthInTicks);
    }

    /**
     * Easy to use {@link RecordItem}.
     *
     * @param comparatorValue    Redstone power outputted by a {@link Items#COMPARATOR}.
     * @param soundEventSupplier Supplier reference to the sound event triggered when playing the disc.
     * @param lengthInTicks The amount of time that the record lasts for, in ticks
     */
    public MusicDiscItem(int comparatorValue, Supplier<SoundEvent> soundEventSupplier, int lengthInTicks) {
        super(comparatorValue, soundEventSupplier, new Properties().stacksTo(1).tab(Unordinary_BasicsCreativeTab.ITEMS), lengthInTicks);
    }
}
