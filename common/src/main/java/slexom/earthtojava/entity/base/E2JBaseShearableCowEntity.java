package slexom.earthtojava.entity.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class E2JBaseShearableCowEntity extends E2JBaseCowEntity implements Shearable {

    private static final EntityDataAccessor<Byte> isSheared = SynchedEntityData.defineId(E2JBaseShearableCowEntity.class, EntityDataSerializers.BYTE);
    private final ItemStack wool;
    private int eatGrassTimer;
    private EatBlockGoal eatGrassGoal;

    public E2JBaseShearableCowEntity(EntityType<? extends E2JBaseShearableCowEntity> type, Level world, ItemStack wool) {
        super(type, world);
        this.wool = wool;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        eatGrassGoal = new EatBlockGoal(this);
        goalSelector.addGoal(5, eatGrassGoal);
    }

    protected void customServerAiStep() {
        eatGrassTimer = eatGrassGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    public void aiStep() {
        if (level().isClientSide) {
            eatGrassTimer = Math.max(0, eatGrassTimer - 1);
        }
        super.aiStep();
    }

    @Environment(EnvType.CLIENT)
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.EAT_GRASS) {
            eatGrassTimer = 40;
        } else {
            super.handleEntityEvent(status);
        }
    }

    public void ate() {
        setSheared(false);
        if (isBaby()) {
            ageUp(30);
        }
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(isSheared, (byte) 0);
    }

    public boolean isSheared() {
        return (entityData.get(isSheared) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = entityData.get(isSheared);
        if (sheared) {
            entityData.set(isSheared, (byte) (b0 | 16));
        } else {
            entityData.set(isSheared, (byte) (b0 & -17));
        }
    }

    public boolean readyForShearing() {
        return isAlive() && !isSheared() && !isBaby();
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.SHEARS)) {
            if (!level().isClientSide && readyForShearing()) {
                shear(SoundSource.PLAYERS);
                itemStack.hurtAndBreak(1, player, getSlotForHand(hand));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    public void shear(SoundSource soundSource) {
        level().playSound(null, this, SoundEvents.SHEEP_SHEAR, soundSource, 1.0F, 1.0F);
        setSheared(true);
        int i = 1 + random.nextInt(3);
        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = spawnAtLocation(wool.getItem(), 1);
            if (itemEntity != null) {
                itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((random.nextFloat() - random.nextFloat()) * 0.1F, random.nextFloat() * 0.05F, (random.nextFloat() - random.nextFloat()) * 0.1F));
            }
        }
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Sheared", isSheared());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setSheared(compound.getBoolean("Sheared"));
    }

    @Environment(EnvType.CLIENT)
    public float getHeadEatPositionScale(float delta) {
        if (eatGrassTimer <= 0) {
            return 0.0F;
        }
        if (eatGrassTimer >= 4 && eatGrassTimer <= 36) {
            return 1.0F;
        }
        return eatGrassTimer < 4 ? ((float) eatGrassTimer - delta) / 4.0F : -((float) (eatGrassTimer - 40) - delta) / 4.0F;
    }

    @Environment(EnvType.CLIENT)
    public float getHeadEatAngleScale(float delta) {
        if (eatGrassTimer > 4 && eatGrassTimer <= 36) {
            float f = ((float) (eatGrassTimer - 4) - delta) / 32.0F;
            return 0.62831855F + 0.21991149F * Mth.sin(f * 28.7F);
        }
        return eatGrassTimer > 0 ? 0.62831855F : getXRot() * 0.017453292F;
    }

}
