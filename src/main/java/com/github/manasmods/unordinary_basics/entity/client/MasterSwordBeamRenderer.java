package com.github.manasmods.unordinary_basics.entity.client;

import com.github.manasmods.unordinary_basics.entity.MasterSwordBeam;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MasterSwordBeamRenderer extends EntityRenderer<MasterSwordBeam> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam.png");

    private final MasterSwordBeamModel model;

    public MasterSwordBeamRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new MasterSwordBeamModel<>(pContext.bakeLayer(MasterSwordBeamModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(MasterSwordBeam entity) {
        return TEXTURE_LOCATION;
    }

    @Override
    public void render(MasterSwordBeam pEntity, float pEntityYaw, float pPartialTick, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
        pMatrixStack.translate(0d,0d,0d);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));

        this.model.rotate(pEntity);
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pMatrixStack, pBuffer, pPackedLight);
    }
}
