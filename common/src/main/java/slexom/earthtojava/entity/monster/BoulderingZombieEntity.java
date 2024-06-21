package slexom.earthtojava.entity.monster;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.ai.pathing.ClimberNavigation;
import slexom.earthtojava.entity.base.E2JBaseZombieEntity;

public class BoulderingZombieEntity extends E2JBaseZombieEntity {
	private static final EntityDataAccessor<Byte> IS_CLIMBING = SynchedEntityData.defineId(BoulderingZombieEntity.class, EntityDataSerializers.BYTE);

	public BoulderingZombieEntity(EntityType<? extends Zombie> entityType, Level world) {
		super(entityType, world);
	}

	protected PathNavigation createNavigation(Level world) {
		return new ClimberNavigation(this, world);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_CLIMBING, (byte) 0);
	}

	public boolean onClimbable() {
		return isClimbing();
	}

	public boolean isClimbing() {
		return (entityData.get(IS_CLIMBING) & 1) != 0;
	}

	public void setClimbing(boolean climbing) {
		byte b = entityData.get(IS_CLIMBING);
		if (climbing) {
			b = (byte) (b | 1);
		} else {
			b &= -2;
		}
		entityData.set(IS_CLIMBING, b);
	}

	public void tick() {
		super.tick();
		if (!level().isClientSide) {
			setClimbing(horizontalCollision);
		}
	}

	@Override
	public boolean isSuppressingSlidingDownLadder() {
		return isClimbing() || super.isSuppressingSlidingDownLadder();
	}
}
