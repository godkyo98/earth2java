package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ColorableAgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import slexom.earthtojava.entity.monster.SkeletonWolfEntity;

@Environment(EnvType.CLIENT)
public class SkeletonWolfModel<T extends SkeletonWolfEntity> extends ColorableAgeableListModel<T> {
    private static final String REAL_HEAD = "real_head";
    private static final String UPPER_BODY = "upper_body";
    private static final String REAL_TAIL = "real_tail";
    private final ModelPart head;
    private final ModelPart realHead;
    private final ModelPart torso;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private final ModelPart realTail;
    private final ModelPart neck;

    public SkeletonWolfModel(ModelPart root) {
        head = root.getChild(PartNames.HEAD);
        realHead = head.getChild(REAL_HEAD);
        torso = root.getChild(PartNames.BODY);
        neck = root.getChild(UPPER_BODY);
        rightHindLeg = root.getChild(PartNames.RIGHT_HIND_LEG);
        leftHindLeg = root.getChild(PartNames.LEFT_HIND_LEG);
        rightFrontLeg = root.getChild(PartNames.RIGHT_FRONT_LEG);
        leftFrontLeg = root.getChild(PartNames.LEFT_FRONT_LEG);
        tail = root.getChild(PartNames.TAIL);
        realTail = tail.getChild(REAL_TAIL);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition modelPartData2 = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(-1.0F, 13.5F, -7.0F));
        modelPartData2.addOrReplaceChild(REAL_HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F).texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F).texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F).texOffs(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F), PartPose.ZERO);
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(18, 14).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(UPPER_BODY, CubeListBuilder.create().texOffs(21, 0).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(-1.0F, 14.0F, -3.0F, 1.5707964F, 0.0F, 0.0F));
        CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F);
        modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, modelPartBuilder, PartPose.offset(-2.5F, 16.0F, 7.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, modelPartBuilder, PartPose.offset(0.5F, 16.0F, 7.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, modelPartBuilder, PartPose.offset(-2.5F, 16.0F, -4.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, modelPartBuilder, PartPose.offset(0.5F, 16.0F, -4.0F));
        PartDefinition modelPartData3 = modelPartData.addOrReplaceChild(PartNames.TAIL, CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 12.0F, 8.0F, 0.62831855F, 0.0F, 0.0F));
        modelPartData3.addOrReplaceChild(REAL_TAIL, CubeListBuilder.create().texOffs(9, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F), PartPose.ZERO);
        return LayerDefinition.create(modelData, 64, 32);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(torso, rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg, tail, neck);
    }

    @Override
    public void prepareMobModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
        if (entity.isAngry()) {
            tail.yRot = 0.0F;
        } else {
            tail.yRot = Mth.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        }
        torso.setPos(0.0F, 14.0F, 2.0F);
        torso.xRot = 1.5707964F;
        neck.setPos(-1.0F, 14.0F, -3.0F);
        neck.xRot = torso.xRot;
        tail.setPos(-1.0F, 12.0F, 8.0F);
        rightHindLeg.setPos(-2.5F, 16.0F, 7.0F);
        leftHindLeg.setPos(0.5F, 16.0F, 7.0F);
        rightFrontLeg.setPos(-2.5F, 16.0F, -4.0F);
        leftFrontLeg.setPos(0.5F, 16.0F, -4.0F);
        rightHindLeg.xRot = Mth.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        leftHindLeg.xRot = Mth.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        rightFrontLeg.xRot = Mth.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        leftFrontLeg.xRot = Mth.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headyRot, float headxRot) {
        head.xRot = headxRot * ((float) Math.PI / 180F);
        head.yRot = headyRot * ((float) Math.PI / 180F);
        tail.xRot = animationProgress;
    }

}