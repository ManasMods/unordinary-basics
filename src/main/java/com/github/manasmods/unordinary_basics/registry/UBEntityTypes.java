package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.entity.GrizzlyBearEntity;
import com.github.manasmods.unordinary_basics.entity.GrolarBearEntity;
import com.github.manasmods.unordinary_basics.entity.MasterSwordBeam;
import com.github.manasmods.unordinary_basics.entity.NonExplosiveWitherSkull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UBEntityTypes {
    public static RegistryObject<EntityType<GrolarBearEntity>> GROLAR_BEAR;
    public static RegistryObject<EntityType<GrizzlyBearEntity>> GRIZZLY_BEAR;
    public static RegistryObject<EntityType<NonExplosiveWitherSkull>> NON_EXPLOSIVE_WITHER_SKULL;
    public static RegistryObject<EntityType<MasterSwordBeam>> MASTER_SWORD_BEAM;

    static void register(DeferredRegister<EntityType<?>> registry) {
//      GRIZZLY_BEAR = registry.register("grizzly_bear",
//               () -> EntityType.Builder.of(GrizzlyBearEntity::new, MobCategory.CREATURE)
//                        .sized(1.7f, 1.7f)
//                        .build(new ResourceLocation(Unordinary_Basics.MOD_ID, "grizzly_bear").toString()));
//        GROLAR_BEAR = registry.register("grolar_bear",
//                () -> EntityType.Builder.of(GrolarBearEntity::new, MobCategory.CREATURE)
//                        .sized(1.55f, 1.55f)
//                        .build(new ResourceLocation(Unordinary_Basics.MOD_ID, "grolar_bear").toString()));
        NON_EXPLOSIVE_WITHER_SKULL = registry.register("non_explosive_wither_skull",
                () -> EntityType.Builder.<NonExplosiveWitherSkull>of(NonExplosiveWitherSkull::new, MobCategory.MISC)
                        .sized(0.3125F, 0.3125F)
                        .clientTrackingRange(4).updateInterval(10)
                        .build(new ResourceLocation(Unordinary_Basics.MOD_ID,"non_explosive_wither_skull").toString()));
        MASTER_SWORD_BEAM = registry.register("master_sword_beam",
                () -> EntityType.Builder.<MasterSwordBeam>of(MasterSwordBeam::new, MobCategory.MISC)
                        .sized(1f, 0.3125F)
                        .clientTrackingRange(4).updateInterval(10)
                        .build(new ResourceLocation(Unordinary_Basics.MOD_ID,"master_sword_beam").toString()));
    }
}