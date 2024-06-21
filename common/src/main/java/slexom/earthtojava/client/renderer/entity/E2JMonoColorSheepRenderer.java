package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.feature.E2JMonoColorSheepWoolFeatureRenderer;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JMonoColorSheepRenderer extends MobRenderer<E2JBaseMonoColorSheepEntity, SheepModel<E2JBaseMonoColorSheepEntity>> {

    private final String registryName;

    public E2JMonoColorSheepRenderer(Context context, String registryName) {
        super(context, new SheepModel<>(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.registryName = registryName;
        String woolTexture = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}_fur.png", registryName);
        addLayer(new E2JMonoColorSheepWoolFeatureRenderer<>(this, context.getModelSet(), woolTexture));
    }

    public ResourceLocation getTextureLocation(E2JBaseMonoColorSheepEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("sheep", registryName);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("sheep", registryName, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}