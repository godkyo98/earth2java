package slexom.earthtojava.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AgeableMob;

public class EntityVariantManager<T extends AgeableMob> {

	public EntityType<T> getChild(AgeableMob parent1, AgeableMob parent2) {
		if (parent1.getRandom().nextInt(100) > 50) {
			return (EntityType<T>) parent2.getType();
		}
		return (EntityType<T>) parent1.getType();
	}

}
