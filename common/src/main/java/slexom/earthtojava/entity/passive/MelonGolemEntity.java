package slexom.earthtojava.entity.passive;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.ai.control.MelonGolemMoveControl;
import slexom.earthtojava.entity.ai.goal.MelonGolemHopGoal;
import slexom.earthtojava.entity.ai.goal.MelonGolemProjectileAttackGoal;
import slexom.earthtojava.entity.projectile.MelonSeedProjectileEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class MelonGolemEntity extends AbstractGolem implements RangedAttackMob {
    private static final EntityDataAccessor<Byte> MELON_EQUIPPED = SynchedEntityData.defineId(MelonGolemEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> SHOOTING_TICKS = SynchedEntityData.defineId(MelonGolemEntity.class, EntityDataSerializers.INT);

    public final BlinkManager blinkManager;

    public MelonGolemEntity(EntityType<? extends MelonGolemEntity> type, Level worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        moveControl = new MelonGolemMoveControl(this);
    }

    public static AttributeSupplier.Builder createMelonGolemAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new MelonGolemProjectileAttackGoal(this, 1.25D, 20, 10.0F));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0, 1.0000001E-5f));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        goalSelector.addGoal(5, new MelonGolemHopGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Mob.class, 10, true, false, (entity) -> entity instanceof Enemy && !(entity instanceof TropicalSlimeEntity)));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(MELON_EQUIPPED, (byte) 16);
        builder.define(SHOOTING_TICKS, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Pumpkin", isMelonEquipped());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Pumpkin")) {
            setMelonEquipped(compound.getBoolean("Pumpkin"));
        }

    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {

            if (level().getBiome(blockPosition()).is(BiomeTags.SNOW_GOLEM_MELTS)) {
                hurt(damageSources().onFire(), 1.0f);
            }
            if (!level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                return;
            }
            BlockState blockState = Blocks.SNOW.defaultBlockState();
            for (int l = 0; l < 4; ++l) {
                int posX = Mth.floor(getX() + (l % 2 * 2 - 1) * 0.25F);
                int posY = Mth.floor(getY());
                int posZ = Mth.floor(getZ() + (l / 2D % 2 * 2 - 1) * 0.25F);
                BlockPos blockPos = new BlockPos(posX, posY, posZ);
                if (level().getBlockState(blockPos).isAir() && blockState.canSurvive(level(), blockPos)) {
                    level().setBlockAndUpdate(blockPos, blockState);
                    level().gameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Context.of(this, blockState));
                }
            }

        }
        int currentShootingTicks = entityData.get(SHOOTING_TICKS);
        if (currentShootingTicks > 0) {
            entityData.set(SHOOTING_TICKS, --currentShootingTicks);
        }
        blinkManager.tickBlink();
    }

    public boolean isShooting() {
        return entityData.get(SHOOTING_TICKS) > 0;
    }

    public void setShootingTicks() {
        entityData.set(SHOOTING_TICKS, 8);
    }

    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        setShootingTicks();
        MelonSeedProjectileEntity melonSeedEntity = new MelonSeedProjectileEntity(level(), this);
        double d0 = target.getEyeY() - 1.1D;
        double d1 = target.getX() - getX();
        double d2 = d0 - melonSeedEntity.getY();
        double d3 = target.getZ() - getZ();
        double f = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
        melonSeedEntity.shoot(d1, d2 + f, d3, 1.6F, 12.0F);
        playSound(SoundEventsInit.MELON_GOLEM_ATTACK.get(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
        level().addFreshEntity(melonSeedEntity);
    }

    public boolean isMelonEquipped() {
        return (entityData.get(MELON_EQUIPPED) & 16) != 0;
    }

    public void setMelonEquipped(boolean melonEquipped) {
        byte b0 = entityData.get(MELON_EQUIPPED);
        if (melonEquipped) {
            entityData.set(MELON_EQUIPPED, (byte) (b0 | 16));
        } else {
            entityData.set(MELON_EQUIPPED, (byte) (b0 & -17));
        }
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SNOW_GOLEM_HURT;
    }


    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }


    public int getJumpDelay() {
        return random.nextInt(20) + 5;
    }


    @Override
    public void jumpFromGround() {
        Vec3 vec3 = getDeltaMovement();
        setDeltaMovement(vec3.x, getJumpPower(), vec3.z);
        hasImpulse = true;
    }

}
