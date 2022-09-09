package com.github.manasmods.unordinary_basics.registry;


import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import com.github.manasmods.unordinary_basics.item.custom.*;
import com.github.manasmods.unordinary_basics.item.templates.MusicDiscItem;
import com.github.manasmods.unordinary_basics.sound.UBSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MilkBucketItem;
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
        registry.register("pouch", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
        registry.register("animal_bait", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("potion_belt", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("goat_milk_bucket", () -> new MilkBucketItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("goat_milk_bottle", () -> new MilkBucketItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("milk_bottle", () -> new MilkBucketItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("map_book", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).durability(265)));
        registry.register("unknown_blade_fragment", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("unknown_handle_fragment", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("unknown_hilt_fragment", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("zenith", ZenithItem::new);
        registry.register("leaper", () -> new LeaperItem(new Item.Properties().stacksTo(1).durability(80)));
        registry.register("quiver", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).durability(265)));
        registry.register("rabbit_boots", () -> new RabbitBootsItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("technoblade_crown", () -> new TechnobladeCrownItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).rarity(Rarity.EPIC)));
        registry.register("glider", () -> new GliderItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));

        registry.register("music_disc_queen", () -> new MusicDiscItem(() -> UBSounds.QUEEN));


    }
    private static void registerSpawnEgg(DeferredRegister<Item> registry) {
        registry.register("grizzly_bear_spawn_egg", () -> new ForgeSpawnEggItem(() -> UBEntityTypes.GRIZZLY_BEAR.get(),
                new Color(96, 65, 39).getRGB(), new Color(86, 73, 61).getRGB(), new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
        registry.register("grolar_bear_spawn_egg", () -> new ForgeSpawnEggItem(() -> UBEntityTypes.GROLAR_BEAR.get(),
                new Color(152, 110, 81).getRGB(), new Color(161, 158, 156).getRGB(), new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
    }
}
