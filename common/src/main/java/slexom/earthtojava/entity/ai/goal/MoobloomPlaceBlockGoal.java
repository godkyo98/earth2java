package slexom.earthtojava.entity.ai.goal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import slexom.earthtojava.entity.passive.MoobloomEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.SoundEventsInit;

public class MoobloomPlaceBlockGoal extends PlaceBlockGoal {

    public MoobloomPlaceBlockGoal(MoobloomEntity entity) {
        super(entity);
    }

    @Override
    protected Block getRandomFlower() {
        return Math.random() > 0.8 ? Blocks.SUNFLOWER : BlockInit.BUTTERCUP.get();
    }

    @Override
    protected SoundEvent getPlantSound() {
        return SoundEventsInit.MOOBLOOM_PLANT.get();
    }
}

