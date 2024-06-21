package slexom.earthtojava.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.entity.passive.CluckshroomEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class CluckshroomPlaceBlockGoal extends Goal {
    private final CluckshroomEntity cluckshroom;

    public CluckshroomPlaceBlockGoal(CluckshroomEntity entity) {
        cluckshroom = entity;
    }

    public boolean canUse() {
        return cluckshroom.getRandom().nextInt(2000) == 0;
    }

    public boolean canPlace(LevelReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
        return !downTarget.isAir() && downTarget.isCollisionShapeFullBlock(world, downTargetPos) && target.isAir() && target.canSurvive(world, targetPos);
    }

    @Override
    public void tick() {
        LevelAccessor world = cluckshroom.level();
        int i = Mth.floor(cluckshroom.getX());
        int j = Mth.floor(cluckshroom.getY());
        int k = Mth.floor(cluckshroom.getZ());
        Block mushroom = Blocks.RED_MUSHROOM;
        BlockPos blockPos = new BlockPos(i, j, k);
        BlockState blockState = mushroom.defaultBlockState();
        BlockPos blockDownPos = blockPos.below();
        BlockState blockDownState = world.getBlockState(blockDownPos);
        if (canPlace(world, blockState, blockPos, blockDownState, blockDownPos)) {
            cluckshroom.playSound(SoundEventsInit.CLUCKSHROOM_LAY_MUSHROOM.get(), 1.0f, 1.0f);
            world.removeBlock(blockPos, false);
            world.setBlock(blockPos, blockState, 3);
        }
    }
}
