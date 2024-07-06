package slexom.earthtojava.entity.projectile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;


public abstract class E2JThrowableItemProjectile extends ThrowableItemProjectile {
    public E2JThrowableItemProjectile(EntityType<? extends E2JThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public E2JThrowableItemProjectile(EntityType<? extends E2JThrowableItemProjectile> entityType, LivingEntity owner, Level world) {
        super(entityType, owner, world);
    }

    public E2JThrowableItemProjectile(EntityType<? extends E2JThrowableItemProjectile> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    @Override
    protected abstract Item getDefaultItem();

    @Environment(EnvType.CLIENT)
    private ParticleOptions getParticle() {
        ItemStack itemStack = this.getItem();
        return !itemStack.isEmpty() && !itemStack.is(this.getDefaultItem()) ? new ItemParticleOption(ParticleTypes.ITEM, itemStack) : ParticleTypes.SPIT;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.DEATH) {
            ParticleOptions particleOptions = getParticle();
            for (int i = 0; i < 8; ++i) {
                level().addParticle(particleOptions, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        playSound(getHitSound(), 1.0F, 1.0F);
        Entity entity = entityHitResult.getEntity();
        entity.hurt(entity.damageSources().thrown(this, getOwner()), 5.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, (byte) 3);
            discard();
        }
    }

    protected abstract SoundEvent getHitSound();
}

