package com.github.manasmods.unordinary_basics.core;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(ItemEntity.class)
public class MixinItemEntity {

    ItemEntity entity = (ItemEntity)((Object)this);

    @Inject(at = @At(value = "HEAD"),method = "tick")
    private void tick(CallbackInfo info){
        if (!entity.getItem().is(Unordinary_BasicsItems.UNKNOWN_HILT_FRAGMENT)) return;

        List<ItemEntity> itemEntities = new ArrayList<>();
        itemEntities.add(entity);
        Level level = entity.getLevel();

        if (hasOtherParts(itemEntities)){
            itemEntities.forEach(itemEntity -> {
                itemEntity.discard();
                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightningBolt.moveTo(itemEntity.getOnPos(),0f,0f);
                level.addFreshEntity(lightningBolt);
            });
            ItemEntity toSummon = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(Unordinary_BasicsItems.ZENITH));
            level.addFreshEntity(toSummon);
        }

    }

    @Inject(at = @At(value = "HEAD"),method = "hurt", cancellable = true)
    private void hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir){
        if (entity.getItem().is(Unordinary_BasicsItems.ZENITH) && pSource.equals(DamageSource.LIGHTNING_BOLT)) cir.cancel();
    }

    private boolean hasOtherParts(List<ItemEntity> itemEntities){

        AtomicBoolean flag1 = new AtomicBoolean(false);
        AtomicBoolean flag2 = new AtomicBoolean(false);
        AtomicBoolean flag3 = new AtomicBoolean(false);

        for(ItemEntity itementity : entity.level.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(0.5D, 0.0D, 0.5D),itemEntity -> itemEntity.getItem().is(Unordinary_BasicsItems.UNKNOWN_BLADE_FRAGMENT)
                || itemEntity.getItem().is(Unordinary_BasicsItems.UNKNOWN_HANDLE_FRAGMENT) || itemEntity.getItem().is(Items.NETHER_STAR))){
            if (itementity.getItem().is(Unordinary_BasicsItems.UNKNOWN_HANDLE_FRAGMENT)){
                if (!flag1.get()) itemEntities.add(itementity);
                flag1.set(true);
            }
            if (itementity.getItem().is(Unordinary_BasicsItems.UNKNOWN_BLADE_FRAGMENT)){
                if (!flag2.get()) itemEntities.add(itementity);
                flag2.set(true);
            }
            if (itementity.getItem().is(Items.NETHER_STAR)){
                if (!flag3.get()) itemEntities.add(itementity);
                flag3.set(true);
            }
        }

        return flag1.get() && flag2.get() && flag3.get();
    }

}
