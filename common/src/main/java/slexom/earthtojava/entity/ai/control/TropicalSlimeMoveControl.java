package slexom.earthtojava.entity.ai.control;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

public class TropicalSlimeMoveControl extends MoveControl {
    private final TropicalSlimeEntity slime;
    private float yRot;
    private int ticksUntilJump;
    private boolean isAggressive;

    public TropicalSlimeMoveControl(TropicalSlimeEntity slime) {
        super(slime);
        this.slime = slime;
        this.yRot = 180.0F * slime.getYRot() / 3.1415927F;
    }

    public void look(float targetYaw, boolean isAggressive) {
        this.yRot = targetYaw;
        this.isAggressive = isAggressive;
    }

    public void move(double speed) {
        this.speedModifier = speed;
        this.operation = Operation.MOVE_TO;
    }

    @Override
    public void tick() {
        this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
        this.mob.yHeadRot = this.mob.getYRot();
        this.mob.yBodyRot = this.mob.getYRot();
        if (operation != Operation.MOVE_TO) {
            mob.setZza(0.0F);
            return;
        }
        operation = Operation.WAIT;
        if (mob.onGround()) {
            mob.setSpeed((float) (speedModifier * mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            if (ticksUntilJump-- <= 0) {
                ticksUntilJump = slime.getJumpDelay();
                if (isAggressive) {
                    ticksUntilJump /= 3;
                }
                slime.getJumpControl().jump();
                if (slime.doPlayJumpSound()) {
                    slime.playSound(slime.getJumpSound(), 1.0F, slime.getJumpSoundPitch());
                }
            } else {
                slime.xxa = 0.0F;
                slime.zza = 0.0F;
                mob.setSpeed(0.0F);
            }
        } else {
            mob.setSpeed((float) (speedModifier * mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
        }

    }

}
