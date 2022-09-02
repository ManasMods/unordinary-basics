package com.github.manasmods.unordinary_basics.entity.client;

import com.github.manasmods.unordinary_basics.entity.GrizzlyBearEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearModel<GrizzlyBearEntity>> {
    private static final ResourceLocation BEAR_LOCATION = new ResourceLocation("unordinary_basics:textures/entity/grizzly_bear.png");

    public GrizzlyBearRenderer(EntityRendererProvider.Context p_174356_) {
        super(p_174356_, new GrizzlyBearModel<>(p_174356_.bakeLayer(ModelLayers.POLAR_BEAR)), 0.9F);
    }

    public ResourceLocation getTextureLocation(GrizzlyBearEntity entity) {
        return BEAR_LOCATION;
    }

    protected void scale(GrizzlyBearEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(1.44F, 1.44F, 1.44F);
        super.scale(pLivingEntity, pMatrixStack, pPartialTickTime);
    }
}
