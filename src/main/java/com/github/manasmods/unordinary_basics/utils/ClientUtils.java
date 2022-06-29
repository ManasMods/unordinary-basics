package com.github.manasmods.unordinary_basics.utils;

import java.io.IOException;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.block.IColorable;
import com.github.manasmods.unordinary_basics.block.IPatternable;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BedBlockEntity;

public class ClientUtils
{
    
    public static Level getLevel() {
        return Minecraft.getInstance().level;
    }
    
    public static Material getTexture(BannerPattern pattern) {
        //System.out.println("Banner: " + pattern.getFilename());
        
        return new Material(Sheets.BANNER_SHEET, new ResourceLocation(Unordinary_Basics.MOD_ID, "entity/banner/" + pattern.getFilename()));
        
        //return Sheets.getBannerMaterial(pattern);
    }
    
    public static Material getBedTexture() {
        return Sheets.BED_TEXTURES[DyeColor.WHITE.getId()];
        
        //return new Material(Sheets.BANNER_SHEET, new ResourceLocation(Unordinary_Basics.MOD_ID, "entity/bed/white"));
    }
    
    private void renderPiece(BedBlockEntity entity, PoseStack pPoseStack, MultiBufferSource pBufferSource, ModelPart pModelPart, Direction pDirection, Material pMaterial, int pPackedLight, int pPackedOverlay, boolean pFoot) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0D, 0.5625D, pFoot ? -1.0D : 0.0D);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        pPoseStack.translate(0.5D, 0.5D, 0.5D);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F + pDirection.toYRot()));
        pPoseStack.translate(-0.5D, -0.5D, -0.5D);
        
        if(entity != null && entity instanceof IPatternable) {
            IPatternable pattern = (IPatternable)entity;
            
            if(pattern.getPatterns().size() > 0) {
                for(int i = 0; i < 17 && i < pattern.getPatterns().size(); i++) {
                    Pair<BannerPattern, DyeColor> pair = pattern.getPatterns().get(i);
                    
                    float[] afloat = pair.getSecond().getTextureDiffuseColors();
                    BannerPattern bannerpattern = pair.getFirst();
                    Material material = ClientUtils.getTexture(bannerpattern);
                    pModelPart.render(pPoseStack, material.buffer(pBufferSource, RenderType::entityNoOutline), pPackedLight, pPackedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);
                }
                
                if(entity != null && entity instanceof IColorable) {
                    IColorable color = (IColorable)entity;
                    
                    float[] afloat = color.getColor().getTextureDiffuseColors();
                    
                    VertexConsumer vertexconsumer = ClientUtils.getBedTexture().buffer(pBufferSource, RenderType::entitySolid);
                    pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);
                } else {
                    VertexConsumer vertexconsumer = ClientUtils.getBedTexture().buffer(pBufferSource, RenderType::entitySolid);
                    
                    pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
                }
                
                pPoseStack.popPose();
                return;
            }
        }
        
        if(entity != null && entity instanceof IColorable) {
            IColorable color = (IColorable)entity;
            
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
