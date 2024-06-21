package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.entity.base.E2JBaseRabbitEntity;

@Environment(EnvType.CLIENT)
public class E2JRabbitRenderer extends MobRenderer<E2JBaseRabbitEntity, RabbitModel<E2JBaseRabbitEntity>> {

    private final String registryName;

    public E2JRabbitRenderer(Context context, String registryName) {
        super(context, new RabbitModel<>(context.bakeLayer(ModelLayers.RABBIT)), 0.3F);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getTextureLocation(E2JBaseRabbitEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("rabbit", registryName);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("rabbit", registryName, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
