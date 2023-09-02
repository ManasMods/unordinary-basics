package com.github.manasmods.unordinary_basics.entity.client;

import com.github.manasmods.unordinary_basics.entity.MasterSwordBeam;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Arrays;

public class MasterSwordBeamRenderer extends EntityRenderer<MasterSwordBeam> {
    protected static final ResourceLocation[] TEXTURES = {
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_0.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_1.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_2.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_3.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_4.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_5.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_6.png"),
            new ResourceLocation("unordinary_basics:textures/entity/master_sword_beam/master_sword_beam_7.png")
    };

    private final MasterSwordBeamModel model;

    public MasterSwordBeamRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new MasterSwordBeamModel<>(pContext.bakeLayer(MasterSwordBeamModel.LAYER_LOCATION));
    }

    public ResourceLocation getTextureLocation(MasterSwordBeam instance) {
        ResourceLocation[] resourceLocations = TEXTURES;
        return Arrays.stream(resourceLocations).toList().get((instance.tickCount) % resourceLocations.length);
    }

    public void render(MasterSwordBeam pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
        pMatrixStack.translate(0d,0d,0d);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        float f = Mth.rotlerp(pEntity.yRotO, pEntity.getYRot(), pPartialTicks);
        float f1 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
        this.model.rotBeam(f,f1);
        //this.model.rotate(pEntity);
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
