package slexom.earthtojava.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {

    @Final
    @Shadow
    private static Predicate<BlockState> PUMPKINS_PREDICATE;

    @Unique
    private BlockPattern furnaceGolemPattern;
    @Unique
    private BlockPattern furnaceGolemDispenserPattern;

    private BlockPattern e2j_getFurnaceGolemPattern() {
        if (furnaceGolemPattern == null) {
            furnaceGolemPattern = BlockPatternBuilder.start().aisle("~^~", "#@#", "~#~").where('@', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', pos -> pos.getState().isAir()).build();
        }
        return furnaceGolemPattern;
    }

    private BlockPattern e2j_getFurnaceGolemDispenserPattern() {
        if (furnaceGolemDispenserPattern == null) {
            furnaceGolemDispenserPattern = BlockPatternBuilder.start().aisle("~ ~", "#@#", "~#~").where('@', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', pos -> pos.getState().isAir()).build();
        }
        return furnaceGolemDispenserPattern;
    }

    @Inject(method = "canSpawnGolem", at = @At("RETURN"), cancellable = true)
    public void e2j_canSpawnGolem(LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            cir.setReturnValue(e2j_getFurnaceGolemDispenserPattern().find(levelReader, blockPos) != null);
        }
    }

    @Inject(at = @At("HEAD"), method = "trySpawnGolem")
    public void e2j_spawnFurnaceGolem(Level level, BlockPos blockPos, CallbackInfo ci) {
        BlockPattern.BlockPatternMatch result = e2j_getFurnaceGolemPattern().find(level, blockPos);
        if (result == null) return;

        FurnaceGolemEntity furnaceGolemEntity = EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get().create(level);
        if (furnaceGolemEntity == null) return;

        furnaceGolemEntity.setPlayerCreated(true);
        CarvedPumpkinBlock.spawnGolemInWorld(level, result, furnaceGolemEntity, result.getBlock(1, 2, 0).getPos());
    }
}
