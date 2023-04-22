package com.github.manasmods.unordinary_basics.block.entity;

import com.github.manasmods.unordinary_basics.block.ItemSorterBlock;
import com.github.manasmods.unordinary_basics.capability.RedstonePouchCapabilityProvider;
import com.github.manasmods.unordinary_basics.menu.ItemSorterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class ItemSorterBlockEntity extends BlockEntity implements MenuProvider {

    public NonNullList<Item> filterItems = NonNullList.withSize(6, Items.AIR);

    public ItemSorterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(Unordinary_BasicsBlockEntities.ITEM_SORTER_BLOCK_ENTITY, pWorldPosition, pBlockState);

    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        deserializeFilterItems(pTag.getCompound("filterItems"));
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("filterItems",serializeFilterItems());
    }

    private CompoundTag serializeFilterItems(){
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < filterItems.size(); i++)
        {
            if (filterItems.get(i) != null)
            {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                new ItemStack(filterItems.get(i)).save(itemTag);
                nbtTagList.add(itemTag);
            }
        }
        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        return nbt;
    }

    private void deserializeFilterItems(CompoundTag nbt){
        ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < tagList.size(); i++)
        {
            CompoundTag itemTags = tagList.getCompound(i);
            int slot = itemTags.getInt("Slot");

            if (slot >= 0 && slot < filterItems.size())
            {
                filterItems.set(slot, ItemStack.of(itemTags).getItem());
            }
        }
        onLoad();
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("unordinary_basics.menu.item_sorter.title");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ItemSorterMenu(pContainerId,pPlayerInventory,this);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    public void filterItemsOfPlayer(Player pPlayer, BlockPos pPos, BlockState pState, Level pLevel){
        BlockEntity entity = pLevel.getBlockEntity(pPos.relative(pState.getValue(ItemSorterBlock.FACING).getOpposite()));

        if (entity != null) entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            Inventory inv = pPlayer.getInventory();
            for (int i = 0; i < inv.getContainerSize(); i++) {
                if (filterItems.contains(inv.getItem(i).getItem())){
                    ItemStack toDump = inv.getItem(i);
                    int remaining = dumpItemStack(toDump,handler);
                    toDump.shrink(toDump.getCount() - remaining);
                }
            }
        });
    }

    private int dumpItemStack(ItemStack stack, IItemHandler handler){
        int availableSlot;

        while (stack.getCount() > 0){
            availableSlot = getFirstAvailableSlot(stack,handler);
            if (availableSlot == -8) return stack.getCount();
            if (handler.getStackInSlot(availableSlot).getCount() + stack.getCount() <= 64){
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(stack.getCount());
                return 0;
            } else {
                int toFill = 64 - handler.getStackInSlot(availableSlot).getCount();
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(toFill);
            }
        }
        return stack.getCount();
    }

    private int getFirstAvailableSlot(ItemStack stack, IItemHandler handler){
        for (int i = 0; i < handler.getSlots(); ++i){
            if (handler.getStackInSlot(i).getCount() == 0 || handler.getStackInSlot(i).getCount() < 64 && handler.getStackInSlot(i).is(stack.getItem())){
                return i;
            }
        }
        return -8;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T be) {

    }
}
