package slexom.earthtojava.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.FurnaceGolemFlamesFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.feature.FurnaceGolemTorchFeatureRenderer;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class FurnaceGolemRenderer extends MobRenderer<FurnaceGolemEntity, IronGolemModel<FurnaceGolemEntity>> {

    public FurnaceGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new IronGolemModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
        addLayer(new FurnaceGolemFlamesFeatureRenderer(this));
        addLayer(new FurnaceGolemTorchFeatureRenderer(this,context.getBlockRenderDispatcher()));
    }

    @Override
    protected void setupRotations(FurnaceGolemEntity furnaceGolemEntity, PoseStack matrices, float animationProgress, float bodyYaw, float tickDelta, float i) {
        super.setupRotations(furnaceGolemEntity, matrices, animationProgress, bodyYaw, tickDelta, i);
        if ((double) furnaceGolemEntity.walkAnimation.speed() < 0.01) {
            return;
        }

        float f1 = furnaceGolemEntity.walkAnimation.position(tickDelta) + 6.0F;
        float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
        matrices.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
    }

    public ResourceLocation getTextureLocation(FurnaceGolemEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("iron_golem", RegistryNames.FURNACE_GOLEM_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("iron_golem", RegistryNames.FURNACE_GOLEM_REGISTRY_NAME, "blink");
        ResourceLocation textureAngry = TextureUtils.getTextureIdentifier("iron_golem", RegistryNames.FURNACE_GOLEM_REGISTRY_NAME, "angry");
        if (entity.isAngry()) {
            return textureAngry;
        }
        if (entity.blinkManager.getBlinkRemainingTicks() > 0) return textureBlink;
        return texture;
    }

}
