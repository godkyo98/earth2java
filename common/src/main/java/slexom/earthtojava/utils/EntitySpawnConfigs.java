package slexom.earthtojava.utils;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.MobCategory;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.init.EntityTypesInit;
import slexom.earthtojava.init.ModTags;

import java.util.List;

public class EntitySpawnConfigs {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static final List<EntitySpawnConfig> ENTITY_SPAWN_CONFIGS = List.of(
            new EntitySpawnConfig(ModTags.HAS_ALBINO_COW, MobCategory.CREATURE, EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), config.albinoCow),
            new EntitySpawnConfig(ModTags.HAS_AMBER_CHICKEN, MobCategory.CREATURE, EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), config.amberChicken),
            new EntitySpawnConfig(ModTags.HAS_ASHEN_COW, MobCategory.CREATURE, EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), config.ashenCow),
            new EntitySpawnConfig(ModTags.HAS_BRONZED_CHICKEN, MobCategory.CREATURE, EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), config.bronzedChicken),
            new EntitySpawnConfig(ModTags.HAS_BOLD_STRIPED_RABBIT, MobCategory.CREATURE, EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.get(), config.boldStripedRabbit),
            new EntitySpawnConfig(ModTags.HAS_COOKIE_COW, MobCategory.CREATURE, EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT.get(), config.cookieCow),
            new EntitySpawnConfig(ModTags.HAS_CREAM_COW, MobCategory.CREATURE, EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT.get(), config.creamCow),
            new EntitySpawnConfig(ModTags.HAS_DAIRY_COW, MobCategory.CREATURE, EntityTypesInit.CREAM_COW_REGISTRY_OBJECT.get(), config.dairyCow),
            new EntitySpawnConfig(ModTags.HAS_PINTO_COW, MobCategory.CREATURE, EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT.get(), config.pintoCow),
            new EntitySpawnConfig(ModTags.HAS_CLUCKSHROOM, MobCategory.CREATURE, EntityTypesInit.PINTO_COW_REGISTRY_OBJECT.get(), config.cluckshroom),
            new EntitySpawnConfig(ModTags.HAS_FANCY_CHICKEN, MobCategory.CREATURE, EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), config.fancyChicken),
            new EntitySpawnConfig(ModTags.HAS_FLECKED_SHEEP, MobCategory.CREATURE, EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT.get(), config.fleckedSheep),
            new EntitySpawnConfig(ModTags.HAS_FRECKLED_RABBIT, MobCategory.CREATURE, EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), config.freckledRabbit),
            new EntitySpawnConfig(ModTags.HAS_FUZZY_SHEEP, MobCategory.CREATURE, EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT.get(), config.fuzzySheep),
            new EntitySpawnConfig(ModTags.HAS_GOLD_CRESTED_CHICKEN, MobCategory.CREATURE, EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT.get(), config.goldCrestedChicken),
            new EntitySpawnConfig(ModTags.HAS_HARELEQUIN_RABBIT, MobCategory.CREATURE, EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT.get(), config.harelequinRabbit),
            new EntitySpawnConfig(ModTags.HAS_HORNED_SHEEP, MobCategory.CREATURE, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), config.hornedSheep),
            new EntitySpawnConfig(ModTags.HAS_INKY_SHEEP, MobCategory.CREATURE, EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), config.inkySheep),
            new EntitySpawnConfig(ModTags.HAS_JOLLY_LLAMA, MobCategory.CREATURE, EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), config.jollyLlama),
            new EntitySpawnConfig(ModTags.HAS_JUMBO_RABBIT, MobCategory.CREATURE, EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT.get(), config.jumboRabbit),
            new EntitySpawnConfig(ModTags.HAS_LONG_NOSED_SHEEP, MobCategory.CREATURE, EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT.get(), config.longNosedSheep),
            new EntitySpawnConfig(ModTags.HAS_MIDNIGHT_CHICKEN, MobCategory.CREATURE, EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT.get(), config.midnightChicken),
            new EntitySpawnConfig(ModTags.HAS_MOOBLOOM, MobCategory.CREATURE, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), config.moobloom),
            new EntitySpawnConfig(ModTags.HAS_MOOLIP, MobCategory.CREATURE, EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), config.moolip),
            new EntitySpawnConfig(ModTags.HAS_MOTTLED_PIG, MobCategory.CREATURE, EntityTypesInit.MOOLIP_REGISTRY_OBJECT.get(), config.mottledPig),
            new EntitySpawnConfig(ModTags.HAS_MUDDY_PIG, MobCategory.CREATURE, EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT.get(), config.muddyPig),
            new EntitySpawnConfig(ModTags.HAS_MUDDY_FOOT_RABBIT, MobCategory.CREATURE, EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), config.muddyFootRabbit),
            new EntitySpawnConfig(ModTags.HAS_PALE_PIG, MobCategory.CREATURE, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), config.palePig),
            new EntitySpawnConfig(ModTags.HAS_PATCHED_SHEEP, MobCategory.CREATURE, EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), config.patchedSheep),
            new EntitySpawnConfig(ModTags.HAS_PIEBALD_PIG, MobCategory.CREATURE, EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT.get(), config.piebaldPig),
            new EntitySpawnConfig(ModTags.HAS_PINK_FOOTED_PIG, MobCategory.CREATURE, EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), config.pinkFootedPig),
            new EntitySpawnConfig(ModTags.HAS_RAINBOW_SHEEP, MobCategory.CREATURE, EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT.get(), config.rainbowSheep),
            new EntitySpawnConfig(ModTags.HAS_ROCKY_SHEEP, MobCategory.CREATURE, EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT.get(), config.rockySheep),
            new EntitySpawnConfig(ModTags.HAS_SKEWBALD_CHICKEN, MobCategory.CREATURE, EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), config.skewbaldChicken),
            new EntitySpawnConfig(ModTags.HAS_SOOTY_PIG, MobCategory.CREATURE, EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.get(), config.sootyPig),
            new EntitySpawnConfig(ModTags.HAS_SPOTTED_PIG, MobCategory.CREATURE, EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT.get(), config.spottedPig),
            new EntitySpawnConfig(ModTags.HAS_STORMY_CHICKEN, MobCategory.CREATURE, EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), config.stormyChicken),
            new EntitySpawnConfig(ModTags.HAS_SUNSET_COW, MobCategory.CREATURE, EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), config.sunsetCow),
            new EntitySpawnConfig(ModTags.HAS_UMBRA_COW, MobCategory.CREATURE, EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), config.umbraCow),
            new EntitySpawnConfig(ModTags.HAS_VESTED_RABBIT, MobCategory.CREATURE, EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT.get(), config.vestedRabbit),
            new EntitySpawnConfig(ModTags.HAS_WOOLY_COW, MobCategory.CREATURE, EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), config.woolyCow),
            // Monsters
            new EntitySpawnConfig(ModTags.HAS_BONE_SPIDER, MobCategory.MONSTER, EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), config.amberChicken),
            new EntitySpawnConfig(ModTags.HAS_BOULDERING_ZOMBIE, MobCategory.MONSTER, EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), config.amberChicken),
            new EntitySpawnConfig(ModTags.HAS_LOBBER_ZOMBIE, MobCategory.MONSTER, EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT.get(), config.amberChicken),
            new EntitySpawnConfig(ModTags.HAS_SKELETON_WOLF, MobCategory.MONSTER, EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT.get(), config.amberChicken),
            new EntitySpawnConfig(ModTags.HAS_TROPICAL_SLIME, MobCategory.MONSTER, EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), config.amberChicken),
            new EntitySpawnConfig(ModTags.HAS_VILER_WITCH, MobCategory.MONSTER, EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), config.amberChicken)
    );

}
