package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.entity.GrizzlyBearEntity;
import com.github.manasmods.unordinary_basics.entity.GrolarBearEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UBEntityTypes {
    public static RegistryObject<EntityType<GrolarBearEntity>> GROLAR_BEAR;
    public static RegistryObject<EntityType<GrizzlyBearEntity>> GRIZZLY_BEAR;
    static void register(DeferredRegister<EntityType<?>> registry) {
//        GRIZZLY_BEAR = registry.register("grizzly_bear",
//                () -> EntityType.Builder.of(GrizzlyBearEntity::new, MobCategory.CREATURE)
//                        .sized(1.7f, 1.7f)
//                        .build(new ResourceLocation(Unordinary_Basics.MOD_ID, "grizzly_bear").toString()));
//        GROLAR_BEAR = registry.register("grolar_bear",
//                () -> EntityType.Builder.of(GrolarBearEntity::new, MobCategory.CREATURE)
//                        .sized(1.55f, 1.55f)
//                        .build(new ResourceLocation(Unordinary_Basics.MOD_ID, "grolar_bear").toString()));
    }
}