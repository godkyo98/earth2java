package slexom.earthtojava.neoforge;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.events.ModEvents;
import slexom.earthtojava.init.*;

@Mod(Earth2JavaMod.MOD_ID)
@EventBusSubscriber(modid = Earth2JavaMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Earth2JavaModNeoForge {

	public Earth2JavaModNeoForge(IEventBus modBus) {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		ModEvents.init();
		SoundEventsInit.init();
		BlockInit.init();
		EntityTypesInit.init();
		EntityAttributeInit.init();
		ItemInit.init();
		BlockEntityTypeInit.init();
		modBus.addListener(Earth2JavaModNeoForge::setup);
	}

	private static void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Earth2JavaMod.onPostInit();
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.BUTTERCUP.getId(), BlockInit.POTTED_BUTTERCUP);
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.PINK_DAISY.getId(), BlockInit.POTTED_PINK_DAISY);
		});
	}

}