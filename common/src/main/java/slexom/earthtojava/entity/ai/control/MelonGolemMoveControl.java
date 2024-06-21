package slexom.earthtojava.entity.ai.control;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

public class MelonGolemMoveControl extends MoveControl {
	private final MelonGolemEntity melonGolem;
	private float yRot;
	private int jumpDelay;
	private boolean isAggressive;

	public MelonGolemMoveControl(MelonGolemEntity entity) {
		super(entity);
		melonGolem = entity;
		yRot = 180.0F * entity.getYRot() / (float) Math.PI;
	}

	public void setDirection(float yRot, boolean isAggressive) {
		this.yRot = yRot;
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
		if (this.operation != MoveControl.Operation.MOVE_TO) {
			this.mob.setZza(0.0F);
		} else {
			this.operation = Operation.WAIT;
			if (this.mob.onGround()) {
				this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
				if (this.jumpDelay-- <= 0) {
					this.jumpDelay = this.melonGolem.getJumpDelay();
					if (this.isAggressive) {
						this.jumpDelay /= 3;
					}

					this.melonGolem.getJumpControl().jump();
				} else {
					this.melonGolem.xxa = 0.0F;
					this.melonGolem.zza = 0.0F;
					this.mob.setSpeed(0.0F);
				}
			} else {
				this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
			}

		}
	}
}
