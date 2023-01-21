package com.github.manasmods.unordinary_basics.sound;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class UBSoundEvent extends SoundEvent {
    /**
     * Easy to use {@link SoundEvent}.
     */
    public UBSoundEvent(String name) {
        super(new ResourceLocation(Unordinary_Basics.MOD_ID, name));
    }
}
