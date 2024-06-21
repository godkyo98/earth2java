package slexom.earthtojava.entity.passive;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

public class UmbraCowEntity extends E2JBaseShearableCowEntity {

	public UmbraCowEntity(EntityType<UmbraCowEntity> type, Level world) {
		super(type, world, new ItemStack(Blocks.BLACK_WOOL));
	}

}
