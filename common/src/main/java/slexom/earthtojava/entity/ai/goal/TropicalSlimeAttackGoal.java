package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeAttackGoal extends Goal {
    private final TropicalSlimeEntity slime;
    private int growTieredTimer;

    public TropicalSlimeAttackGoal(TropicalSlimeEntity slime) {
        this.slime = slime;
        setFlags(EnumSet.of(Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = slime.getTarget();
        if (livingentity == null) {
            return false;
        }
        if (!livingentity.isAlive()) {
            return false;
        }
        return (!(livingentity instanceof Player) || !((Player) livingentity).getAbilities().invulnerable) && slime.getMoveControl() instanceof TropicalSlimeMoveControl;
    }

    @Override
    public void start() {
        growTieredTimer = 300;
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity livingentity = slime.getTarget();
        if (livingentity == null) {
            return false;
        }
        if (!livingentity.isAlive()) {
            return false;
        }
        if (livingentity instanceof Player && ((Player) livingentity).getAbilities().invulnerable) {
            return false;
        }
        return --growTieredTimer > 0;
    }

    @Override
    public void tick() {
        slime.lookAt(slime.getTarget(), 10.0F, 10.0F);
        ((TropicalSlimeMoveControl) slime.getMoveControl()).look(slime.getYRot(), slime.canAttack());
    }
}
