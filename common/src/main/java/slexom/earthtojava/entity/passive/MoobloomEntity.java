package slexom.earthtojava.entity.passive;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.ai.goal.MoobloomPlaceBlockGoal;
import slexom.earthtojava.init.BlockInit;

public class MoobloomEntity extends FlowerPlacingCowEntity {

    public MoobloomEntity(EntityType<MoobloomEntity> type, Level world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(8, new MoobloomPlaceBlockGoal(this));
    }

    @Override
    public void dropCustomItems() {
        for (int i = 0; i < 5; ++i) {
            level().addFreshEntity(new ItemEntity(level(), getX(), getY(1.0D), getZ(), new ItemStack(BlockInit.BUTTERCUP.get())));
        }
    }

}