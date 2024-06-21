package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MelonGolemCarvedMelonFeatureRenderer extends RenderLayer<MelonGolemEntity, SnowGolemModel<MelonGolemEntity>> {
    private final BlockRenderDispatcher blockRenderer;
    private final ItemRenderer itemRenderer;

    public MelonGolemCarvedMelonFeatureRenderer(RenderLayerParent<MelonGolemEntity, SnowGolemModel<MelonGolemEntity>> featureRendererContext, BlockRenderDispatcher blockRenderManager, ItemRenderer itemRenderer) {
        super(featureRendererContext);
        this.blockRenderer = blockRenderManager;
        this.itemRenderer = itemRenderer;
    }

    @SuppressWarnings("deprecation")
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int light, MelonGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isMelonEquipped()) {
            return;
        }
        if (entity.isInvisible()) {
            return;
        }

        boolean hasOutline = Minecraft.getInstance().shouldEntityAppearGlowing(entity);

        matrixStack.pushPose();
        getParentModel().getHead().translateAndRotate(matrixStack);
        matrixStack.translate(0.0D, -0.34375D, 0.0D);
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        matrixStack.scale(0.625F, -0.625F, -0.625F);
        ItemStack head = new ItemStack(BlockInit.CARVED_MELON.get());
        ItemStack headBlink = new ItemStack(BlockInit.MELON_GOLEM_HEAD_BLINK.get());
        ItemStack headShoot = new ItemStack(BlockInit.MELON_GOLEM_HEAD_SHOOT.get());
        ItemStack itemStack;
        if (entity.isShooting()) {
            itemStack = headShoot;
        } else {
            if (entity.blinkManager.getBlinkRemainingTicks() > 0) itemStack = headBlink;
            else itemStack = head;
        }

        if (hasOutline) {
            BlockState blockState = BlockInit.CARVED_MELON.get().defaultBlockState();
            BakedModel bakedModel = blockRenderer.getBlockModel(blockState);
            int n = LivingEntityRenderer.getOverlayCoords(entity, 0.0f);
            matrixStack.translate(-0.5f, -0.5f, -0.5f);
            blockRenderer.getModelRenderer().renderModel(matrixStack.last(), vertexConsumerProvider.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockState, bakedModel, 0.0f, 0.0f, 0.0f, light, n);
        } else {
            itemRenderer.renderStatic(entity, itemStack, ItemDisplayContext.HEAD, false, matrixStack, vertexConsumerProvider, entity.level(), light, LivingEntityRenderer.getOverlay(entity, 0.0f), entity.getId());
        }

        matrixStack.popPose();
    }
}