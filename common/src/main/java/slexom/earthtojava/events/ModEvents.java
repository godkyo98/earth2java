package slexom.earthtojava.events;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.init.BlockInit;

public class ModEvents {

	public static void init() {
		melonBlockShearEvent();
	}

	private static void melonBlockShearEvent() {
		InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) -> {
			Level world = player.getCommandSenderWorld();
			if (world.isClientSide()) {
				return EventResult.pass();
			}
			BlockState blockState = world.getBlockState(pos);
			Block block = blockState.getBlock();
			if (block == Blocks.MELON) {
				ItemStack itemStack = player.getItemInHand(hand);
				if (itemStack.getItem() instanceof ShearsItem) {
					Direction direction = face.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : face;
					world.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
					world.setBlock(pos, BlockInit.CARVED_MELON.get().defaultBlockState().setValue(CarvedMelonBlock.FACING, direction), 11);
					ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5D + direction.getStepX() * 0.65D, pos.getY() + 0.1D, pos.getZ() + 0.5D + direction.getStepZ() * 0.65D, new ItemStack(Items.MELON_SEEDS, 4));
					itemEntity.setDeltaMovement(0.05D * direction.getStepX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * direction.getStepZ() + world.random.nextDouble() * 0.02D);
					world.addFreshEntity(itemEntity);
					itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
					return EventResult.interrupt(world.isClientSide);
				}
				return EventResult.pass();
			}
			return EventResult.pass();
		});


	}

}
