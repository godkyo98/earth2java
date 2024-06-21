package slexom.earthtojava.entity.passive;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.ai.goal.HornedSheepActiveTargetGoal;
import slexom.earthtojava.entity.ai.goal.HornedSheepMeleeAttackGoal;
import slexom.earthtojava.entity.ai.goal.HornedSheepRevengeGoal;
import slexom.earthtojava.entity.base.E2JBaseSheepEntity;
import slexom.earthtojava.utils.Utils;

import java.util.UUID;


public class HornedSheepEntity extends E2JBaseSheepEntity implements NeutralMob, Shearable {

    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(HornedSheepEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(HornedSheepEntity.class, EntityDataSerializers.INT);
    private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
    private EatBlockGoal eatGrassGoal;
    private UUID lastHurtBy;

    public HornedSheepEntity(EntityType<? extends HornedSheepEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createHornedSheepAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte) 0);
        builder.define(ANGER_TIME, 0);
    }

    protected void registerGoals() {
        eatGrassGoal = new EatBlockGoal(this);
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false));
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        goalSelector.addGoal(4, eatGrassGoal);
        goalSelector.addGoal(5, new HornedSheepMeleeAttackGoal(this, 1.4D, true));
        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, (new HornedSheepRevengeGoal(this)).setAlertOthers());
        targetSelector.addGoal(2, new HornedSheepActiveTargetGoal(this));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        addPersistentAngerSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        readPersistentAngerSaveData(level(), compound);
    }

    public void setLastHurtByMob(@Nullable LivingEntity livingBase) {
        super.setLastHurtByMob(livingBase);
        if (livingBase != null) {
            lastHurtBy = livingBase.getUUID();
        }
    }

    protected void customServerAiStep() {
        if (isAngry()) {
            int i = getAnger();
            setAnger(i - 1);
            LivingEntity livingentity = getTarget();
            if (i == 0 && livingentity != null) {
                setSheepAttacker(livingentity);
            }
        }
    }

    public boolean isAngry() {
        return getAnger() > 0;
    }

    private int getAnger() {
        return entityData.get(ANGER_TIME);
    }

    private void setAnger(int angerTime) {
        entityData.set(ANGER_TIME, angerTime);
    }

    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            boolean flag = isAngry() && getTarget() != null && getTarget().distanceToSqr(this) < 4.0D;
            setNearTarget(flag);
        }
    }

    private void setNearTarget(boolean p_226452_1_) {
        setSheepFlag(p_226452_1_);
    }

    private void setSheepFlag(boolean p_226404_2_) {
        if (p_226404_2_) {
            entityData.set(DATA_FLAGS_ID, (byte) (entityData.get(DATA_FLAGS_ID) | 2));
        } else {
            entityData.set(DATA_FLAGS_ID, (byte) (entityData.get(DATA_FLAGS_ID) & ~2));
        }
    }

    public ResourceKey<LootTable> getDefaultLootTable() {
        if (isSheared()) {
            return getType().getDefaultLootTable();
        }

        return switch (getColor()) {
            case WHITE -> Utils.lootTableOf("entities/horned_sheep/white");
            case ORANGE -> Utils.lootTableOf("entities/horned_sheep/orange");
            case MAGENTA -> Utils.lootTableOf("entities/horned_sheep/magenta");
            case LIGHT_BLUE -> Utils.lootTableOf("entities/horned_sheep/light_blue");
            case YELLOW -> Utils.lootTableOf("entities/horned_sheep/yellow");
            case LIME -> Utils.lootTableOf("entities/horned_sheep/lime");
            case PINK -> Utils.lootTableOf("entities/horned_sheep/pink");
            case GRAY -> Utils.lootTableOf("entities/horned_sheep/gray");
            case LIGHT_GRAY -> Utils.lootTableOf("entities/horned_sheep/light_gray");
            case CYAN -> Utils.lootTableOf("entities/horned_sheep/cyan");
            case PURPLE -> Utils.lootTableOf("entities/horned_sheep/purple");
            case BLUE -> Utils.lootTableOf("entities/horned_sheep/blue");
            case BROWN -> Utils.lootTableOf("entities/horned_sheep/brown");
            case GREEN -> Utils.lootTableOf("entities/horned_sheep/green");
            case RED -> Utils.lootTableOf("entities/horned_sheep/red");
            case BLACK -> Utils.lootTableOf("entities/horned_sheep/black");
        };
    }

    public boolean setSheepAttacker(Entity attacker) {
        setAnger(400 + random.nextInt(400));
        if (attacker instanceof LivingEntity) {
            setLastHurtByMob((LivingEntity) attacker);
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source)) {
            return false;
        }
        Entity entity = source.getEntity();
        if (!level().isClientSide && entity instanceof Player && !((Player) entity).isCreative() && hasLineOfSight(entity) && !isNoAi()) {
            setSheepAttacker(entity);
        }
        return super.hurt(source, amount);
    }


    @Override
    public int getRemainingPersistentAngerTime() {
        return entityData.get(ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int ticks) {
        entityData.set(ANGER_TIME, ticks);
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return lastHurtBy;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {
        lastHurtBy = uuid;
    }

    @Override
    public void startPersistentAngerTimer() {
        setRemainingPersistentAngerTime(ANGER_TIME_RANGE.sample(random));
    }


}
