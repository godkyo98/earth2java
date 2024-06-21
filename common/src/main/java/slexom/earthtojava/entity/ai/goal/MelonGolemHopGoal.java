package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.MelonGolemMoveControl;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

import java.util.EnumSet;

public class MelonGolemHopGoal extends Goal {
    private final MelonGolemEntity melonGolem;

    public MelonGolemHopGoal(MelonGolemEntity entity) {
        melonGolem = entity;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    public boolean canUse() {
        return !melonGolem.isPassenger();
    }

    @Override
    public void tick() {
        ((MelonGolemMoveControl) melonGolem.getMoveControl()).move(1.0D);
    }
}
