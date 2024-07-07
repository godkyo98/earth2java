package slexom.earthtojava.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
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
    private static final Predicate<BlockState> IS_MELON = blockState -> blockState != null && (blockState.is(BlockInit.CARVED_MELON.get()) || blockState.is(BlockInit.MELON_LANTERN.get()));
    @Nullable
    private BlockPattern melonGolemBase;
    @Nullable
    private BlockPattern melonGolemFull;

    public CarvedMelonBlock(Properties settings) {
        super(settings);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (oldState.is(state.getBlock())) {
            return;
        }
        this.trySpawnGolem(level, pos);
    }

    public boolean canSpawnGolem(LevelReader level, BlockPos pos) {
        return getOrCreateSnowGolemBase().find(level, pos) != null;
    }

    private void trySpawnGolem(Level level, BlockPos pos) {
        BlockPattern.BlockPatternMatch blockPatternMatch = getOrCreateSnowGolemFull().find(level, pos);
        if (blockPatternMatch != null) {
            MelonGolemEntity melonGolemEntity = EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get().create(level);

            if (melonGolemEntity != null) {
                spawnGolemInWorld(level, blockPatternMatch, melonGolemEntity, blockPatternMatch.getBlock(0, 2, 0).getPos());
            }
        }
    }

    public static void spawnGolemInWorld(Level level, BlockPattern.BlockPatternMatch patternMatch, MelonGolemEntity entity, BlockPos pos) {
        clearPatternBlocks(level, patternMatch);
        entity.moveTo(pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F);
        level.addFreshEntity(entity);
        for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, entity.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, entity);
        }

        updatePatternBlocks(level, patternMatch);
    }

    public static void clearPatternBlocks(Level level, BlockPattern.BlockPatternMatch patternMatch) {
        for (int i = 0; i < patternMatch.getWidth(); ++i) {
            for (int j = 0; j < patternMatch.getHeight(); ++j) {
                BlockInWorld blockInWorld = patternMatch.getBlock(i, j, 0);
                level.setBlock(blockInWorld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                level.levelEvent(2001, blockInWorld.getPos(), Block.getId(blockInWorld.getState()));
            }
        }
    }

    public static void updatePatternBlocks(Level level, BlockPattern.BlockPatternMatch patternMatch) {
        for (int i = 0; i < patternMatch.getWidth(); ++i) {
            for (int j = 0; j < patternMatch.getHeight(); ++j) {
                BlockInWorld blockInWorld = patternMatch.getBlock(i, j, 0);
                level.blockUpdated(blockInWorld.getPos(), Blocks.AIR);
            }
        }
    }

    public MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private BlockPattern getOrCreateSnowGolemBase() {
        if (melonGolemBase == null) {
            melonGolemBase = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }
        return melonGolemBase;
    }

    private BlockPattern getOrCreateSnowGolemFull() {
        if (melonGolemFull == null) {
            melonGolemFull = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', BlockInWorld.hasState(IS_MELON)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }
        return melonGolemFull;
    }

}