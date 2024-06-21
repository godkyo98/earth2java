package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.entity.base.*;
import slexom.earthtojava.entity.monster.*;
import slexom.earthtojava.entity.passive.*;
import slexom.earthtojava.entity.projectile.BoneShardEntity;
import slexom.earthtojava.entity.projectile.MelonSeedProjectileEntity;
import slexom.earthtojava.entity.projectile.RottenFleshProjectileEntity;
import slexom.earthtojava.utils.Utils;

public final class EntityTypesInit {

	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> ALBINO_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> AMBER_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> ASHEN_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> BOLD_STRIPED_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<BoneShardEntity>> BONE_SHARD_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<BoneSpiderEntity>> BONE_SPIDER_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<BoulderingZombieEntity>> BOULDERING_ZOMBIE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> BRONZED_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<CluckshroomEntity>> CLUCKSHROOM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> COOKIE_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> CREAM_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> DAIRY_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<FancyChickenEntity>> FANCY_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> FLECKED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> FRECKLED_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<FurnaceGolemEntity>> FURNACE_GOLEM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> FUZZY_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> HARELEQUIN_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<HornedSheepEntity>> HORNED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> INKY_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<JollyLlamaEntity>> JOLLY_LLAMA_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<JumboRabbitEntity>> JUMBO_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<LobberZombieEntity>> LOBBER_ZOMBIE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> LONG_NOSED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MelonGolemEntity>> MELON_GOLEM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MelonSeedProjectileEntity>> MELON_SEED_PROJECTILE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> MIDNIGHT_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MoobloomEntity>> MOOBLOOM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MoolipEntity>> MOOLIP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> MOTTLED_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> MUDDY_FOOT_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MuddyPigEntity>> MUDDY_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PALE_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> PATCHED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PIEBALD_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PINK_FOOTED_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> PINTO_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<RainbowSheepEntity>> RAINBOW_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> ROCKY_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<RottenFleshProjectileEntity>> ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<SkeletonWolfEntity>> SKELETON_WOLF_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> SKEWBALD_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> SOOTY_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> SPOTTED_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> STORMY_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> SUNSET_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<TropicalSlimeEntity>> TROPICAL_SLIME_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<UmbraCowEntity>> UMBRA_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> VESTED_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<VilerWitchEntity>> VILER_WITCH_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<WoolyCowEntity>> WOOLY_COW_REGISTRY_OBJECT;

	static {
		ASHEN_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.ASHEN_COW_REGISTRY_NAME);
		ALBINO_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.ALBINO_COW_REGISTRY_NAME);
		COOKIE_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.COOKIE_COW_REGISTRY_NAME);
		CREAM_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.CREAM_COW_REGISTRY_NAME);
		DAIRY_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.DAIRY_COW_REGISTRY_NAME);
		PINTO_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.PINTO_COW_REGISTRY_NAME);
		SUNSET_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.SUNSET_COW_REGISTRY_NAME);

		AMBER_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.AMBER_CHICKEN_REGISTRY_NAME);
		BRONZED_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.BRONZED_CHICKEN_REGISTRY_NAME);
		GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.GOLD_CRESTED_CHICKEN_REGISTRY_NAME);
		MIDNIGHT_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.MIDNIGHT_CHICKEN_REGISTRY_NAME);
		SKEWBALD_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.SKEWBALD_CHICKEN_REGISTRY_NAME);
		STORMY_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.STORMY_CHICKEN_REGISTRY_NAME);

		MOTTLED_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.MOTTLED_PIG_REGISTRY_NAME);
		PALE_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.PALE_PIG_REGISTRY_NAME);
		PIEBALD_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.PIEBALD_PIG_REGISTRY_NAME);
		PINK_FOOTED_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.PINK_FOOTED_PIG_REGISTRY_NAME);
		SOOTY_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.SOOTY_PIG_REGISTRY_NAME);
		SPOTTED_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.SPOTTED_PIG_REGISTRY_NAME);

		BOLD_STRIPED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.BOLD_STRIPED_RABBIT_REGISTRY_NAME);
		FRECKLED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.FRECKLED_RABBIT_REGISTRY_NAME);
		HARELEQUIN_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.HARELEQUIN_RABBIT_REGISTRY_NAME);
		MUDDY_FOOT_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.MUDDY_FOOT_RABBIT_REGISTRY_NAME);
		VESTED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.VESTED_RABBIT_REGISTRY_NAME);

		FLECKED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.FLECKED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BROWN_WOOL));
		FUZZY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.FUZZY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.WHITE_WOOL));
		INKY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.INKY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BLACK_WOOL));
		LONG_NOSED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.LONG_NOSED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BROWN_WOOL));
		PATCHED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.PATCHED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.WHITE_WOOL));
		ROCKY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.ROCKY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.GRAY_WOOL));

		BONE_SHARD_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.BONE_SHARD_REGISTRY_NAME), () -> EntityType.Builder.<BoneShardEntity>of(BoneShardEntity::new, MobCategory.MISC)
				.sized(0.25F, 0.25F)
				.clientTrackingRange(4)
				.updateInterval(10)
				.build(RegistryNames.BONE_SHARD_REGISTRY_NAME));
		BONE_SPIDER_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.BONE_SPIDER_REGISTRY_NAME), () -> EntityType.Builder.of(BoneSpiderEntity::new, MobCategory.MONSTER)
				.sized(0.6F, 0.7F)
				.clientTrackingRange(8)
				.build(RegistryNames.BONE_SPIDER_REGISTRY_NAME));
		BOULDERING_ZOMBIE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME), () -> EntityType.Builder.of(BoulderingZombieEntity::new, MobCategory.MONSTER)
				.sized(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
				.clientTrackingRange(8)
				.build(RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME));
		CLUCKSHROOM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.CLUCKSHROOM_REGISTRY_NAME), () -> EntityType.Builder.of(CluckshroomEntity::new, MobCategory.CREATURE)
				.sized(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.CLUCKSHROOM_REGISTRY_NAME));
		FANCY_CHICKEN_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.FANCY_CHICKEN_REGISTRY_NAME), () -> EntityType.Builder.of(FancyChickenEntity::new, MobCategory.CREATURE)
				.sized(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.FANCY_CHICKEN_REGISTRY_NAME));
		FURNACE_GOLEM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.FURNACE_GOLEM_REGISTRY_NAME), () -> EntityType.Builder.of(FurnaceGolemEntity::new, MobCategory.MISC)
				.sized(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
				.fireImmune()
				.clientTrackingRange(10)
				.build(RegistryNames.FURNACE_GOLEM_REGISTRY_NAME));
		HORNED_SHEEP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.HORNED_SHEEP_REGISTRY_NAME), () -> EntityType.Builder.of(HornedSheepEntity::new, MobCategory.CREATURE)
				.sized(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.HORNED_SHEEP_REGISTRY_NAME));
		JOLLY_LLAMA_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.JOLLY_LLAMA_REGISTRY_NAME), () -> EntityType.Builder.of(JollyLlamaEntity::new, MobCategory.CREATURE)
				.sized(EntityType.LLAMA.getWidth(), EntityType.LLAMA.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.JOLLY_LLAMA_REGISTRY_NAME));
		JUMBO_RABBIT_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.JUMBO_RABBIT_REGISTRY_NAME), () -> EntityType.Builder.of(JumboRabbitEntity::new, MobCategory.CREATURE)
				.sized(0.8F, 1.0F)
				.clientTrackingRange(8)
				.build(RegistryNames.JUMBO_RABBIT_REGISTRY_NAME));
		LOBBER_ZOMBIE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME), () -> EntityType.Builder.of(LobberZombieEntity::new, MobCategory.MONSTER)
				.sized(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
				.clientTrackingRange(8)
				.build(RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME));
		MELON_GOLEM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.MELON_GOLEM_REGISTRY_NAME), () -> EntityType.Builder.of(MelonGolemEntity::new, MobCategory.MISC)
				.sized(EntityType.SNOW_GOLEM.getWidth(), EntityType.SNOW_GOLEM.getHeight())
				.immuneTo(Blocks.POWDER_SNOW)
				.clientTrackingRange(8)
				.build(RegistryNames.MELON_GOLEM_REGISTRY_NAME));
		MELON_SEED_PROJECTILE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.MELON_SEED_PROJECTILE_REGISTRY_NAME), () -> EntityType.Builder.<MelonSeedProjectileEntity>of(MelonSeedProjectileEntity::new, MobCategory.MISC)
				.sized(0.25F, 0.25F)
				.clientTrackingRange(4)
				.updateInterval(10)
				.build(RegistryNames.MELON_SEED_PROJECTILE_REGISTRY_NAME));
		MOOBLOOM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.MOOBLOOM_REGISTRY_NAME), () -> EntityType.Builder.of(MoobloomEntity::new, MobCategory.CREATURE)
				.sized(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.MOOBLOOM_REGISTRY_NAME));
		MOOLIP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.MOOLIP_REGISTRY_NAME), () -> EntityType.Builder.of(MoolipEntity::new, MobCategory.CREATURE)
				.sized(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.MOOLIP_REGISTRY_NAME));
		MUDDY_PIG_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.MUDDY_PIG_REGISTRY_NAME), () -> EntityType.Builder.of(MuddyPigEntity::new, MobCategory.CREATURE)
				.sized(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.MUDDY_PIG_REGISTRY_NAME));
		RAINBOW_SHEEP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME), () -> EntityType.Builder.of(RainbowSheepEntity::new, MobCategory.CREATURE)
				.sized(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME));
		ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME), () -> EntityType.Builder.<RottenFleshProjectileEntity>of(RottenFleshProjectileEntity::new, MobCategory.MISC)
				.sized(0.25F, 0.25F)
				.clientTrackingRange(4)
				.updateInterval(10)
				.build(RegistryNames.ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME));
		SKELETON_WOLF_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.SKELETON_WOLF_REGISTRY_NAME), () -> EntityType.Builder.of(SkeletonWolfEntity::new, MobCategory.MONSTER)
				.sized(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.SKELETON_WOLF_REGISTRY_NAME));
		TROPICAL_SLIME_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.TROPICAL_SLIME_REGISTRY_NAME), () -> EntityType.Builder.of(TropicalSlimeEntity::new, MobCategory.CREATURE)
				.sized(2.04F, 2.04F)
				.fireImmune()
				.clientTrackingRange(10)
				.build(RegistryNames.TROPICAL_SLIME_REGISTRY_NAME));
		UMBRA_COW_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.UMBRA_COW_REGISTRY_NAME), () -> EntityType.Builder.of(UmbraCowEntity::new, MobCategory.CREATURE)
				.sized(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.UMBRA_COW_REGISTRY_NAME));
		VILER_WITCH_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.VILER_WITCH_REGISTRY_NAME), () -> EntityType.Builder.of(VilerWitchEntity::new, MobCategory.MONSTER)
				.sized(EntityType.WITCH.getWidth(), EntityType.WITCH.getHeight())
				.clientTrackingRange(8)
				.build(RegistryNames.VILER_WITCH_REGISTRY_NAME));
		WOOLY_COW_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(RegistryNames.WOOLY_COW_REGISTRY_NAME), () -> EntityType.Builder.of(WoolyCowEntity::new, MobCategory.CREATURE)
				.sized(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.clientTrackingRange(10)
				.build(RegistryNames.WOOLY_COW_REGISTRY_NAME));
	}

	private EntityTypesInit() {
		throw new IllegalStateException("Utility class");
	}

	public static void init() {
	}

	private static RegistrySupplier<EntityType<E2JBaseChickenEntity>> registerBaseChickenEntity(String registryName) {
		EntityType.Builder<E2JBaseChickenEntity> entityType = EntityType.Builder.of(E2JBaseChickenEntity::new, MobCategory.CREATURE)
				.sized(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
				.clientTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBaseCowEntity>> registerBaseCowEntity(String registryName) {
		EntityType.Builder<E2JBaseCowEntity> entityType = EntityType.Builder.of(E2JBaseCowEntity::new, MobCategory.CREATURE)
				.sized(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.clientTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBasePigEntity>> registerBasePigEntity(String registryName) {
		EntityType.Builder<E2JBasePigEntity> entityType = EntityType.Builder.of(E2JBasePigEntity::new, MobCategory.CREATURE)
				.sized(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
				.clientTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBaseRabbitEntity>> registerBaseRabbitEntity(String registryName) {
		EntityType.Builder<E2JBaseRabbitEntity> entityType = EntityType.Builder.of(E2JBaseRabbitEntity::new, MobCategory.CREATURE)
				.sized(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
				.clientTrackingRange(8);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> registerBaseMonoColorSheepEntity(String registryName, ItemStack wool) {
		EntityType.Builder<E2JBaseMonoColorSheepEntity> entityType = EntityType.Builder.<E2JBaseMonoColorSheepEntity>of((type, world) -> new E2JBaseMonoColorSheepEntity(type, world, wool), MobCategory.CREATURE)
				.sized(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
				.clientTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modResourceLocationOf(registryName), () -> entityType.build(registryName));
	}


}


