package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.MoolipPinkDaisyFeatureRenderer;
import slexom.earthtojava.entity.passive.MoolipEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class MoolipRenderer extends MobRenderer<MoolipEntity, CowEntityModel<MoolipEntity>> {

    public MoolipRenderer(EntityRendererProvider.Context context) {
        super(context, new CowEntityModel<>(context.bakeLayer(ModelLayers.MOOSHROOM)), 0.7F);
        addLayer(new MoolipPinkDaisyFeatureRenderer<>(this));
    }

    public ResourceLocation getTextureLocation(MoolipEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOLIP_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOLIP_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}