package slexom.earthtojava.entity.passive;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.ai.goal.*;

public class TropicalSlimeEntity extends Monster implements Enemy {

    private final int size;
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    public TropicalSlimeEntity(EntityType<TropicalSlimeEntity> type, Level world) {
        super(type, world);
        size = 4;
        xpReward = size;
        setNoAi(false);
        moveControl = new TropicalSlimeMoveControl(this);
        setAttributes();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new TropicalSlimeSwimGoal(this));
        goalSelector.addGoal(1, new TropicalSlimeFloatGoal(this));
        goalSelector.addGoal(2, new TropicalSlimeAttackGoal(this));
        goalSelector.addGoal(3, new TropicalSlimeFaceRandomGoal(this));
        goalSelector.addGoal(5, new TropicalSlimeHopGoal(this));
        targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    protected void setAttributes() {
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(16.0D);
        getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.6D);
        getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (isBaby()) {
            return super.mobInteract(player, hand);
        }
        if (player.getAbilities().instabuild) {
            return super.mobInteract(player, hand);
        }

        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() != Items.BUCKET) return super.mobInteract(player, hand);

        if (level().isClientSide) {
            return super.mobInteract(player, hand);
        }

        remove(RemovalReason.KILLED);
        level().addParticle(ParticleTypes.EXPLOSION, getX(), getY(0.5D), getZ(), 0.0D, 0.0D, 0.0D);
        player.playSound(SoundEvents.SLIME_SQUISH, 1.0F, 1.0F);
        spawnWater();
        giveTropicalFishBucket(player, itemstack);
        return InteractionResult.sidedSuccess(level().isClientSide);
    }

    private void giveTropicalFishBucket(Player player, ItemStack itemstack) {
        itemstack.shrink(1);
        if (!player.getInventory().add(new ItemStack(Items.TROPICAL_FISH_BUCKET))) {
            player.drop(new ItemStack(Items.TROPICAL_FISH_BUCKET), false);
        }
    }

    private void spawnWater() {
        int x = Mth.floor(getX());
        int y = Mth.floor(getY());
        int z = Mth.floor(getZ());
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState waterState = Blocks.WATER.defaultBlockState();
        level().removeBlock(blockPos, false);
        level().setBlockAndUpdate(blockPos, waterState);
    }

    protected ParticleOptions getSquishParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    public void tick() {
        stretch += (targetStretch - stretch) * 0.5F;
        lastStretch = stretch;
        super.tick();

        if (onGround() && !onGroundLastTick) {
            int i = size;
            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for (int j = 0; j < i * 8; ++j) {
                float f = random.nextFloat() * ((float) Math.PI * 2F);
                float f1 = random.nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * i * 0.5F * f1;
                float f3 = Mth.cos(f) * i * 0.5F * f1;
                level().addParticle(getSquishParticle(), getX() + f2, getY(), getZ() + f3, 0.0D, 0.0D, 0.0D);
            }
            playSound(getSquishSound(), getSoundVolume(), ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            targetStretch = -0.5F;
        } else if (!onGround() && onGroundLastTick) {
            targetStretch = 1.0F;
        }
        onGroundLastTick = onGround();
        updateStretch();
    }

    protected void updateStretch() {
        targetStretch *= 0.6F;
    }

    public int getJumpDelay() {
        return random.nextInt(20) + 10;
    }

    @Override
    public void push(Entity entity) {
        super.push(entity);
        if (entity instanceof IronGolem && canAttack()) {
            damage((LivingEntity) entity);
        }
    }

    @Override
    public void playerTouch(Player player) {
        if (canAttack()) {
            damage(player);
        }

    }

    protected float getAttackDamage() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    protected void damage(LivingEntity livingEntity) {
        if (!isAlive()) {
            return;
        }
        if (!isWithinMeleeAttackRange(livingEntity)) {
            return;
        }

        if (!hasLineOfSight(livingEntity)) {
            return;
        }

        DamageSource damageSource = this.damageSources().mobAttack(this);
        if (livingEntity.hurt(damageSource, this.getAttackDamage())) {
            this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            Level level = this.level();
            if (level instanceof ServerLevel serverLevel) {
                EnchantmentHelper.doPostAttackEffects(serverLevel, livingEntity, damageSource);
            }
        }
    }


    public boolean canAttack() {
        return isEffectiveAi();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.SLIME_SQUISH;
    }

    public boolean doPlayJumpSound() {
        return true;
    }

    public float getJumpSoundPitch() {
        return ((random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f) * 0.8f;
    }

    @Override
    public void jumpFromGround() {
        Vec3 vec3 = getDeltaMovement();
        setDeltaMovement(vec3.x, getJumpPower(), vec3.z);
        hasImpulse = true;
    }

    public SoundEvent getJumpSound() {
        return SoundEvents.SLIME_JUMP;
    }

    protected boolean spawnCustomParticles() {
        return false;
    }

}
 
