package slexom.earthtojava.utils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import slexom.earthtojava.config.ModConfig;

public record EntitySpawnConfig(
        TagKey<Biome> biomeTag,
        MobCategory category,
        EntityType<?>  entityType,
        boolean shouldSpawn,
        int weight,
        int groupMin,
        int groupMax
) {

    public EntitySpawnConfig(TagKey<Biome> biomeTag, MobCategory category, EntityType<?> entityType, ModConfig.EntityConfig config) {
        this(biomeTag, category, entityType, config.spawn, config.weight, config.groupMin, config.groupMax);
    }


    public MobSpawnSettings.SpawnerData spawnerData(){
        return new MobSpawnSettings.SpawnerData(entityType(), weight(), groupMin(), groupMax());
    }
}
