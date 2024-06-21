package slexom.earthtojava.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBaseRabbitEntity extends Rabbit {

	public final BlinkManager blinkManager;
	private final EntityVariantManager<E2JBaseRabbitEntity> variantManager;

	public E2JBaseRabbitEntity(EntityType<? extends Rabbit> type, Level worldIn) {
		super(type, worldIn);
		blinkManager = new BlinkManager();
		variantManager = new EntityVariantManager<>();
	}

	public static AttributeSupplier.Builder createRabbitAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		blinkManager.tickBlink();
	}

	@Override
	public E2JBaseRabbitEntity getBreedOffspring(ServerLevel world, AgeableMob other) {
		return variantManager.getChild(this, other).create(world);
	}

}
