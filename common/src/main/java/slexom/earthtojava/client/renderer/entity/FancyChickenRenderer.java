package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import slexom.earthtojava.client.renderer.entity.model.FancyChickenModel;
import slexom.earthtojava.entity.passive.FancyChickenEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class FancyChickenRenderer extends MobRenderer<FancyChickenEntity, FancyChickenModel<FancyChickenEntity>> {

    public FancyChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new FancyChickenModel<>(context.bakeLayer(EntityModelLayersInit.FANCY_CHICKEN_ENTITY_MODEL_LAYER)), 0.3F);
    }

    protected float getAnimationProgress(FancyChickenEntity chickenEntity, float f) {
        float g = Mth.lerp(f, chickenEntity.oFlap, chickenEntity.flap);
        float h = Mth.lerp(f, chickenEntity.oFlapSpeed, chickenEntity.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }

    public ResourceLocation getTextureLocation(FancyChickenEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("chicken", RegistryNames.FANCY_CHICKEN_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("chicken", RegistryNames.FANCY_CHICKEN_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }


}
