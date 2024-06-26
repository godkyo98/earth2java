package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import slexom.earthtojava.client.renderer.entity.feature.CluckshroomMushroomFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.entity.passive.CluckshroomEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class CluckshroomRenderer extends MobRenderer<CluckshroomEntity, CluckshroomModel<CluckshroomEntity>> {

    public CluckshroomRenderer(Context context) {
        super(context, new CluckshroomModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
        addLayer(new CluckshroomMushroomFeatureRenderer<>(this, context.getBlockRenderDispatcher()));
    }

    @Override
    protected float getBob(CluckshroomEntity chickenEntity, float f) {
        float g = Mth.lerp(f, chickenEntity.oFlap, chickenEntity.flap);
        float h = Mth.lerp(f, chickenEntity.oFlapSpeed, chickenEntity.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }

    @Override
    public ResourceLocation getTextureLocation(CluckshroomEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("chicken", RegistryNames.CLUCKSHROOM_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("chicken", RegistryNames.CLUCKSHROOM_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
