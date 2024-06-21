package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import slexom.earthtojava.client.renderer.entity.model.LobberZombieModel;
import slexom.earthtojava.entity.monster.LobberZombieEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class LobberZombieRenderer extends AbstractZombieRenderer<LobberZombieEntity, LobberZombieModel<LobberZombieEntity>> {

    public LobberZombieRenderer(EntityRendererProvider.Context context) {
        this(context, EntityModelLayersInit.LOBBER_ZOMBIE_ENTITY_MODEL_LAYER, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public LobberZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, ModelLayerLocation legsArmorLayer, ModelLayerLocation bodyArmorLayer) {
        super(context, new LobberZombieModel<>(context.bakeLayer(layer)), new LobberZombieModel<>(context.bakeLayer(legsArmorLayer)), new LobberZombieModel<>(context.bakeLayer(bodyArmorLayer)));
    }

    public ResourceLocation getTextureLocation(LobberZombieEntity entity) {
        ResourceLocation texture = TextureUtils.getTextureIdentifier("zombie", RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME);
        ResourceLocation textureBlink = TextureUtils.getTextureIdentifier("zombie", RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME, "blink");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}