package slexom.earthtojava.entity;


import net.minecraft.util.RandomSource;

public class BlinkManager {
	private final RandomSource random = RandomSource.create();

	private int lastBlink = 0;
	private int nextBlinkInterval = random.nextInt(300) + 60;
	private int remainingTick = 0;
	private int internalBlinkTick = 0;

	public void tickBlink() {
		if (remainingTick > 0) {
			--remainingTick;
		}
		if (internalBlinkTick == (lastBlink + nextBlinkInterval)) {
			lastBlink = internalBlinkTick;
			nextBlinkInterval = random.nextInt(740) + 60;
			remainingTick = 4;
		}
		++internalBlinkTick;
	}

	public int getBlinkRemainingTicks() {
		return remainingTick;
	}
}
