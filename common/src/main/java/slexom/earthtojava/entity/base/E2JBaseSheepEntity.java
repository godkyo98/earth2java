package slexom.earthtojava.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBaseSheepEntity extends Sheep {

    public final BlinkManager blinkManager;
    private final EntityVariantManager<E2JBaseSheepEntity> variantManager;

    public E2JBaseSheepEntity(EntityType<? extends Sheep> type, Level worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        variantManager = new EntityVariantManager<>();
    }

    public static AttributeSupplier.Builder createSheepAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.23000000417232513D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        blinkManager.tickBlink();
    }

    @Override
    public E2JBaseSheepEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob other) {
        return variantManager.getChild(this, other).create(serverWorld);
    }

}
