package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.ItemModelProvider;
import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;

public class Unordinary_BasicsItemModelProvider extends ItemModelProvider {
    public Unordinary_BasicsItemModelProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, Unordinary_Basics.MOD_ID);
    }

    @Override
    protected void generate() {
        singleTexture(Unordinary_BasicsItems.ANIMAL_BAIT);
        singleTexture(Unordinary_BasicsItems.GOAT_MILK_BOTTLE);
        singleTexture(Unordinary_BasicsItems.GOAT_MILK_BUCKET);
        singleTexture(Unordinary_BasicsItems.MAP_BOOK);
        singleTexture(Unordinary_BasicsItems.MILK_BOTTLE);
        singleTexture(Unordinary_BasicsItems.POTION_BELT);
        singleTexture(Unordinary_BasicsItems.POUCH);
        singleTexture(Unordinary_BasicsItems.RABBIT_BOOTS);
        singleTexture(Unordinary_BasicsItems.TECHNOBLADE_CROWN);
        singleTexture(Unordinary_BasicsItems.REDSTONE_POUCH);
        singleTexture(Unordinary_BasicsItems.BARREL_BACKPACK);
        singleTexture(Unordinary_BasicsItems.CHEST_BACKPACK);
        singleTexture(Unordinary_BasicsItems.EQUINE_TRACKER);

        singleTexture(Unordinary_BasicsItems.UNKNOWN_BLADE_FRAGMENT);
        singleTexture(Unordinary_BasicsItems.UNKNOWN_SWORD_HANDLE_FRAGMENT);
        singleTexture(Unordinary_BasicsItems.UNKNOWN_HILT_FRAGMENT);

        singleTexture(Unordinary_BasicsItems.MUSIC_DISC_QUEEN);

        singleTexture(Item.byBlock(Unordinary_BasicsBlocks.ITEM_SORTER));

        basicItem(new ResourceLocation(Unordinary_Basics.MOD_ID,"quiver_no_items"));
        basicItem(new ResourceLocation(Unordinary_Basics.MOD_ID,"quiver_has_items"));

        basicItem(new ResourceLocation(Unordinary_Basics.MOD_ID,"builders_glove_gui"));

        //generate models for slime compass
        for (int i = 0; i < 32; ++i){
            if (i >= 10) {
                basicItem(new ResourceLocation(Unordinary_Basics.MOD_ID, "slime_compass/slime_compass_" + i),new ResourceLocation("unordinary_basics", "item/slime_compass/slime_compass_" + i));
            } else basicItem(new ResourceLocation(Unordinary_Basics.MOD_ID, "slime_compass/slime_compass_0" + i),new ResourceLocation("unordinary_basics", "item/slime_compass/slime_compass_0" + i));
        }
    }

    //for seperate model location
    public ItemModelBuilder basicItem(ResourceLocation item, ResourceLocation modelLocation)
    {
        return getBuilder(modelLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }
}