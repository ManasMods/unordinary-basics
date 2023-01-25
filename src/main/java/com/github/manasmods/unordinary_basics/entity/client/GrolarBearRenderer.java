package com.github.manasmods.unordinary_basics.entity.client;

import com.github.manasmods.unordinary_basics.entity.GrizzlyBearEntity;
import com.github.manasmods.unordinary_basics.entity.GrolarBearEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrolarBearRenderer extends MobRenderer<GrolarBearEntity, GrolarBearModel<GrolarBearEntity>> {
    private static final ResourceLocation BEAR_LOCATION = new ResourceLocation("unordinary_basics:textures/entity/grolar_bear.png");

    public GrolarBearRenderer(EntityRendererProvider.Context p_174356_) {
        super(p_174356_, new GrolarBearModel<>(p_174356_.bakeLayer(ModelLayers.POLAR_BEAR)), 0.9F);
    }

    public ResourceLocation getTextureLocation(GrolarBearEntity entity) {
        return BEAR_LOCATION;
    }

    protected void scale(GrolarBearEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(1.32F, 1.32F, 1.32F);
        super.scale(pLivingEntity, pMatrixStack, pPartialTickTime);
    }
}
