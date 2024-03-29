package com.github.manasmods.unordinary_basics.data;

import com.github.manasmods.manascore.api.data.gen.RecipeProvider;
import com.github.manasmods.unordinary_basics.UnordinaryBasics;
import com.github.manasmods.unordinary_basics.block.Unordinary_BasicsBlocks;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.utils.UBTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class UBRecipeProvider extends RecipeProvider {

    public UBRecipeProvider(GatherDataEvent gatherDataEvent) {
        super(gatherDataEvent);
    }

    protected static final ImmutableList<ItemLike> COPPER_BLOCK_SMELTABLES = ImmutableList.of(Items.RAW_COPPER_BLOCK);
    protected static final ImmutableList<ItemLike> GOLD_BLOCK_SMELTABLES = ImmutableList.of(Items.RAW_GOLD_BLOCK);
    protected static final ImmutableList<ItemLike> IRON_BLOCK_SMELTABLES = ImmutableList.of(Items.RAW_IRON_BLOCK);

    @SuppressWarnings("ConstantConditions")
    protected void generate(Consumer<FinishedRecipe> consumer) {
        vanillaRecipes(consumer);
        modRecipes(consumer);
        vanillaQOLSmelting(consumer);
        modImprovedRecipes(consumer);
        modStairsAndSlabs(consumer);
        modBlockToBlock(consumer);
    }

    private void vanillaRecipes(Consumer<FinishedRecipe> consumer) {

        //IMPORTANT! For recipes that already have vanilla counterparts (e.g. hoppers, chests)
        //make sure to save the new recipe with our own modid

        ShapedRecipeBuilder.shaped(Items.HOPPER)
                .define('I', Items.IRON_INGOT)
                .define('#', UBTags.Items.STORAGE_WOODEN)
                .pattern("I I")
                .pattern("I#I")
                .pattern(" I ")
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .unlockedBy("has_wooden_storage", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STORAGE_WOODEN).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.HOPPER).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.FURNACE)
                .define('#', UBTags.Items.STONE)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.FURNACE).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.DISPENSER)
                .define('#', UBTags.Items.STONE)
                .define('B', Items.BOW)
                .define('R', Items.REDSTONE)
                .pattern("###")
                .pattern("#B#")
                .pattern("#R#")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.DISPENSER).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.DROPPER)
                .define('#', UBTags.Items.STONE)
                .define('R', Items.REDSTONE)
                .pattern("###")
                .pattern("# #")
                .pattern("#R#")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.DROPPER).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.OBSERVER)
                .define('#', UBTags.Items.STONE)
                .define('R', Items.REDSTONE)
                .define('Q', Items.QUARTZ)
                .pattern("###")
                .pattern("RRQ")
                .pattern("###")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.OBSERVER).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.PISTON)
                .define('#', UBTags.Items.STONE)
                .define('R', Items.REDSTONE)
                .define('I', Items.IRON_INGOT)
                .define('W', ItemTags.PLANKS)
                .pattern("WWW")
                .pattern("#I#")
                .pattern("#R#")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.PISTON).getPath()));

        ShapedRecipeBuilder.shaped(Items.STONE_SWORD)
                .define('#', UBTags.Items.STONE)
                .define('S', Items.STICK)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.STONE_SWORD).getPath()));

        ShapedRecipeBuilder.shaped(Items.STONE_PICKAXE)
                .define('#', UBTags.Items.STONE)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.STONE_PICKAXE).getPath()));

        ShapedRecipeBuilder.shaped(Items.STONE_AXE)
                .define('#', UBTags.Items.STONE)
                .define('S', Items.STICK)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.STONE_AXE).getPath()));

        ShapedRecipeBuilder.shaped(Items.STONE_SHOVEL)
                .define('#', UBTags.Items.STONE)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.STONE_SHOVEL).getPath()));

        ShapedRecipeBuilder.shaped(Items.STONE_HOE)
                .define('#', UBTags.Items.STONE)
                .define('S', Items.STICK)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_stones", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.STONE).build()))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.STONE_HOE).getPath()));

        ShapedRecipeBuilder.shaped(Items.SADDLE)
                .define('L', Items.LEATHER)
                .define('S', Items.STRING)
                .define('I', Items.IRON_INGOT)
                .pattern("S S")
                .pattern("LIL")
                .pattern("S S")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.SADDLE).getPath()));

        ShapedRecipeBuilder.shaped(Items.NAME_TAG)
                .define('P', Items.PAPER)
                .define('S', Items.STRING)
                .pattern("P")
                .pattern("S")
                .unlockedBy("has_string", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.NAME_TAG).getPath()));

        ShapedRecipeBuilder.shaped(Items.TRIDENT)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .define('P', Items.PRISMARINE_SHARD)
                .pattern("III")
                .pattern("DPD")
                .pattern(" P ")
                .unlockedBy("has_prismarine_shard", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PRISMARINE_SHARD).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.TRIDENT).getPath()));

        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_HELMET)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("NIN")
                .pattern("N N")
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.CHAINMAIL_HELMET).getPath()));

        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_CHESTPLATE)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("I I")
                .pattern("NIN")
                .pattern("NNN")
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.CHAINMAIL_CHESTPLATE).getPath()));

        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_LEGGINGS)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("NIN")
                .pattern("I I")
                .pattern("N N")
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.CHAINMAIL_LEGGINGS).getPath()));

        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_BOOTS)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("N N")
                .pattern("I I")
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.CHAINMAIL_BOOTS).getPath()));

        ShapelessRecipeBuilder.shapeless(Items.ARROW)
                .requires(ItemTags.ARROWS)
                .unlockedBy("has_arrow", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.ARROWS).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Items.LEATHER_HORSE_ARMOR)
                .define('I', Items.LEATHER)
                .define('S', Items.STRING)
                .pattern("IS ")
                .pattern("III")
                .pattern("ISI")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.LEATHER_HORSE_ARMOR).getPath()));

        ShapedRecipeBuilder.shaped(Items.IRON_HORSE_ARMOR)
                .define('I', Items.IRON_INGOT)
                .define('B', Items.IRON_BLOCK)
                .define('S', Items.STRING)
                .pattern("IS ")
                .pattern("IBI")
                .pattern("ISI")
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.IRON_HORSE_ARMOR).getPath()));

        ShapedRecipeBuilder.shaped(Items.GOLDEN_HORSE_ARMOR)
                .define('I', Items.GOLD_INGOT)
                .define('B', Items.GOLD_BLOCK)
                .define('S', Items.STRING)
                .pattern("IS ")
                .pattern("IBI")
                .pattern("ISI")
                .unlockedBy("has_gold_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLD_INGOT).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.GOLDEN_HORSE_ARMOR).getPath()));

        ShapedRecipeBuilder.shaped(Items.DIAMOND_HORSE_ARMOR)
                .define('I', Items.DIAMOND)
                .define('B', Items.DIAMOND_BLOCK)
                .define('S', Items.STRING)
                .pattern("IS ")
                .pattern("IBI")
                .pattern("ISI")
                .unlockedBy("has_diamond", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.DIAMOND_HORSE_ARMOR).getPath()));

        ShapedRecipeBuilder.shaped(Items.ENCHANTED_GOLDEN_APPLE, 2)
                .define('A', Items.GOLDEN_APPLE)
                .define('B', Items.GOLD_BLOCK)
                .define('S', Items.NETHER_STAR)
                .pattern("BAB")
                .pattern("ASA")
                .pattern("BAB")
                .unlockedBy("has_golden_apple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLDEN_APPLE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.ENCHANTED_GOLDEN_APPLE).getPath()));

        ShapelessRecipeBuilder.shapeless(Items.GREEN_DYE, 2)
                .requires(Items.YELLOW_DYE)
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_yellow_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.YELLOW_DYE).build()))
                .unlockedBy("has_blue_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BLUE_DYE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.GREEN_DYE).getPath()));

        ShapelessRecipeBuilder.shapeless(Items.BLACK_DYE, 2)
                .requires(UBTags.Items.BLACK_DYE_RESOURCES)
                .unlockedBy("has_black_dyes", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(UBTags.Items.BLACK_DYE_RESOURCES).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.BLACK_DYE).getPath()));

        ShapedRecipeBuilder.shaped(Items.BROWN_MUSHROOM_BLOCK, 4)
                .define('M', Items.BROWN_MUSHROOM)
                .pattern("MM")
                .pattern("MM")
                .unlockedBy("has_brown_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BROWN_MUSHROOM).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.BROWN_MUSHROOM_BLOCK).getPath()));

        ShapedRecipeBuilder.shaped(Items.RED_MUSHROOM_BLOCK, 4)
                .define('M', Items.RED_MUSHROOM)
                .pattern("MM")
                .pattern("MM")
                .unlockedBy("has_red_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.RED_MUSHROOM).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.RED_MUSHROOM_BLOCK).getPath()));

        ShapedRecipeBuilder.shaped(Items.MUSHROOM_STEM, 4)
                .define('R', Items.RED_MUSHROOM)
                .define('B', Items.BROWN_MUSHROOM)
                .pattern("RB")
                .pattern("RB")
                .unlockedBy("has_brown_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BROWN_MUSHROOM).build()))
                .unlockedBy("has_red_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.RED_MUSHROOM).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.MUSHROOM_STEM).getPath()));

        ShapedRecipeBuilder.shaped(Items.CAKE, 1)
                .define('#', UBTags.Items.MILK_BOTTLE)
                .define('W', Items.WHEAT)
                .define('E', Items.EGG)
                .define('S', Items.SUGAR)
                .pattern("###")
                .pattern("SES")
                .pattern("WWW")
                .unlockedBy("has_goat_milk_bottle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.GOAT_MILK_BOTTLE).build()))
                .unlockedBy("has_milk_bottle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.MILK_BOTTLE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.CAKE).getPath()));

        // Brick Blocks

        ShapedRecipeBuilder.shaped(Blocks.BRICKS, 2)
                .define('#', Items.BRICK)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_brick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BRICK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Items.BRICK).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.STONE_BRICKS, 9)
                .define('#', Blocks.STONE)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stone", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.STONE).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Item.byBlock(Blocks.STONE)).getPath()));

        ShapedRecipeBuilder.shaped(Blocks.QUARTZ_BRICKS, 9)
                .define('#', Blocks.QUARTZ_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_quartz_block", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.QUARTZ_BLOCK).build()))
                .save(consumer,new ResourceLocation(UnordinaryBasics.MOD_ID,ForgeRegistries.ITEMS.getKey(Item.byBlock(Blocks.QUARTZ_BLOCK)).getPath()));

    }

    private void modRecipes(Consumer<FinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.ANIMAL_BAIT)
                .requires(Items.CARROT)
                .requires(Items.GOLDEN_CARROT)
                .requires(Items.POTATO)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT_SEEDS)
                .requires(Unordinary_BasicsItems.POUCH)
                .unlockedBy("has_pouch", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.POUCH).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.POUCH)
                .define('S', Items.STRING)
                .define('L', Items.LEATHER)
                .pattern("SL")
                .pattern("LL")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.RABBIT_BOOTS)
                .define('F', Items.RABBIT_FOOT)
                .define('L', Items.RABBIT_HIDE)
                .define('B', Items.LEATHER_BOOTS)
                .pattern("LBL")
                .pattern("F F")
                .unlockedBy("has_rabbit_hide", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.RABBIT_HIDE).build()))
                .unlockedBy("has_rabbit_foot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.RABBIT_FOOT).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.POTION_BELT)
                .define('S', Items.STRING)
                .define('L', Items.LEATHER)
                .define('P', Unordinary_BasicsItems.POUCH)
                .pattern(" L ")
                .pattern("LSL")
                .pattern(" LP")
                .unlockedBy("has_pouch", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.POUCH).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.SLIME_COMPASS)
                .define('C', Items.COMPASS)
                .define('S', Items.SLIME_BALL)
                .define('A', Items.AMETHYST_SHARD)
                .pattern("ASA")
                .pattern("SCS")
                .pattern("ASA")
                .unlockedBy("has_compass", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COMPASS).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.REDSTONE_POUCH)
                .define('X', Unordinary_BasicsItems.POUCH)
                .define('R', Items.REDSTONE)
                .pattern("RRR")
                .pattern("RXR")
                .pattern("RRR")
                .unlockedBy("has_pouch", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.POUCH).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.GLIDER)
                .define('P', Items.PHANTOM_MEMBRANE)
                .define('S', Items.STRING)
                .define('I', Items.STICK)
                .pattern("PPP")
                .pattern("SIS")
                .pattern("I I")
                .unlockedBy("has_pouch", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.POUCH).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.BARREL_BACKPACK)
                .define('S', Items.STRING)
                .define('P', ItemTags.PLANKS)
                .define('C', Items.BARREL)
                .pattern("SCS")
                .pattern(" P ")
                .unlockedBy("has_barrel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BARREL).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.CHEST_BACKPACK)
                .define('S', Items.STRING)
                .define('P', ItemTags.PLANKS)
                .define('C', Items.CHEST)
                .pattern("SCS")
                .pattern(" P ")
                .unlockedBy("has_chest", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.CHEST).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.QUIVER)
                .define('L', Items.LEATHER)
                .define('S', Tags.Items.STRING)
                .pattern("LS")
                .pattern("LS")
                .pattern("LS")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.BUILDERS_GLOVE)
                .define('L', Items.LEATHER)
                .define('W', ItemTags.WOOL)
                .pattern("LLL")
                .pattern("LWL")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsBlocks.ITEM_SORTER)
                .define('H', Items.HOPPER)
                .define('N', Items.IRON_NUGGET)
                .define('T', ItemTags.SIGNS)
                .pattern("NTN")
                .pattern(" H ")
                .unlockedBy("has_hopper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.HOPPER).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.MILK_BOTTLE, 3)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy("has_milk_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.MILK_BUCKET).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.GOAT_MILK_BOTTLE, 3)
                .requires(Unordinary_BasicsItems.GOAT_MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy("has_goat_milk_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsItems.GOAT_MILK_BUCKET).build()))
                .save(consumer);

        crownSmithing(consumer, Items.NETHERITE_HELMET, Unordinary_BasicsItems.TECHNOBLADE_CROWN);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsItems.EQUINE_TRACKER)
                .define('A', Items.AMETHYST_SHARD)
                .define('I', Items.IRON_INGOT)
                .pattern(" I ")
                .pattern("IAI")
                .pattern(" I ")
                .unlockedBy("has_amethyst_shard", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.AMETHYST_SHARD).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.COPPER_COIN)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COPPER_INGOT).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.IRON_COIN)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_copper_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.GOLD_COIN)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_gold_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLD_INGOT).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.DIAMOND_COIN)
                .requires(Items.DIAMOND)
                .unlockedBy("has_diamond", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Unordinary_BasicsItems.NETHERITE_COIN)
                .requires(Items.NETHERITE_INGOT)
                .unlockedBy("has_netherite_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.NETHERITE_INGOT).build()))
                .save(consumer);

        nineStorage(consumer, Items.ANDESITE, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.ANDESITE_BRICK_STAIRS, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.ANDESITE_BRICK_SLAB, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.ANDESITE_BRICK_WALL, Unordinary_BasicsBlocks.ANDESITE_BRICKS);

        nineStorage(consumer, Items.POLISHED_ANDESITE, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);

        nineStorage(consumer, Items.CALCITE, Unordinary_BasicsBlocks.CALCITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.CALCITE_BRICK_STAIRS, Unordinary_BasicsBlocks.CALCITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.CALCITE_BRICK_SLAB, Unordinary_BasicsBlocks.CALCITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.CALCITE_BRICK_WALL, Unordinary_BasicsBlocks.CALCITE_BRICKS);

        nineStorage(consumer, Items.DIORITE, Unordinary_BasicsBlocks.DIORITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.DIORITE_BRICK_STAIRS, Unordinary_BasicsBlocks.DIORITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.DIORITE_BRICK_SLAB, Unordinary_BasicsBlocks.DIORITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.DIORITE_BRICK_WALL, Unordinary_BasicsBlocks.DIORITE_BRICKS);

        nineStorage(consumer, Items.POLISHED_DIORITE, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);

        nineStorage(consumer, Items.DRIPSTONE_BLOCK, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.DRIPSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.DRIPSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.DRIPSTONE_BRICK_WALL, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);

        nineStorage(consumer, Items.GRANITE, Unordinary_BasicsBlocks.GRANITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.GRANITE_BRICK_STAIRS, Unordinary_BasicsBlocks.GRANITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.GRANITE_BRICK_SLAB, Unordinary_BasicsBlocks.GRANITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.GRANITE_BRICK_WALL, Unordinary_BasicsBlocks.GRANITE_BRICKS);

        nineStorage(consumer, Items.POLISHED_GRANITE, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);

        nineStorage(consumer, Items.TUFF, Unordinary_BasicsBlocks.TUFF_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.TUFF_BRICK_STAIRS, Unordinary_BasicsBlocks.TUFF_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.TUFF_BRICK_SLAB, Unordinary_BasicsBlocks.TUFF_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.TUFF_BRICK_WALL, Unordinary_BasicsBlocks.TUFF_BRICKS);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsBlocks.POLISHED_TUFF)
                .define('#', Items.TUFF)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_tuff", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.TUFF).build()))
                .save(consumer);
        nineStorage(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_TUFF);
        slab(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_TUFF);
        wall(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_TUFF);

        nineStorage(consumer, Items.SOUL_SAND, Unordinary_BasicsBlocks.SOUL_SANDSTONE);
        stairs(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.SOUL_SANDSTONE);
        slab(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.SOUL_SANDSTONE);
        wall(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.SOUL_SANDSTONE);

        smeltingRecipe(consumer, Ingredient.of(Unordinary_BasicsBlocks.SOUL_SANDSTONE), Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE, 0.1F, 200);
        stairs(consumer, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
        slab(consumer, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
        slab(consumer, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE)
                .define('#', Unordinary_BasicsBlocks.SOUL_SANDSTONE)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_soul_sandstone", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsBlocks.SOUL_SANDSTONE).build()))
                .save(consumer);
        stairs(consumer, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
        slab(consumer, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
        wall(consumer, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);

        ShapedRecipeBuilder.shaped(Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE)
                .define('#', Unordinary_BasicsBlocks.SOUL_SANDSTONE_SLAB)
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_soul_sandstone", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Unordinary_BasicsBlocks.SOUL_SANDSTONE).build()))
                .save(consumer);        
        stairs(consumer, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);
        slab(consumer, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);
        wall(consumer, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);

        nineStorage(consumer, Items.SANDSTONE, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);

        nineStorage(consumer, Items.RED_SANDSTONE, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);

        nineStorage(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
        wall(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.ANDESITE_BRICK_STAIRS, Unordinary_BasicsBlocks.ANDESITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.ANDESITE_BRICK_SLAB, Unordinary_BasicsBlocks.ANDESITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.ANDESITE_BRICK_WALL, Unordinary_BasicsBlocks.ANDESITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_ANDESITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CALCITE_BRICK_STAIRS, Unordinary_BasicsBlocks.CALCITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CALCITE_BRICK_SLAB, Unordinary_BasicsBlocks.CALCITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CALCITE_BRICK_WALL, Unordinary_BasicsBlocks.CALCITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.DIORITE_BRICK_STAIRS, Unordinary_BasicsBlocks.DIORITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.DIORITE_BRICK_SLAB, Unordinary_BasicsBlocks.DIORITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.DIORITE_BRICK_WALL, Unordinary_BasicsBlocks.DIORITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_DIORITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.DRIPSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.DRIPSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.DRIPSTONE_BRICK_WALL, Unordinary_BasicsBlocks.DRIPSTONE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.GRANITE_BRICK_STAIRS, Unordinary_BasicsBlocks.GRANITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.GRANITE_BRICK_SLAB, Unordinary_BasicsBlocks.GRANITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.GRANITE_BRICK_WALL, Unordinary_BasicsBlocks.GRANITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_GRANITE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.TUFF_BRICK_STAIRS, Unordinary_BasicsBlocks.TUFF_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.TUFF_BRICK_SLAB, Unordinary_BasicsBlocks.TUFF_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.TUFF_BRICK_WALL, Unordinary_BasicsBlocks.TUFF_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_STAIRS, Unordinary_BasicsBlocks.POLISHED_TUFF);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_SLAB, Unordinary_BasicsBlocks.POLISHED_TUFF, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_WALL, Unordinary_BasicsBlocks.POLISHED_TUFF);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_STAIRS, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_SLAB, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICK_WALL, Unordinary_BasicsBlocks.POLISHED_TUFF_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.SOUL_SANDSTONE);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.SOUL_SANDSTONE);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.SMOOTH_SOUL_SANDSTONE);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.CHISELED_SOUL_SANDSTONE);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_STAIRS, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_SLAB, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE_WALL, Unordinary_BasicsBlocks.CUT_SOUL_SANDSTONE);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.SANDSTONE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.SANDSTONE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.RED_SANDSTONE_BRICKS);

        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_STAIRS, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_SLAB, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS, 2);
        stonecutterResultFromBase(consumer, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICK_WALL, Unordinary_BasicsBlocks.SOUL_SANDSTONE_BRICKS);


    }

    private void vanillaQOLSmelting(Consumer<FinishedRecipe> consumer) {

        smeltingRecipe(consumer, Ingredient.of(Items.ROTTEN_FLESH), Items.LEATHER, 0.35F, 200);
        campfireRecipe(consumer, Ingredient.of(Items.ROTTEN_FLESH), Items.LEATHER, 0.35F, 600);
        smeltingRecipe(consumer, Ingredient.of(Items.RAW_COPPER_BLOCK), Items.COPPER_BLOCK, 0.35F, 500);
        smeltingRecipe(consumer, Ingredient.of(Items.RAW_GOLD_BLOCK), Items.GOLD_BLOCK, 0.35F, 500);
        smeltingRecipe(consumer, Ingredient.of(Items.RAW_IRON_BLOCK), Items.IRON_BLOCK, 0.35F, 500);
        oreBlasting(consumer, COPPER_BLOCK_SMELTABLES, Items.COPPER_BLOCK, 0.35F, 250, "copper");
        oreBlasting(consumer, GOLD_BLOCK_SMELTABLES, Items.GOLD_BLOCK, 0.35F, 250, "gold");
        oreBlasting(consumer, IRON_BLOCK_SMELTABLES, Items.IRON_BLOCK, 0.35F, 250, "iron");

    }

    private void modImprovedRecipes(Consumer<FinishedRecipe> consumer) {

        stairs(consumer, Blocks.OAK_STAIRS, Blocks.OAK_PLANKS);
        slab(consumer, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
        stairs(consumer, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_PLANKS);
        slab(consumer, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        stairs(consumer, Blocks.BIRCH_STAIRS, Blocks.BIRCH_PLANKS);
        slab(consumer, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        stairs(consumer, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_PLANKS);
        slab(consumer, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        stairs(consumer, Blocks.ACACIA_STAIRS, Blocks.ACACIA_PLANKS);
        slab(consumer, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        stairs(consumer, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_PLANKS);
        slab(consumer, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        stairs(consumer, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_PLANKS);
        slab(consumer, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
        stairs(consumer, Blocks.WARPED_STAIRS, Blocks.WARPED_PLANKS);
        slab(consumer, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);
        stairs(consumer, Blocks.STONE_STAIRS, Blocks.STONE);
        slab(consumer, Blocks.STONE_SLAB, Blocks.STONE);
        stairs(consumer, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE);
        slab(consumer, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE);
        stairs(consumer, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE);
        slab(consumer, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE);
        stairs(consumer, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICKS);
        slab(consumer, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICKS);
        stairs(consumer, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS);
        slab(consumer, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICKS);
        stairs(consumer, Blocks.ANDESITE_STAIRS, Blocks.ANDESITE);
        slab(consumer, Blocks.ANDESITE_SLAB, Blocks.ANDESITE);
        stairs(consumer, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE);
        slab(consumer, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE);
        stairs(consumer, Blocks.DIORITE_STAIRS, Blocks.DIORITE);
        slab(consumer, Blocks.DIORITE_SLAB, Blocks.DIORITE);
        stairs(consumer, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE);
        slab(consumer, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE);
        stairs(consumer, Blocks.GRANITE_STAIRS, Blocks.GRANITE);
        slab(consumer, Blocks.GRANITE_SLAB, Blocks.GRANITE);
        stairs(consumer, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE);
        slab(consumer, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE);
        stairs(consumer, Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE);
        slab(consumer, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE);
        slab(consumer, Blocks.CUT_SANDSTONE_SLAB, Blocks.CUT_SANDSTONE);
        stairs(consumer, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE);
        slab(consumer, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE);
        stairs(consumer, Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE);
        slab(consumer, Blocks.RED_SANDSTONE_SLAB, Blocks.RED_SANDSTONE);
        slab(consumer, Blocks.CUT_RED_SANDSTONE_SLAB, Blocks.CUT_RED_SANDSTONE);
        stairs(consumer, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE);
        slab(consumer, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE);
        stairs(consumer, Blocks.BRICK_STAIRS, Blocks.BRICKS);
        slab(consumer, Blocks.BRICK_SLAB, Blocks.BRICKS);
        stairs(consumer, Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE);
        slab(consumer, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE);
        stairs(consumer, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICKS);
        slab(consumer, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICKS);
        stairs(consumer, Blocks.DARK_PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE);
        slab(consumer, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE);
        stairs(consumer, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICKS);
        slab(consumer, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICKS);
        stairs(consumer, Blocks.RED_NETHER_BRICK_STAIRS, Blocks.RED_NETHER_BRICKS);
        slab(consumer, Blocks.RED_NETHER_BRICK_SLAB, Blocks.RED_NETHER_BRICKS);
        stairs(consumer, Blocks.QUARTZ_STAIRS, Blocks.QUARTZ_BLOCK);
        slab(consumer, Blocks.QUARTZ_SLAB, Blocks.QUARTZ_BLOCK);
        stairs(consumer, Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.SMOOTH_QUARTZ);
        slab(consumer, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ);
        stairs(consumer, Blocks.PURPUR_STAIRS, Blocks.PURPUR_BLOCK);
        slab(consumer, Blocks.PURPUR_SLAB, Blocks.PURPUR_BLOCK);
        stairs(consumer, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICKS);
        slab(consumer, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICKS);
        stairs(consumer, Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE);
        slab(consumer, Blocks.BLACKSTONE_SLAB, Blocks.BLACKSTONE);
        stairs(consumer, Blocks.POLISHED_BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE);
        slab(consumer, Blocks.POLISHED_BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE);
        stairs(consumer, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICKS);
        slab(consumer, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        stairs(consumer, Blocks.CUT_COPPER_STAIRS, Blocks.CUT_COPPER);
        slab(consumer, Blocks.CUT_COPPER_SLAB, Blocks.CUT_COPPER);
        stairs(consumer, Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER);
        slab(consumer, Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER);
        stairs(consumer, Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER);
        slab(consumer, Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER);
        stairs(consumer, Blocks.OXIDIZED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER);
        slab(consumer, Blocks.OXIDIZED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER);
        stairs(consumer, Blocks.WAXED_CUT_COPPER_STAIRS, Blocks.WAXED_CUT_COPPER);
        slab(consumer, Blocks.WAXED_CUT_COPPER_SLAB, Blocks.WAXED_CUT_COPPER);
        stairs(consumer, Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, Blocks.WAXED_EXPOSED_CUT_COPPER);
        slab(consumer, Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.WAXED_EXPOSED_CUT_COPPER);
        stairs(consumer, Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, Blocks.WAXED_WEATHERED_CUT_COPPER);
        slab(consumer, Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, Blocks.WAXED_WEATHERED_CUT_COPPER);
        stairs(consumer, Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Blocks.WAXED_OXIDIZED_CUT_COPPER);
        slab(consumer, Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, Blocks.WAXED_OXIDIZED_CUT_COPPER);
        stairs(consumer, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE);
        slab(consumer, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE);
        stairs(consumer, Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE);
        slab(consumer, Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE);
        stairs(consumer, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICKS);
        slab(consumer, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICKS);
        stairs(consumer, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILES);
        slab(consumer, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILES);

    }

    private void modStairsAndSlabs(Consumer<FinishedRecipe> consumer) {

        stoneStairs(consumer, Unordinary_BasicsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CALCITE_SLAB, Blocks.CALCITE);
        stoneStairs(consumer, Unordinary_BasicsBlocks.TUFF_STAIRS, Blocks.TUFF);
        stoneSlab(consumer, Unordinary_BasicsBlocks.TUFF_SLAB, Blocks.TUFF);
        stoneStairs(consumer, Unordinary_BasicsBlocks.DRIPSTONE_STAIRS, Blocks.DRIPSTONE_BLOCK);
        stoneSlab(consumer, Unordinary_BasicsBlocks.DRIPSTONE_SLAB, Blocks.DRIPSTONE_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS, Blocks.GRASS_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Blocks.GRASS_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.DIRT_STAIRS, Blocks.DIRT);
        slab(consumer, Unordinary_BasicsBlocks.DIRT_SLAB, Blocks.DIRT);
        stairs(consumer, Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS, Blocks.COARSE_DIRT);
        slab(consumer, Unordinary_BasicsBlocks.COARSE_DIRT_SLAB, Blocks.COARSE_DIRT);
        stairs(consumer, Unordinary_BasicsBlocks.PODZOL_STAIRS, Blocks.PODZOL);
        slab(consumer, Unordinary_BasicsBlocks.PODZOL_SLAB, Blocks.PODZOL);
        stairs(consumer, Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS, Blocks.ROOTED_DIRT);
        slab(consumer, Unordinary_BasicsBlocks.ROOTED_DIRT_SLAB, Blocks.ROOTED_DIRT);
        stairs(consumer, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS, Blocks.CRIMSON_NYLIUM);
        slab(consumer, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB, Blocks.CRIMSON_NYLIUM);
        stairs(consumer, Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS, Blocks.WARPED_NYLIUM);
        slab(consumer, Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB, Blocks.WARPED_NYLIUM);
        stairs(consumer, Unordinary_BasicsBlocks.SAND_STAIRS, Blocks.SAND);
        slab(consumer, Unordinary_BasicsBlocks.SAND_SLAB, Blocks.SAND);
        stairs(consumer, Unordinary_BasicsBlocks.RED_SAND_STAIRS, Blocks.RED_SAND);
        slab(consumer, Unordinary_BasicsBlocks.RED_SAND_SLAB, Blocks.RED_SAND);
        stairs(consumer, Unordinary_BasicsBlocks.GRAVEL_STAIRS, Blocks.GRAVEL);
        slab(consumer, Unordinary_BasicsBlocks.GRAVEL_SLAB, Blocks.GRAVEL);
        stairs(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS, Blocks.RAW_IRON_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB, Blocks.RAW_IRON_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Blocks.RAW_COPPER_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB, Blocks.RAW_COPPER_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS, Blocks.RAW_GOLD_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB, Blocks.RAW_GOLD_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS, Blocks.AMETHYST_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB, Blocks.AMETHYST_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.OAK_LOG_STAIRS, Blocks.OAK_LOG);
        slab(consumer, Unordinary_BasicsBlocks.OAK_LOG_SLAB, Blocks.OAK_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS, Blocks.SPRUCE_LOG);
        slab(consumer, Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB, Blocks.SPRUCE_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS, Blocks.BIRCH_LOG);
        slab(consumer, Unordinary_BasicsBlocks.BIRCH_LOG_SLAB, Blocks.BIRCH_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS, Blocks.JUNGLE_LOG);
        slab(consumer, Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB, Blocks.JUNGLE_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS, Blocks.ACACIA_LOG);
        slab(consumer, Unordinary_BasicsBlocks.ACACIA_LOG_SLAB, Blocks.ACACIA_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS, Blocks.DARK_OAK_LOG);
        slab(consumer, Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB, Blocks.DARK_OAK_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS, Blocks.CRIMSON_STEM);
        slab(consumer, Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB, Blocks.CRIMSON_STEM);
        stairs(consumer, Unordinary_BasicsBlocks.WARPED_STEM_STAIRS, Blocks.WARPED_STEM);
        slab(consumer, Unordinary_BasicsBlocks.WARPED_STEM_SLAB, Blocks.WARPED_STEM);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS, Blocks.STRIPPED_OAK_LOG);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB, Blocks.STRIPPED_OAK_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Blocks.STRIPPED_SPRUCE_LOG);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB, Blocks.STRIPPED_SPRUCE_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS, Blocks.STRIPPED_BIRCH_LOG);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB, Blocks.STRIPPED_BIRCH_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Blocks.STRIPPED_JUNGLE_LOG);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB, Blocks.STRIPPED_JUNGLE_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS, Blocks.STRIPPED_ACACIA_LOG);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB, Blocks.STRIPPED_ACACIA_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, Blocks.STRIPPED_DARK_OAK_LOG);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Blocks.STRIPPED_DARK_OAK_LOG);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS, Blocks.STRIPPED_CRIMSON_STEM);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB, Blocks.STRIPPED_CRIMSON_STEM);
        stairs(consumer, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS, Blocks.STRIPPED_WARPED_STEM);
        slab(consumer, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB, Blocks.STRIPPED_WARPED_STEM);
        stairs(consumer, Unordinary_BasicsBlocks.GLASS_STAIRS, Blocks.GLASS);
        slab(consumer, Unordinary_BasicsBlocks.GLASS_SLAB, Blocks.GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.TINTED_GLASS_STAIRS, Blocks.TINTED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.TINTED_GLASS_SLAB, Blocks.TINTED_GLASS);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE);
        stairs(consumer, Unordinary_BasicsBlocks.OBSIDIAN_STAIRS, Blocks.OBSIDIAN);
        slab(consumer, Unordinary_BasicsBlocks.OBSIDIAN_SLAB, Blocks.OBSIDIAN);
        stoneStairs(consumer, Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS, Blocks.PURPUR_PILLAR);
        stoneSlab(consumer, Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB, Blocks.PURPUR_PILLAR);
        stairs(consumer, Unordinary_BasicsBlocks.ICE_STAIRS, Blocks.ICE);
        slab(consumer, Unordinary_BasicsBlocks.ICE_SLAB, Blocks.ICE);
        stairs(consumer, Unordinary_BasicsBlocks.SNOW_STAIRS, Blocks.SNOW);
        slab(consumer, Unordinary_BasicsBlocks.SNOW_SLAB, Blocks.SNOW);
        stairs(consumer, Unordinary_BasicsBlocks.CLAY_STAIRS, Blocks.CLAY);
        slab(consumer, Unordinary_BasicsBlocks.CLAY_SLAB, Blocks.CLAY);
        stairs(consumer, Unordinary_BasicsBlocks.PUMPKIN_STAIRS, Blocks.PUMPKIN);
        slab(consumer, Unordinary_BasicsBlocks.PUMPKIN_SLAB, Blocks.PUMPKIN);
        stairs(consumer, Unordinary_BasicsBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK);
        slab(consumer, Unordinary_BasicsBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK);
        stairs(consumer, Unordinary_BasicsBlocks.SOUL_SAND_STAIRS, Blocks.SOUL_SAND);
        slab(consumer, Unordinary_BasicsBlocks.SOUL_SAND_SLAB, Blocks.SOUL_SAND);
        stairs(consumer, Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS, Blocks.SOUL_SOIL);
        slab(consumer, Unordinary_BasicsBlocks.SOUL_SOIL_SLAB, Blocks.SOUL_SOIL);
        stoneStairs(consumer, Unordinary_BasicsBlocks.BASALT_STAIRS, Blocks.BASALT);
        stoneSlab(consumer, Unordinary_BasicsBlocks.BASALT_SLAB, Blocks.BASALT);
        stoneStairs(consumer, Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS, Blocks.POLISHED_BASALT);
        stoneSlab(consumer, Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB, Blocks.POLISHED_BASALT);
        stairs(consumer, Unordinary_BasicsBlocks.GLOWSTONE_STAIRS, Blocks.GLOWSTONE);
        slab(consumer, Unordinary_BasicsBlocks.GLOWSTONE_SLAB, Blocks.GLOWSTONE);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS, Blocks.CRACKED_STONE_BRICKS);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB, Blocks.CRACKED_STONE_BRICKS);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS, Blocks.CHISELED_STONE_BRICKS);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB, Blocks.CHISELED_STONE_BRICKS);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Blocks.CRACKED_DEEPSLATE_BRICKS);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Blocks.CRACKED_DEEPSLATE_TILES);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Blocks.CRACKED_DEEPSLATE_TILES);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS, Blocks.CHISELED_DEEPSLATE);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB, Blocks.CHISELED_DEEPSLATE);
        stairs(consumer, Unordinary_BasicsBlocks.MELON_STAIRS, Blocks.MELON);
        slab(consumer, Unordinary_BasicsBlocks.MELON_SLAB, Blocks.MELON);
        stairs(consumer, Unordinary_BasicsBlocks.MYCELIUM_STAIRS, Blocks.MYCELIUM);
        slab(consumer, Unordinary_BasicsBlocks.MYCELIUM_SLAB, Blocks.MYCELIUM);
        stairs(consumer, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS, Blocks.CRACKED_NETHER_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB, Blocks.CRACKED_NETHER_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS, Blocks.CHISELED_NETHER_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB, Blocks.CHISELED_NETHER_BRICKS);
        stoneStairs(consumer, Unordinary_BasicsBlocks.END_STONE_STAIRS, Blocks.END_STONE);
        stoneSlab(consumer, Unordinary_BasicsBlocks.END_STONE_SLAB, Blocks.END_STONE);
        stairs(consumer, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Blocks.CHISELED_QUARTZ_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Blocks.CHISELED_QUARTZ_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        slab(consumer, Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS, Blocks.QUARTZ_PILLAR);
        slab(consumer, Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB, Blocks.QUARTZ_PILLAR);
        stairs(consumer, Unordinary_BasicsBlocks.HAY_BALE_STAIRS, Blocks.HAY_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.HAY_BALE_SLAB, Blocks.HAY_BLOCK);
        stoneStairs(consumer, Unordinary_BasicsBlocks.TERRACOTTA_STAIRS, Blocks.TERRACOTTA);
        stoneSlab(consumer, Unordinary_BasicsBlocks.TERRACOTTA_SLAB, Blocks.TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.PACKED_ICE_STAIRS, Blocks.PACKED_ICE);
        slab(consumer, Unordinary_BasicsBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE);
        stairs(consumer, Unordinary_BasicsBlocks.SEA_LANTERN_STAIRS, Blocks.SEA_LANTERN);
        slab(consumer, Unordinary_BasicsBlocks.SEA_LANTERN_SLAB, Blocks.SEA_LANTERN);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE);
        stairs(consumer, Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS, Blocks.MAGMA_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB, Blocks.MAGMA_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.NETHER_WART_BLOCK_STAIRS, Blocks.NETHER_WART_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.NETHER_WART_BLOCK_SLAB, Blocks.NETHER_WART_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.WARPED_WART_BLOCK_STAIRS, Blocks.WARPED_WART_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.WARPED_WART_BLOCK_SLAB, Blocks.WARPED_WART_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS, Blocks.BONE_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.BONE_BLOCK_SLAB, Blocks.BONE_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.BLUE_ICE_STAIRS, Blocks.BLUE_ICE);
        slab(consumer, Unordinary_BasicsBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE);
        stairs(consumer, Unordinary_BasicsBlocks.DRIED_KELP_STAIRS, Blocks.DRIED_KELP_BLOCK);
        slab(consumer, Unordinary_BasicsBlocks.DRIED_KELP_SLAB, Blocks.DRIED_KELP_BLOCK);
        stairs(consumer, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS, Blocks.CRYING_OBSIDIAN);
        slab(consumer, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB, Blocks.CRYING_OBSIDIAN);
        stoneStairs(consumer, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        stoneSlab(consumer, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        stairs(consumer, Unordinary_BasicsBlocks.WHITE_WOOL_STAIRS, Blocks.WHITE_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.WHITE_WOOL_SLAB, Blocks.WHITE_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.ORANGE_WOOL_STAIRS, Blocks.ORANGE_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.ORANGE_WOOL_SLAB, Blocks.ORANGE_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.MAGENTA_WOOL_STAIRS, Blocks.MAGENTA_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.MAGENTA_WOOL_SLAB, Blocks.MAGENTA_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_STAIRS, Blocks.LIGHT_BLUE_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_SLAB, Blocks.LIGHT_BLUE_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.YELLOW_WOOL_STAIRS, Blocks.YELLOW_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.YELLOW_WOOL_SLAB, Blocks.YELLOW_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.LIME_WOOL_STAIRS, Blocks.LIME_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.LIME_WOOL_SLAB, Blocks.LIME_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.PINK_WOOL_STAIRS, Blocks.PINK_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.PINK_WOOL_SLAB, Blocks.PINK_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.GRAY_WOOL_STAIRS, Blocks.GRAY_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.GRAY_WOOL_SLAB, Blocks.GRAY_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_STAIRS, Blocks.LIGHT_GRAY_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_SLAB, Blocks.LIGHT_GRAY_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.CYAN_WOOL_STAIRS, Blocks.CYAN_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.CYAN_WOOL_SLAB, Blocks.CYAN_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.PURPLE_WOOL_STAIRS, Blocks.PURPLE_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.PURPLE_WOOL_SLAB, Blocks.PURPLE_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.BLUE_WOOL_STAIRS, Blocks.BLUE_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.BLUE_WOOL_SLAB, Blocks.BLUE_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.BROWN_WOOL_STAIRS, Blocks.BROWN_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.BROWN_WOOL_SLAB, Blocks.BROWN_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.GREEN_WOOL_STAIRS, Blocks.GREEN_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.GREEN_WOOL_SLAB, Blocks.GREEN_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.RED_WOOL_STAIRS, Blocks.RED_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.RED_WOOL_SLAB, Blocks.RED_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.BLACK_WOOL_STAIRS, Blocks.BLACK_WOOL);
        slab(consumer, Unordinary_BasicsBlocks.BLACK_WOOL_SLAB, Blocks.BLACK_WOOL);
        stairs(consumer, Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS, Blocks.WHITE_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB, Blocks.WHITE_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS, Blocks.ORANGE_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB, Blocks.ORANGE_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS, Blocks.MAGENTA_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB, Blocks.MAGENTA_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, Blocks.LIGHT_BLUE_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, Blocks.LIGHT_BLUE_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS, Blocks.YELLOW_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB, Blocks.YELLOW_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS, Blocks.LIME_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB, Blocks.LIME_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS, Blocks.PINK_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB, Blocks.PINK_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS, Blocks.GRAY_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB, Blocks.GRAY_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Blocks.LIGHT_GRAY_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Blocks.LIGHT_GRAY_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS, Blocks.CYAN_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB, Blocks.CYAN_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS, Blocks.PURPLE_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB, Blocks.PURPLE_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS, Blocks.BLUE_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB, Blocks.BLUE_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS, Blocks.BROWN_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB, Blocks.BROWN_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS, Blocks.GREEN_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB, Blocks.GREEN_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS, Blocks.RED_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB, Blocks.RED_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS, Blocks.BLACK_TERRACOTTA);
        slab(consumer, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB, Blocks.BLACK_TERRACOTTA);
        stairs(consumer, Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_STAIRS, Blocks.WHITE_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_SLAB, Blocks.WHITE_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_STAIRS, Blocks.ORANGE_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_SLAB, Blocks.ORANGE_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_STAIRS, Blocks.MAGENTA_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_SLAB, Blocks.MAGENTA_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, Blocks.LIGHT_BLUE_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, Blocks.LIGHT_BLUE_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_STAIRS, Blocks.YELLOW_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_SLAB, Blocks.YELLOW_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.LIME_STAINED_GLASS_STAIRS, Blocks.LIME_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.LIME_STAINED_GLASS_SLAB, Blocks.LIME_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.PINK_STAINED_GLASS_STAIRS, Blocks.PINK_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.PINK_STAINED_GLASS_SLAB, Blocks.PINK_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_STAIRS, Blocks.GRAY_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_SLAB, Blocks.GRAY_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, Blocks.LIGHT_GRAY_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, Blocks.LIGHT_GRAY_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_STAIRS, Blocks.CYAN_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_SLAB, Blocks.CYAN_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_STAIRS, Blocks.PURPLE_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_SLAB, Blocks.PURPLE_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_STAIRS, Blocks.BLUE_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_SLAB, Blocks.BLUE_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_STAIRS, Blocks.BROWN_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_SLAB, Blocks.BROWN_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_STAIRS, Blocks.GREEN_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_SLAB, Blocks.GREEN_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.RED_STAINED_GLASS_STAIRS, Blocks.RED_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.RED_STAINED_GLASS_SLAB, Blocks.RED_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_STAIRS, Blocks.BLACK_STAINED_GLASS);
        slab(consumer, Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_SLAB, Blocks.BLACK_STAINED_GLASS);
        stairs(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS, Blocks.WHITE_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB, Blocks.WHITE_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS, Blocks.ORANGE_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB, Blocks.ORANGE_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS, Blocks.MAGENTA_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB, Blocks.MAGENTA_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS, Blocks.LIGHT_BLUE_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB, Blocks.LIGHT_BLUE_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS, Blocks.YELLOW_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB, Blocks.YELLOW_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS, Blocks.LIME_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB, Blocks.LIME_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS, Blocks.PINK_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB, Blocks.PINK_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS, Blocks.GRAY_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB, Blocks.GRAY_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Blocks.LIGHT_GRAY_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB, Blocks.LIGHT_GRAY_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS, Blocks.CYAN_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB, Blocks.CYAN_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS, Blocks.PURPLE_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB, Blocks.PURPLE_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS, Blocks.BLUE_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB, Blocks.BLUE_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS, Blocks.BROWN_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB, Blocks.BROWN_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS, Blocks.GREEN_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB, Blocks.GREEN_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS, Blocks.RED_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_SLAB, Blocks.RED_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS, Blocks.BLACK_CONCRETE);
        slab(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB, Blocks.BLACK_CONCRETE);
        stairs(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS, Blocks.WHITE_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_SLAB, Blocks.WHITE_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS, Blocks.ORANGE_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_SLAB, Blocks.ORANGE_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Blocks.MAGENTA_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_SLAB, Blocks.MAGENTA_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS, Blocks.YELLOW_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_SLAB, Blocks.YELLOW_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS, Blocks.LIME_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_SLAB, Blocks.LIME_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS, Blocks.PINK_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_SLAB, Blocks.PINK_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS, Blocks.GRAY_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_SLAB, Blocks.GRAY_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS, Blocks.CYAN_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_SLAB, Blocks.CYAN_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS, Blocks.PURPLE_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_SLAB, Blocks.PURPLE_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS, Blocks.BLUE_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_SLAB, Blocks.BLUE_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS, Blocks.BROWN_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_SLAB, Blocks.BROWN_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS, Blocks.GREEN_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_SLAB, Blocks.GREEN_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS, Blocks.RED_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_SLAB, Blocks.RED_CONCRETE_POWDER);
        stairs(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS, Blocks.BLACK_CONCRETE_POWDER);
        slab(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_SLAB, Blocks.BLACK_CONCRETE_POWDER);

    }

    private void modBlockToBlock(Consumer<FinishedRecipe> consumer) {

        stairsToBlock(consumer, Blocks.OAK_STAIRS, Blocks.OAK_PLANKS);
        stairsToBlock(consumer, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_PLANKS);
        stairsToBlock(consumer, Blocks.BIRCH_STAIRS, Blocks.BIRCH_PLANKS);
        stairsToBlock(consumer, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_PLANKS);
        stairsToBlock(consumer, Blocks.ACACIA_STAIRS, Blocks.ACACIA_PLANKS);
        stairsToBlock(consumer, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_PLANKS);
        stairsToBlock(consumer, Blocks.WARPED_STAIRS, Blocks.WARPED_PLANKS);
        stairsToBlock(consumer, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_PLANKS);
        stairsToBlock(consumer, Blocks.STONE_STAIRS, Blocks.STONE);
        stairsToBlock(consumer, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE);
        stairsToBlock(consumer, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE);
        stairsToBlock(consumer, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICKS);
        stairsToBlock(consumer, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS);
        stairsToBlock(consumer, Blocks.ANDESITE_STAIRS, Blocks.ANDESITE);
        stairsToBlock(consumer, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE);
        stairsToBlock(consumer, Blocks.DIORITE_STAIRS, Blocks.DIORITE);
        stairsToBlock(consumer, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE);
        stairsToBlock(consumer, Blocks.GRANITE_STAIRS, Blocks.GRANITE);
        stairsToBlock(consumer, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE);
        stairsToBlock(consumer, Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE);
        stairsToBlock(consumer, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE);
        stairsToBlock(consumer, Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE);
        stairsToBlock(consumer, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE);
        stairsToBlock(consumer, Blocks.BRICK_STAIRS, Blocks.BRICKS);
        stairsToBlock(consumer, Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE);
        stairsToBlock(consumer, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICKS);
        stairsToBlock(consumer, Blocks.DARK_PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE);
        stairsToBlock(consumer, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICKS);
        stairsToBlock(consumer, Blocks.RED_NETHER_BRICK_STAIRS, Blocks.RED_NETHER_BRICKS);
        stairsToBlock(consumer, Blocks.QUARTZ_STAIRS, Blocks.QUARTZ_BLOCK);
        stairsToBlock(consumer, Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.SMOOTH_QUARTZ);
        stairsToBlock(consumer, Blocks.PURPUR_STAIRS, Blocks.PURPUR_BLOCK);
        stairsToBlock(consumer, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICKS);
        stairsToBlock(consumer, Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE);
        stairsToBlock(consumer, Blocks.POLISHED_BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE);
        stairsToBlock(consumer, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICKS);
        stairsToBlock(consumer, Blocks.CUT_COPPER_STAIRS, Blocks.CUT_COPPER);
        stairsToBlock(consumer, Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.OXIDIZED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.WAXED_CUT_COPPER_STAIRS, Blocks.WAXED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, Blocks.WAXED_EXPOSED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, Blocks.WAXED_WEATHERED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Blocks.WAXED_OXIDIZED_CUT_COPPER);
        stairsToBlock(consumer, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE);
        stairsToBlock(consumer, Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE);
        stairsToBlock(consumer, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICKS);
        stairsToBlock(consumer, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILES);
        slabsToBlock(consumer, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
        slabsToBlock(consumer, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        slabsToBlock(consumer, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        slabsToBlock(consumer, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        slabsToBlock(consumer, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        slabsToBlock(consumer, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        slabsToBlock(consumer, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);
        slabsToBlock(consumer, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
        slabsToBlock(consumer, Blocks.STONE_SLAB, Blocks.STONE);
        slabsToBlock(consumer, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE);
        slabsToBlock(consumer, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE);
        slabsToBlock(consumer, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICKS);
        slabsToBlock(consumer, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICKS);
        slabsToBlock(consumer, Blocks.ANDESITE_SLAB, Blocks.ANDESITE);
        slabsToBlock(consumer, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE);
        slabsToBlock(consumer, Blocks.DIORITE_SLAB, Blocks.DIORITE);
        slabsToBlock(consumer, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE);
        slabsToBlock(consumer, Blocks.GRANITE_SLAB, Blocks.GRANITE);
        slabsToBlock(consumer, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE);
        slabsToBlock(consumer, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE);
        slabsToBlock(consumer, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE);
        slabsToBlock(consumer, Blocks.RED_SANDSTONE_SLAB, Blocks.RED_SANDSTONE);
        slabsToBlock(consumer, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE);
        slabsToBlock(consumer, Blocks.BRICK_SLAB, Blocks.BRICKS);
        slabsToBlock(consumer, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE);
        slabsToBlock(consumer, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICKS);
        slabsToBlock(consumer, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE);
        slabsToBlock(consumer, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICKS);
        slabsToBlock(consumer, Blocks.RED_NETHER_BRICK_SLAB, Blocks.RED_NETHER_BRICKS);
        slabsToBlock(consumer, Blocks.QUARTZ_SLAB, Blocks.QUARTZ_BLOCK);
        slabsToBlock(consumer, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ);
        slabsToBlock(consumer, Blocks.PURPUR_SLAB, Blocks.PURPUR_BLOCK);
        slabsToBlock(consumer, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICKS);
        slabsToBlock(consumer, Blocks.BLACKSTONE_SLAB, Blocks.BLACKSTONE);
        slabsToBlock(consumer, Blocks.POLISHED_BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE);
        slabsToBlock(consumer, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        slabsToBlock(consumer, Blocks.CUT_COPPER_SLAB, Blocks.CUT_COPPER);
        slabsToBlock(consumer, Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.OXIDIZED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.WAXED_CUT_COPPER_SLAB, Blocks.WAXED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.WAXED_EXPOSED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, Blocks.WAXED_WEATHERED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, Blocks.WAXED_OXIDIZED_CUT_COPPER);
        slabsToBlock(consumer, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE);
        slabsToBlock(consumer, Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE);
        slabsToBlock(consumer, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICKS);
        slabsToBlock(consumer, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILES);

        stairsToBlock(consumer, Unordinary_BasicsBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CALCITE_SLAB, Blocks.CALCITE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.TUFF_STAIRS, Blocks.TUFF);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.TUFF_SLAB, Blocks.TUFF);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.DRIPSTONE_STAIRS, Blocks.DRIPSTONE_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.DRIPSTONE_SLAB, Blocks.DRIPSTONE_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRASS_BLOCK_STAIRS, Blocks.GRASS_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRASS_BLOCK_SLAB, Blocks.GRASS_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.DIRT_STAIRS, Blocks.DIRT);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.DIRT_SLAB, Blocks.DIRT);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.COARSE_DIRT_STAIRS, Blocks.COARSE_DIRT);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.COARSE_DIRT_SLAB, Blocks.COARSE_DIRT);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PODZOL_STAIRS, Blocks.PODZOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PODZOL_SLAB, Blocks.PODZOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ROOTED_DIRT_STAIRS, Blocks.ROOTED_DIRT);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ROOTED_DIRT_SLAB, Blocks.ROOTED_DIRT);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_STAIRS, Blocks.CRIMSON_NYLIUM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRIMSON_NYLIUM_SLAB, Blocks.CRIMSON_NYLIUM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WARPED_NYLIUM_STAIRS, Blocks.WARPED_NYLIUM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WARPED_NYLIUM_SLAB, Blocks.WARPED_NYLIUM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.SAND_STAIRS, Blocks.SAND);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.SAND_SLAB, Blocks.SAND);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.RED_SAND_STAIRS, Blocks.RED_SAND);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.RED_SAND_SLAB, Blocks.RED_SAND);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRAVEL_STAIRS, Blocks.GRAVEL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRAVEL_SLAB, Blocks.GRAVEL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_STAIRS, Blocks.RAW_IRON_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_IRON_SLAB, Blocks.RAW_IRON_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_STAIRS, Blocks.RAW_COPPER_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_COPPER_SLAB, Blocks.RAW_COPPER_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_STAIRS, Blocks.RAW_GOLD_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_RAW_GOLD_SLAB, Blocks.RAW_GOLD_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_STAIRS, Blocks.AMETHYST_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLOCK_OF_AMETHYST_SLAB, Blocks.AMETHYST_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.OAK_LOG_STAIRS, Blocks.OAK_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.OAK_LOG_SLAB, Blocks.OAK_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.SPRUCE_LOG_STAIRS, Blocks.SPRUCE_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.SPRUCE_LOG_SLAB, Blocks.SPRUCE_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BIRCH_LOG_STAIRS, Blocks.BIRCH_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BIRCH_LOG_SLAB, Blocks.BIRCH_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.JUNGLE_LOG_STAIRS, Blocks.JUNGLE_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.JUNGLE_LOG_SLAB, Blocks.JUNGLE_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ACACIA_LOG_STAIRS, Blocks.ACACIA_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ACACIA_LOG_SLAB, Blocks.ACACIA_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.DARK_OAK_LOG_STAIRS, Blocks.DARK_OAK_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.DARK_OAK_LOG_SLAB, Blocks.DARK_OAK_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRIMSON_STEM_STAIRS, Blocks.CRIMSON_STEM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRIMSON_STEM_SLAB, Blocks.CRIMSON_STEM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WARPED_STEM_STAIRS, Blocks.WARPED_STEM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WARPED_STEM_SLAB, Blocks.WARPED_STEM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_STAIRS, Blocks.STRIPPED_OAK_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_OAK_LOG_SLAB, Blocks.STRIPPED_OAK_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_STAIRS, Blocks.STRIPPED_SPRUCE_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_SPRUCE_LOG_SLAB, Blocks.STRIPPED_SPRUCE_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_STAIRS, Blocks.STRIPPED_BIRCH_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_BIRCH_LOG_SLAB, Blocks.STRIPPED_BIRCH_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_STAIRS, Blocks.STRIPPED_JUNGLE_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_JUNGLE_LOG_SLAB, Blocks.STRIPPED_JUNGLE_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_STAIRS, Blocks.STRIPPED_ACACIA_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_ACACIA_LOG_SLAB, Blocks.STRIPPED_ACACIA_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, Blocks.STRIPPED_DARK_OAK_LOG);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_DARK_OAK_LOG_SLAB, Blocks.STRIPPED_DARK_OAK_LOG);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_STAIRS, Blocks.STRIPPED_CRIMSON_STEM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_CRIMSON_STEM_SLAB, Blocks.STRIPPED_CRIMSON_STEM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_STAIRS, Blocks.STRIPPED_WARPED_STEM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.STRIPPED_WARPED_STEM_SLAB, Blocks.STRIPPED_WARPED_STEM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GLASS_STAIRS, Blocks.GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GLASS_SLAB, Blocks.GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.TINTED_GLASS_STAIRS, Blocks.TINTED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.TINTED_GLASS_SLAB, Blocks.TINTED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CUT_SANDSTONE_STAIRS, Blocks.CUT_SANDSTONE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.OBSIDIAN_STAIRS, Blocks.OBSIDIAN);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.OBSIDIAN_SLAB, Blocks.OBSIDIAN);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PURPUR_PILLAR_STAIRS, Blocks.PURPUR_PILLAR);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PURPUR_PILLAR_SLAB, Blocks.PURPUR_PILLAR);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ICE_STAIRS, Blocks.ICE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ICE_SLAB, Blocks.ICE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.SNOW_STAIRS, Blocks.SNOW_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.SNOW_SLAB, Blocks.SNOW_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CLAY_STAIRS, Blocks.CLAY);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CLAY_SLAB, Blocks.CLAY);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PUMPKIN_STAIRS, Blocks.PUMPKIN);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PUMPKIN_SLAB, Blocks.PUMPKIN);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.SOUL_SAND_STAIRS, Blocks.SOUL_SAND);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.SOUL_SAND_SLAB, Blocks.SOUL_SAND);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.SOUL_SOIL_STAIRS, Blocks.SOUL_SOIL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.SOUL_SOIL_SLAB, Blocks.SOUL_SOIL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BASALT_STAIRS, Blocks.BASALT);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BASALT_SLAB, Blocks.BASALT);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.POLISHED_BASALT_STAIRS, Blocks.POLISHED_BASALT);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.POLISHED_BASALT_SLAB, Blocks.POLISHED_BASALT);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GLOWSTONE_STAIRS, Blocks.GLOWSTONE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GLOWSTONE_SLAB, Blocks.GLOWSTONE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_STAIRS, Blocks.CRACKED_STONE_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_STONE_BRICK_SLAB, Blocks.CRACKED_STONE_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_STAIRS, Blocks.CHISELED_STONE_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_STONE_BRICK_SLAB, Blocks.CHISELED_STONE_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Blocks.CRACKED_DEEPSLATE_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Blocks.CRACKED_DEEPSLATE_TILES);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Blocks.CRACKED_DEEPSLATE_TILES);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_STAIRS, Blocks.CHISELED_DEEPSLATE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_DEEPSLATE_SLAB, Blocks.CHISELED_DEEPSLATE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MELON_STAIRS, Blocks.MELON);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MELON_SLAB, Blocks.MELON);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MYCELIUM_STAIRS, Blocks.MYCELIUM);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MYCELIUM_SLAB, Blocks.MYCELIUM);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_STAIRS, Blocks.CRACKED_NETHER_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_NETHER_BRICK_SLAB, Blocks.CRACKED_NETHER_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_STAIRS, Blocks.CHISELED_NETHER_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_NETHER_BRICK_SLAB, Blocks.CHISELED_NETHER_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.END_STONE_STAIRS, Blocks.END_STONE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.END_STONE_SLAB, Blocks.END_STONE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_STAIRS, Blocks.CHISELED_QUARTZ_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CHISELED_QUARTZ_BLOCK_SLAB, Blocks.CHISELED_QUARTZ_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.QUARTZ_PILLAR_STAIRS, Blocks.QUARTZ_PILLAR);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.QUARTZ_PILLAR_SLAB, Blocks.QUARTZ_PILLAR);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.HAY_BALE_STAIRS, Blocks.HAY_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.HAY_BALE_SLAB, Blocks.HAY_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.TERRACOTTA_STAIRS, Blocks.TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.TERRACOTTA_SLAB, Blocks.TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PACKED_ICE_STAIRS, Blocks.PACKED_ICE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.SEA_LANTERN_STAIRS, Blocks.SEA_LANTERN);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.SEA_LANTERN_SLAB, Blocks.SEA_LANTERN);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CUT_RED_SANDSTONE_STAIRS, Blocks.CUT_RED_SANDSTONE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MAGMA_BLOCK_STAIRS, Blocks.MAGMA_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MAGMA_BLOCK_SLAB, Blocks.MAGMA_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.NETHER_WART_BLOCK_STAIRS, Blocks.NETHER_WART_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.NETHER_WART_BLOCK_SLAB, Blocks.NETHER_WART_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WARPED_WART_BLOCK_STAIRS, Blocks.WARPED_WART_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WARPED_WART_BLOCK_SLAB, Blocks.WARPED_WART_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BONE_BLOCK_STAIRS, Blocks.BONE_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BONE_BLOCK_SLAB, Blocks.BONE_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_ICE_STAIRS, Blocks.BLUE_ICE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.DRIED_KELP_STAIRS, Blocks.DRIED_KELP_BLOCK);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.DRIED_KELP_SLAB, Blocks.DRIED_KELP_BLOCK);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_STAIRS, Blocks.CRYING_OBSIDIAN);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRYING_OBSIDIAN_SLAB, Blocks.CRYING_OBSIDIAN);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_WOOL_STAIRS, Blocks.WHITE_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_WOOL_SLAB, Blocks.WHITE_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_WOOL_STAIRS, Blocks.ORANGE_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_WOOL_SLAB, Blocks.ORANGE_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_WOOL_STAIRS, Blocks.MAGENTA_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_WOOL_SLAB, Blocks.MAGENTA_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_STAIRS, Blocks.LIGHT_BLUE_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_WOOL_SLAB, Blocks.LIGHT_BLUE_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_WOOL_STAIRS, Blocks.YELLOW_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_WOOL_SLAB, Blocks.YELLOW_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIME_WOOL_STAIRS, Blocks.LIME_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIME_WOOL_SLAB, Blocks.LIME_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PINK_WOOL_STAIRS, Blocks.PINK_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PINK_WOOL_SLAB, Blocks.PINK_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_WOOL_STAIRS, Blocks.GRAY_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_WOOL_SLAB, Blocks.GRAY_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_STAIRS, Blocks.LIGHT_GRAY_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_WOOL_SLAB, Blocks.LIGHT_GRAY_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_WOOL_STAIRS, Blocks.CYAN_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_WOOL_SLAB, Blocks.CYAN_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_WOOL_STAIRS, Blocks.PURPLE_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_WOOL_SLAB, Blocks.PURPLE_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_WOOL_STAIRS, Blocks.BLUE_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_WOOL_SLAB, Blocks.BLUE_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_WOOL_STAIRS, Blocks.BROWN_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_WOOL_SLAB, Blocks.BROWN_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_WOOL_STAIRS, Blocks.GREEN_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_WOOL_SLAB, Blocks.GREEN_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.RED_WOOL_STAIRS, Blocks.RED_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.RED_WOOL_SLAB, Blocks.RED_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_WOOL_STAIRS, Blocks.BLACK_WOOL);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_WOOL_SLAB, Blocks.BLACK_WOOL);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_TERRACOTTA_STAIRS, Blocks.WHITE_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_TERRACOTTA_SLAB, Blocks.WHITE_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_STAIRS, Blocks.ORANGE_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_TERRACOTTA_SLAB, Blocks.ORANGE_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_STAIRS, Blocks.MAGENTA_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_TERRACOTTA_SLAB, Blocks.MAGENTA_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, Blocks.LIGHT_BLUE_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, Blocks.LIGHT_BLUE_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_STAIRS, Blocks.YELLOW_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_TERRACOTTA_SLAB, Blocks.YELLOW_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIME_TERRACOTTA_STAIRS, Blocks.LIME_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIME_TERRACOTTA_SLAB, Blocks.LIME_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PINK_TERRACOTTA_STAIRS, Blocks.PINK_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PINK_TERRACOTTA_SLAB, Blocks.PINK_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_STAIRS, Blocks.GRAY_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_TERRACOTTA_SLAB, Blocks.GRAY_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Blocks.LIGHT_GRAY_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Blocks.LIGHT_GRAY_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_STAIRS, Blocks.CYAN_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_TERRACOTTA_SLAB, Blocks.CYAN_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_STAIRS, Blocks.PURPLE_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_TERRACOTTA_SLAB, Blocks.PURPLE_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_STAIRS, Blocks.BLUE_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_TERRACOTTA_SLAB, Blocks.BLUE_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_TERRACOTTA_STAIRS, Blocks.BROWN_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_TERRACOTTA_SLAB, Blocks.BROWN_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_STAIRS, Blocks.GREEN_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_TERRACOTTA_SLAB, Blocks.GREEN_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.RED_TERRACOTTA_STAIRS, Blocks.RED_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.RED_TERRACOTTA_SLAB, Blocks.RED_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_STAIRS, Blocks.BLACK_TERRACOTTA);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_TERRACOTTA_SLAB, Blocks.BLACK_TERRACOTTA);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_STAIRS, Blocks.WHITE_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_STAINED_GLASS_SLAB, Blocks.WHITE_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_STAIRS, Blocks.ORANGE_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_STAINED_GLASS_SLAB, Blocks.ORANGE_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_STAIRS, Blocks.MAGENTA_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_STAINED_GLASS_SLAB, Blocks.MAGENTA_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, Blocks.LIGHT_BLUE_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, Blocks.LIGHT_BLUE_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_STAIRS, Blocks.YELLOW_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_STAINED_GLASS_SLAB, Blocks.YELLOW_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIME_STAINED_GLASS_STAIRS, Blocks.LIME_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIME_STAINED_GLASS_SLAB, Blocks.LIME_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PINK_STAINED_GLASS_STAIRS, Blocks.PINK_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PINK_STAINED_GLASS_SLAB, Blocks.PINK_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_STAIRS, Blocks.GRAY_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_STAINED_GLASS_SLAB, Blocks.GRAY_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, Blocks.LIGHT_GRAY_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, Blocks.LIGHT_GRAY_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_STAIRS, Blocks.CYAN_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_STAINED_GLASS_SLAB, Blocks.CYAN_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_STAIRS, Blocks.PURPLE_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_STAINED_GLASS_SLAB, Blocks.PURPLE_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_STAIRS, Blocks.BLUE_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_STAINED_GLASS_SLAB, Blocks.BLUE_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_STAIRS, Blocks.BROWN_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_STAINED_GLASS_SLAB, Blocks.BROWN_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_STAIRS, Blocks.GREEN_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_STAINED_GLASS_SLAB, Blocks.GREEN_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.RED_STAINED_GLASS_STAIRS, Blocks.RED_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.RED_STAINED_GLASS_SLAB, Blocks.RED_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_STAIRS, Blocks.BLACK_STAINED_GLASS);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_STAINED_GLASS_SLAB, Blocks.BLACK_STAINED_GLASS);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_STAIRS, Blocks.WHITE_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_SLAB, Blocks.WHITE_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_STAIRS, Blocks.ORANGE_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_SLAB, Blocks.ORANGE_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_STAIRS, Blocks.MAGENTA_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_SLAB, Blocks.MAGENTA_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_STAIRS, Blocks.LIGHT_BLUE_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_SLAB, Blocks.LIGHT_BLUE_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_STAIRS, Blocks.YELLOW_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_SLAB, Blocks.YELLOW_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_STAIRS, Blocks.LIME_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_SLAB, Blocks.LIME_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_STAIRS, Blocks.PINK_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_SLAB, Blocks.PINK_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_STAIRS, Blocks.GRAY_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_SLAB, Blocks.GRAY_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_STAIRS, Blocks.LIGHT_GRAY_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_SLAB, Blocks.LIGHT_GRAY_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_STAIRS, Blocks.CYAN_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_SLAB, Blocks.CYAN_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_STAIRS, Blocks.PURPLE_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_SLAB, Blocks.PURPLE_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_STAIRS, Blocks.BLUE_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_SLAB, Blocks.BLUE_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_STAIRS, Blocks.BROWN_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_SLAB, Blocks.BROWN_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_STAIRS, Blocks.GREEN_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_SLAB, Blocks.GREEN_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_STAIRS, Blocks.RED_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_SLAB, Blocks.RED_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_STAIRS, Blocks.BLACK_CONCRETE);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_SLAB, Blocks.BLACK_CONCRETE);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_STAIRS, Blocks.WHITE_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.WHITE_CONCRETE_POWDER_SLAB, Blocks.WHITE_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_STAIRS, Blocks.ORANGE_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.ORANGE_CONCRETE_POWDER_SLAB, Blocks.ORANGE_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_STAIRS, Blocks.MAGENTA_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.MAGENTA_CONCRETE_POWDER_SLAB, Blocks.MAGENTA_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_STAIRS, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_BLUE_CONCRETE_POWDER_SLAB, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_STAIRS, Blocks.YELLOW_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.YELLOW_CONCRETE_POWDER_SLAB, Blocks.YELLOW_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_STAIRS, Blocks.LIME_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIME_CONCRETE_POWDER_SLAB, Blocks.LIME_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_STAIRS, Blocks.PINK_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PINK_CONCRETE_POWDER_SLAB, Blocks.PINK_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_STAIRS, Blocks.GRAY_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GRAY_CONCRETE_POWDER_SLAB, Blocks.GRAY_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_STAIRS, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.LIGHT_GRAY_CONCRETE_POWDER_SLAB, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_STAIRS, Blocks.CYAN_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.CYAN_CONCRETE_POWDER_SLAB, Blocks.CYAN_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_STAIRS, Blocks.PURPLE_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.PURPLE_CONCRETE_POWDER_SLAB, Blocks.PURPLE_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_STAIRS, Blocks.BLUE_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLUE_CONCRETE_POWDER_SLAB, Blocks.BLUE_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_STAIRS, Blocks.BROWN_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BROWN_CONCRETE_POWDER_SLAB, Blocks.BROWN_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_STAIRS, Blocks.GREEN_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.GREEN_CONCRETE_POWDER_SLAB, Blocks.GREEN_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_STAIRS, Blocks.RED_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.RED_CONCRETE_POWDER_SLAB, Blocks.RED_CONCRETE_POWDER);
        stairsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_STAIRS, Blocks.BLACK_CONCRETE_POWDER);
        slabsToBlock(consumer, Unordinary_BasicsBlocks.BLACK_CONCRETE_POWDER_SLAB, Blocks.BLACK_CONCRETE_POWDER);
    }

    protected static void crownSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item
            pIngredientItem, Item pResultItem) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE), pResultItem).unlocks("has_enchanted_golden_apple", has(Items.ENCHANTED_GOLDEN_APPLE)).save(pFinishedRecipeConsumer, getItemName(pResultItem) + "_smithing");
    }

    private void stoneSlab(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Block pSlab, Block pMaterial){
        slab(pFinishedRecipeConsumer,pSlab,pMaterial);
        stonecutterResultFromBase(pFinishedRecipeConsumer,pSlab,pMaterial,2);
    }

    private void stoneStairs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Block pStair, Block pMaterial){
        stairs(pFinishedRecipeConsumer,pStair,pMaterial);
        stonecutterResultFromBase(pFinishedRecipeConsumer,pStair,pMaterial);
    }
}