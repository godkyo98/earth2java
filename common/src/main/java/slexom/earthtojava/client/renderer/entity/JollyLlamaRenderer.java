package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.JollyLlamaModel;
import slexom.earthtojava.entity.passive.JollyLlamaEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class JollyLlamaRenderer extends MobRenderer<JollyLlamaEntity, JollyLlamaModel> {

    public JollyLlamaRenderer(EntityRendererProvider.Context context) {
        super(context, new JollyLlamaModel(context.bakeLayer(EntityModelLayersInit.JOLLY_LLAMA_ENTITY_MODEL_LAYER)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(JollyLlamaEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("llama", RegistryNames.JOLLY_LLAMA_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("llama", RegistryNames.JOLLY_LLAMA_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
