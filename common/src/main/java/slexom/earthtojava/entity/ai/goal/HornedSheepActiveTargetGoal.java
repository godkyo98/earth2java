package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepActiveTargetGoal extends NearestAttackableTargetGoal<Player> {
    public HornedSheepActiveTargetGoal(HornedSheepEntity sheepEntity) {
        super(sheepEntity, Player.class, true);
    }

    @Override
    public boolean canUse() {
        return canCharge() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        boolean flag = canCharge();
        if (flag && mob.getTarget() != null) {
            return super.canContinueToUse();
        }
        target = null;
        return false;
    }

    private boolean canCharge() {
        HornedSheepEntity sheepEntity = (HornedSheepEntity) mob;

        return sheepEntity.isAngry();
    }
}
