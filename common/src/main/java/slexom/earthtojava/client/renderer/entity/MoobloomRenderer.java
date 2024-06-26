package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.MoobloomButtercupFeatureRenderer;
import slexom.earthtojava.entity.passive.MoobloomEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class MoobloomRenderer extends MobRenderer<MoobloomEntity, CowModel<MoobloomEntity>> {

    public MoobloomRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.MOOSHROOM)), 0.7F);
        addLayer(new MoobloomButtercupFeatureRenderer<>(this, context.getBlockRenderDispatcher()));
    }

    public ResourceLocation getTextureLocation(MoobloomEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOBLOOM_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOBLOOM_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}