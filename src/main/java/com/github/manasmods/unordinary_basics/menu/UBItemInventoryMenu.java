package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.client.ClientUBInventoryData;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;
import net.minecraftforge.items.wrapper.PlayerOffhandInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class UBItemInventoryMenu extends RecipeBookMenu<CraftingContainer> {
    private final Player player;

    private final InvWrapper inventoryHelper;
    private final PlayerArmorInvWrapper armorHelper;
    private final PlayerOffhandInvWrapper offhandHelper;
    private final Inventory inventory;
    private final int externalSlotCount;

    private final CraftingContainer craftSlots = new CraftingContainer(this, 2, 2);
    private final ResultContainer resultSlots = new ResultContainer();
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = new ResourceLocation("item/empty_armor_slot_shield");
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};

    private final int windowId;
    private UBInventoryItemStackHandler stackHandler;

    public UBItemInventoryMenu(int windowId, Inventory inventory, Player player) {
        super(Unordinary_BasicsMenuTypes.ITEM_INVENTORY_MENU, windowId);
        this.player = player;
        this.inventoryHelper = new InvWrapper(inventory);
        this.armorHelper = new PlayerArmorInvWrapper(inventory);
        this.offhandHelper = new PlayerOffhandInvWrapper(inventory);
        this.inventory = inventory;
        this.windowId = windowId;

        if (player.level.isClientSide) {
            this.stackHandler = (UBInventoryItemStackHandler) ClientUBInventoryData.getHandler();
        } else {
            player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
                this.stackHandler = (UBInventoryItemStackHandler) handler;
            });
        }

        addPlayerInventorySlots();

        this.externalSlotCount = getExternalSlotCount();
    }

    public Slot addSlotEx(Slot slot){
        return this.addSlot(slot);
    }

    public UBInventoryItemStackHandler getStackHandler(){
        return stackHandler;
    }

    private int getExternalSlotCount(){
        int slotCount = 0;
        for (int i = 0; i < stackHandler.getSlots(); ++i){
            if (stackHandler.getStackInSlot(i).isEmpty() || !(stackHandler.getStackInSlot(i).getItem() instanceof IUBInventoryItem)) continue;
            IUBInventoryItem item = (IUBInventoryItem)stackHandler.getStackInSlot(i).getItem();
            slotCount += item.getSlotCount();
        }
        return slotCount;
    }

    public void addSlots(){
        for (int i = 0; i < stackHandler.getSlots(); ++i){
            if (stackHandler.getStackInSlot(i).isEmpty() || !(stackHandler.getStackInSlot(i).getItem() instanceof IUBInventoryItem)) continue;
            IUBInventoryItem item = (IUBInventoryItem)stackHandler.getStackInSlot(i).getItem();
            item.addSlots(windowId,inventory,player,this,stackHandler.getStackInSlot(i));
        }
    }


    private void addPlayerInventorySlots() {
        int index = 0;

        //Indexes are provided in format [INCLUSIVE - EXCLUDED)

        //0 - 9
        //Hotbar
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(this.inventoryHelper, index++, 8 + 18 * col, 142));
        }

        //9 - 36
        //Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new SlotItemHandler(this.inventoryHelper, index++, 8 + 18 * col, 84 + 18 * row));
            }
        }

        //36 - 40
        //Armor
        for (int row = 0; row < 4; row++){
            final EquipmentSlot equipmentslot = SLOT_IDS[row];
            addSlot(new SlotItemHandler(armorHelper,3 - row,8,8 + row * 18){

                public int getMaxStackSize() {
                    return 1;
                }

                public boolean mayPlace(ItemStack stack) {
                    return stack.canEquip(equipmentslot, player);
                }

                public boolean mayPickup(Player p) {
                    ItemStack itemstack = this.getItem();
                    return !itemstack.isEmpty() && !p.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.mayPickup(p);
                }

                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBItemInventoryMenu.TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
                }
            });
        }

        addSlots();

        //40 - 41
        //Offhand
        addSlot(new SlotItemHandler(offhandHelper,0,77,62){
            @Nullable
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, UBItemInventoryMenu.EMPTY_ARMOR_SLOT_SHIELD);
            }
        });

        //41 - 46
        //Crafting
        for(int col = 0; col < 2; ++col){
            for (int row = 0; row < 2; ++row){
                this.addSlot(new Slot(this.craftSlots, row + col * 2, 98 + row * 18, 18 + col * 18));
            }
        }

        this.addSlot(new ResultSlot(player, this.craftSlots, this.resultSlots, 0, 154, 28));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(Player player) {
        return true;
    }

    private boolean applicableForUBSlots(ItemStack stack){
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            if (stackHandler.isItemValid(stack,0) || stackHandler.isItemValid(stack,1)) return true;
            AtomicBoolean shouldReturn = new AtomicBoolean(false);
            stackHandler.getStackInSlot(i).getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                if (handler.isItemValid(0,stack)) {
                    shouldReturn.set(true);
                }
            });
            if (shouldReturn.get()) return true;
        }

        return false;
    }

    private boolean isArmor(ItemStack stack, Entity entity){
        for (int i = 0; i < 4; i++) {
            final EquipmentSlot equipmentslot = SLOT_IDS[i];
            if (stack.canEquip(equipmentslot,entity)) return true;
        }
        return false;
    }


    @Override
    public ItemStack quickMoveStack(Player pPlayer, int index) {
        //TODO: write a functional quickmovestack for this menu
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = slots.get(index);

        /*if (slot != null && slot.hasItem()) {
            final ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index == 40 + externalSlotCount + 5) {
                if (!this.moveItemStackTo(itemstack1, 1, 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index < 36 && index > 8) {
                if (!moveItemStackTo(itemstack1, 0, 9, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(pPlayer, itemstack1);
        }*/

        return itemstack;
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        resultSlots.clearContent();
        if (!pPlayer.level.isClientSide) {
            this.clearContainer(pPlayer, this.craftSlots);
        }
    }


    @Override
    public void fillCraftSlotsStackedContents(StackedContents pItemHelper) {
        this.craftSlots.fillStackedContents(pItemHelper);
    }

    @Override
    public void clearCraftingContent() {
        this.resultSlots.clearContent();
        this.craftSlots.clearContent();
    }

    @Override
    public boolean recipeMatches(Recipe<? super CraftingContainer> pRecipe) {
        return pRecipe.matches(this.craftSlots, this.player.level);
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return 2;
    }

    @Override
    public int getGridHeight() {
        return 2;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public boolean shouldMoveToInventory(int pSlotIndex) {
        return pSlotIndex != this.getResultSlotIndex();
    }

    @Override
    public void slotsChanged(Container pContainer) {
        slotChangedCraftingGrid(this, this.player.level, this.player, this.craftSlots, this.resultSlots);
    }

    //from CraftingContainer class, since it's protected
    private static void slotChangedCraftingGrid(AbstractContainerMenu pMenu, Level pLevel, Player pPlayer, CraftingContainer pContainer, ResultContainer pResult) {
        if (!pLevel.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)pPlayer;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = pLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, pContainer, pLevel);
            if (optional.isPresent()) {
                CraftingRecipe craftingrecipe = optional.get();
                if (pResult.setRecipeUsed(pLevel, serverplayer, craftingrecipe)) {
                    itemstack = craftingrecipe.assemble(pContainer);
                }
            }

            pResult.setItem(0, itemstack);
            pMenu.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(pMenu.containerId, pMenu.incrementStateId(), 0, itemstack));
        }
    }
}
