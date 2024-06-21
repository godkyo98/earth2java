package slexom.earthtojava.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.EnumSet;
import java.util.function.Predicate;

public class JollyLlamaEatFernGoal extends Goal {
    private static final Predicate<BlockState> FERN_PREDICATE = BlockStatePredicate.forBlock(Blocks.FERN);
    private final Mob mob;
    private final Level world;
    private int timer;

    public JollyLlamaEatFernGoal(Mob mob) {
        this.mob = mob;
        world = mob.level();
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    public boolean canUse() {
        if (mob.getRandom().nextInt(mob.isBaby() ? 150 : 1500) == 0) {
            BlockPos blockPos = mob.blockPosition();
            if (FERN_PREDICATE.test(world.getBlockState(blockPos))) {
                return true;
            }
            return world.getBlockState(blockPos.below()).is(Blocks.FERN);
        }
        return false;
    }

    @Override
    public void start() {
        timer = 40;
        world.broadcastEntityEvent(mob, (byte) 10);
        mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        timer = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return timer > 0;
    }

    public int getTimer() {
        return timer;
    }

    @Override
    public void tick() {
        timer = Math.max(0, timer - 1);
        if (timer == adjustedTickDelay(4)) {
            BlockPos blockPos = mob.blockPosition();
            if (FERN_PREDICATE.test(world.getBlockState(blockPos))) {
                mob.playSound(SoundEventsInit.JOLLY_LLAMA_DETECT_FERN.get(), 1.0f, 1.0f);
                if (world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    world.destroyBlock(blockPos, false);
                }
                mob.ate();
            } else {
                BlockPos blockPos2 = blockPos.below();
                if (world.getBlockState(blockPos2).is(Blocks.FERN)) {
                    if (world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        world.levelEvent(2001, blockPos2, Block.getId(Blocks.FERN.defaultBlockState()));
                        world.setBlock(blockPos2, Blocks.DIRT.defaultBlockState(), 2);
                    }

                    mob.ate();
                }
            }
        }
    }

}