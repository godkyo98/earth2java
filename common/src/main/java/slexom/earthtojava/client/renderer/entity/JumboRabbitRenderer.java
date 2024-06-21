package slexom.earthtojava.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.JumboRabbitModel;
import slexom.earthtojava.entity.passive.JumboRabbitEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

public class JumboRabbitRenderer extends MobRenderer<JumboRabbitEntity, JumboRabbitModel<JumboRabbitEntity>> {


    public JumboRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new JumboRabbitModel<>(context.bakeLayer(EntityModelLayersInit.JUMBO_RABBIT_ENTITY_MODEL_LAYER)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(JumboRabbitEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("rabbit", RegistryNames.JUMBO_RABBIT_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("rabbit", RegistryNames.JUMBO_RABBIT_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}