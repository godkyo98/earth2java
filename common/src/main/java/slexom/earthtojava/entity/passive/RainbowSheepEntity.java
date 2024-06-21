package slexom.earthtojava.entity.passive;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.SoundEventsInit;

public class RainbowSheepEntity extends E2JBaseMonoColorSheepEntity {

	public RainbowSheepEntity(EntityType<? extends RainbowSheepEntity> type, Level world) {
		super(type, world, new ItemStack(BlockInit.RAINBOW_WOOL.get()));
	}

	protected SoundEvent getAmbientSound() {
		return SoundEventsInit.RAINBOW_SHEEP_AMBIENT.get();
	}

	protected SoundEvent getDeathSound() {
		return SoundEventsInit.RAINBOW_SHEEP_DEATH.get();
	}

}
