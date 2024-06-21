package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepMeleeAttackGoal extends MeleeAttackGoal {

    final HornedSheepEntity attacker;

    public HornedSheepMeleeAttackGoal(HornedSheepEntity attacker, double speed, boolean pauseWhenMobIdle) {
        super(attacker, speed, pauseWhenMobIdle);
        this.attacker = attacker;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && attacker.isAngry();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && attacker.isAngry();
    }
}
