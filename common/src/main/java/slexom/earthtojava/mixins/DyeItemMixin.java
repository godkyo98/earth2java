package slexom.earthtojava.mixins;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

@Mixin(DyeItem.class)
public class DyeItemMixin {

    @Inject(at = @At("HEAD"), method = "interactLivingEntity", cancellable = true)
    public void earth2java_interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> returnable) {
        if (entity instanceof E2JBaseMonoColorSheepEntity) {
            returnable.setReturnValue(InteractionResult.PASS);
        }
    }

}
