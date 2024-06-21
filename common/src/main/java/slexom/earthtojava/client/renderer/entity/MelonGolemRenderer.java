package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.MelonGolemCarvedMelonFeatureRenderer;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

@Environment(EnvType.CLIENT)
public class MelonGolemRenderer extends MobRenderer<MelonGolemEntity, SnowGolemEntityModel<MelonGolemEntity>> {
    private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snow_golem.png");

    public MelonGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemEntityModel<>(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        addLayer(new MelonGolemCarvedMelonFeatureRenderer(this, context.getBlockRenderManager(), context.getItemRenderer()));
    }

    public ResourceLocation getTextureLocation(MelonGolemEntity entity) {
        return SNOW_MAN_TEXTURES;
    }

}