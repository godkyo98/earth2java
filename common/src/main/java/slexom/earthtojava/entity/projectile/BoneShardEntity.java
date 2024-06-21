package slexom.earthtojava.entity.projectile;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import slexom.earthtojava.init.EntityTypesInit;
import slexom.earthtojava.init.ItemInit;
import slexom.earthtojava.init.SoundEventsInit;

public class BoneShardEntity extends E2JThrowableItemProjectile {

    public BoneShardEntity(Level world, LivingEntity owner) {
        super(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), owner, world);
    }

    public BoneShardEntity(Level world, double x, double y, double z) {
        super(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), x, y, z, world);
    }

    public BoneShardEntity(EntityType<BoneShardEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.BONE_SHARD.get();
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEventsInit.BONE_SPIDER_BONE_STAB.get();
    }
}