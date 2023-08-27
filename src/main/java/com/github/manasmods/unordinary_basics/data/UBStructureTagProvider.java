package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import com.github.manasmods.unordinary_basics.world.structure.UBStructures;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class UBStructureTagProvider extends StructureTagsProvider {

    public UBStructureTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, UnordinaryBasics.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        tag(UBTags.Structures.MASTER_SWORD_SHRINE).add(UBStructures.MASTER_SWORD_SHRINE);

    }
}
