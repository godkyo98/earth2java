package slexom.earthtojava.block.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import slexom.earthtojava.init.BlockEntityTypeInit;

public class RainbowBedBlockEntity extends BlockEntity {
	private DyeColor color;

	public RainbowBedBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(BlockEntityTypeInit.RAINBOW_BED.get(), blockPos, blockState);
	}

	public RainbowBedBlockEntity(BlockPos blockPos, BlockState blockState, DyeColor dyeColor) {
		this(blockPos, blockState);
		setColor(dyeColor);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Environment(EnvType.CLIENT)
	public DyeColor getColor() {
		if (color == null) {
			color = ((BedBlock) getBlockState().getBlock()).getColor();
		}
		return color;
	}

	@Environment(EnvType.CLIENT)
	public void setColor(DyeColor color) {
		this.color = color;
	}
}