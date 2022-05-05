package com.github.manasmods.vanilla_plus.registry;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import com.github.manasmods.vanilla_plus.block.entity.JukeboxBlockEntity;
import com.github.manasmods.vanilla_plus.menu.JukeBoxMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;

class MenuRegistry {
    public static void register(DeferredRegister<MenuType<?>> registry) {
        registry.register("juke_box_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
            BlockEntity blockEntity = Vanilla_Plus.getProxy().getLevelOrOverworld().getBlockEntity(data.readBlockPos());
            if (blockEntity instanceof JukeboxBlockEntity jukeboxBlockEntity) {
                return new JukeBoxMenu(windowId, inv, jukeboxBlockEntity.getContainer());
            } else {
                throw new IllegalStateException("Can not create JukeBoxMenu from non JukeBox block");
            }
        }));
    }
}
