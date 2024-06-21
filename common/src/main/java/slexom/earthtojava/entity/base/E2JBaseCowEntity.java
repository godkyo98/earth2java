package slexom.earthtojava.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBaseCowEntity extends Cow {

    public final BlinkManager blinkManager;
    private final EntityVariantManager<E2JBaseCowEntity> variantManager;

    public E2JBaseCowEntity(EntityType<? extends Cow> type, Level worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        variantManager = new EntityVariantManager<>();
    }

    public static AttributeSupplier.Builder createCowAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        blinkManager.tickBlink();
    }

    @Override
    public E2JBaseCowEntity getBreedOffspring(ServerLevel world, AgeableMob other) {
        return variantManager.getChild(this, other).create(world);
    }


}
