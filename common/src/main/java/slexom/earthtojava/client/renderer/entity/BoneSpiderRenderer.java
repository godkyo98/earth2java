package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.BoneSpiderEyesFeatureRenderer;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class BoneSpiderRenderer extends MobRenderer<BoneSpiderEntity, SpiderModel<BoneSpiderEntity>> {

    public BoneSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.8F);
        addLayer(new BoneSpiderEyesFeatureRenderer<>(this));
    }

    protected float getLyingAngle(BoneSpiderEntity spiderEntity) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(BoneSpiderEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("spider", RegistryNames.BONE_SPIDER_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("spider", RegistryNames.BONE_SPIDER_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
