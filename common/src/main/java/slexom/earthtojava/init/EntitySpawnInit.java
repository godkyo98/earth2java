package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import slexom.earthtojava.mixins.SpawnRestrictionAccessor;
import slexom.earthtojava.utils.EntitySpawnConfig;
import slexom.earthtojava.utils.EntitySpawnConfigs;

public final class EntitySpawnInit {

    private EntitySpawnInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {
        initSpawnRestriction();
        registerEntities();
    }

    public static void initSpawnRestriction() {
        for (EntitySpawnConfig mobSpawnConfig : EntitySpawnConfigs.ENTITY_SPAWN_CONFIGS) {
            EntityType<?> rawEntityType = mobSpawnConfig.entityType();

            if (mobSpawnConfig.category().equals(MobCategory.CREATURE)) {
                if (Animal.class.isAssignableFrom(rawEntityType.getBaseClass())) {
                    @SuppressWarnings("unchecked")
                    EntityType<? extends Animal> entityType = (EntityType<? extends Animal>) rawEntityType;
                    SpawnRestrictionAccessor.callRegister(entityType, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
                }
            }

            if (mobSpawnConfig.category().equals(MobCategory.MONSTER)) {
                if (Monster.class.isAssignableFrom(rawEntityType.getBaseClass())) {
                    @SuppressWarnings("unchecked")
                    EntityType<? extends Monster> entityType = (EntityType<? extends Monster>) rawEntityType;
                    SpawnRestrictionAccessor.callRegister(entityType, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
                }
            }
        }
    }

    private static void registerEntities() {
        for (EntitySpawnConfig mobSpawnConfig : EntitySpawnConfigs.ENTITY_SPAWN_CONFIGS) {
            if (mobSpawnConfig.shouldSpawn()) {
                BiomeModifications.addProperties(mobSpawnConfig.predicate(), (biomeContext, mutable) -> mutable.getSpawnProperties().addSpawn(mobSpawnConfig.category(), mobSpawnConfig.spawnerData()));
            }
        }
    }

}
