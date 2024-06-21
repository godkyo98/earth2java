package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;

public class BoneSpiderMeleeAttackGoal extends MeleeAttackGoal {
    public BoneSpiderMeleeAttackGoal(BoneSpiderEntity spider) {
        super(spider, 1.0D, false);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !mob.isVehicle();
    }

    @Override
    public boolean canContinueToUse() {
        float f = mob.getLightLevelDependentMagicValue();
        if (f >= 0.5F && mob.getRandom().nextInt(100) == 0) {
            mob.setTarget(null);
            return false;
        }
        return super.canContinueToUse();
    }

}
