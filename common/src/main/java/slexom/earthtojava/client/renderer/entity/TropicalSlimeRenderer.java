package slexom.earthtojava.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class TropicalSlimeRenderer extends MobRenderer<TropicalSlimeEntity, SlimeModel<TropicalSlimeEntity>> {
    private static final int ANIMATION_FRAMES = 48;
    private static final float ANIMATION_TIME = 12.0F;
    private int currentFrame = 0;

    public TropicalSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel<>(context.bakeLayer(ModelLayers.SLIME)), 0.25F);
        addLayer(new SlimeOuterLayer<>(this, context.getModelSet()));
    }

    @Override
    public void render(TropicalSlimeEntity slimeEntity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int light) {
        shadowRadius = 1.0F;
        currentFrame = (int) (Math.floor(slimeEntity.tickCount / ANIMATION_TIME) % ANIMATION_FRAMES);
        super.render(slimeEntity, entityYaw, partialTicks, matrixStack, vertexConsumerProvider, light);
    }

    @Override
    protected void scale(TropicalSlimeEntity slimeEntity, PoseStack matrices, float amount) {
        matrices.scale(0.999F, 0.999F, 0.999F);
        matrices.translate(0.0D, 0.001D, 0.0D);
        float f1 = 4.0F;
        float f2 = Mth.lerp(amount, slimeEntity.lastStretch, slimeEntity.stretch) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrices.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(TropicalSlimeEntity entity) {
        String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/slime/tropical_slime/tropical_slime_anim_{0}.png", (currentFrame + 1));
        return ResourceLocation.parse(frameLocation);
    }
}