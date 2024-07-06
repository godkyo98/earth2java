package slexom.earthtojava.neoforge.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import slexom.earthtojava.neoforge.init.ModBiomeModifiers;
import slexom.earthtojava.utils.EntitySpawnConfig;
import slexom.earthtojava.utils.EntitySpawnConfigs;

public class EntitySpawnBiomeModifier implements BiomeModifier {
    public static final MapCodec<EntitySpawnBiomeModifier> CODEC = MapCodec.unit(EntitySpawnBiomeModifier::new);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            MobSpawnSettingsBuilder mobSpawnSettings = builder.getMobSpawnSettings();
            for (EntitySpawnConfig mobSpawnConfig : EntitySpawnConfigs.ENTITY_SPAWN_CONFIGS) {
                if (biome.is(mobSpawnConfig.biomeTag()) && mobSpawnConfig.shouldSpawn()) {
                    mobSpawnSettings.getSpawner(mobSpawnConfig.category()).add(mobSpawnConfig.spawnerData());
                }
            }
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return ModBiomeModifiers.BIOME_MODIFIER.get();
    }
}
