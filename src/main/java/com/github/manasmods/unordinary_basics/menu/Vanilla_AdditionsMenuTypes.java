package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Unordinary_Basics.MOD_ID)
public class Vanilla_AdditionsMenuTypes {
    @ObjectHolder("juke_box_menu")
    public static final MenuType<JukeBoxMenu> JUKE_BOX_MENU = null;
    @ObjectHolder("fletching_table_menu")
    public static final MenuType<FletchingTableMenu> FLETCHING_TABLE_MENU = null;
}
