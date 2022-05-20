package com.github.manasmods.unordinary_basics.events;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Sheep sheep) {
            sheep.goalSelector.addGoal(3, new TemptGoal(sheep, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Cow cow) {
            cow.goalSelector.addGoal(3, new TemptGoal(cow, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Pig pig) {
            pig.goalSelector.addGoal(3, new TemptGoal(pig, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Chicken chicken) {
            chicken.goalSelector.addGoal(3, new TemptGoal(chicken, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Horse horse) {
            horse.goalSelector.addGoal(3, new TemptGoal(horse, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Donkey donkey) {
            donkey.goalSelector.addGoal(3, new TemptGoal(donkey, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Mule mule) {
            mule.goalSelector.addGoal(3, new TemptGoal(mule, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Parrot parrot) {
            parrot.goalSelector.addGoal(3, new TemptGoal(parrot, 1.5D, Ingredient.of(Unordinary_BasicsItems.ANIMAL_BAIT), false));
        }
        else if (event.getEntity() instanceof Villager villager) {
            villager.goalSelector.addGoal(3, new TemptGoal(villager, 1.5D, Ingredient.of(Items.EMERALD), false));
            villager.goalSelector.addGoal(3, new TemptGoal(villager, 1.75D, Ingredient.of(Items.EMERALD_BLOCK), false));
        }

    }
    private void setupEvents()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addListener(ModEvents::onEntityJoinWorld);
    }
}