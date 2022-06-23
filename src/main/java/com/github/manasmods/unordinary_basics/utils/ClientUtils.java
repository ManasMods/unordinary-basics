package com.github.manasmods.unordinary_basics.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class ClientUtils
{
    
    public static Level getLevel() {
        return Minecraft.getInstance().level;
    }

}
