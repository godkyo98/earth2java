package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.entity.base.E2JBaseCowEntity;

@Environment(EnvType.CLIENT)
public class E2JCowRenderer extends MobRenderer<E2JBaseCowEntity, CowModel<E2JBaseCowEntity>> {

    private final String registryName;

    public E2JCowRenderer(Context context, String registryName) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getTextureLocation(E2JBaseCowEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("cow", registryName);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("cow", registryName, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
