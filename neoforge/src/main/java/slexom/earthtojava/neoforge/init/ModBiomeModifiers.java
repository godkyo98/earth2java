package slexom.earthtojava.neoforge.init;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.neoforge.worldgen.EntitySpawnBiomeModifier;

public class ModBiomeModifiers {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Earth2JavaMod.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<EntitySpawnBiomeModifier>> BIOME_MODIFIER = BIOME_MODIFIERS.register("entity_spawn", () ->EntitySpawnBiomeModifier.CODEC);
}
