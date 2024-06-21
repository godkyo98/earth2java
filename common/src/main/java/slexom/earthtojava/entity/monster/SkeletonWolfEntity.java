package slexom.earthtojava.entity.monster;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.UUID;

public class SkeletonWolfEntity extends Monster implements NeutralMob {

	protected static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(SkeletonWolfEntity.class, EntityDataSerializers.INT );
	private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);

	private float headRotationCourse;
	private float headRotationCourseOld;
	private UUID targetUuid;

	public SkeletonWolfEntity(EntityType<SkeletonWolfEntity> type, Level world) {
		super(type, world);
	}

	public static AttributeSupplier.Builder createSkeletonWolfAttributes() {
		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(2, new RestrictSunGoal(this));
		goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
		goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
		goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, false));
		goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (isAngry()) {
			return SoundEventsInit.SKELETON_WOLF_GROWL.get();
		}
		if (random.nextInt(3) == 0 && getHealth() < 10.0F) {
			return SoundEventsInit.SKELETON_WOLF_WHINE.get();
		}
		return SoundEventsInit.SKELETON_WOLF_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEventsInit.SKELETON_WOLF_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEventsInit.SKELETON_WOLF_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		playSound(SoundEventsInit.SKELETON_WOLF_STEP.get(), 0.35F, 1.0F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ANGER_TIME, 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		readPersistentAngerSaveData(level(), compound);
	}

	@Environment(EnvType.CLIENT)
	public float getTailAngle() {
		if (isAngry()) {
			return 1.5393804F;
		}
		return ((float) Math.PI / 5F);
	}


	public int getRemainingPersistentAngerTime() {
		return entityData.get(ANGER_TIME);
	}

	public void setRemainingPersistentAngerTime(int ticks) {
		entityData.set(ANGER_TIME, ticks);
	}

	public void startPersistentAngerTimer() {
		setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(random));
	}

	@Nullable
	public UUID getPersistentAngerTarget() {
		return targetUuid;
	}

	public void setPersistentAngerTarget(@Nullable UUID uuid) {
		targetUuid = uuid;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (isAlive() && isSunBurnTick()) {
			igniteForSeconds(8);
		}
		if (!level().isClientSide) {
			updatePersistentAnger((ServerLevel) level(), true);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (isAlive()) {
			headRotationCourseOld = headRotationCourse;
			headRotationCourse += (0.0F - headRotationCourse) * 0.4F;
		}
	}

}
 
