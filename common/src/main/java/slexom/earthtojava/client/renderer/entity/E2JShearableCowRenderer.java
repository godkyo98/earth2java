package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.E2JShearableCowModel;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

@Environment(EnvType.CLIENT)
public class E2JShearableCowRenderer extends MobRenderer<E2JBaseShearableCowEntity, E2JShearableCowModel<E2JBaseShearableCowEntity>> {

    private final String registryName;

    public E2JShearableCowRenderer(EntityRendererProvider.Context context, String registryName) {
        super(context, new E2JShearableCowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.registryName = registryName;
    }

    public ResourceLocation getTextureLocation(E2JBaseShearableCowEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("cow", registryName);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("cow", registryName, "blink");
        ResourceLocation textureSheared = TextureUtils.getTextureIdentifier("cow", registryName, "sheared");
        ResourceLocation textureShearedBlink = TextureUtils.getTextureIdentifier("cow", registryName, "sheared_blink");
        boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
        if (entity.isSheared()) {
            if (blink) return textureShearedBlink;
            return textureSheared;
        }
        return blink ? textureBlink : texture;
    }
}