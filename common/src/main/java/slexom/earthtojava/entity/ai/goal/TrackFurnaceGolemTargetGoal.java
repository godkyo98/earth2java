package slexom.earthtojava.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class TrackFurnaceGolemTargetGoal extends DefendVillageTargetGoal {
    private final FurnaceGolemEntity golem;


    public TrackFurnaceGolemTargetGoal(FurnaceGolemEntity golem) {
        super(golem);
        this.golem = golem;
    }


    @Override
    public void start() {
        golem.playSound(SoundEventsInit.FURNACE_GOLEM_AGGRO.get(), 1.0F, 1.0F);
        golem.setAngry(true);
        super.start();
    }

    @Override
    public void stop() {
        golem.setAngry(false);
        super.stop();
    }
}
