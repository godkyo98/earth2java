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
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import slexom.earthtojava.entity.passive.JollyLlamaEntity;

@Environment(EnvType.CLIENT)
public class JollyLlamaModel extends EntityModel<JollyLlamaEntity> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private float headPitchModifier;

    public JollyLlamaModel(ModelPart root) {
        head = root.getChild(PartNames.HEAD);
        body = root.getChild(PartNames.BODY);
        rightHindLeg = root.getChild(PartNames.RIGHT_HIND_LEG);
        leftHindLeg = root.getChild(PartNames.LEFT_HIND_LEG);
        rightFrontLeg = root.getChild(PartNames.RIGHT_FRONT_LEG);
        leftFrontLeg = root.getChild(PartNames.LEFT_FRONT_LEG);
    }

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, dilation).texOffs(0, 14).addBox("neck", -4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, dilation).texOffs(17, 0).addBox("ear", -4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, dilation).texOffs(17, 0).addBox("ear", 1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, dilation).texOffs(96, 16).addBox("bells", -4.5F, -16.0F, -6.5F, 9.0F, 18.0F, 7.0F, dilation).texOffs(96, 0).addBox("berries", -2.5F, -14.05F, -10.5F, 5.0F, 5.0F, 10.0F, dilation).texOffs(0, 47).addBox("horn_left", 4.5F, -30.0F, -2.0F, 8.0F, 16.0F, 1.0F, dilation).texOffs(0, 47).mirror().addBox("horn_right", -12.5F, -30.0F, -2.0F, 8.0F, 16.0F, 1.0F, dilation), PartPose.offset(0.0F, 7.0F, -6.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(29, 0).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, dilation), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(29, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, dilation);
        modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, modelPartBuilder, PartPose.offset(-3.5F, 10.0F, 6.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, modelPartBuilder, PartPose.offset(3.5F, 10.0F, 6.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, modelPartBuilder, PartPose.offset(-3.5F, 10.0F, -5.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, modelPartBuilder, PartPose.offset(3.5F, 10.0F, -5.0F));
        return LayerDefinition.create(modelData, 128, 64);
    }

    public void setupAnim(JollyLlamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        head.xRot = headPitch * ((float) Math.PI / 180F);
        head.yRot = headYaw * ((float) Math.PI / 180F);
        rightHindLeg.xRot = Mth.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        leftHindLeg.xRot = Mth.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        rightFrontLeg.xRot = Mth.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        leftFrontLeg.xRot = Mth.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        if (young) {
            matrices.pushPose();
            matrices.scale(0.71428573F, 0.64935064F, 0.7936508F);
            matrices.translate(0.0D, 1.3125D, 0.22D);
            head.render(matrices, vertices, light, overlay, color);
            matrices.popPose();
            matrices.pushPose();
            matrices.scale(0.625F, 0.45454544F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            body.render(matrices, vertices, light, overlay, color);
            matrices.popPose();
            matrices.pushPose();
            matrices.scale(0.45454544F, 0.41322312F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            ImmutableList.of(rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg).forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
            matrices.popPose();
        } else {
            ImmutableList.of(head, body, rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg).forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
        }
    }

    public void prepareMobModel(JollyLlamaEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        super.prepareMobModel(entity, limbAngle, limbDistance, tickDelta);
        head.y = 6.0F + entity.getNeckAngle(tickDelta) * 9.0F;
        headPitchModifier = entity.getHeadAngle(tickDelta);
    }
}