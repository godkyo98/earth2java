package slexom.earthtojava.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.init.BlockEntityTypeInit;

public class RainbowBedBlock extends BedBlock {
	public RainbowBedBlock(DyeColor colorIn, Properties properties) {
		super(colorIn, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityTypeInit.RAINBOW_BED.get().create(pos, state);
	}

}