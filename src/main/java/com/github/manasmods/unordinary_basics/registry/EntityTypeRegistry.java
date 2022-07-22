package com.github.manasmods.unordinary_basics.registry;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.entity.projectile.GrappleHookEntity;
import com.github.manasmods.unordinary_basics.item.GliderItem;
import com.github.manasmods.unordinary_basics.item.GrappleHookItem;
import com.github.manasmods.unordinary_basics.item.RabbitBootsItem;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Unordinary_Basics.MOD_ID);

    public static RegistryObject<EntityType<GrappleHookEntity>> GRAPPLE_HOOK = ENTITY_TYPES.register("grapple_hook", () -> EntityType.Builder.<GrappleHookEntity>of(GrappleHookEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(Unordinary_Basics.MOD_ID, "grapple_hook").toString()));

    static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
