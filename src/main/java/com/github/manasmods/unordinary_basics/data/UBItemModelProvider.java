package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.ItemModelProvider;
import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UBItemModelProvider extends ItemModelProvider {
    public UBItemModelProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent, UnordinaryBasics.MOD_ID);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
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

        handheldSingleTexture(Unordinary_BasicsItems.DECAYED_MASTER_SWORD);
        handheldSingleTexture(Unordinary_BasicsItems.MASTER_SWORD);

        basicItem(new ResourceLocation(UnordinaryBasics.MOD_ID,"quiver_no_items"));
        basicItem(new ResourceLocation(UnordinaryBasics.MOD_ID,"quiver_has_items"));

        basicItem(new ResourceLocation(UnordinaryBasics.MOD_ID,"builders_glove_gui"));
        
        wallItem(Unordinary_BasicsBlocks.ANDESITE_BRICK_WALL, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.CALCITE_BRICK_WALL, Unordinary_BasicsBlocks.CALCITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.DIORITE_BRICK_WALL, Unordinary_BasicsBlocks.DIORITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.DRIPSTONE_BRICK_WALL, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.GRANITE_BRICK_WALL, Unordinary_BasicsBlocks.GRANITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.TUFF_BRICK_WALL, Unordinary_BasicsBlocks.TUFF_BRICKS);
        wallItem(Unordinary_BasicsBlocks.POLISHED_TUFF_WALL, Unordinary_BasicsBlocks.POLISHED_TUFF);
        wallItem(Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
        wallItem(Unordinary_BasicsBlocks.SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
        wallItem(Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);
        wallItem(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
        wallItem(Unordinary_BasicsBlocks.SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
        wallItem(Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);

        blockItem(Unordinary_BasicsBlocks.MASTER_SWORD_SHRINE);

        //generate models for slime compass
        for (int i = 0; i < 32; ++i){
            if (i >= 10) {
                basicItem(new ResourceLocation(UnordinaryBasics.MOD_ID, "slime_compass/slime_compass_" + i),new ResourceLocation("unordinary_basics", "item/slime_compass/slime_compass_" + i));
            } else basicItem(new ResourceLocation(UnordinaryBasics.MOD_ID, "slime_compass/slime_compass_0" + i),new ResourceLocation("unordinary_basics", "item/slime_compass/slime_compass_0" + i));
        }
    }

    //for seperate model location
    public ItemModelBuilder basicItem(ResourceLocation item, ResourceLocation modelLocation)
    {
        return getBuilder(modelLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                    modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(UnordinaryBasics.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(UnordinaryBasics.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(Block block, Block baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(UnordinaryBasics.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(UnordinaryBasics.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(),
                new ResourceLocation(UnordinaryBasics.MOD_ID,"block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }
}