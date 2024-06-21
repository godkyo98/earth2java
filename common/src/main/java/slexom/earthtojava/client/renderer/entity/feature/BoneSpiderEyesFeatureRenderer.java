package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;

@Environment(EnvType.CLIENT)
public class BoneSpiderEyesFeatureRenderer<T extends BoneSpiderEntity, M extends SpiderModel<T>> extends EyesLayer<T, M> {

    public BoneSpiderEyesFeatureRenderer(RenderLayerParent<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    public RenderType renderType() {
        ResourceLocation identifier = ResourceLocation.parse("earthtojavamobs:textures/mobs/spider/bone_spider/bone_spider_eyes.png");
        return RenderType.eyes(identifier);
    }
}
