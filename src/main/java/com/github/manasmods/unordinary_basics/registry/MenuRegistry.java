package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.entity.JukeboxBlockEntity;
import com.github.manasmods.unordinary_basics.menu.FletchingTableMenu;
import com.github.manasmods.unordinary_basics.menu.JukeBoxMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.server.ServerLifecycleHooks;

class MenuRegistry {
    public static void register(DeferredRegister<MenuType<?>> registry) {
        registry.register("juke_box_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            BlockEntity blockEntity = Unordinary_Basics.getProxy().getLevelOrOverworld().getBlockEntity(pos);
            Level level = ServerLifecycleHooks.getCurrentServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, data.readResourceLocation()));
            if (blockEntity instanceof JukeboxBlockEntity jukeboxBlockEntity) {
                return new JukeBoxMenu(windowId, inv, jukeboxBlockEntity, ContainerLevelAccess.create(level, pos));
            } else {
                throw new IllegalStateException("Can not create JukeBoxMenu from non JukeBox block");
            }
        }));
        registry.register("fletching_table_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            Level level = ServerLifecycleHooks.getCurrentServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, data.readResourceLocation()));
            return new FletchingTableMenu(windowId, ContainerLevelAccess.create(level, pos), inv, pos);
        }));
    }
}
