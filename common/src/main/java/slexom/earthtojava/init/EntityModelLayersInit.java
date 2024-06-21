package slexom.earthtojava.init;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import slexom.earthtojava.client.renderer.block.entity.RainbowBedBlockEntityRenderer;
import slexom.earthtojava.client.renderer.entity.model.*;
import slexom.earthtojava.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class EntityModelLayersInit {
    public static final ModelLayerLocation BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation FANCY_CHICKEN_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation HORNED_SHEEP_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation JOLLY_LLAMA_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation JUMBO_RABBIT_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation LOBBER_ZOMBIE_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation MUDDY_PIG_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation RAINBOW_BED_FOOT_MODEL_LAYER;
    public static final ModelLayerLocation RAINBOW_BED_HEAD_MODEL_LAYER;
    public static final ModelLayerLocation RAINBOW_SHEEP_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation SKELETON_WOLF_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation SOOTY_PIG_ENTITY_MODEL_LAYER;
    public static final ModelLayerLocation VILER_WITCH_ENTITY_MODEL_LAYER;
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> E2J_MODEL_LAYERS = new HashMap<>();

    static {
        FANCY_CHICKEN_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.FANCY_CHICKEN_REGISTRY_NAME, FancyChickenModel.getTexturedModelData());
        HORNED_SHEEP_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.HORNED_SHEEP_REGISTRY_NAME, HornedSheepModel.getTexturedModelData());
        JOLLY_LLAMA_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.JOLLY_LLAMA_REGISTRY_NAME, JollyLlamaModel.getTexturedModelData(CubeDeformation.NONE));
        JUMBO_RABBIT_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.JUMBO_RABBIT_REGISTRY_NAME, JumboRabbitModel.getTexturedModelData());
        MUDDY_PIG_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.MUDDY_PIG_REGISTRY_NAME, MuddyPigModel.getTexturedModelData(CubeDeformation.NONE));
        RAINBOW_SHEEP_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME, RainbowSheepModel.getTexturedModelData());
        RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME + "_fur", RainbowSheepWoolModel.getTexturedModelData());
        SKELETON_WOLF_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.SKELETON_WOLF_REGISTRY_NAME, SkeletonWolfModel.getTexturedModelData());
        BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME, BoulderingZombieModel.getTexturedModelData());
        LOBBER_ZOMBIE_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME, LobberZombieModel.getTexturedModelData());
        VILER_WITCH_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.VILER_WITCH_REGISTRY_NAME, VilerWitchModel.getTexturedModelData());
        SOOTY_PIG_ENTITY_MODEL_LAYER = registerEntityModelLayer(RegistryNames.SOOTY_PIG_REGISTRY_NAME, SootyPigModel.getTexturedModelData(CubeDeformation.NONE));
        RAINBOW_BED_HEAD_MODEL_LAYER = registerEntityModelLayer("rainbow_bed_head", RainbowBedBlockEntityRenderer.createHeadLayer());
        RAINBOW_BED_FOOT_MODEL_LAYER = registerEntityModelLayer("rainbow_bed_foot", RainbowBedBlockEntityRenderer.createFootLayer());
    }

    private EntityModelLayersInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {

    }

    public static ModelLayerLocation registerEntityModelLayer(String registryName, LayerDefinition modelPart) {
        ModelLayerLocation entityModelLayer = new ModelLayerLocation(Utils.modResourceLocationOf(registryName), "main");
        E2J_MODEL_LAYERS.put(entityModelLayer, () -> modelPart);
        EntityModelLayerRegistry.register(entityModelLayer, () -> modelPart);

        return entityModelLayer;
    }
}
