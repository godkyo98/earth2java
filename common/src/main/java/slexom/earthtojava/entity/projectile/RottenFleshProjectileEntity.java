package slexom.earthtojava.entity.projectile;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import slexom.earthtojava.init.EntityTypesInit;
import slexom.earthtojava.init.SoundEventsInit;

public class RottenFleshProjectileEntity extends E2JThrowableItemProjectile {


    public RottenFleshProjectileEntity(Level world, LivingEntity owner) {
        super(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT.get(), owner, world);
    }

    public RottenFleshProjectileEntity(Level world, double x, double y, double z) {
        super(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT.get(), x, y, z, world);
    }

    public RottenFleshProjectileEntity(EntityType<RottenFleshProjectileEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.ROTTEN_FLESH;
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEventsInit.LOBBER_ZOMBIE_PROJECTILE_SOUND.get();
    }
}