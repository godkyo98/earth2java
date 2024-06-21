package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import slexom.earthtojava.client.renderer.entity.model.VilerWitchModel;
import slexom.earthtojava.entity.monster.VilerWitchEntity;

@Environment(EnvType.CLIENT)
public class VilerWitchHeldItemFeatureRenderer<T extends VilerWitchEntity> extends CrossedArmsItemLayer<T, VilerWitchModel<T>> {
    public VilerWitchHeldItemFeatureRenderer(RenderLayerParent<T, VilerWitchModel<T>> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent, itemInHandRenderer);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getMainHandItem();
        matrixStack.pushPose();
        if (itemStack.is(Items.POTION)) {
            getParentModel().getHead().translateAndRotate(matrixStack);
            getParentModel().getNose().translateAndRotate(matrixStack);
            matrixStack.translate(0.0625f, 0.25f, 0.0f);
            matrixStack.mulPose(Axis.ZP.rotationDegrees(180.0f));
            matrixStack.mulPose(Axis.XP.rotationDegrees(140.0f));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(10.0f));
            matrixStack.translate(0.0f, -0.4f, 0.4f);
        }
        super.render(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l);
        matrixStack.popPose();
    }
}