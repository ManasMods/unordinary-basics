package com.github.manasmods.vanilla_plus.utils;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import net.minecraft.network.chat.TranslatableComponent;

public class Translation {
    public static TranslatableComponent of(String path) {
        return new TranslatableComponent(Vanilla_Plus.MOD_ID + "." + path);
    }
}
