package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import slexom.earthtojava.entity.monster.BoulderingZombieEntity;

@Environment(EnvType.CLIENT)
public class BoulderingZombieModel<T extends BoulderingZombieEntity> extends ZombieModel<T> {
    public BoulderingZombieModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static MeshDefinition getModelData(CubeDeformation dilation, float pivotOffsetY) {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation), PartPose.offset(0.0F, 0.0F + pivotOffsetY, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation.extend(0.5F)), PartPose.offset(0.0F, 0.0F + pivotOffsetY, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation), PartPose.offset(0.0F, 0.0F + pivotOffsetY, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(16, 32).addBox(-3.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, dilation), PartPose.offset(-5.0F, 2.0F + pivotOffsetY, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(34, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, dilation), PartPose.offset(5.0F, 2.0F + pivotOffsetY, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), PartPose.offset(-1.9F, 12.0F + pivotOffsetY, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(0, 34).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), PartPose.offset(1.9F, 12.0F + pivotOffsetY, 0.0F));
        return modelData;
    }

    public static LayerDefinition getTexturedModelData() {
        return LayerDefinition.create(getModelData(CubeDeformation.NONE, 0.0F), 64, 64);
    }

    @Override
    public void prepareMobModel(T livingEntity, float f, float g, float h) {
        rightArmPose = ArmPose.EMPTY;
        leftArmPose = ArmPose.EMPTY;
        super.prepareMobModel(livingEntity, f, g, h);
    }

    @Override
    public void setupAnim(T livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setupAnim(livingEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        if (livingEntity.isClimbing()) {
            rightArm.yRot = -0.4F;
            rightArm.xRot = (float) Math.PI + Mth.cos(animationProgress * 0.65F + (float) Math.PI) * 0.33F;
            leftArm.yRot = 0.4F;
            leftArm.xRot = (float) Math.PI + Mth.cos(animationProgress * 0.65F) * 0.33F;
        }
    }

}
