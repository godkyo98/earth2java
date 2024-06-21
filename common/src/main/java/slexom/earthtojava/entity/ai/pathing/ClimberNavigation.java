package slexom.earthtojava.entity.ai.pathing;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

public class ClimberNavigation extends GroundPathNavigation {
	private BlockPos targetPos;

	public ClimberNavigation(Mob mobEntity, Level world) {
		super(mobEntity, world);
	}

	@Override
	public Path createPath(BlockPos target, int distance) {
		targetPos = target;
		return super.createPath(target, distance);
	}

	@Override
	public Path createPath(Entity entity, int distance) {
		targetPos = entity.blockPosition();
		return super.createPath(entity, distance);
	}

	@Override
	public boolean moveTo(Entity entity, double speed) {
		Path path = createPath(entity, 0);
		if (path != null) {
			return moveTo(path, speed);
		}
		targetPos = entity.blockPosition();
		speedModifier = speed;
		return true;
	}

	@Override
	public void tick() {
		if (isDone()) {
			if (targetPos != null) {
				if (!targetPos.closerToCenterThan(mob.position(), Math.max(mob.getBbWidth(), 1.0D)) && ((mob.getY() <= targetPos.getY()) || !(BlockPos.containing(targetPos.getX(), mob.getY(), targetPos.getZ())).closerToCenterThan(mob.position(), Math.max(mob.getBbWidth(), 1.0D)))) {
					mob.getMoveControl().setWantedPosition(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speedModifier);
				} else {
					targetPos = null;
				}
			}
			return;
		}
		super.tick();
	}
}