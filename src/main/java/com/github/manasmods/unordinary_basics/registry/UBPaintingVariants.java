package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UBPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, UnordinaryBasics.MOD_ID);

    public static final RegistryObject<PaintingVariant> TROJAN_RABBIT = PAINTING_VARIANTS.register("trojan_rabbit", () -> new PaintingVariant(64, 32));
    public static final RegistryObject<PaintingVariant> BIRCH = PAINTING_VARIANTS.register("birch", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> MANGROVE = PAINTING_VARIANTS.register("mangrove", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> SWAMP = PAINTING_VARIANTS.register("swamp", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> WILD = PAINTING_VARIANTS.register("wild", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> FORTRESS = PAINTING_VARIANTS.register("fortress", () -> new PaintingVariant(64, 32));
    public static final RegistryObject<PaintingVariant> BAD_APPLE = PAINTING_VARIANTS.register("bad_apple", () -> new PaintingVariant(16, 16));

    public static void register(IEventBus modEventBus) {
        PAINTING_VARIANTS.register(modEventBus);
    }
}
