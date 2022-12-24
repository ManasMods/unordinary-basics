package com.github.manasmods.unordinary_basics.core;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class MixinArmorTooltips {

    private static final Component CHAINMAIL_TOOLTIP = new TranslatableComponent("tooltip.unordinary_basics.chainmail_armor");

    @Inject(method = "appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V", at = @At("HEAD"))
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag isAdvanced, CallbackInfo callback) {
        Item me = (Item) ((Object) this);
        ResourceLocation registryName = me.getRegistryName();
        assert registryName != null;
        if (registryName.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE) && registryName.getPath().contains("chainmail")) {
            components.add(CHAINMAIL_TOOLTIP);
        }
    }
}
