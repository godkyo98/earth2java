package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class MelonGolemProjectileAttackGoal extends RangedAttackGoal {
    private final MelonGolemEntity melonGolemEntity;

    public MelonGolemProjectileAttackGoal(MelonGolemEntity mob, double mobSpeed, int intervalTicks, float maxShootRange) {
        super(mob, mobSpeed, intervalTicks, maxShootRange);
        melonGolemEntity = mob;
    }

    public MelonGolemProjectileAttackGoal(MelonGolemEntity mob, double mobSpeed, int minIntervalTicks, int maxIntervalTicks, float maxShootRange) {
        super(mob, mobSpeed, minIntervalTicks, maxIntervalTicks, maxShootRange);
        melonGolemEntity = mob;
    }

    @Override
    public void start() {
        super.start();
        melonGolemEntity.playSound(SoundEventsInit.MELON_GOLEM_AGGRO.get(), 1.0F, 1.0F);
    }

}
