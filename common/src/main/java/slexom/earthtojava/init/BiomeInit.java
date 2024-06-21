package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import slexom.earthtojava.Earth2JavaMod;

import java.util.Objects;
import java.util.function.Predicate;

public final class BiomeInit {

    private BiomeInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {
        Predicate<BiomeModifications.BiomeContext> plainsPredicate = ctx -> Objects.equals(ctx.getKey().get(), Biomes.PLAINS.location());
        BiomeModifications.addProperties(plainsPredicate, (biomeContext, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Earth2JavaMod.MOD_ID, "earth_flowers"))));
    }

}
