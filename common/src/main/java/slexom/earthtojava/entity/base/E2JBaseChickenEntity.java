package slexom.earthtojava.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBaseChickenEntity extends Chicken {

	public final BlinkManager blinkManager;
	private final EntityVariantManager<E2JBaseChickenEntity> variantManager;

	public E2JBaseChickenEntity(EntityType<? extends Chicken> type, Level worldIn) {
		super(type, worldIn);
		blinkManager = new BlinkManager();
		variantManager = new EntityVariantManager<>();
	}

	public static AttributeSupplier.Builder createChickenAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		blinkManager.tickBlink();
	}

	@Override
	public E2JBaseChickenEntity getBreedOffspring(ServerLevel world, AgeableMob other) {
		return variantManager.getChild(this, other).create(world);
	}

}
