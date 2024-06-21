package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeFloatGoal extends Goal {
    private final TropicalSlimeEntity slime;

    public TropicalSlimeFloatGoal(TropicalSlimeEntity slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        this.slime.getNavigation().setCanSwim(true);
    }

    public boolean canUse() {
        return (slime.isTouchingWater() || slime.isInLava()) && slime.getMoveControl() instanceof TropicalSlimeMoveControl;
    }

    @Override
    public void tick() {
        if (slime.getRandom().nextFloat() < 0.8F) {
            slime.getJumpControl().setActive();
        }
        ((TropicalSlimeMoveControl) slime.getMoveControl()).move(1.2D);
    }
}
