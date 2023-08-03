package com.github.manasmods.unordinary_basics.painting;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UBPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_MOVTIES =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Unordinary_Basics.MOD_ID);

    /** **/

    public static final RegistryObject<PaintingVariant> TROJAN_RABBIT =
            PAINTING_MOVTIES.register("trojan_rabbit", () -> new PaintingVariant(64, 32));
    public static final RegistryObject<PaintingVariant> BIRCH =
            PAINTING_MOVTIES.register("birch", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> MANGROVE =
            PAINTING_MOVTIES.register("mangrove", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> SWAMP =
            PAINTING_MOVTIES.register("swamp", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> WILD =
            PAINTING_MOVTIES.register("wild", () -> new PaintingVariant(96, 48));
    public static final RegistryObject<PaintingVariant> FORTRESS =
            PAINTING_MOVTIES.register("fortress", () -> new PaintingVariant(64, 32));
    public static final RegistryObject<PaintingVariant> CAT =
            PAINTING_MOVTIES.register("cat", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> JIN =
            PAINTING_MOVTIES.register("jin", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> NAO =
            PAINTING_MOVTIES.register("nao", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> YUKI =
            PAINTING_MOVTIES.register("yuki", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> TOMO =
            PAINTING_MOVTIES.register("tomo", () -> new PaintingVariant(16, 16));

    /** **/

    public static void register(IEventBus eventBus) {
        PAINTING_MOVTIES.register(eventBus);
    }
}