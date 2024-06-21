package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import slexom.earthtojava.entity.passive.MuddyPigEntity;

@Environment(EnvType.CLIENT)
public class MuddyPigModel<T extends MuddyPigEntity> extends net.minecraft.client.model.QuadrupedModel<T> {

    public MuddyPigModel(ModelPart root) {
        super(root, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
        float mudBoxX = -1.0F;
        float mudBoxY = -5.0F;
        float mudBoxZ = -7.0F;
        MeshDefinition modelData = QuadrupedModel.createBodyMesh(6, dilation);
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, dilation)
                        .texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, dilation)
                        .texOffs(24, 0).addBox(mudBoxX, mudBoxY, mudBoxZ, 4.0F, 1.0F, 4.0F, dilation)
                        .texOffs(40, 0).addBox(mudBoxX, mudBoxY - 6.0F, mudBoxZ + 2.0F, 4.0F, 6.0F, 1.0F, dilation),
                PartPose.offset(0.0F, 12.0F, -6.0F));
        return LayerDefinition.create(modelData, 64, 32);

    }

    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        head.zRot = entityIn.getShakeAngle(partialTick, -0.07F);
        head.xRot = (float) Math.PI / 8.0F;
        body.zRot = entityIn.getShakeAngle(partialTick, -0.14F);
    }

}
