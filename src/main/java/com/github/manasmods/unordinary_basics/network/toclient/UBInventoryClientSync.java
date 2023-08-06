package com.github.manasmods.unordinary_basics.network.toclient;

import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import com.github.manasmods.unordinary_basics.capability.UBInventoryItemStackHandler;
import com.github.manasmods.unordinary_basics.client.ClientUBInventoryData;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UBInventoryClientSync {
        private final IUBInventoryHandler handler;

        public UBInventoryClientSync(IUBInventoryHandler handler) {
            this.handler = handler;
        }

        public UBInventoryClientSync(FriendlyByteBuf buf) {
            NonNullList<ItemStack> created = NonNullList.withSize(2,ItemStack.EMPTY);
            for (int i = 0; i < 2; ++i) {
                ItemStack stack = buf.readItem();

                stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
                    if (itemHandler instanceof ItemStackHandler stackHandler) {
                        stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound("inventory"));
                    }
                });
                created.set(i,stack);
            }
            this.handler = new UBInventoryItemStackHandler(created);
        }

        public void toBytes(FriendlyByteBuf buf) {
            for (int i = 0; i < 2; ++i) {
                ItemStack place = handler.getStackInSlot(i);
                place.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
                    if (itemHandler instanceof ItemStackHandler stackHandler) {
                        place.getOrCreateTag().put("inventory", stackHandler.serializeNBT());
                    }
                });
                buf.writeItem(place);
            }
        }

        public void handle(Supplier<NetworkEvent.Context> ctx) {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() -> {
                ClientUBInventoryData.set(handler);
            });
            ctx.get().setPacketHandled(true);
        }
}
