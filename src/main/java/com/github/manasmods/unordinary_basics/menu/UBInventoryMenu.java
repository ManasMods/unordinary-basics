package com.github.manasmods.unordinary_basics.menu;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class UBInventoryMenu extends AbstractContainerMenu {

    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_slots/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_slots/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_slots/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_slots/empty_armor_slot_boots");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = new ResourceLocation("item/empty_slots/empty_armor_slot_shield");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_WAIST = new ResourceLocation("item/empty_slots/empty_armor_slot_waist");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BACK = new ResourceLocation("item/empty_slots/empty_armor_slot_chestplate");

    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET, EMPTY_ARMOR_SLOT_BACK, EMPTY_ARMOR_SLOT_WAIST};
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private final CraftingContainer craftSlots = new CraftingContainer(this, 2, 2);
    private final ResultContainer resultSlots = new ResultContainer();
    public final boolean active;
    private final Player owner;

    public UBInventoryMenu(Inventory inventory, boolean p_84622_, Player player) {
        super((MenuType<?>) null, 0);
        this.active = p_84622_;
        this.owner = player;

        for (int k = 0; k < 4; ++k) {

            for (int l = 0; l < 3; ++l) {
                for (int j1 = 0; j1 < 9; ++j1) {
                    this.addSlot(new Slot(inventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
                }
            }

            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
            }
            this.addSlot(new Slot(inventory, 37, 27, 8) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_HELMET);
                }
            });
            this.addSlot(new Slot(inventory, 38, 27, 8 + 18) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE);
                }
            });
            this.addSlot(new Slot(inventory, 39, 27, 8 + (18*2)) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS);
                }
            });
            this.addSlot(new Slot(inventory, 40, 27, 8 + (18*3)) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_BOOTS);
                }
            });
            this.addSlot(new Slot(inventory, 41, 132, 26) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_BACK);
                }
            });
            this.addSlot(new Slot(inventory, 42, 132, 26 + 18) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_WAIST);
                }
            });
            this.addSlot(new Slot(inventory, 43, 132, 26 + (18*3)) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_SHIELD);
                }
            });
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
