package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.HornedSheepWoolFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.entity.passive.HornedSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class HornedSheepRenderer extends MobRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    public HornedSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new HornedSheepModel<>(context.bakeLayer(EntityModelLayersInit.HORNED_SHEEP_ENTITY_MODEL_LAYER)), 0.7F);
        addLayer(new HornedSheepWoolFeatureRenderer(this, context.getModelSet()));
    }

    public ResourceLocation getTextureLocation(HornedSheepEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("sheep", RegistryNames.HORNED_SHEEP_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("sheep", RegistryNames.HORNED_SHEEP_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}