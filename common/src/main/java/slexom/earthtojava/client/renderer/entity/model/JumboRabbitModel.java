package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import slexom.earthtojava.entity.passive.JumboRabbitEntity;

@Environment(EnvType.CLIENT)
public class JumboRabbitModel<T extends JumboRabbitEntity> extends EntityModel<T> {

    private static final String LEFT_HAUNCH = "left_haunch";
    private static final String RIGHT_HAUNCH = "right_haunch";
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHaunch;
    private final ModelPart rightHaunch;
    private final ModelPart body;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart head;
    private final ModelPart rightEar;
    private final ModelPart leftEar;
    private final ModelPart tail;
    private final ModelPart nose;
    private float jumpProgress;

    public JumboRabbitModel(ModelPart root) {
        leftHindLeg = root.getChild(PartNames.LEFT_HIND_FOOT);
        rightHindLeg = root.getChild(PartNames.RIGHT_HIND_FOOT);
        leftHaunch = root.getChild(LEFT_HAUNCH);
        rightHaunch = root.getChild(RIGHT_HAUNCH);
        body = root.getChild(PartNames.BODY);
        leftFrontLeg = root.getChild(PartNames.LEFT_FRONT_LEG);
        rightFrontLeg = root.getChild(PartNames.RIGHT_FRONT_LEG);
        head = root.getChild(PartNames.HEAD);
        rightEar = root.getChild(PartNames.RIGHT_EAR);
        leftEar = root.getChild(PartNames.LEFT_EAR);
        tail = root.getChild(PartNames.TAIL);
        nose = root.getChild(PartNames.NOSE);
    }

    public static LayerDefinition getTexturedModelData() {
        float bodyX = -3.5F;
        float bodyY = -6.0F;
        float bodyZ = -11.5F;
        float headX = bodyX + 1.5F;
        float headY = -8.0F;
        float headZ = -5.0F;
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_FOOT, CubeListBuilder.create().texOffs(24, 47).addBox(0.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F).mirror(), PartPose.offset(3.0F, 17.5F, 3.7F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_FOOT, CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F), PartPose.offset(-3.0F, 17.5F, 3.7F));
        modelPartData.addOrReplaceChild(LEFT_HAUNCH, CubeListBuilder.create().texOffs(42, 14).addBox(0.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F).mirror(), PartPose.offsetAndRotation(3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(RIGHT_HAUNCH, CubeListBuilder.create().texOffs(42, 29).addBox(-2.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F), PartPose.offsetAndRotation(-3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 0).addBox(bodyX, bodyY, bodyZ, 8.0F, 8.0F, 14.0F), PartPose.offsetAndRotation(0.0F, 19.0F, 8.0F, -0.34906584F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, CubeListBuilder.create().texOffs(8, 33).addBox(0.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F), PartPose.offsetAndRotation(3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F), PartPose.offsetAndRotation(-3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 22).addBox(headX, headY, headZ, 5.0F, 5.0F, 6.0F), PartPose.offset(0.0F, 16.0F, -1.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(48, 0).addBox(headX - 1.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F).texOffs(48, 11).addBox(headX, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, 0.0F, -0.2617994F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_EAR, CubeListBuilder.create().texOffs(56, 0).addBox(headX + 3.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F).texOffs(54, 11).addBox(headX + 3, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, 0.0F, 0.2617994F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.TAIL, CubeListBuilder.create().texOffs(22, 22).addBox(bodyX + 2.5F, bodyY + 1.0F, bodyZ + 14.0F, 3.0F, 3.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 20.0F, 7.0F, -0.3490659F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.NOSE, CubeListBuilder.create().texOffs(22, 27).addBox(headX + 2.0F, headY + 2.0F, headZ - 1.0F, 1.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 16.0F, -1.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    public void render(PoseStack poseStack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (young) {
            poseStack.pushPose();
            poseStack.scale(0.56666666F, 0.56666666F, 0.56666666F);
            poseStack.translate(0.0D, 1.375D, 0.125D);
            ImmutableList.of(head, leftEar, rightEar, nose).forEach((modelPart) -> modelPart.render(poseStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            poseStack.popPose();
            poseStack.pushPose();
            poseStack.scale(0.4F, 0.4F, 0.4F);
            poseStack.translate(0.0D, 2.25D, 0.0D);
            ImmutableList.of(leftHindLeg, rightHindLeg, leftHaunch, rightHaunch, body, leftFrontLeg, rightFrontLeg, tail).forEach(modelPart -> modelPart.render(poseStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            poseStack.popPose();
        } else {
            poseStack.pushPose();
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.translate(0.0D, 1.0D, 0.0D);
            ImmutableList.of(leftHindLeg, rightHindLeg, leftHaunch, rightHaunch, body, leftFrontLeg, rightFrontLeg, head, rightEar, leftEar, tail, nose).forEach(modelPart -> modelPart.render(poseStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            poseStack.popPose();
        }
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadyRot, float headxRot) {
        float f = ageInTicks - (float) entity.tickCount;
        nose.xRot = headxRot * ((float) Math.PI / 180F);
        head.xRot = headxRot * ((float) Math.PI / 180F);
        rightEar.xRot = headxRot * ((float) Math.PI / 180F);
        leftEar.xRot = headxRot * ((float) Math.PI / 180F);
        nose.yRot = netHeadyRot * ((float) Math.PI / 180F);
        head.yRot = netHeadyRot * ((float) Math.PI / 180F);
        rightEar.yRot = nose.yRot - 0.2617994F;
        leftEar.yRot = nose.yRot + 0.2617994F;
        jumpProgress = Mth.sin(entity.getJumpCompletion(f) * (float) Math.PI);
        leftHaunch.xRot = (jumpProgress * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        rightHaunch.xRot = (jumpProgress * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        leftHindLeg.xRot = jumpProgress * 50.0F * ((float) Math.PI / 180F);
        rightHindLeg.xRot = jumpProgress * 50.0F * ((float) Math.PI / 180F);
        leftFrontLeg.xRot = (jumpProgress * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        rightFrontLeg.xRot = (jumpProgress * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }

    @Override
    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        jumpProgress = Mth.sin(entityIn.getJumpCompletion(partialTick) * (float) Math.PI);
    }
}
