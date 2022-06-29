package com.github.manasmods.unordinary_basics.core;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.manasmods.unordinary_basics.block.IColorable;
import com.github.manasmods.unordinary_basics.block.IPatternable;
import com.github.manasmods.unordinary_basics.utils.ClientUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Vector3f;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BedBlockEntity;

@Mixin(BedRenderer.class)
public abstract class MixinBedRenderer implements BlockEntityRenderer<BedBlockEntity>
{
    private BedBlockEntity entity;
    
    @Inject(method = "render(Lnet/minecraft/world/level/block/entity/BedBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "HEAD"))
    public void render(BedBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, CallbackInfo info) {
        this.entity = pBlockEntity;
    }
    
    /**
     * 
     * @param pFoot {@code true} if piece to render is the foot of the bed, {@code false} otherwise or if being rendered
     * by a {@link net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer}
     */
    @Redirect(method = "render", 
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/BedRenderer;renderPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/core/Direction;Lnet/minecraft/client/resources/model/Material;IIZ)V"))
    private void redirectRenderPiece(BedRenderer renderer, PoseStack pPoseStack, MultiBufferSource pBufferSource, ModelPart pModelPart, Direction pDirection, Material pMaterial, int pPackedLight, int pPackedOverlay, boolean pFoot) {
        renderPiece(renderer, pPoseStack, pBufferSource, pModelPart, pDirection, pMaterial, pPackedLight, pPackedOverlay, pFoot);
    }
    
    private void renderPiece(BedRenderer renderer, PoseStack pPoseStack, MultiBufferSource pBufferSource, ModelPart pModelPart, Direction pDirection, Material pMaterial, int pPackedLight, int pPackedOverlay, boolean pFoot) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0D, 0.5625D, pFoot ? -1.0D : 0.0D);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        pPoseStack.translate(0.5D, 0.5D, 0.5D);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F + pDirection.toYRot()));
        pPoseStack.translate(-0.5D, -0.5D, -0.5D);
        
        if(entity != null && entity instanceof IPatternable) {
            IPatternable pattern = (IPatternable)this.entity;
            
            if(pattern.getPatterns().size() > 0) {
                for(int i = 0; i < 17 && i < pattern.getPatterns().size(); i++) {
                    Pair<BannerPattern, DyeColor> pair = pattern.getPatterns().get(i);
                    
                    float[] afloat = pair.getSecond().getTextureDiffuseColors();
                    BannerPattern bannerpattern = pair.getFirst();
                    Material material = ClientUtils.getTexture(bannerpattern);
                    pModelPart.render(pPoseStack, material.buffer(pBufferSource, RenderType::entityNoOutline), pPackedLight, pPackedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);
                }
                
                if(entity != null && entity instanceof IColorable) {
                    IColorable color = (IColorable)this.entity;
                    
                    float[] afloat = color.getColor().getTextureDiffuseColors();
                    
                    VertexConsumer vertexconsumer = pMaterial.buffer(pBufferSource, RenderType::entitySolid);
                    pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);
                } else {
                    VertexConsumer vertexconsumer = pMaterial.buffer(pBufferSource, RenderType::entitySolid);
                    
                    pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
                }
                
                pPoseStack.popPose();
                return;
            }
        }
        
        if(entity != null && entity instanceof IColorable) {
            IColorable color = (IColorable)this.entity;
            
            float[] afloat = color.getColor().getTextureDiffuseColors();
            
            VertexConsumer vertexconsumer = pMaterial.buffer(pBufferSource, RenderType::entitySolid);
            pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);
        } else {
            VertexConsumer vertexconsumer = pMaterial.buffer(pBufferSource, RenderType::entitySolid);
            
            pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
        }
        
        
        pPoseStack.popPose();
     }
    
}
