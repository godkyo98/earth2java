package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

@Environment(EnvType.CLIENT)
public class E2JShearableCowModel<T extends E2JBaseShearableCowEntity> extends CowModel<T> {
    private float headPitchModifier;

    public E2JShearableCowModel(ModelPart root) {
        super(root);
    }

    @Override
    public void prepareMobModel(T sheepEntity, float limbAngle, float limbDistance, float tickDelta) {
        super.prepareMobModel(sheepEntity, limbAngle, limbDistance, tickDelta);
        head.y = 4.0F + sheepEntity.getHeadEatPositionScale(tickDelta) * 7.0F;
        headPitchModifier = sheepEntity.getHeadEatAngleScale(tickDelta);
    }

    @Override
    public void setupAnim(T sheepEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setupAnim(sheepEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        head.xRot = headPitchModifier;
    }
}
