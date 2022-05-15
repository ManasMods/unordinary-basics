package com.github.manasmods.unordinary_basics.utils;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.network.chat.TranslatableComponent;

public class Translation {
    public static TranslatableComponent of(String path) {
        return new TranslatableComponent(Unordinary_Basics.MOD_ID + "." + path);
    }
}
