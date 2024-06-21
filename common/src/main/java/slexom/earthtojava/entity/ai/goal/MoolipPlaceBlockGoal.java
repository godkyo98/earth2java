package slexom.earthtojava.entity.ai.goal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import slexom.earthtojava.entity.passive.MoolipEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class MoolipPlaceBlockGoal extends PlaceBlockGoal {

    public MoolipPlaceBlockGoal(MoolipEntity entity) {
        super(entity);
    }

    @Override
    protected Block getRandomFlower() {
        double random = Math.random();
        if (random > 0.6) {
            return Blocks.LILAC;
        } else if (random > 0.2) {
            return Blocks.ALLIUM;
        } else {
            return Blocks.PEONY;
        }
    }

    @Override
    protected SoundEvent getPlantSound() {
        return SoundEventsInit.MOOLIP_PLANT.get();
    }
}