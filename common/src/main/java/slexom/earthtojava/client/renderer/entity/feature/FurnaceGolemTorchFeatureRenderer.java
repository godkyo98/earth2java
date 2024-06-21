package slexom.earthtojava.client.renderer.entity.feature;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;

@Environment(EnvType.CLIENT)
public class FurnaceGolemTorchFeatureRenderer extends RenderLayer<FurnaceGolemEntity, IronGolemModel<FurnaceGolemEntity>> {
    private final BlockRenderDispatcher blockRenderer;

    public FurnaceGolemTorchFeatureRenderer(RenderLayerParent<FurnaceGolemEntity, IronGolemModel<FurnaceGolemEntity>> featureRendererContext, BlockRenderDispatcher blockRenderDispatcher) {
        super(featureRendererContext);
        this.blockRenderer = blockRenderDispatcher;
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, FurnaceGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getOfferFlowerTick() != 0) {
            poseStack.pushPose();
            ModelPart modelPart = this.getParentModel().getFlowerHoldingArm();
            modelPart.translateAndRotate(poseStack);
            poseStack.translate(-1.1875F, 1.0625F, -0.9375F);
            poseStack.translate(0.5F, 0.5F, 0.5F);
            float m = 0.5F;
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            this.blockRenderer.renderSingleBlock(Blocks.TORCH.defaultBlockState(), poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
