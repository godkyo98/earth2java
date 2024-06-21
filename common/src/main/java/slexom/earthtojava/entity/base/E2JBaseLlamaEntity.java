package slexom.earthtojava.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.BlinkManager;

public class E2JBaseLlamaEntity extends Llama {

    public final BlinkManager blinkManager;

    public E2JBaseLlamaEntity(EntityType<? extends Llama> type, Level worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
    }

    public static AttributeSupplier.Builder createJollyLlamaAttributes() {
        return createBaseChestedHorseAttributes().add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        blinkManager.tickBlink();
    }

}
