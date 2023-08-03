package com.github.manasmods.unordinary_basics.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(Item.class)
public class MixinArmorTooltips {

    private static final Set<String> NETHERITE_WHITELIST;
    static {
        NETHERITE_WHITELIST = new HashSet<>();
        NETHERITE_WHITELIST.add("netherite_helmet");
        NETHERITE_WHITELIST.add("netherite_chestplate");
        NETHERITE_WHITELIST.add("netherite_leggings");
        NETHERITE_WHITELIST.add("netherite_boots");
    }

    private static final Component CHAINMAIL_TOOLTIP = Component.translatable("tooltip.unordinary_basics.chainmail_armor");
    private static final Component NETHERITE_TOOLTIP = Component.translatable("tooltip.unordinary_basics.netherite_armor");

    @Inject(method = "appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V", at = @At("HEAD"))
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag isAdvanced, CallbackInfo callback) {
        Item me = (Item) ((Object) this);
        ResourceLocation registryName = ForgeRegistries.ITEMS.getKey(me);
        assert registryName != null;
        if (registryName.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            String path = registryName.getPath();
            if (path.contains("chainmail")) {
                components.add(CHAINMAIL_TOOLTIP);
            } else if (NETHERITE_WHITELIST.contains(path)) {
                components.add(NETHERITE_TOOLTIP);
            }
        }
    }
}
