package slexom.earthtojava.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import slexom.earthtojava.init.EntityTypesInit;

@Mixin(Wolf.class)
public class WolfMixin {
    @ModifyReturnValue(method = "method_18444(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At("RETURN"))
    private static boolean earth2java_followTamedPredicate(boolean original, @NotNull LivingEntity entity) {
        EntityType<?> entityType = entity.getType();

        return original
                || entityType.equals(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get())
                || entityType.equals(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT.get())
                || entityType.equals(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get())
                || entityType.equals(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT.get())
                || entityType.equals(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT.get())
                || entityType.equals(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get());
    }

}
