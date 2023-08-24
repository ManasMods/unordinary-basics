package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import javax.annotation.Nullable;

public class Unordinary_BasicsEntityTypeTagProvider extends EntityTypeTagsProvider {
    public Unordinary_BasicsEntityTypeTagProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    public Unordinary_BasicsEntityTypeTagProvider(GatherDataEvent gatherDataEvent) {
        this(gatherDataEvent.getGenerator(), Unordinary_Basics.MOD_ID, gatherDataEvent.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        tag(UBTags.EntityTypes.SCULK_ENEMIES).add(EntityType.WARDEN);
    }
}
