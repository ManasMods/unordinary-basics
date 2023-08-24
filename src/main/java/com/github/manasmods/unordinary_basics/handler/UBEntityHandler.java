package com.github.manasmods.unordinary_basics.handler;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.entity.MasterSwordWardenEntity;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.registry.UBEntityTypes;
import com.github.manasmods.unordinary_basics.utils.UBUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UBEntityHandler {
    /** Used to add the {@link TemptGoal} for the animal bait item to all targeted entities. */
    @SubscribeEvent
    public static void onAnimalJoinWorld(final EntityJoinLevelEvent event) {
        //Check if entity is a PathfinderMob
        if (!(event.getEntity() instanceof PathfinderMob mob)) return;
        //Check if mob is one of the targets
        if (!UBUtils.isInstanceOf(mob, Sheep.class, Cow.class, Pig.class, Chicken.class, Horse.class, Donkey.class, Mule.class, Parrot.class, Rabbit.class)) return;
        //Add Goal
        mob.goalSelector.addGoal(3, new TemptGoal(mob, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
    }

    @SubscribeEvent
    public static void onVillagerJoinWorld(final EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) return;
        villager.goalSelector.addGoal(3, new TemptGoal(villager, 1.5D, Ingredient.of(Items.EMERALD), false));
        villager.goalSelector.addGoal(3, new TemptGoal(villager, 1.75D, Ingredient.of(Items.EMERALD_BLOCK), false));
    }

    @SubscribeEvent
    public static void onWardenSpawn(final EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Warden warden)) return;
        if (warden.getRandom().nextInt(4) == 2 && event.getLevel() instanceof ServerLevel level) { //1 out of 4 warden to spawn this one
            SpawnUtil.trySpawnMob(UBEntityTypes.MASTER_SWORD_WARDEN.get(), MobSpawnType.TRIGGERED, level,
                    warden.blockPosition(), 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
            event.setCanceled(true);
        }
    }

    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(UBEntityTypes.MASTER_SWORD_WARDEN.get(), MasterSwordWardenEntity.createAttributes().build());
//        event.put(UBEntityTypes.GRIZZLY_BEAR.get(), GrizzlyBearEntity.createAttributes().build());
//        event.put(UBEntityTypes.GROLAR_BEAR.get(), GrolarBearEntity.createAttributes().build());
    }

    public static void registerEntityPlacements() {
//        SpawnPlacements.register(UBEntityTypes.GRIZZLY_BEAR.get(),
//                SpawnPlacements.Type.IN_WATER,
//                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                GrizzlyBearEntity::checkGrizzlyBearEntitySpawnRules);
//        SpawnPlacements.register(UBEntityTypes.GROLAR_BEAR.get(),
//                SpawnPlacements.Type.IN_WATER,
//                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                GrolarBearEntity::checkGrolarBearEntitySpawnRules);
    }
}