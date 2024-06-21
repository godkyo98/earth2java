package slexom.earthtojava.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.function.Predicate;

public class CarvedMelonBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<CarvedMelonBlock> CODEC = simpleCodec(CarvedMelonBlock::new);
    private static final Predicate<BlockState> IS_MELON = blockState -> blockState != null && (blockState.getBlock() == BlockInit.CARVED_MELON.get() || blockState.getBlock() == BlockInit.MELON_LANTERN.get());
    @Nullable
    private BlockPattern snowmanBasePattern;
    @Nullable
    private BlockPattern snowmanPattern;

    public CarvedMelonBlock(Properties settings) {
        super(settings);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public static void spawnGolemInWorld(Level world, BlockPattern.BlockPatternMatch patternResult, MelonGolemEntity entity, BlockPos pos) {
        clearPatternBlocks(world, patternResult);
        entity.moveTo(pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F);
        world.addFreshEntity(entity);
        for (ServerPlayer serverPlayer : world.getEntitiesOfClass(ServerPlayer.class, entity.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, entity);
        }

        updatePatternBlocks(world, patternResult);
    }

    public static void clearPatternBlocks(Level world, BlockPattern.BlockPatternMatch patternResult) {
        for (int i = 0; i < patternResult.getWidth(); ++i) {
            for (int j = 0; j < patternResult.getHeight(); ++j) {
                BlockInWorld cachedBlockPosition = patternResult.getBlock(i, j, 0);
                world.setBlock(cachedBlockPosition.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(2001, cachedBlockPosition.getPos(), Block.getId(cachedBlockPosition.getState()));
            }
        }
    }

    public static void updatePatternBlocks(Level level, BlockPattern.BlockPatternMatch blockPatternMatch) {
        for (int i = 0; i < blockPatternMatch.getWidth(); ++i) {
            for (int j = 0; j < blockPatternMatch.getHeight(); ++j) {
                BlockInWorld blockInWorld = blockPatternMatch.getBlock(i, j, 0);
                level.blockUpdated(blockInWorld.getPos(), Blocks.AIR);
            }
        }
    }

    public MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState2.is(blockState.getBlock())) {
            this.trySpawnGolem(level, blockPos);
        }
    }

    public boolean canSpawnGolem(LevelReader levelReader, BlockPos blockPos) {
        return getSnowmanBasePattern().find(levelReader, blockPos) != null;
    }

    private void trySpawnGolem(Level world, BlockPos pos) {
        BlockPattern.BlockPatternMatch result = getSnowmanPattern().find(world, pos);
        if (result != null) {
            MelonGolemEntity melonGolemEntity = EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get().create(world);

            if (melonGolemEntity != null) {
                spawnGolemInWorld(world, result, melonGolemEntity, result.getBlock(0, 2, 0).getPos());
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private BlockPattern getSnowmanBasePattern() {
        if (snowmanBasePattern == null) {
            snowmanBasePattern = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }
        return snowmanBasePattern;
    }

    private BlockPattern getSnowmanPattern() {
        if (snowmanPattern == null) {
            snowmanPattern = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', BlockInWorld.hasState(IS_MELON)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }
        return snowmanPattern;
    }

}