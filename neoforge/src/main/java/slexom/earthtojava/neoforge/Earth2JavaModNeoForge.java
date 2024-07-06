package slexom.earthtojava.neoforge;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.neoforge.init.ModBiomeModifiers;

@Mod(Earth2JavaMod.MOD_ID)
public class Earth2JavaModNeoForge {

    public Earth2JavaModNeoForge(IEventBus eventBus) {
        Earth2JavaMod.initialize();
        ModBiomeModifiers.BIOME_MODIFIERS.register(eventBus);
        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BlockInit.onPostInit();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.BUTTERCUP.getId(), BlockInit.POTTED_BUTTERCUP);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.PINK_DAISY.getId(), BlockInit.POTTED_PINK_DAISY);
        });
    }

}