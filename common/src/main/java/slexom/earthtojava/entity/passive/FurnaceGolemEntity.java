package slexom.earthtojava.entity.passive;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.ai.goal.FurnaceGolemActiveTargetGoal;
import slexom.earthtojava.entity.ai.goal.TrackFurnaceGolemTargetGoal;
import slexom.earthtojava.init.SoundEventsInit;

public class FurnaceGolemEntity extends IronGolem {
    public static final EntityDataAccessor<Boolean> IS_ANGRY = SynchedEntityData.defineId(FurnaceGolemEntity.class, EntityDataSerializers.BOOLEAN);
    public final BlinkManager blinkManager;
    private int attackTimer;

    public FurnaceGolemEntity(EntityType<? extends IronGolem> type, Level worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        xpReward = 5;
        setNoAi(false);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.6D, false));
        goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6D));
        goalSelector.addGoal(5, new OfferFlowerGoal(this));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new TrackFurnaceGolemTargetGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
        targetSelector.addGoal(3, new FurnaceGolemActiveTargetGoal(this, Mob.class, 5, false, false, entity -> entity instanceof Enemy && !(entity instanceof Creeper) && !(entity instanceof TropicalSlimeEntity)));
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        attackTimer = 10;
        level().broadcastEntityEvent(this, (byte) 4);
        float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float g = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        DamageSource damageSource = this.damageSources().onFire();
        boolean hurt = entity.hurt(damageSource, g);
        if (hurt) {
            double knockBackResistance;
            if (entity instanceof LivingEntity livingEntity) {
                knockBackResistance = livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            } else {
                knockBackResistance = 0.0;
            }

            double d = knockBackResistance;
            double e = Math.max(0.0, 1.0 - d);
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.4000000059604645 * e, 0.0));
            Level level = this.level();
            if (level instanceof ServerLevel serverLevel) {
                EnchantmentHelper.doPostAttackEffects(serverLevel, entity, damageSource);
            }
        }
        playSound(SoundEventsInit.FURNACE_GOLEM_ATTACK.get(), 1.0F, 1.0F);
        return hurt;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (isAngry()) {
            float rand = random.nextFloat();
            if (rand > 0.80F && rand <= 0.83F) {
                int x = Mth.floor(getX());
                int y = Mth.floor(getY());
                int z = Mth.floor(getZ());
                BlockPos ground = new BlockPos(x, y - 1, z);
                BlockPos around = ground.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                BlockPos above = around.above();
                boolean solidFace = level().getBlockState(around).isFaceSturdy(level(), around, Direction.UP);
                boolean aboveIsAir = level().getBlockState(above).isAir();

                if (solidFace && aboveIsAir) {
                    level().setBlock(above, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }

        if (isInWaterOrBubble()) {
            hurt(damageSources().drown(), 5.0F);
        }

        blinkManager.tickBlink();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_ANGRY, false);
    }

    public boolean isAngry() {
        return entityData.get(IS_ANGRY);
    }

    public void setAngry(boolean angry) {
        entityData.set(IS_ANGRY, angry);
    }

}
