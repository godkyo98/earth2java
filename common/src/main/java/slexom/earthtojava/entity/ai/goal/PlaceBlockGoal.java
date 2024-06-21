package slexom.earthtojava.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
public abstract class PlaceBlockGoal extends Goal {
    private final Entity entity;

    public PlaceBlockGoal(Entity entity) {
        this.entity = entity;
    }

    public boolean canUse() {
        return entity.getRandom().nextInt(2000) == 0;
    }

    public boolean canPlace(LevelReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
        return !downTarget.isAir() && downTarget.isCollisionShapeFullBlock(world, downTargetPos) && target.isAir() && target.canSurvive(world, targetPos);
    }

    @Override
    public void tick() {
        Level world = entity.level();
        int i = Mth.floor(entity.getX());
        int j = Mth.floor(entity.getY());
        int k = Mth.floor(entity.getZ());
        Block flower = getRandomFlower();
        BlockPos blockPos = new BlockPos(i, j, k);
        BlockState blockState = flower.defaultBlockState();
        BlockPos blockDownPos = blockPos.below();
        BlockState blockDownState = world.getBlockState(blockDownPos);
        if (canPlace(world, blockState, blockPos, blockDownState, blockDownPos)) {
            entity.playSound(getPlantSound(), 1.0f, 1.0f);
            world.removeBlock(blockPos, false);
            world.setBlock(blockPos, blockState, 3);
        }
    }

    protected abstract Block getRandomFlower();

    protected abstract SoundEvent getPlantSound();
}