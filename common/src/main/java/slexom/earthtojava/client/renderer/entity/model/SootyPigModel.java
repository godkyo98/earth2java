package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import slexom.earthtojava.entity.base.E2JBasePigEntity;

@Environment(EnvType.CLIENT)
public class SootyPigModel extends QuadrupedModel<E2JBasePigEntity> {
    public SootyPigModel(ModelPart root) {
        super(root, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    private static CubeListBuilder legsModelPartBuilder(int textureX, int textureY, CubeDeformation dilation) {
        return CubeListBuilder.create().texOffs(textureX, textureY).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, dilation);
    }

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
        MeshDefinition modelData = QuadrupedModel.createBodyMesh(6, dilation);
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, dilation).texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, dilation), PartPose.offset(0.0F, 12.0F, -6.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, legsModelPartBuilder(0, 42, dilation), PartPose.offset(-3.0F, 18.0F, 7.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, legsModelPartBuilder(16, 42, dilation), PartPose.offset(3.0F, 18.0F, 7.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, legsModelPartBuilder(0, 32, dilation), PartPose.offset(-3.0F, 18.0F, -5.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, legsModelPartBuilder(16, 32, dilation), PartPose.offset(3.0F, 18.0F, -5.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }
}
