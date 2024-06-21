package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

@Environment(EnvType.CLIENT)
public class E2JMonoColorSheepWoolFeatureRenderer<T extends E2JBaseMonoColorSheepEntity, M extends SheepModel<T>> extends RenderLayer<T, M> {
    private final SheepFurModel<T> sheepModel;

    private final ResourceLocation texture;

    public E2JMonoColorSheepWoolFeatureRenderer(RenderLayerParent<T, M> featureRendererContext, EntityModelSet entityModelLoader, String texture) {
        super(featureRendererContext);
        this.texture = ResourceLocation.parse(texture);
        sheepModel = new SheepFurModel<>(entityModelLoader.bakeLayer(ModelLayers.SHEEP_FUR));

    }

    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int packedLightIn, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isSheared() && !livingEntity.isInvisible()) {
            float red = 0.9019608F;
            float green = 0.9019608F;
            float blue = 0.9019608F;
            int color = Color.ofRGB(red, green, blue).getColor();
            coloredCutoutModelCopyLayerRender(getParentModel(), sheepModel, texture, matrixStack, vertexConsumerProvider, packedLightIn, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, color);
        }
    }
}
