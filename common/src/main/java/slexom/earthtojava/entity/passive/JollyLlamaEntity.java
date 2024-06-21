package slexom.earthtojava.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.ai.goal.JollyLlamaEatFernGoal;
import slexom.earthtojava.entity.base.E2JBaseLlamaEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class JollyLlamaEntity extends E2JBaseLlamaEntity {

	private int eatFernTimer;
	private JollyLlamaEatFernGoal eatFernGoal;

	public JollyLlamaEntity(EntityType<JollyLlamaEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
		eatFernGoal = new JollyLlamaEatFernGoal(this);
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
		goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2D));
		goalSelector.addGoal(2, new LlamaFollowCaravanGoal(this, 2.1D));
		goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
		goalSelector.addGoal(2, eatFernGoal);
		goalSelector.addGoal(5, new FollowParentGoal(this, 1.0D));
		goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	protected void customServerAiStep() {
		eatFernTimer = eatFernGoal.getTimer();
		super.customServerAiStep();
	}

	public void aiStep() {
		if (level().isClientSide) {
			eatFernTimer = Math.max(0, eatFernTimer - 1);
		}
		super.aiStep();
	}

	@Environment(EnvType.CLIENT)
	public void handleEntityEvent(byte status) {
		if (status == 10) {
			eatFernTimer = 40;
		} else {
			super.handleEntityEvent(status);
		}

	}

	@Environment(EnvType.CLIENT)
	public float getNeckAngle(float delta) {
		if (eatFernTimer <= 0) {
			return 0.0F;
		}
		if (eatFernTimer >= 4 && eatFernTimer <= 36) {
			return 1.0F;
		}
		return eatFernTimer < 4 ? ((float) eatFernTimer - delta) / 4.0F : -((float) (eatFernTimer - 40) - delta) / 4.0F;
	}

	@Environment(EnvType.CLIENT)
	public float getHeadAngle(float delta) {
		if (eatFernTimer > 4 && eatFernTimer <= 36) {
			float f = ((float) (eatFernTimer - 4) - delta) / 32.0F;
			return 0.62831855F + 0.21991149F * Mth.sin(f * 28.7F);
		}
		return eatFernTimer > 0 ? 0.62831855F : getXRot() * 0.017453292F;
	}

	public void onEatingGrass() {
		playSound(SoundEventsInit.JOLLY_LLAMA_EAT.get(), 0.5f, 1.0f);
	}


	@Override
	public void playAmbientSound() {
		super.playAmbientSound();
		playSound(SoundEventsInit.JOLLY_LLAMA_BELL.get(), 0.3f, 1.0f);
	}

	@Override
	public boolean canTakeItem(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canMate(Animal other) {
		return false;
	}

	@Override
	@Nullable
	public DyeColor getSwag() {
		return null;
	}
}
