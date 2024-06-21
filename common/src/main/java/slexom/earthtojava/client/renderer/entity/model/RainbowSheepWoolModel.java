package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;

@Environment(EnvType.CLIENT)
public class RainbowSheepWoolModel<T extends RainbowSheepEntity> extends QuadrupedModel<T> {
    private float headRotationAngleX;

    public RainbowSheepWoolModel(ModelPart root) {
        super(root, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 6.0F, -8.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(22, 36).addBox(-6.0F, -11.5F, -8.5F, 12.0F, 19.0F, 9.0F, new CubeDeformation(1.75F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        CubeListBuilder hindLegModelPartBuilder = CubeListBuilder.create().texOffs(0, 29).addBox(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.5F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, hindLegModelPartBuilder, PartPose.offset(-3.0F, 12.0F, 7.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, hindLegModelPartBuilder, PartPose.offset(3.0F, 12.0F, 7.0F));
        CubeListBuilder frontLegModelPartBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.5F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, frontLegModelPartBuilder, PartPose.offset(-3.0F, 12.0F, -5.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, frontLegModelPartBuilder, PartPose.offset(3.0F, 12.0F, -5.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        head.y = 6.0F + entityIn.getHeadEatPositionScale(partialTick) * 9.0F;
        headRotationAngleX = entityIn.getHeadEatAngleScale(partialTick);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        head.xRot = headRotationAngleX;
    }
}