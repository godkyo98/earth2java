package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.item.BoneShardItem;
import slexom.earthtojava.item.E2JSpawnEggItem;
import slexom.earthtojava.item.FancyFeatherItem;
import slexom.earthtojava.item.HornItem;
import slexom.earthtojava.utils.Utils;

import java.util.List;

public final class ItemInit {

    public static final RegistrySupplier<Item> ALBINO_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> AMBER_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> ASHEN_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> BOLD_STRIPED_RABBIT_SPAWN_EGG;
    public static final RegistrySupplier<Item> BONE_SPIDER_SPAWN_EGG;
    public static final RegistrySupplier<Item> BOULDERING_ZOMBIE_SPAWN_EGG;
    public static final RegistrySupplier<Item> BRONZED_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> CLUCKSHROOM_SPAWN_EGG;
    public static final RegistrySupplier<Item> COOKIE_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> CREAM_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> DAIRY_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> FANCY_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> FLECKED_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> FURNACE_GOLEM_SPAWN_EGG;
    public static final RegistrySupplier<Item> GOLD_CRESTED_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> HARELEQUIN_RABBIT_SPAWN_EGG;
    public static final RegistrySupplier<Item> HORNED_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> INKY_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> JOLLY_LLAMA_SPAWN_EGG;
    public static final RegistrySupplier<Item> JUMBO_RABBIT_SPAWN_EGG;
    public static final RegistrySupplier<Item> LOBBER_ZOMBIE_SPAWN_EGG;
    public static final RegistrySupplier<Item> MELON_GOLEM_SPAWN_EGG;
    public static final RegistrySupplier<Item> MIDNIGHT_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> MOOBLOOM_SPAWN_EGG;
    public static final RegistrySupplier<Item> MOOLIP_SPAWN_EGG;
    public static final RegistrySupplier<Item> MUDDY_FOOT_RABBIT_SPAWN_EGG;
    public static final RegistrySupplier<Item> MUDDY_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> PALE_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> PATCHED_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> PIEBALD_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> PINK_FOOTED_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> PINTO_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> RAINBOW_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> ROCKY_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> SKELETON_WOLF_SPAWN_EGG;
    public static final RegistrySupplier<Item> SKEWBALD_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> SPOTTED_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> STORMY_CHICKEN_SPAWN_EGG;
    public static final RegistrySupplier<Item> SUNSET_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> TROPICAL_SLIME_SPAWN_EGG;
    public static final RegistrySupplier<Item> FUZZY_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> UMBRA_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> VESTED_RABBIT_SPAWN_EGG;
    public static final RegistrySupplier<Item> VILER_WITCH_SPAWN_EGG;
    public static final RegistrySupplier<Item> WOOLY_COW_SPAWN_EGG;
    public static final RegistrySupplier<Item> MOTTLED_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> SOOTY_PIG_SPAWN_EGG;
    public static final RegistrySupplier<Item> FRECKLED_RABBIT_SPAWN_EGG;
    public static final RegistrySupplier<Item> LONG_NOSED_SHEEP_SPAWN_EGG;
    public static final RegistrySupplier<Item> BONE_SHARD;
    public static final RegistrySupplier<Item> FANCY_FEATHER;
    public static final RegistrySupplier<Item> HORN;
    public static final RegistrySupplier<Item> RAINBOW_BED;
    public static final RegistrySupplier<Item> BUTTERCUP;
    public static final RegistrySupplier<Item> PINK_DAISY;
    public static final RegistrySupplier<Item> CARVED_MELON;
    public static final RegistrySupplier<Item> MELON_GOLEM_HEAD_BLINK;
    public static final RegistrySupplier<Item> MELON_GOLEM_HEAD_SHOOT;
    public static final RegistrySupplier<Item> MELON_LANTERN;
    public static final RegistrySupplier<Item> RAINBOW_CARPET;
    public static final RegistrySupplier<Item> RAINBOW_WOOL;
    private static final Item.Properties spawnEggProps = new Item.Properties().arch$tab(Earth2JavaMod.CREATIVE_TAB_SUPPLIER);

    static {
        ALBINO_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.ALBINO_COW_REGISTRY_NAME, EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, 0xdecac3, 0xf0a590);
        COOKIE_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.COOKIE_COW_REGISTRY_NAME, EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, 0x4c5662, 0xdbcdbe);
        AMBER_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.AMBER_CHICKEN_REGISTRY_NAME, EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, 0xd13719, 0xe38a2b);
        ASHEN_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.ASHEN_COW_REGISTRY_NAME, EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, 0x3c3c49, 0x898491);
        BOLD_STRIPED_RABBIT_SPAWN_EGG = registerSpawnEgg(RegistryNames.BOLD_STRIPED_RABBIT_REGISTRY_NAME, EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, 0x030303, 0xa4632b);
        BONE_SPIDER_SPAWN_EGG = registerSpawnEgg(RegistryNames.BONE_SPIDER_REGISTRY_NAME, EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, 0x200d16, 0xd6e7e5);
        BOULDERING_ZOMBIE_SPAWN_EGG = registerSpawnEgg(RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME, EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, 0x3a4046, 0x492320);
        BRONZED_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.BRONZED_CHICKEN_REGISTRY_NAME, EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, 0x040f30, 0xb2492a);
        CLUCKSHROOM_SPAWN_EGG = registerSpawnEgg(RegistryNames.CLUCKSHROOM_REGISTRY_NAME, EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, 0xef0000, 0xffffee);
        CREAM_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.CREAM_COW_REGISTRY_NAME, EntityTypesInit.CREAM_COW_REGISTRY_OBJECT, 0xfcbf66, 0xfff2cd);
        DAIRY_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.DAIRY_COW_REGISTRY_NAME, EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT, 0xf6f4f9, 0x2e2e2d);
        FANCY_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.FANCY_CHICKEN_REGISTRY_NAME, EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, 0xf7b035, 0x478e8b);
        FLECKED_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.FLECKED_SHEEP_REGISTRY_NAME, EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, 0x2c1e17, 0x907666);
        FRECKLED_RABBIT_SPAWN_EGG = registerSpawnEgg(RegistryNames.FRECKLED_RABBIT_REGISTRY_NAME, EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT, 0xf6f1e8, 0xab9e8d);
        FUZZY_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.FUZZY_SHEEP_REGISTRY_NAME, EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT, 0xf8f6f5, 0x3d312b);
        FURNACE_GOLEM_SPAWN_EGG = registerSpawnEgg(RegistryNames.FURNACE_GOLEM_REGISTRY_NAME, EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, 0x56585a, 0xff5501);
        GOLD_CRESTED_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.GOLD_CRESTED_CHICKEN_REGISTRY_NAME, EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT, 0xd1cec2, 0xe5aa40);
        HARELEQUIN_RABBIT_SPAWN_EGG = registerSpawnEgg(RegistryNames.HARELEQUIN_RABBIT_REGISTRY_NAME, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, 0x1d1b1a, 0xb09984);
        HORNED_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.HORNED_SHEEP_REGISTRY_NAME, EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, 0xececec, 0x291811);
        INKY_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.INKY_SHEEP_REGISTRY_NAME, EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, 0x181716, 0x8a7564);
        JOLLY_LLAMA_SPAWN_EGG = registerSpawnEgg(RegistryNames.JOLLY_LLAMA_REGISTRY_NAME, EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, 0x5f3425, 0x3b7e3e);
        JUMBO_RABBIT_SPAWN_EGG = registerSpawnEgg(RegistryNames.JUMBO_RABBIT_REGISTRY_NAME, EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, 0xb57766, 0xf7d1c0);
        LOBBER_ZOMBIE_SPAWN_EGG = registerSpawnEgg(RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME, EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, 0x8e9c7e, 0x607c17);
        LONG_NOSED_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.LONG_NOSED_SHEEP_REGISTRY_NAME, EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT, 0x2c231e, 0x9e8061);
        MELON_GOLEM_SPAWN_EGG = registerSpawnEgg(RegistryNames.MELON_GOLEM_REGISTRY_NAME, EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, 0xeeffff, 0x52811c);
        MIDNIGHT_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.MIDNIGHT_CHICKEN_REGISTRY_NAME, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, 0x06050B, 0x17225a);
        MOOBLOOM_SPAWN_EGG = registerSpawnEgg(RegistryNames.MOOBLOOM_REGISTRY_NAME, EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, 0xfaca00, 0xf7edc1);
        MOOLIP_SPAWN_EGG = registerSpawnEgg(RegistryNames.MOOLIP_REGISTRY_NAME, EntityTypesInit.MOOLIP_REGISTRY_OBJECT, 0xea88be, 0xf9e7eb);
        MOTTLED_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.MOTTLED_PIG_REGISTRY_NAME, EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT, 0x50403c, 0x806a68);
        MUDDY_FOOT_RABBIT_SPAWN_EGG = registerSpawnEgg(RegistryNames.MUDDY_FOOT_RABBIT_REGISTRY_NAME, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, 0xe5e0dd, 0x463832);
        MUDDY_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.MUDDY_PIG_REGISTRY_NAME, EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, 0xe6918b, 0x573621);
        PALE_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.PALE_PIG_REGISTRY_NAME, EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, 0xd3a0a0, 0xead3d3);
        PATCHED_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.PATCHED_SHEEP_REGISTRY_NAME, EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, 0xf3f0ee, 0x3b4054);
        PIEBALD_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.PIEBALD_PIG_REGISTRY_NAME, EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, 0xd7c0a9, 0x9b4628);
        PINK_FOOTED_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.PINK_FOOTED_PIG_REGISTRY_NAME, EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, 0x514246, 0xb39da2);
        PINTO_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.PINTO_COW_REGISTRY_NAME, EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, 0xc16921, 0xd8c4ad);
        RAINBOW_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME, EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, 0xffffff, 0xffffff);
        ROCKY_SHEEP_SPAWN_EGG = registerSpawnEgg(RegistryNames.ROCKY_SHEEP_REGISTRY_NAME, EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, 0xa69f9b, 0xe9d0bd);
        SKELETON_WOLF_SPAWN_EGG = registerSpawnEgg(RegistryNames.SKELETON_WOLF_REGISTRY_NAME, EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, 0xededed, 0xbababa);
        SKEWBALD_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.SKEWBALD_CHICKEN_REGISTRY_NAME, EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT, 0xffe8cf, 0x353028);
        SOOTY_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.SOOTY_PIG_REGISTRY_NAME, EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT, 0x3f3c4c, 0xefcbc1);
        SPOTTED_PIG_SPAWN_EGG = registerSpawnEgg(RegistryNames.SPOTTED_PIG_REGISTRY_NAME, EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, 0xedd4d1, 0x413938);
        STORMY_CHICKEN_SPAWN_EGG = registerSpawnEgg(RegistryNames.STORMY_CHICKEN_REGISTRY_NAME, EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, 0x3e2525, 0xc0c0c0);
        SUNSET_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.SUNSET_COW_REGISTRY_NAME, EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, 0x993d0d, 0x171514);
        TROPICAL_SLIME_SPAWN_EGG = registerSpawnEgg(RegistryNames.TROPICAL_SLIME_REGISTRY_NAME, EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, 0x0e496e, 0x8ed3ff);
        UMBRA_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.UMBRA_COW_REGISTRY_NAME, EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT, 0x090c1a, 0x2e2c44);
        VESTED_RABBIT_SPAWN_EGG = registerSpawnEgg(RegistryNames.VESTED_RABBIT_REGISTRY_NAME, EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, 0xdedede, 0x747474);
        VILER_WITCH_SPAWN_EGG = registerSpawnEgg(RegistryNames.VILER_WITCH_REGISTRY_NAME, EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, 0x0d0e19, 0xa09280);
        WOOLY_COW_SPAWN_EGG = registerSpawnEgg(RegistryNames.WOOLY_COW_REGISTRY_NAME, EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, 0xcc3300, 0xff9933);

        BUTTERCUP = registerBlockItem("buttercup", BlockInit.BUTTERCUP);
        PINK_DAISY = registerBlockItem("pink_daisy", BlockInit.PINK_DAISY);
        CARVED_MELON = registerBlockItem("carved_melon", BlockInit.CARVED_MELON);
        MELON_GOLEM_HEAD_BLINK = registerBlockItem("melon_golem_blink", BlockInit.MELON_GOLEM_HEAD_BLINK);
        MELON_GOLEM_HEAD_SHOOT = registerBlockItem("melon_golem_shoot", BlockInit.MELON_GOLEM_HEAD_SHOOT);
        MELON_LANTERN = registerBlockItem("melon_lantern", BlockInit.MELON_LANTERN);
        RAINBOW_CARPET = registerBlockItem("rainbow_carpet", BlockInit.RAINBOW_CARPET);
        RAINBOW_WOOL = registerBlockItem("rainbow_wool", BlockInit.RAINBOW_WOOL);

        HORN = Earth2JavaMod.ITEM_REGISTRAR.register(Utils.modResourceLocationOf("horn"), () -> new HornItem(new Item.Settings().arch$tab(Earth2JavaMod.CREATIVE_TAB_SUPPLIER).maxCount(64)));
        FANCY_FEATHER = Earth2JavaMod.ITEM_REGISTRAR.register(Utils.modResourceLocationOf("fancy_feather"), () -> new FancyFeatherItem(new Item.Settings().arch$tab(Earth2JavaMod.CREATIVE_TAB_SUPPLIER).maxCount(64)));
        BONE_SHARD = Earth2JavaMod.ITEM_REGISTRAR.register(Utils.modResourceLocationOf("bone_shard"), () -> new BoneShardItem(new Item.Settings().arch$tab(Earth2JavaMod.CREATIVE_TAB_SUPPLIER).maxCount(16)));
        RAINBOW_BED = Earth2JavaMod.ITEM_REGISTRAR.register(Utils.modResourceLocationOf("rainbow_bed"), () -> new BedItem(BlockInit.RAINBOW_BED.get(), (new Item.Settings()).maxCount(1).arch$tab(ItemGroups.BUILDING_BLOCKS)));

    }

    private ItemInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {

    }

    private static RegistrySupplier<Item> registerSpawnEgg(String registryName, RegistrySupplier<? extends EntityType<? extends Mob>> entityType, int primaryColor, int secondaryColor) {
        return Earth2JavaMod.ITEM_REGISTRAR.register(Utils.modResourceLocationOf(registryName + "_spawn_egg"), () -> new E2JSpawnEggItem(entityType, primaryColor, secondaryColor, spawnEggProps));
    }

    private static RegistrySupplier<Item> registerBlockItem(String registryName, RegistrySupplier<Block> block) {
        return Earth2JavaMod.ITEM_REGISTRAR.register(Utils.modResourceLocationOf(registryName), () -> new BlockItem(block.get(), new Item.Settings().arch$tab(Earth2JavaMod.CREATIVE_TAB_SUPPLIER)) {
            @Environment(EnvType.CLIENT)
            public void appendTooltip(ItemStack stack, @Nullable Level world, List<Text> tooltip, TooltipContext context) {
                Utils.appendE2JTooltip(getTranslationKey() + ".desc", tooltip);
            }
        });
    }

}
