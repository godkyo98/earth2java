package slexom.earthtojava.client.renderer.entity.feature;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.entity.passive.MoobloomEntity;
import slexom.earthtojava.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MoobloomButtercupFeatureRenderer<T extends MoobloomEntity> extends RenderLayer<T, CowModel<T>> {
    private final BlockRenderDispatcher blockRenderer;

    public MoobloomButtercupFeatureRenderer(RenderLayerParent<T, CowModel<T>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, T entity, float f, float g, float h, float j, float k, float l) {
        if (!entity.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean bl = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();
            if (!entity.isInvisible() || bl) {
                BlockState blockState = BlockInit.BUTTERCUP.get().defaultBlockState();
                int coords = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
                BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState);
                poseStack.pushPose();
                poseStack.translate(-0.1D, -0.2D, 0.4D);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.66F, -0.66F, 0.66F);
                poseStack.translate(-0.5D, -0.5D, -0.5D);
                renderButtercupBlock(poseStack, multiBufferSource, light, bl, blockState, coords, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(-0.2D, -0.1D, 0.1D);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.66F, -0.66F, 0.66F);
                poseStack.translate(-0.5D, -0.5D, -0.5D);
                renderButtercupBlock(poseStack, multiBufferSource, light, bl, blockState, coords, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2D, -0.15D, -0.1D);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.66F, -0.66F, 0.66F);
                poseStack.translate(-0.5D, -0.5D, -0.5D);
                renderButtercupBlock(poseStack, multiBufferSource, light, bl, blockState, coords, bakedModel);
                poseStack.popPose();
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.1D, -0.5D, -0.2D);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-0.66F, -0.66F, 0.66F);
                poseStack.translate(-0.5D, -0.5D, -0.5D);
                renderButtercupBlock(poseStack, multiBufferSource, light, bl, blockState, coords, bakedModel);
                poseStack.popPose();
            }
        }
    }

    private void renderButtercupBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, boolean bl, BlockState blockState, int j, BakedModel bakedModel) {
        if (bl) {
            blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, j);
        } else {
            blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, i, j);
        }
    }
}