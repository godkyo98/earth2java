package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.MuddyPigModel;
import slexom.earthtojava.entity.passive.MuddyPigEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class MuddyPigRenderer extends MobRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> {

    public MuddyPigRenderer(EntityRendererProvider.Context context) {
        super(context, new MuddyPigModel<>(context.bakeLayer(EntityModelLayersInit.MUDDY_PIG_ENTITY_MODEL_LAYER)), 0.7F);
        addLayer(new SaddleLayer<>(this, new MuddyPigModel<>(context.bakeLayer(ModelLayers.PIG_SADDLE)), new ResourceLocation("textures/entity/pig/pig_saddle.png")));
    }

    public ResourceLocation getTextureLocation(MuddyPigEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME, "blink");
        ResourceLocation textureDried = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME, "dried");
        ResourceLocation textureDriedBlink = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME, "dried_blink");
        boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
        if (entity.isInMuddyState()) {
            return blink ? textureBlink : texture;
        }

        return blink ? textureDriedBlink : textureDried;
    }


}
