package slexom.earthtojava.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import slexom.earthtojava.entity.base.*;

public class E2JEntityRendererFactories {

    private E2JEntityRendererFactories() {
    }

    public static EntityRendererProvider<E2JBaseChickenEntity> chickenRendererFactory(String identifier) {
        return context -> new E2JChickenRenderer(context, identifier);
    }

    public static EntityRendererProvider<E2JBaseCowEntity> cowRendererFactory(String identifier) {
        return context -> new E2JCowRenderer(context, identifier);

    }

    public static EntityRendererProvider<E2JBaseShearableCowEntity> shearableCowRendererFactory(String identifier) {
        return context -> new E2JShearableCowRenderer(context, identifier);
    }

    public static EntityRendererProvider<E2JBaseMonoColorSheepEntity> monoColorSheepRendererFactory(String identifier) {
        return context -> new E2JMonoColorSheepRenderer(context, identifier);
    }

    public static EntityRendererProvider<E2JBasePigEntity> pigRendererFactory(String identifier) {
        return context -> new E2JPigRenderer(context, identifier);
    }

    public static EntityRendererProvider<E2JBaseRabbitEntity> rabbitRendererFactory(String identifier) {
        return context -> new E2JRabbitRenderer(context, identifier);
    }

}