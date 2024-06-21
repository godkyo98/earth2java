package slexom.earthtojava.entity.passive;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.ai.goal.CluckshroomPlaceBlockGoal;
import slexom.earthtojava.entity.base.E2JBaseChickenEntity;


public class CluckshroomEntity extends E2JBaseChickenEntity {

    public CluckshroomEntity(EntityType<CluckshroomEntity> type, Level world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(2, new RestrictSunGoal(this));
        goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        goalSelector.addGoal(3, new CluckshroomPlaceBlockGoal(this));
    }

}