package slexom.earthtojava.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.init.BlockEntityTypeInit;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    @Shadow
    @Final
    private BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    @Inject(at = @At("HEAD"), method = "renderByItem", cancellable = true)
    private void e2jRenderRainbowBed(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, CallbackInfo ci) {
        Item item = stack.getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof RainbowBedBlock) {
                BlockState blockState = block.defaultBlockState();
                blockEntityRenderDispatcher.renderItem((BlockEntity) BlockEntityTypeInit.RAINBOW_BED.get().create(BlockPos.ZERO, blockState), matrices, vertexConsumers, light, overlay);
                ci.cancel();
            }
        }
    }

}
