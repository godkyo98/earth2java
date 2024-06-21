package slexom.earthtojava.entity.passive;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.base.E2JBaseCowEntity;
public abstract class FlowerPlacingCowEntity extends E2JBaseCowEntity implements Shearable {

    public FlowerPlacingCowEntity(EntityType<? extends FlowerPlacingCowEntity> type, Level world) {
        super(type, world);
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof ShearsItem && readyForShearing()) {
            shear(SoundSource.PLAYERS);
            if (!level().isClientSide) {
                itemStack.hurtAndBreak(1, player,  getSlotForHand(hand));
            }
            return InteractionResult.sidedSuccess(level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    public void shear(SoundSource soundSource) {
        level().playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, soundSource, 1.0F, 1.0F);
        if (level().isClientSide()) return;

        Cow cowEntity = EntityType.COW.create(level());
        if (cowEntity == null) return;

        ((ServerLevel) level()).sendParticles(ParticleTypes.EXPLOSION, getX(), getY(0.5D), getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        discard();
        cowEntity.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
        cowEntity.setHealth(getHealth());
        cowEntity.yBodyRot = yBodyRot;
        if (hasCustomName()) {
            cowEntity.setCustomName(getCustomName());
            cowEntity.setCustomNameVisible(isCustomNameVisible());
        }
        if (isPersistenceRequired()) {
            cowEntity.setPersistenceRequired();
        }
        cowEntity.setInvulnerable(isInvulnerable());
        level().addFreshEntity(cowEntity);

        dropCustomItems();
    }

    public abstract void dropCustomItems();

    @Override
    public boolean readyForShearing() {
        return isAlive() && !isBaby();
    }
}
