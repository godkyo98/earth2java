package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.RainbowSheepWoolFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class RainbowSheepRenderer extends MobRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
    public RainbowSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new RainbowSheepModel<>(context.bakeLayer(EntityModelLayersInit.RAINBOW_SHEEP_ENTITY_MODEL_LAYER)), 0.7F);
        addLayer(new RainbowSheepWoolFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public ResourceLocation getTextureLocation(RainbowSheepEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("sheep", RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("sheep", RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
