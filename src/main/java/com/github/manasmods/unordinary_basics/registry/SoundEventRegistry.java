package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.sound.UBSoundEvent;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

class SoundEventRegistry {
    /**
     * This Method will register all SoundEvents to Forge.
     * It is called though the {@link Unordinary_BasicsRegistry#register(IEventBus)} Method.
     */
    static void register(DeferredRegister<SoundEvent> registry) {
        registry.register("queen", () -> new UBSoundEvent("queen"));
    }
}
