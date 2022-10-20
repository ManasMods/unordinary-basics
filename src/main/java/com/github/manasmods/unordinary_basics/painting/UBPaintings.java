package com.github.manasmods.unordinary_basics.painting;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UBPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOVTIES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Unordinary_Basics.MOD_ID);

    /** **/

    public static final RegistryObject<Motive> TROJAN_RABBIT =
            PAINTING_MOVTIES.register("trojan_rabbit", () -> new Motive(64, 32));

    public static final RegistryObject<Motive> BIRCH =
            PAINTING_MOVTIES.register("birch", () -> new Motive(96, 48));
    public static final RegistryObject<Motive> MANGROVE =
            PAINTING_MOVTIES.register("mangrove", () -> new Motive(96, 48));
    public static final RegistryObject<Motive> SWAMP =
            PAINTING_MOVTIES.register("swamp", () -> new Motive(96, 48));
    public static final RegistryObject<Motive> WILD =
            PAINTING_MOVTIES.register("wild", () -> new Motive(96, 48));
    public static final RegistryObject<Motive> FORTRESS =
            PAINTING_MOVTIES.register("fortress", () -> new Motive(64, 32));

    public static final RegistryObject<Motive> CAT =
            PAINTING_MOVTIES.register("cat", () -> new Motive(16, 16));

    /** **/

    public static void register(IEventBus eventBus) {
        PAINTING_MOVTIES.register(eventBus);
    }
}