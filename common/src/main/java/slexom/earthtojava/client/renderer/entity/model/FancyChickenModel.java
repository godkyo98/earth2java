package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import slexom.earthtojava.entity.passive.FancyChickenEntity;

@Environment(EnvType.CLIENT)
public class FancyChickenModel<T extends FancyChickenEntity> extends ChickenModel<T> {

    private static final String CREST = "crest";
    private final ModelPart tail;
    private final ModelPart crest;

    public FancyChickenModel(ModelPart root) {
        super(root);
        tail = root.getChild(PartNames.TAIL);
        crest = root.getChild(CREST);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 6.0F, 3.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        modelPartData.addOrReplaceChild(PartNames.BEAK, CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -6.0F, -4.0F, 4.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        modelPartData.addOrReplaceChild(RED_THING, CubeListBuilder.create().texOffs(14, 4).addBox(-0.5F, -4.0F, -3.0F, 1.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -4.0F, -1.0F, 6.0F, 8.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.5707964F, 0.0F, 0.0F));
        CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, -2.0F, -3.0F, 3.0F, 7.0F, 3.0F);
        modelPartData.addOrReplaceChild(PartNames.RIGHT_LEG, modelPartBuilder, PartPose.offset(-2.0F, 19.0F, 1.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_LEG, modelPartBuilder, PartPose.offset(1.0F, 19.0F, 1.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_WING, CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(-4.0F, 11.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_WING, CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(4.0F, 11.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.TAIL, CubeListBuilder.create().texOffs(48, 15).addBox(-1.0F, -12.0F, 8.0F, 1.0F, 10.0F, 7.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        modelPartData.addOrReplaceChild(CREST, CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -12.0F, -3.0F, 1.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        return LayerDefinition.create(modelData, 64, 32);

    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return new ImmutableList.Builder<ModelPart>().addAll(super.headParts()).add(crest).build();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return new ImmutableList.Builder<ModelPart>().addAll(super.bodyParts()).add(tail).build();
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        crest.xRot = head.xRot;
        crest.yRot = head.yRot;
    }
}
