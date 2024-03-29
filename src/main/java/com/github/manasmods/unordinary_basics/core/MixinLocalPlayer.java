package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.capability.CapabilityUBInventory;
import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import com.github.manasmods.unordinary_basics.client.ClientUBInventoryData;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LocalPlayer.class)
public class MixinLocalPlayer extends AbstractClientPlayer {
    public MixinLocalPlayer(ClientLevel pClientLevel, GameProfile pGameProfile, @Nullable ProfilePublicKey pProfilePublicKey) {
        super(pClientLevel, pGameProfile, pProfilePublicKey);
    }

    LocalPlayer localPlayer = (LocalPlayer) (Object)this;

    @Inject(at = @At(value = "INVOKE",target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"),method = "aiStep")
    public void aiStep(CallbackInfo ci){
        ItemStack itemstack = localPlayer.getItemBySlot(EquipmentSlot.CHEST);
        if (!itemstack.is(Items.ELYTRA)){
            ItemStack stack;

            IUBInventoryHandler handler = ClientUBInventoryData.getHandler();
            stack = handler.getStackInSlot(CapabilityUBInventory.SLOT_INDEX.get(CapabilityUBInventory.UBSlot.BACK));

            if (stack.is(Items.ELYTRA)){
                itemstack = stack;
                if (itemstack.canElytraFly(localPlayer) && localPlayer.tryToStartFallFlying()) {
                    localPlayer.connection.send(new ServerboundPlayerCommandPacket(localPlayer, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
                }
            }
        }
    }
}
