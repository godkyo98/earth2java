package slexom.earthtojava.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.BlinkManager;

public class E2JBaseZombieEntity extends Zombie {

    public final BlinkManager blinkManager;

    public E2JBaseZombieEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
        blinkManager = new BlinkManager();
    }

    public static AttributeSupplier.Builder createZombieAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.23D).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        blinkManager.tickBlink();
    }

}
