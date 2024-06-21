package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Color;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepWoolModel;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;

public class RainbowSheepWoolFeatureRenderer extends RenderLayer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_fur.png");
    private final RainbowSheepWoolModel<RainbowSheepEntity> sheepModel;

    public RainbowSheepWoolFeatureRenderer(RenderLayerParent<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> featureRendererContext, EntityModelLoader entityModelLoader) {
        super(featureRendererContext);
        sheepModel = new RainbowSheepWoolModel<>(entityModelLoader.bakeLayer(EntityModelLayersInit.RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER));
    }

    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int packedLightIn, RainbowSheepEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isSheared() && !livingEntity.isInvisible()) {
            float red = 0.9019608F;
            float green = 0.9019608F;
            float blue = 0.9019608F;
            int color = Color.ofRGB(red, green, blue).getColor();
            coloredCutoutModelCopyLayerRender(getParentModel(), sheepModel, TEXTURE, matrixStack, vertexConsumerProvider, packedLightIn, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, color);
        }
    }

}