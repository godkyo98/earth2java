package slexom.earthtojava.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.VilerWitchHeldItemFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.VilerWitchModel;
import slexom.earthtojava.entity.monster.VilerWitchEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class VilerWitchRenderer extends MobRenderer<VilerWitchEntity, VilerWitchModel<VilerWitchEntity>> {


    public VilerWitchRenderer(EntityRendererProvider.Context context) {
        super(context, new VilerWitchModel<>(context.bakeLayer(EntityModelLayersInit.VILER_WITCH_ENTITY_MODEL_LAYER)), 0.5F);
        addLayer(new VilerWitchHeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    public void render(VilerWitchEntity witchEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        model.setLiftingNose(!witchEntity.getMainHandStack().isEmpty());
        super.render(witchEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public ResourceLocation getTextureLocation(VilerWitchEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("witch", RegistryNames.VILER_WITCH_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("witch", RegistryNames.VILER_WITCH_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

    protected void scale(VilerWitchEntity witchEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
