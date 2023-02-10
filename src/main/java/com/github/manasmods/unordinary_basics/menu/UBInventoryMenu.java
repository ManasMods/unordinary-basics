package com.github.manasmods.unordinary_basics.menu;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryItem;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.menu.slot.SlotUBInventory;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBInventoryMenu;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;
import net.minecraftforge.items.wrapper.PlayerOffhandInvWrapper;

import java.util.Optional;

public class UBInventoryMenu extends RecipeBookMenu<CraftingContainer> {
    private final Player player;

    private final InvWrapper inventoryHelper;
    private final PlayerArmorInvWrapper armorHelper;
    private final PlayerOffhandInvWrapper offhandHelper;
    private final Inventory inventory;

    private final CraftingContainer craftSlots = new CraftingContainer(this, 2, 2);
    private final ResultContainer resultSlots = new ResultContainer();

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

        addPlayerInventorySlots();
        addSlots();
        stackHandler.setMenu(this);
    }

    public Slot addSlotEx(Slot slot){
        return this.addSlot(slot);
    }

    public UBInventoryItemStackHandler getStackHandler(){
        return stackHandler;
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

        //Hot bar
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(this.inventoryHelper, index++, 8 + 18 * col, 142));
        }

        //Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new SlotItemHandler(this.inventoryHelper, index++, 8 + 18 * col, 84 + 18 * row));
            }
        }

        //Armor
        for (int row = 0; row < 4; row++){
            addSlot(new SlotItemHandler(armorHelper,3 - row,8,8 + row * 18));
        }

        //Offhand
        addSlot(new SlotItemHandler(offhandHelper,0,77,62));

        //TODO: Crafting - doesn't work yet
        for(int col = 0; col < 2; ++col){
            for (int row = 0; row < 2; ++row){
                this.addSlot(new Slot(this.craftSlots, row + col * 2, 98 + row * 18, 18 + col * 18));
            }
        }

        this.addSlot(new ResultSlot(player, this.craftSlots, this.resultSlots, 0, 154, 28));

        //UB Slots
        addSlot(new SlotUBInventory(stackHandler,CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.BACK),77,26));
        addSlot(new SlotUBInventory(stackHandler,CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.WAIST),77,44));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(Player player) {
        return true;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
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
