package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.block.entity.JukeboxBlockEntity;
import com.github.manasmods.unordinary_basics.menu.*;
import com.github.manasmods.unordinary_basics.utils.ClientUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

class MenuRegistry {

    public static void register(DeferredRegister<MenuType<?>> registry) {
        registry.register("juke_box_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            //This gets called by the client only at all times
            Level level = ClientUtils.getLevel();
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof JukeboxBlockEntity jukeboxBlockEntity) {
                return new JukeBoxMenu(windowId, inv, jukeboxBlockEntity, ContainerLevelAccess.create(level, pos));
            } else {
                throw new IllegalStateException("Can not create JukeBoxMenu from non JukeBox block");
            }
        }));

        registry.register("fletching_table_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            //This gets called by the client only at all times
            Level level = ClientUtils.getLevel();
            return new FletchingTableMenu(windowId, ContainerLevelAccess.create(level, pos), inv, pos);
        }));
        registry.register("inventory_menu", () -> IForgeMenuType.create((windowId, inv, data) -> new UBInventoryMenu(windowId, inv, inv.player)));

        registry.register("item_inventory_menu", () -> IForgeMenuType.create((windowId, inv, data) -> new UBItemInventoryMenu(windowId, inv, inv.player)));

        registry.register("builders_glove_menu", () -> IForgeMenuType.create(BuildersGloveMenu::new));

        registry.register("item_sorter_menu", () -> IForgeMenuType.create(ItemSorterMenu::new));

        registry.register("prismarine_smoker_menu", () -> new PrismarineSmokerMenu());
    }
}
