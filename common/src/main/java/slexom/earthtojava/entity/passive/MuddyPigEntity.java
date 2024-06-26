package slexom.earthtojava.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import slexom.earthtojava.entity.ai.goal.MuddyPigMoveToTargetGoal;
import slexom.earthtojava.entity.base.E2JBasePigEntity;


public class MuddyPigEntity extends E2JBasePigEntity {

	private static final EntityDataAccessor<Boolean> MUDDY_STATE = SynchedEntityData.defineId(MuddyPigEntity.class, EntityDataSerializers.BOOLEAN);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
	private int outOfMud = 0;
	private int finallyInMud = 0;

	private boolean isShaking;
	private float timeMuddyPigIsShaking;
	private float prevTimeMuddyPigIsShaking;

	public MuddyPigEntity(EntityType<MuddyPigEntity> type, Level world) {
		super(type, world);
	}

	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
		goalSelector.addGoal(3, new TemptGoal(this, 1.2D, TEMPTATION_ITEMS, false));
		goalSelector.addGoal(4, new MuddyPigMoveToTargetGoal(this, 1.2D));
		goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		goalSelector.addGoal(8, new RandomStrollGoal(this, 1.0D, 100));
	}

	private boolean isOverMudBlock() {
		int x = Mth.floor(getX());
		int y = Mth.floor(getY());
		int z = Mth.floor(getZ());
		BlockPos blockPos = new BlockPos(x, y, z).below();
		return level().getBlockState(blockPos).getBlock().equals(Blocks.MUD);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isOverMudBlock()) {
			if (isInMuddyState()) return;

			if (!isShaking) {
				isShaking = true;
				timeMuddyPigIsShaking = 0.0F;
				prevTimeMuddyPigIsShaking = 0.0F;
				level().broadcastEntityEvent(this, (byte) 8);
			}
			if (++finallyInMud > 60) {
				setMuddyState(true);
				finallyInMud = 0;
				resetShake();
			}
		} else {
			if (!isInMuddyState()) return;

			outOfMud++;
			if (outOfMud > 300) {
				setMuddyState(false);
				outOfMud = 0;
			}

		}
	}

	private void resetShake() {
		isShaking = false;
		prevTimeMuddyPigIsShaking = 0.0F;
		timeMuddyPigIsShaking = 0.0F;
	}

	public void tick() {
		super.tick();
		if (!isAlive()) return;
		if (!isShaking) return;

		prevTimeMuddyPigIsShaking = timeMuddyPigIsShaking;
		timeMuddyPigIsShaking += 0.033F;
		if (prevTimeMuddyPigIsShaking >= 2.0F) {
			resetShake();
		}

	}

	@Override
	public void die(DamageSource cause) {
		resetShake();
		super.die(cause);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(MUDDY_STATE, false);
	}

	public boolean isInMuddyState() {
		return entityData.get(MUDDY_STATE);
	}

	public void setMuddyState(boolean inMud) {
		entityData.set(MUDDY_STATE, inMud);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsInMud", isInMuddyState());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		setMuddyState(compound.getBoolean("IsInMud"));
	}

	@Environment(EnvType.CLIENT)
	public void handleEntityEvent(byte status) {
		if (status == EntityEvent.SHAKE_WETNESS) {
			isShaking = true;
			timeMuddyPigIsShaking = 0.0F;
			prevTimeMuddyPigIsShaking = 0.0F;
		} else {
			super.handleEntityEvent(status);
		}
	}

	@Environment(EnvType.CLIENT)
	public float getShakeAngle(float tickDelta, float d) {
		float f = (Mth.lerp(tickDelta, prevTimeMuddyPigIsShaking, timeMuddyPigIsShaking) + d) / 1.8F;
		if (f < 0.0F) {
			f = 0.0F;
		} else if (f > 1.0F) {
			f = 1.0F;
		}
		return Mth.sin(f * (float) Math.PI) * Mth.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
	}

}
 