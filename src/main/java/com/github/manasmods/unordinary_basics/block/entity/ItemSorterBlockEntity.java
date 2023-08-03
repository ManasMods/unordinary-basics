package com.github.manasmods.unordinary_basics.block.entity;

import com.github.manasmods.unordinary_basics.block.ItemSorterBlock;
import com.github.manasmods.unordinary_basics.menu.ItemSorterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.FormattedCharSequence;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ItemSorterBlockEntity extends BlockEntity implements MenuProvider {

    private final NonNullList<Item> filterItems = NonNullList.withSize(6, Items.AIR);

    private FormattedCharSequence[] renderMessages;

    private boolean renderMessagedFiltered;
    private final Component[] messages = new Component[]{(Component) Component.EMPTY, (Component) Component.EMPTY, (Component) Component.EMPTY, (Component) Component.EMPTY};
    private final Component[] filteredMessages = new Component[]{(Component) Component.EMPTY, (Component) Component.EMPTY, (Component) Component.EMPTY, (Component) Component.EMPTY};
    private static final String[] RAW_TEXT_FIELD_NAMES = new String[]{"Text1", "Text2", "Text3", "Text4"};
    private static final String[] FILTERED_TEXT_FIELD_NAMES = new String[]{"FilteredText1", "FilteredText2", "FilteredText3", "FilteredText4"};
    public ItemSorterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(Unordinary_BasicsBlockEntities.ITEM_SORTER_BLOCK_ENTITY, pWorldPosition, pBlockState);

    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        deserializeFilterItems(pTag.getCompound("filterItems"));

        for(int i = 0; i < 4; ++i) {
            String s = pTag.getString(RAW_TEXT_FIELD_NAMES[i]);
            Component component = this.deserializeTextSafe(s);
            this.messages[i] = component;
            String s1 = FILTERED_TEXT_FIELD_NAMES[i];
            if (pTag.contains(s1, 8)) {
                this.filteredMessages[i] = this.deserializeTextSafe(pTag.getString(s1));
            } else {
                this.filteredMessages[i] = component;
            }
        }

        this.renderMessages = null;
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("filterItems",serializeFilterItems());

        for(int i = 0; i < 4; ++i) {
            Component component = this.messages[i];
            String s = Component.Serializer.toJson(component);
            pTag.putString(RAW_TEXT_FIELD_NAMES[i], s);
            Component component1 = this.filteredMessages[i];
            if (!component1.equals(component)) {
                pTag.putString(FILTERED_TEXT_FIELD_NAMES[i], Component.Serializer.toJson(component1));
            }
        }
    }

    public NonNullList<Item> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(int index, Item item){
        filterItems.set(index,item);
        setChanged();
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

    private Component deserializeTextSafe(String pText) {
        try {
            Component component = Component.Serializer.fromJson(pText);
            if (component != null) {
                return component;
            }
        } catch (Exception exception) {
        }

        return (Component) Component.EMPTY;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("unordinary_basics.menu.item_sorter.title");
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
        this.saveAdditional(tag);
        return tag;
    }

    public void filterItemsOfPlayer(Player pPlayer, BlockPos pPos, BlockState pState, Level pLevel){
        BlockEntity entity = pLevel.getBlockEntity(pPos.relative(pState.getValue(ItemSorterBlock.FACING).getOpposite()));

        if (entity != null) entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
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
            if (handler.getStackInSlot(availableSlot).getCount() + stack.getCount() <= stack.getMaxStackSize()){
                handler.insertItem(availableSlot, new ItemStack(stack.getItem(),stack.getCount()),false);
                stack.shrink(stack.getCount());
                return 0;
            } else {
                int toFill = stack.getMaxStackSize() - handler.getStackInSlot(availableSlot).getCount();
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

    public void setMessage(int pLine, Component pMessage) {
        this.setMessage(pLine, pMessage, pMessage);
    }

    public Component getMessage(int pIndex, boolean pFiltered) {
        return this.getMessages(pFiltered)[pIndex];
    }

    public void setMessage(int pLine, Component pMessage, Component pFilteredMessage) {
        this.messages[pLine] = pMessage;
        this.filteredMessages[pLine] = pFilteredMessage;
        this.renderMessages = null;
        this.setChanged();
    }

    public FormattedCharSequence[] getRenderMessages(boolean pRenderMessagedFiltered, Function<Component, FormattedCharSequence> pMessageTransformer) {
        if (this.renderMessages == null || this.renderMessagedFiltered != pRenderMessagedFiltered) {
            this.renderMessagedFiltered = pRenderMessagedFiltered;
            this.renderMessages = new FormattedCharSequence[4];

            for(int i = 0; i < 4; ++i) {
                this.renderMessages[i] = pMessageTransformer.apply(this.getMessage(i, pRenderMessagedFiltered));
            }
        }

        setChanged();
        return this.renderMessages;
    }

    private Component[] getMessages(boolean pFiltered) {
        return pFiltered ? this.filteredMessages : this.messages;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T be) {

    }

    @Override
    public void setChanged() {
        super.setChanged();
    }
}
