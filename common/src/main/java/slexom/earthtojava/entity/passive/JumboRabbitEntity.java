package slexom.earthtojava.entity.passive;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.base.E2JBaseRabbitEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class JumboRabbitEntity extends E2JBaseRabbitEntity {

	public JumboRabbitEntity(EntityType<JumboRabbitEntity> type, Level world) {
		super(type, world);
	}

	@Override
	protected SoundEvent getJumpSound() {
		return SoundEventsInit.JUMBO_RABBIT_JUMP.get();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEventsInit.JUMBO_RABBIT_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEventsInit.JUMBO_RABBIT_HURT.get();
	}

}
