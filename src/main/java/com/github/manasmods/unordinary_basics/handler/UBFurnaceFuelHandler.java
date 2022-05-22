package com.github.manasmods.unordinary_basics.handler;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unordinary_Basics.MOD_ID)
public class UBFurnaceFuelHandler {

    @SubscribeEvent
    public static void UBFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        if(event.getItemStack().is(Items.LAVA_BUCKET)) {
            event.setBurnTime(38400);
        }
    }
}
/*

From Kaupenjoe in his discord

Alright, so let's think about this :)
When you middle mouse button click on the FurnaceFuelBurnTimeEvent class, you will that it contains the following fields:
public class FurnaceFuelBurnTimeEvent extends Event
{
    @Nonnull
    private final ItemStack itemStack;
    @Nullable
    private final RecipeType<?> recipeType;
    private int burnTime;
now that's a pretty good start. These are accessible via getter (and setter) method:
    public ItemStack getItemStack()
    {
        return itemStack;
    }
 (also part of that class) :PauseChamp:
When you look at the top of the class, in event very often, you find some comments/javadoc that describes what happens in that event
FurnaceFuelBurnTimeEvent is fired when determining the fuel value for an ItemStack.
this then means if you get the item stack from the event and check if it is a lava bucket and then change the burn time... it should work :PauseChamp:
event.getItemStack() getting the item stack
if(event.getItemStack().is(Items.LAVA_BUCKET)) { seeing if it is a lava bucket
event.setBurnTime(9001); setting the time something different. Putting it together:
if(event.getItemStack().is(Items.LAVA_BUCKET)) {
  event.setBurnTime(2000);
}
and also don't forget to add @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID) with your mod id above the class :)
 */