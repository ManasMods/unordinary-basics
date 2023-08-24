package com.github.manasmods.unordinary_basics.entity.client;

import com.github.manasmods.unordinary_basics.entity.MasterSwordWardenEntity;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WardenEmissiveLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MasterSwordWardenRenderer extends MobRenderer<MasterSwordWardenEntity, WardenModel<MasterSwordWardenEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/warden/warden.png");
    private static final ResourceLocation BIOLUMINESCENT_LAYER_TEXTURE = new ResourceLocation("textures/entity/warden/warden_bioluminescent_layer.png");
    private static final ResourceLocation HEART_TEXTURE = new ResourceLocation("textures/entity/warden/warden_heart.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = new ResourceLocation("textures/entity/warden/warden_pulsating_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = new ResourceLocation("textures/entity/warden/warden_pulsating_spots_2.png");

    public MasterSwordWardenRenderer(EntityRendererProvider.Context context) {
        super(context, new WardenModel<>(context.bakeLayer(ModelLayers.WARDEN)), 0.9F);
        this.addLayer(new MasterSwordLayer(this, context.getItemInHandRenderer()));
        this.addLayer(new WardenEmissiveLayer<>(this, BIOLUMINESCENT_LAYER_TEXTURE, (warden, v, v1)
                -> 1.0F, WardenModel::getBioluminescentLayerModelParts));
        this.addLayer(new WardenEmissiveLayer<>(this, PULSATING_SPOTS_TEXTURE_1, (warden, v, v1)
                -> Math.max(0.0F, Mth.cos(v1 * 0.045F) * 0.25F), WardenModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new WardenEmissiveLayer<>(this, PULSATING_SPOTS_TEXTURE_2, (warden, v, v1)
                -> Math.max(0.0F, Mth.cos(v1 * 0.045F + (float) Math.PI) * 0.25F), WardenModel::getPulsatingSpotsLayerModelParts));
        this.addLayer(new WardenEmissiveLayer<>(this, TEXTURE, (warden, v, v1)
                -> warden.getTendrilAnimation(v), WardenModel::getTendrilsLayerModelParts));
        this.addLayer(new WardenEmissiveLayer<>(this, HEART_TEXTURE, (warden, v, v1)
                -> warden.getHeartAnimation(v), WardenModel::getHeartLayerModelParts));
    }

    public ResourceLocation getTextureLocation(MasterSwordWardenEntity p_234791_) {
        return TEXTURE;
    }
}
