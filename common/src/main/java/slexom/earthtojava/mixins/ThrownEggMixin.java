package slexom.earthtojava.mixins;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrownEgg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slexom.earthtojava.init.EntityTypesInit;


@Mixin(ThrownEgg.class)
public class ThrownEggMixin {
    private final RandomSource random = RandomSource.create();

    @Redirect(
            method = "onHit",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/EntityType;CHICKEN:Lnet/minecraft/world/entity/EntityType;"
            )
    )
    public EntityType<?> e2j_onHit() {
        int r = random.nextInt(20);
        return switch (r) {
            case 2 -> EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get();
            case 4 -> EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.get();
            case 6 -> EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get();
            case 8 -> EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get();
            case 10 -> EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.get();
            case 12 -> EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT.get();
            default -> EntityType.CHICKEN;
        };
    }

}
