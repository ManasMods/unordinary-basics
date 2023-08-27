package com.github.manasmods.unordinary_basics.utils;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Translation {
    public static MutableComponent of(String path) {
        return Component.translatable(UnordinaryBasics.MOD_ID + "." + path);
    }
}
