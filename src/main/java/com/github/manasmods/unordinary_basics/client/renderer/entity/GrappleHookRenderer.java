package com.github.manasmods.unordinary_basics.client.renderer.entity;

import com.github.manasmods.unordinary_basics.entity.projectile.GrappleHookEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrappleHookRenderer extends ArrowRenderer<GrappleHookEntity> {

    public GrappleHookRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(GrappleHookEntity entity) {
        return new ResourceLocation("minecraft", "textures/entity/projectiles/arrow.png");
    }
}
