package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.menu.slot.SlotUBInventory;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBInventoryMenu;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;
import net.minecraftforge.items.wrapper.PlayerOffhandInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class UBInventoryMenu extends RecipeBookMenu<CraftingContainer> {
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
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BACK_PACK = new ResourceLocation(Unordinary_Basics.MOD_ID,"item/empty_slots/empty_armor_slot_back_pack");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BACK_WINGS = new ResourceLocation(Unordinary_Basics.MOD_ID,"item/empty_slots/empty_armor_slot_back_wings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BACK_QUIVER = new ResourceLocation(Unordinary_Basics.MOD_ID,"item/empty_slots/empty_armor_slot_back_quiver");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_WAIST = new ResourceLocation(Unordinary_Basics.MOD_ID,"item/empty_slots/empty_armor_slot_waist");
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};

    private final int windowId;
    private UBInventoryItemStackHandler stackHandler;

    public UBInventoryMenu(int windowId, Inventory inventory, Player player) {
        super(Unordinary_BasicsMenuTypes.INVENTORY_MENU, windowId);
        this.player = player;
        this.inventoryHelper = new InvWrapper(inventory);
        this.armorHelper = new PlayerArmorInvWrapper(inventory);
        this.offhandHelper = new PlayerOffhandInvWrapper(inventory);
        this.inventory = inventory;
        this.windowId = windowId;

        player.getCapability(CapabilityUBInventory.UB_INVENTORY_CAPABILITY).ifPresent(handler -> {
            this.stackHandler = (UBInventoryItemStackHandler) handler;
        });

        this.externalSlotCount = getExternalSlotCount();

        addPlayerInventorySlots();
        stackHandler.setMenu(this);
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
            if (stackHandler.getStackInSlot(i).isEmpty()) continue;
            IUBInventoryItem item = (IUBInventoryItem)stackHandler.getStackInSlot(i).getItem();
            slotCount += item.getSlotCount();
        }
        return slotCount;
    }

    public void addSlots(){
        //RUNNING THIS CRASHES THE GAME WHYYYYY
        /*List<Slot> removeList = new ArrayList<>();
        for (Slot slot : slots){
            if (slot instanceof IUBItemSlot){
                removeList.add(slot);
            }
        }

        slots.removeAll(removeList);*/

        for (int i = 0; i < stackHandler.getSlots(); ++i){
            if (stackHandler.getStackInSlot(i).isEmpty()) continue;
            IUBInventoryItem item = (IUBInventoryItem)stackHandler.getStackInSlot(i).getItem();
            item.addSlots(windowId,inventory,player,this,stackHandler.getStackInSlot(i));
        }
    }

    /*
    This implementation creates a weird 'flinching effect' when replacing UBInv items, but I almost went crazy trying to remove slots dynamically.
    WHICH IS NOT SUPPOSED TO BE DONE.
    If anyone has any idea on how to make the above code work without the game throwing an error that literally doesn't point out any part of this code for whatever reason,
    GO FOR IT. I WILL NOT. rant over
    */
    public void resetScreen(){
        Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestUBInventoryMenu());
    }

    private void addPlayerInventorySlots() {
        int index = 0;

        //Indexes are provided in format (INCLUSIVE - EXCLUSIVE)

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
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
                }
            });
        }

        //40 - 42
        //UB Slots
        addSlot(new SlotUBInventory(stackHandler,CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.BACK),77,26){
            @SuppressWarnings("ConstantConditions")
            @Nullable
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                int timeRemainder = (int)player.getLevel().getGameTime() % 150;
                if (timeRemainder <= 50){
                    return Pair.of(InventoryMenu.BLOCK_ATLAS,EMPTY_ARMOR_SLOT_BACK_PACK);
                } else if (timeRemainder <= 100){
                    return Pair.of(InventoryMenu.BLOCK_ATLAS,EMPTY_ARMOR_SLOT_BACK_QUIVER);
                } else if (timeRemainder < 150){
                    return Pair.of(InventoryMenu.BLOCK_ATLAS,EMPTY_ARMOR_SLOT_BACK_WINGS);
                }
                return Pair.of(InventoryMenu.BLOCK_ATLAS,EMPTY_ARMOR_SLOT_BACK_PACK);
            }
        });
        addSlot(new SlotUBInventory(stackHandler,CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.WAIST),77,44,Pair.of(InventoryMenu.BLOCK_ATLAS,EMPTY_ARMOR_SLOT_WAIST)));

        // 42 - 42 + external slot count
        //Custom Items' Slots
        addSlots();

        //42 + external slot count - 43 + external slot count
        //Offhand
        addSlot(new SlotItemHandler(offhandHelper,0,77,62){
            @Nullable
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, UBInventoryMenu.EMPTY_ARMOR_SLOT_SHIELD);
            }
        });

        //43 + external slot count - 48 + external slot count
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
            stackHandler.getStackInSlot(i).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
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
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            final ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index == 42 + externalSlotCount + 5) {
                if (!this.moveItemStackTo(itemstack1, 1, 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index < 36 && index > 8) {
                if (!moveItemStackTo(itemstack1, 0, 9, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 9 && applicableForUBSlots(itemstack1) && !isArmor(itemstack1,pPlayer)) {
                if (!moveItemStackTo(itemstack1, 36, 42 + externalSlotCount, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 9 && !applicableForUBSlots(itemstack1) && !isArmor(itemstack1,pPlayer)) {
                if (!moveItemStackTo(itemstack1, 9, 40, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 9 && !applicableForUBSlots(itemstack1) && isArmor(itemstack1,pPlayer)) {
                if (!moveItemStackTo(itemstack1, 36, 40, true)) {
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
        }
        return itemstack;
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        stackHandler.setMenu(null);
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
