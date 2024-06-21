package slexom.earthtojava.mixins;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatRenderer.class)
public class CatRendererMixin {
    @Unique
    final ResourceLocation PEANUT_BUTTER_TEXTURE = ResourceLocation.parse("earthtojavamobs:textures/mobs/cat/peanut_butter/peanut_butter_jellie.png");

    @Inject(at = @At("HEAD"), method = "getTextureLocation*", cancellable = true)
    public void e2j_getTextureLocation(Cat cat, CallbackInfoReturnable<ResourceLocation> cir) {
        String catName = ChatFormatting.stripFormatting(cat.getName().getString());
        if ("Peanut Butter".equals(catName)) {
            cir.setReturnValue(PEANUT_BUTTER_TEXTURE);
        }
    }

}
