package slexom.earthtojava.entity.projectile;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import slexom.earthtojava.init.EntityTypesInit;
import slexom.earthtojava.init.SoundEventsInit;

public class MelonSeedProjectileEntity extends E2JThrowableItemProjectile {


    public MelonSeedProjectileEntity(Level world, LivingEntity owner) {
        super(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), owner, world);
    }

    public MelonSeedProjectileEntity(Level world, double x, double y, double z) {
        super(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), x, y, z, world);
    }

    public MelonSeedProjectileEntity(EntityType<MelonSeedProjectileEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.MELON_SEEDS;
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEventsInit.MELON_GOLEM_SEED_HIT.get();
    }
}
