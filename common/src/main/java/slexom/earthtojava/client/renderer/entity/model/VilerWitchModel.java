package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

@Environment(EnvType.CLIENT)
public class VilerWitchModel<T extends Entity> extends VillagerModel<T> {

    private boolean liftingNose;

    public VilerWitchModel(ModelPart root) {
        super(root);
    }

//    public VilerWitchModel(float scale) {
//        super(scale, 96, 128);
//        this.wart.setPivot(0.0F, -2.0F, 0.0F);
//        this.wart.setTextureOffset(0, 0).addCuboid(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, -0.25F);
//        this.nose.addOrReplaceChild(this.wart);
//
//        this.head = (new ModelPart(this)).setTextureSize(96, 128);
//        this.head.setPivot(0.0F, 0.0F, 0.0F);
//        this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, scale);
//
//        this.field_17141 = (new ModelPart(this)).setTextureSize(96, 128);
//        this.field_17141.setPivot(-10.0F, -11.03125F, -10.0F);
//        this.field_17141.setTextureOffset(0, 64).addCuboid(0.0F, 0.0F, 0.0F, 20.0F, 3.0F, 20.0F);
//        this.head.addOrReplaceChild(this.field_17141);
//        this.head.addOrReplaceChild(this.nose);
//
//        ModelPart hatBox1 = (new ModelPart(this)).setTextureSize(96, 128);
//        hatBox1.setPivot(6.75F, -4.0F, 7.0F);
//        hatBox1.setTextureOffset(0, 87).addCuboid(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F);
//        hatBox1.pitch = -0.05235988F;
//        hatBox1.roll = 0.02617994F;
//        this.field_17141.addOrReplaceChild(hatBox1);
//        ModelPart hatBox2 = (new ModelPart(this)).setTextureSize(96, 128);
//        hatBox2.setPivot(1.75F, -4.0F, 2.0F);
//        hatBox2.setTextureOffset(0, 98).addCuboid(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F);
//        hatBox2.pitch = -0.10471976F;
//        hatBox2.roll = 0.05235988F;
//        hatBox1.addOrReplaceChild(hatBox2);
//        ModelPart hatPoint = (new ModelPart(this)).setTextureSize(96, 128);
//        hatPoint.setPivot(1.75F, -2.0F, 2.0F);
//        hatPoint.setTextureOffset(16, 103).addCuboid(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.25F);
//        hatPoint.pitch = -0.20943952F;
//        hatPoint.roll = 0.10471976F;
//        hatBox2.addOrReplaceChild(hatPoint);
//        this.torso = (new ModelPart(this)).setTextureSize(96, 128);
//        this.torso.setPivot(0.0F, 0.0F, 0.0F);
//        this.torso.setTextureOffset(16, 20).addCuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, scale);
//        this.robe = (new ModelPart(this)).setTextureSize(96, 128);
//        this.robe.setPivot(0.0F, 0.0F, 0.0F);
//        this.robe.setTextureOffset(0, 38).addCuboid(-5.0F, 0.0F, -3.0F, 10.0F, 19.0F, 7.0F, scale + 0.5F);
//        this.torso.addOrReplaceChild(this.robe);
//        this.arms.setTextureSize(96, 128).setTextureOffset(68, 0).addCuboid(-8.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, scale);
//        this.arms.setTextureSize(96, 128).setTextureOffset(68, 0).addCuboid(4.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, scale, true);
//        this.hood.setPivot(-4.0F, -10.9F, -4.0F);
//        this.hood.setTextureOffset(32, 0).addCuboid(-0.5F, 0.0F, -0.5F, 9.0F, 11.0F, 9.0F);
//        this.head.addOrReplaceChild(this.hood);
//    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = VillagerModel.createBodyModel();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
        PartDefinition hat = head.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 20.0F, 3.0F, 20.0F), PartPose.offset(-10.0F, -11.03125F, -10.0F));
        PartDefinition hat2 = hat.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F), PartPose.offsetAndRotation(6.75F, -4.0F, 7.0F, -0.05235988F, 0.0F, 0.02617994F));
        PartDefinition hat3 = hat2.addOrReplaceChild("hat3", CubeListBuilder.create().texOffs(0, 98).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F));
        hat3.addOrReplaceChild("hat4", CubeListBuilder.create().texOffs(16, 103).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, -0.20943952F, 0.0F, 0.10471976F));
        hat.addOrReplaceChild(PartNames.HAT_RIM, CubeListBuilder.create().texOffs(20, 103).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), PartPose.ZERO);
        PartDefinition nose = head.getChild(PartNames.NOSE);
        nose.addOrReplaceChild("mole", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        PartDefinition body = modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F), PartPose.ZERO);
        body.addOrReplaceChild(PartNames.JACKET, CubeListBuilder.create().texOffs(0, 38).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 19.0F, 7.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
        modelPartData.addOrReplaceChild(PartNames.ARMS, CubeListBuilder.create()
                        .texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F)
                        .texOffs(44, 22).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, true)
                        .texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F)
                        .texOffs(68, 0).addBox(-8.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F)
                        .texOffs(68, 0).addBox(4.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, true)
                , PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
        head.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, 0.0F, -0.5F, 9.0F, 11.0F, 9.0F), PartPose.offset(-4.0F, -10.9F, -4.0F));
        return LayerDefinition.create(modelData, 96, 128);
    }


    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        nose.setPos(0.0F, -2.0F, 0.0F);
        float f = 0.01F * (entity.getId() % 10);
        nose.xRot = Mth.sin(entity.tickCount * f) * 4.5F * 0.017453292F;
        nose.yRot = 0.0F;
        nose.zRot = Mth.cos(entity.tickCount * f) * 2.5F * 0.017453292F;
        if (liftingNose) {
            nose.setPos(0.0F, 1.0F, -1.5F);
            nose.xRot = -0.9F;
        }

    }

    public ModelPart getNose() {
        return nose;
    }

    public void setLiftingNose(boolean liftingNose) {
        this.liftingNose = liftingNose;
    }
}
