package slexom.earthtojava.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.entity.passive.MuddyPigEntity;

public class MuddyPigMoveToTargetGoal extends MoveToBlockGoal {
    private final MuddyPigEntity muddyPig;

    public MuddyPigMoveToTargetGoal(MuddyPigEntity entity, double speed) {
        super(entity, speed, 16, 3);
        muddyPig = entity;
    }

    @Override
    public boolean canUse() {
        return !muddyPig.isInMuddyState() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (muddyPig.isInMuddyState()) return false;
        if (tryTicks > 600) return false;

        return isValidTarget(muddyPig.level(), blockPos);
    }

    @Override
    public boolean shouldRecalculatePath() {
        return tryTicks % 100 == 0;
    }

    @Override
    protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        BlockState blockState = block.defaultBlockState();

        return blockState.is(Blocks.MUD);
    }
}
