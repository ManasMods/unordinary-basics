package com.github.manasmods.unordinary_basics.registry;


import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import com.github.manasmods.unordinary_basics.item.custom.BackpackItem;
import com.github.manasmods.unordinary_basics.item.custom.BuildersGloveItem;
import com.github.manasmods.unordinary_basics.item.custom.DecayedMasterSwordItem;
import com.github.manasmods.unordinary_basics.item.custom.EffectCurativeItem;
import com.github.manasmods.unordinary_basics.item.custom.GliderItem;
import com.github.manasmods.unordinary_basics.item.custom.LeaperItem;
import com.github.manasmods.unordinary_basics.item.custom.MasterSwordItem;
import com.github.manasmods.unordinary_basics.item.custom.PotionBeltItem;
import com.github.manasmods.unordinary_basics.item.custom.QuiverItem;
import com.github.manasmods.unordinary_basics.item.custom.RabbitBootsItem;
import com.github.manasmods.unordinary_basics.item.custom.RedstonePouchItem;
import com.github.manasmods.unordinary_basics.item.custom.SlimeCompassItem;
import com.github.manasmods.unordinary_basics.item.custom.TechnobladeCrownItem;
import com.github.manasmods.unordinary_basics.item.custom.ZenithItem;
import com.github.manasmods.unordinary_basics.item.templates.MusicDiscItem;
import com.github.manasmods.unordinary_basics.item.templates.TooltipItem;
import com.github.manasmods.unordinary_basics.sound.UBSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

class ItemRegistry {
    /**
     * This Method will register all Items to Forge.
     * It is called though the {@link Unordinary_BasicsRegistry#register(IEventBus)} Method.
     */
    static void register(DeferredRegister<Item> registry) {
        registerMisc(registry);
//        registerSpawnEgg(registry);
    }

    private static void registerMisc(DeferredRegister<Item> registry) {
        registry.register("equine_tracker", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
        registry.register("pouch", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
        registry.register("animal_bait", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("potion_belt", () -> new PotionBeltItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("goat_milk_bucket", () -> new EffectCurativeItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1),Items.BUCKET));
        registry.register("goat_milk_bottle", () -> new EffectCurativeItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1),Items.GLASS_BOTTLE));
        registry.register("milk_bottle", () -> new EffectCurativeItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1),Items.GLASS_BOTTLE));
        registry.register("decayed_master_sword", DecayedMasterSwordItem::new);
        registry.register("master_sword", MasterSwordItem::new);
        registry.register("map_book", () -> new Item(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).durability(265)));
        registry.register("unknown_blade_fragment", () -> new TooltipItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1),Component.translatable("tooltip.unordinary_basics.unknown_blade_fragment")));
        registry.register("unknown_sword_handle_fragment", () -> new TooltipItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1),Component.translatable("tooltip.unordinary_basics.unknown_sword_handle_fragment")));
        registry.register("unknown_hilt_fragment", () -> new TooltipItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1), Component.translatable("tooltip.unordinary_basics.unknown_hilt_fragment")));
        registry.register("zenith", ZenithItem::new);
        registry.register("leaper", () -> new LeaperItem(new Item.Properties().stacksTo(1).durability(80)));
        registry.register("quiver", () -> new QuiverItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("rabbit_boots", () -> new RabbitBootsItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("technoblade_crown", () -> new TechnobladeCrownItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1).rarity(Rarity.EPIC)));
        registry.register("glider", () -> new GliderItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("builders_glove", () -> new BuildersGloveItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("slime_compass", () -> new SlimeCompassItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("redstone_pouch", () -> new RedstonePouchItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("barrel_backpack", () -> new BackpackItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));
        registry.register("chest_backpack", () -> new BackpackItem(new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS).stacksTo(1)));

        registry.register("music_disc_queen", () -> new MusicDiscItem((() -> UBSounds.QUEEN),2700));


    }

    /*
    private static void registerSpawnEgg(DeferredRegister<Item> registry) {
        registry.register("grizzly_bear_spawn_egg", () -> new ForgeSpawnEggItem(() -> UBEntityTypes.GRIZZLY_BEAR.get(),
                new Color(96, 65, 39).getRGB(), new Color(86, 73, 61).getRGB(), new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
        registry.register("grolar_bear_spawn_egg", () -> new ForgeSpawnEggItem(() -> UBEntityTypes.GROLAR_BEAR.get(),
                new Color(152, 110, 81).getRGB(), new Color(161, 158, 156).getRGB(), new Item.Properties().tab(Unordinary_BasicsCreativeTab.ITEMS)));
    }
     */
}
