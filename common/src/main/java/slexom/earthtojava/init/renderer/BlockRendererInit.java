package slexom.earthtojava.init.renderer;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import slexom.earthtojava.init.BlockInit;

public class BlockRendererInit {
	private BlockRendererInit() {
		throw new IllegalStateException("Utility class");
	}

	public static void init() {
		RenderTypeRegistry.register(RenderType.cutout(), BlockInit.BUTTERCUP.get());
		RenderTypeRegistry.register(RenderType.cutout(), BlockInit.POTTED_BUTTERCUP.get());
		RenderTypeRegistry.register(RenderType.cutout(), BlockInit.PINK_DAISY.get());
		RenderTypeRegistry.register(RenderType.cutout(), BlockInit.POTTED_PINK_DAISY.get());
	}
}
