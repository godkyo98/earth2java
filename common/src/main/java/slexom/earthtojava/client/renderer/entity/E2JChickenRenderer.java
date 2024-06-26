package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import slexom.earthtojava.entity.base.E2JBaseChickenEntity;

@Environment(EnvType.CLIENT)
public class E2JChickenRenderer extends MobRenderer<E2JBaseChickenEntity, ChickenModel<E2JBaseChickenEntity>> {
    private final String registryName;

    public E2JChickenRenderer(Context context, String registryName) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
        this.registryName = registryName;
    }

    protected float getBob(E2JBaseChickenEntity chickenEntity, float f) {
        float g = Mth.lerp(f, chickenEntity.oFlap, chickenEntity.flap);
        float h = Mth.lerp(f, chickenEntity.oFlapSpeed, chickenEntity.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }

    public ResourceLocation getTextureLocation(E2JBaseChickenEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("chicken", registryName);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("chicken", registryName, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }


}
