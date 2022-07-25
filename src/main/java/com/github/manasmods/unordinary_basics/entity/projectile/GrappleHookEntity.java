package com.github.manasmods.unordinary_basics.entity.projectile;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.github.manasmods.unordinary_basics.registry.EntityTypeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GrappleHookEntity extends AbstractArrow {

    private final LivingEntity shooter;

    public GrappleHookEntity(LivingEntity shooter, Level level) {
        super(EntityTypeRegistry.GRAPPLE_HOOK.get(), shooter, level);
        this.shooter = shooter;
    }

    @OnlyIn(Dist.CLIENT)
    public GrappleHookEntity(EntityType<? extends GrappleHookEntity> entityType, Level level) {
        super(entityType, level);
        shooter = null;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(Unordinary_BasicsItems.GRAPPLE_HOOK);
    }

    @Override
    protected void onHit(HitResult result) {
        if (!getLevel().isClientSide()) {
            shooter.moveTo(Vec3.atCenterOf(getOnPos().above()));
        }
    }
}
