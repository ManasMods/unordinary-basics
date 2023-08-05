package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ObjectHolder;

public class Unordinary_BasicsMenuTypes {
    @ObjectHolder(registryName = Unordinary_Basics.MOD_ID + ":menu_types", value = "unordinary_basics:juke_box_menu")
    public static final MenuType<JukeBoxMenu> JUKE_BOX_MENU = null;
    @ObjectHolder(registryName = Unordinary_Basics.MOD_ID + ":menu_types", value = "unordinary_basics:fletching_table_menu")
    public static final MenuType<FletchingTableMenu> FLETCHING_TABLE_MENU = null;
    @ObjectHolder(registryName = Unordinary_Basics.MOD_ID + ":menu_types", value = "unordinary_basics:inventory_menu")
    public static final MenuType<UBInventoryMenu> INVENTORY_MENU = null;
    @ObjectHolder(registryName = Unordinary_Basics.MOD_ID + ":menu_types", value = "unordinary_basics:builders_glove_menu")
    public static final MenuType<BuildersGloveMenu> BUILDERS_GLOVE_MENU = null;
    @ObjectHolder(registryName = Unordinary_Basics.MOD_ID + ":menu_types", value = "unordinary_basics:item_sorter_menu")
    public static final MenuType<ItemSorterMenu> ITEM_SORTER_MENU = null;
}
