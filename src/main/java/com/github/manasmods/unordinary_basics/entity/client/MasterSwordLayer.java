package com.github.manasmods.unordinary_basics.entity.client;

import com.github.manasmods.unordinary_basics.entity.MasterSwordWardenEntity;
import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class MasterSwordLayer extends RenderLayer<MasterSwordWardenEntity, WardenModel<MasterSwordWardenEntity>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public MasterSwordLayer(RenderLayerParent<MasterSwordWardenEntity, WardenModel<MasterSwordWardenEntity>> parent, ItemInHandRenderer p_234863_) {
        super(parent);
        this.itemInHandRenderer = p_234863_;
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight,
                       MasterSwordWardenEntity pLivingEntity, float pLimbSwing,
                       float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack itemstack = new ItemStack(Unordinary_BasicsItems.MASTER_SWORD);

        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(pPartialTicks, pLivingEntity.yRotO, pLivingEntity.getYHeadRot()) - 90.0F));
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pLivingEntity.xRotO, pLivingEntity.getXRot()) + 45.0F));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        pMatrixStack.translate(0F, 2.9, 0F);
        pMatrixStack.scale(2, 2, 2);

        this.itemInHandRenderer.renderItem(pLivingEntity, itemstack, ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.popPose();
    }
}
