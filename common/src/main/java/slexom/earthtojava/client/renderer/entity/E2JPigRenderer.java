package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.entity.base.E2JBasePigEntity;

@Environment(EnvType.CLIENT)
public class E2JPigRenderer extends MobRenderer<E2JBasePigEntity, PigModel<E2JBasePigEntity>> {

    private final String registryName;

    public E2JPigRenderer(Context context, String registryName) {
        super(context, new PigModel<>(context.bakeLayer(ModelLayers.PIG)), 0.7F);
        addLayer(new SaddleLayer<>(this, new PigModel<>(context.bakeLayer(ModelLayers.PIG_SADDLE)), ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")));
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getTextureLocation(E2JBasePigEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("pig", registryName);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("pig", registryName, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
