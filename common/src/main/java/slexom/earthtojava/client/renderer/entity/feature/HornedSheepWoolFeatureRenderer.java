package slexom.earthtojava.client.renderer.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import slexom.earthtojava.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

@Environment(EnvType.CLIENT)
public class HornedSheepWoolFeatureRenderer extends RenderLayer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("textures/entity/sheep/sheep_fur.png");
    private final SheepFurModel<HornedSheepEntity> model;

    public HornedSheepWoolFeatureRenderer(RenderLayerParent<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> featureRendererContext, EntityModelSet entityModelLoader) {
        super(featureRendererContext);
        model = new SheepFurModel<>(entityModelLoader.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, HornedSheepEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isSheared() && !entity.isInvisible()) {
            int color;
            if (entity.hasCustomName() && "jeb_".equals(entity.getName().getString())) {
                int i = entity.tickCount / 25 + entity.getId();
                int colors = DyeColor.values().length;
                int k = i % colors;
                int l = (i + 1) % colors;
                float f3 = ((float) (entity.tickCount % 25) + partialTicks) / 25.0F;
                int s = Sheep.getColor(DyeColor.byId(k));
                int t = Sheep.getColor(DyeColor.byId(l));
                color = FastColor.ARGB32.lerp(f3, s, t);
            } else {
                color = HornedSheepEntity.getColor(entity.getColor());

            }

            coloredCutoutModelCopyLayerRender(getParentModel(), model, TEXTURE, poseStack, multiBufferSource, light, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, color);
        }
    }
}

