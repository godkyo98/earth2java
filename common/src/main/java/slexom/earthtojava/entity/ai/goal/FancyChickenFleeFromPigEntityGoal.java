package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Pig;
import slexom.earthtojava.init.SoundEventsInit;

public class FancyChickenFleeFromPigEntityGoal extends AvoidEntityGoal<Pig> {
    public FancyChickenFleeFromPigEntityGoal(PathfinderMob mob, Class<Pig> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
    }

    @Override
    public void start() {
        super.start();
        mob.playSound(SoundEventsInit.FANCY_CHICKEN_FLEE.get(), 1.0F, 1.0F);
    }

}
