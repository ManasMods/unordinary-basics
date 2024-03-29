package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.BlockTagProvider;
import com.github.manasmods.manascore.api.data.gen.ItemTagProvider;
import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

public class UBItemTagProvider extends ItemTagProvider {

    public UBItemTagProvider(GatherDataEvent gatherDataEvent, BlockTagProvider blockTagProvider) {
        super(gatherDataEvent, UnordinaryBasics.MOD_ID, blockTagProvider);
    }

    @Override
    protected void generate() {
        tag(UBTags.Items.POTION_BELT_ITEMS)
                .addTag(UBTags.Items.MILK_BOTTLE)
                .add(Items.GLASS_BOTTLE,Items.DRAGON_BREATH,Items.HONEY_BOTTLE);
        tag(UBTags.Items.UB_SLOT_BACK)
                .add(Items.ELYTRA);
        tag(UBTags.Items.UB_SLOT_WAIST);

        tag(UBTags.Items.MILK_BOTTLE)
                .add(Unordinary_BasicsItems.MILK_BOTTLE, Unordinary_BasicsItems.GOAT_MILK_BOTTLE);
        tag(UBTags.Items.BEAR_FOOD)
                .add(Items.APPLE, Items.COD, Items.CHICKEN, Items.BEEF, Items.MUTTON);
        tag(UBTags.Items.TAME_BEAR_FOOD)
                .add(Items.SWEET_BERRIES, Items.SALMON);
        tag(UBTags.Items.STORAGE_WOODEN)
                .addTag(Tags.Items.BARRELS_WOODEN).addTag(Tags.Items.CHESTS_WOODEN);
        tag(UBTags.Items.STONE)
                .add(Items.COBBLESTONE,Items.STONE,Items.BLACKSTONE,Items.DEEPSLATE,Items.BASALT);
        tag(UBTags.Items.BLACK_DYE_RESOURCES)
                .add(Items.CHARCOAL, Items.COAL, Items.INK_SAC, Items.WITHER_ROSE);
    }
}
