package slexom.earthtojava.entity.passive;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.ai.goal.FancyChickenFleeFromPigEntityGoal;
import slexom.earthtojava.entity.base.E2JBaseChickenEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class FancyChickenEntity extends E2JBaseChickenEntity {

	public FancyChickenEntity(EntityType<FancyChickenEntity> type, Level world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		goalSelector.addGoal(3, new FancyChickenFleeFromPigEntityGoal(this, Pig.class, 6.0F, 1.0D, 1.2D));
	}

	protected SoundEvent getAmbientSound() {
		return SoundEventsInit.FANCY_CHICKEN_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEventsInit.FANCY_CHICKEN_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return SoundEventsInit.FANCY_CHICKEN_DEATH.get();
	}

}
