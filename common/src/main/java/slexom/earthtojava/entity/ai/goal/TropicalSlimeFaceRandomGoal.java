package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeFaceRandomGoal extends Goal {
    private final TropicalSlimeEntity slime;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public TropicalSlimeFaceRandomGoal(TropicalSlimeEntity slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.LOOK));
    }

    public boolean canUse() {
        return slime.getTarget() == null && (slime.onGround() || slime.isInWater() || slime.isInLava() || slime.hasEffect(MobEffects.LEVITATION)) && slime.getMoveControl() instanceof TropicalSlimeMoveControl;
    }

    @Override
    public void tick() {
        if (--nextRandomizeTime <= 0) {
            nextRandomizeTime = 40 + slime.getRandom().nextInt(60);
            chosenDegrees = (float) slime.getRandom().nextInt(360);
        }
        ((TropicalSlimeMoveControl) slime.getMoveControl()).look(chosenDegrees, false);
    }

}
