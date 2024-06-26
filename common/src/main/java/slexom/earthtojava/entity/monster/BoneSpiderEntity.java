package slexom.earthtojava.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.ai.goal.BoneSpiderMeleeAttackGoal;
import slexom.earthtojava.entity.ai.pathing.ClimberNavigation;
import slexom.earthtojava.entity.projectile.BoneShardEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class BoneSpiderEntity extends Spider implements RangedAttackMob {
	public final BlinkManager blinkManager;

	public BoneSpiderEntity(EntityType<BoneSpiderEntity> type, Level worldIn) {
		super(type, worldIn);
		blinkManager = new BlinkManager();
	}

	public static AttributeSupplier.Builder createBoneSpiderAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 32.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		blinkManager.tickBlink();
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new ClimberNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(3, new RangedAttackGoal(this, 1.0D, 40, 12.0F));
		goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(4, new BoneSpiderMeleeAttackGoal(this));
		goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
		BoneShardEntity boneShard = new BoneShardEntity(level(), this);
		double d0 = target.getEyeY() - 1.1D;
		double d1 = target.getX() - getX();
		double d2 = d0 - boneShard.getY();
		double d3 = target.getZ() - getZ();
		double f = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
		boneShard.shoot(d1, d2 + f, d3, 1.6F, 8.0F);
		playSound(SoundEventsInit.BONE_SPIDER_SPIT.get(), 1.0F, 1.2F / (getRandom().nextFloat() * 0.4F + 0.8F));
		level().addFreshEntity(boneShard);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEventsInit.BONE_SPIDER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEventsInit.BONE_SPIDER_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(SoundEventsInit.BONE_SPIDER_WALK.get(), 0.15F, 1.0F);
	}
}
