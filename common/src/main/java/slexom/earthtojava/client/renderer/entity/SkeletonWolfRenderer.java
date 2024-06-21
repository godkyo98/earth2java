package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.SkeletonWolfModel;
import slexom.earthtojava.entity.monster.SkeletonWolfEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class SkeletonWolfRenderer extends MobRenderer<SkeletonWolfEntity, SkeletonWolfModel<SkeletonWolfEntity>> {

    public SkeletonWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonWolfModel<>(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
    }

    protected float getAnimationProgress(SkeletonWolfEntity wolfEntity, float f) {
        return wolfEntity.getTailAngle();
    }

    public ResourceLocation getTextureLocation(SkeletonWolfEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("wolf", RegistryNames.SKELETON_WOLF_REGISTRY_NAME);
        ResourceLocation textureAngry = TextureUtils.getTextureIdentifier("wolf", RegistryNames.SKELETON_WOLF_REGISTRY_NAME, "angry");
        return entity.isAngry() ? textureAngry : texture;
    }

}
