package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomMushroomFeatureRenderer<T extends CluckshroomEntity> extends RenderLayer<T, CluckshroomModel<T>> {
    private final BlockRenderDispatcher blockRenderer;

    public CluckshroomMushroomFeatureRenderer(RenderLayerParent<T, CluckshroomModel<T>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;

    }

    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean bl = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();
            if (!entity.isInvisible() || bl) {
                BlockState blockState = Blocks.RED_MUSHROOM.defaultBlockState();
                int i = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
                BakedModel bakedModel = blockRenderer.getBlockModel(blockState);
                matrixStack.pushPose();
                matrixStack.translate(-0.1F, 0.6F, 0.05D);
                matrixStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                matrixStack.scale(-0.5F, -0.5F, 0.5F);
                matrixStack.translate(-0.5D, -0.5D, -0.5D);
                renderMushroomBlock(matrixStack, vertexConsumerProvider, i, bl, blockState, i, bakedModel);
                matrixStack.popPose();
                matrixStack.pushPose();
                getParentModel().getHead().translateAndRotate(matrixStack);
                matrixStack.translate(0.05F, -0.6F, 0.0D);
                matrixStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                matrixStack.scale(-0.5F, -0.5F, 0.5F);
                matrixStack.translate(-0.5D, -0.5D, -0.5D);
                renderMushroomBlock(matrixStack, vertexConsumerProvider, i, bl, blockState, i, bakedModel);
                matrixStack.popPose();
            }
        }
    }

    private void renderMushroomBlock(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, boolean bl, BlockState blockState, int j, BakedModel bakedModel) {
        if (bl) {
            this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, j);
        } else {
            this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, i, j);
        }

    }
}