package com.github.manasmods.vanilla_plus.registry;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import com.github.manasmods.vanilla_plus.block.entity.JukeboxBlockEntity;
import com.github.manasmods.vanilla_plus.menu.JukeBoxMenu;
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
            BlockEntity blockEntity = Vanilla_Plus.getProxy().getLevelOrOverworld().getBlockEntity(pos);
            Level level = ServerLifecycleHooks.getCurrentServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, data.readResourceLocation()));
            if (blockEntity instanceof JukeboxBlockEntity jukeboxBlockEntity) {
                return new JukeBoxMenu(windowId, inv, jukeboxBlockEntity.getContainer(), ContainerLevelAccess.create(level, pos));
            } else {
                throw new IllegalStateException("Can not create JukeBoxMenu from non JukeBox block");
            }
        }));
    }
}
