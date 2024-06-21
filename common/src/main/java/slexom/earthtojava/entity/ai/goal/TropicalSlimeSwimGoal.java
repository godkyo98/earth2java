/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeSwimGoal extends Goal {
    private final TropicalSlimeEntity slime;

    public TropicalSlimeSwimGoal(TropicalSlimeEntity slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        slime.getNavigation().setCanFloat(true);
    }

    @Override
    public boolean canUse() {
        return (slime.isInWater() || slime.isInLava()) && slime.getMoveControl() instanceof TropicalSlimeMoveControl;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (slime.getRandom().nextFloat() < 0.8f) {
            slime.getJumpControl().jump();
        }

        MoveControl moveControl = slime.getMoveControl();
        if (moveControl instanceof TropicalSlimeMoveControl tropicalSlimeMoveControl) {
            tropicalSlimeMoveControl.move(1.2);
        }
    }
}

