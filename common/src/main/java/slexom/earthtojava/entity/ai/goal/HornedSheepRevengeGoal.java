package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepRevengeGoal extends HurtByTargetGoal {

    public HornedSheepRevengeGoal(HornedSheepEntity mob) {
        super(mob);
    }

    @Override
    protected void alertOther(Mob mob, LivingEntity target) {
        if (mob instanceof HornedSheepEntity && this.mob.hasLineOfSight(target) && ((HornedSheepEntity) mob).setSheepAttacker(target)) {
            mob.setTarget(target);
        }
    }

}
