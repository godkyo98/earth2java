package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.BoulderingZombieModel;
import slexom.earthtojava.entity.monster.BoulderingZombieEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class BoulderingZombieRenderer extends AbstractZombieRenderer<BoulderingZombieEntity, BoulderingZombieModel<BoulderingZombieEntity>> {

    public BoulderingZombieRenderer(EntityRendererProvider.Context context) {
        this(context, EntityModelLayersInit.BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public BoulderingZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, ModelLayerLocation legsArmorLayer, ModelLayerLocation bodyArmorLayer) {
        super(context, new BoulderingZombieModel<>(context.bakeLayer(layer)), new BoulderingZombieModel<>(context.bakeLayer(legsArmorLayer)), new BoulderingZombieModel<>(context.bakeLayer(bodyArmorLayer)));
    }

    public ResourceLocation getTextureLocation(BoulderingZombieEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("zombie", RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("zombie", RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}