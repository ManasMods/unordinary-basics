package com.github.manasmods.unordinary_basics.registry;


import com.github.manasmods.unordinary_basics.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.awt.*;

class ItemRegistry {
    /**
     * This Method will register all Items to Forge.
     * It is called though the {@link Unordinary_BasicsRegistry#register(IEventBus)} Method.
     */
    static void register(DeferredRegister<Item> registry) {
        registerMisc(registry);
        registerSpawnEgg(registry);
    }

    private static void registerMisc(DeferredRegister<Item> registry) {
        registry.register("animal_bait", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("glider", () -> new GliderItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("leaper", () -> new LeaperItem(new Item.Properties().stacksTo(1).durability(80)));
        registry.register("map_book", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).durability(265)));
        registry.register("potion_belt", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("pouch", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
        registry.register("quiver", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).durability(265)));
        registry.register("rabbit_boots", () -> new RabbitBootsItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("technoblade_crown", () -> new TechnobladeCrownItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).rarity(Rarity.EPIC)));
    }
    private static void registerSpawnEgg(DeferredRegister<Item> registry) {
        registry.register("grizzly_bear_spawn_egg", () -> new ForgeSpawnEggItem(() -> UBEntityTypes.GRIZZLY_BEAR.get(),
                new Color(96, 65, 39).getRGB(), new Color(86, 73, 61).getRGB(), new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
    }
}
