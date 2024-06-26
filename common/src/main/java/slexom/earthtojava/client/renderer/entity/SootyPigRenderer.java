package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.SootyPigModel;
import slexom.earthtojava.entity.base.E2JBasePigEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class SootyPigRenderer extends MobRenderer<E2JBasePigEntity, SootyPigModel> {

    public SootyPigRenderer(Context context) {
        super(context, new SootyPigModel(context.bakeLayer(EntityModelLayersInit.SOOTY_PIG_ENTITY_MODEL_LAYER)), 0.7F);
        addLayer(new SaddleLayer<>(this, new SootyPigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)), ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(E2JBasePigEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("pig", RegistryNames.SOOTY_PIG_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("pig", RegistryNames.SOOTY_PIG_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
