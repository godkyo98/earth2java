package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class FurnaceGolemFlamesFeatureRenderer extends RenderLayer<FurnaceGolemEntity, IronGolemModel<FurnaceGolemEntity>> {

    private static final int ANIMATION_FRAMES = 6;
    private static final float ANIMATION_TIME = 6.0F;
    private int currentFrame = 0;

    public FurnaceGolemFlamesFeatureRenderer(RenderLayerParent<FurnaceGolemEntity, IronGolemModel<FurnaceGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, FurnaceGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isInvisible() && entity.isAngry()) {
            currentFrame = (int) (Math.floor(entity.tickCount / ANIMATION_TIME) % ANIMATION_FRAMES);
            String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_flames_layer_anim_{0}.png", (currentFrame + 1));
            ResourceLocation identifier = ResourceLocation.parse(frameLocation);
            renderColoredCutoutModel(getParentModel(), identifier, matrices, vertexConsumers, light, entity, 0xFFFFFFFF);
        }
    }
}