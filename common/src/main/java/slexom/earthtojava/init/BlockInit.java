package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.utils.Utils;

public final class BlockInit {

    public static final RegistrySupplier<Block> BUTTERCUP;
    public static final RegistrySupplier<Block> CARVED_MELON;
    public static final RegistrySupplier<Block> MELON_GOLEM_HEAD_BLINK;
    public static final RegistrySupplier<Block> MELON_GOLEM_HEAD_SHOOT;
    public static final RegistrySupplier<Block> MELON_LANTERN;
    public static final RegistrySupplier<Block> PINK_DAISY;
    public static final RegistrySupplier<FlowerPotBlock> POTTED_BUTTERCUP;
    public static final RegistrySupplier<FlowerPotBlock> POTTED_PINK_DAISY;
    public static final RegistrySupplier<Block> RAINBOW_BED;
    public static final RegistrySupplier<Block> RAINBOW_CARPET;
    public static final RegistrySupplier<Block> RAINBOW_WOOL;
    private static final BlockBehaviour.Properties FLOWERS_SETTINGS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY);
    private static final BlockBehaviour.Properties CARVED_MELON_SETTINGS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).isValidSpawn((state, world, pos, type) -> true).pushReaction(PushReaction.DESTROY);
    private static final BlockBehaviour.Properties POTTED_FLOWER_SETTINGS = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);


    static {
        BUTTERCUP = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("buttercup"), () -> new FlowerBlock(MobEffects.JUMP, 5, FLOWERS_SETTINGS));
        PINK_DAISY = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("pink_daisy"), () -> new FlowerBlock(MobEffects.JUMP, 5, FLOWERS_SETTINGS));
        CARVED_MELON = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("carved_melon"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS));
        MELON_GOLEM_HEAD_BLINK = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("melon_golem_blink"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS));
        MELON_GOLEM_HEAD_SHOOT = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("melon_golem_shoot"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS));
        MELON_LANTERN = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("melon_lantern"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS.lightLevel(state -> 15)));
        POTTED_BUTTERCUP = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("potted_buttercup"), () -> new FlowerPotBlock(BUTTERCUP.get(), POTTED_FLOWER_SETTINGS));
        POTTED_PINK_DAISY = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("potted_pink_daisy"), () -> new FlowerPotBlock(PINK_DAISY.get(), POTTED_FLOWER_SETTINGS));
        RAINBOW_BED = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("rainbow_bed"), () -> new RainbowBedBlock(DyeColor.WHITE, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));
        RAINBOW_CARPET = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("rainbow_carpet"), () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.1F).sound(SoundType.WOOL).ignitedByLava()));
        RAINBOW_WOOL = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modResourceLocationOf("rainbow_wool"), () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));
    }

    private BlockInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {

    }

    public static void onPostInit() {
        registerCompostable();
        registerFlammable();
    }

    public static void registerCompostable() {
        ComposterBlock.COMPOSTABLES.put(BUTTERCUP.get().asItem(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(PINK_DAISY.get().asItem(), 0.65F);
    }

    public static void registerFlammable() {
        flammableBlock(BUTTERCUP.get(), 60, 100);
        flammableBlock(PINK_DAISY.get(), 60, 100);
        flammableBlock(RAINBOW_CARPET.get(), 60, 20);
        flammableBlock(RAINBOW_WOOL.get(), 30, 60);
    }

    private static void flammableBlock(Block block, int encouragement, int flammability) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(block, encouragement, flammability);
    }


}
