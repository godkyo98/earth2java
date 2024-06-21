package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

@Environment(EnvType.CLIENT)
public class HornedSheepModel<T extends HornedSheepEntity> extends SheepModel<T> {

    public HornedSheepModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        float hornX = -7.0F;
        float hornY = -5.0F;
        float hornZ = -4.0F;
        MeshDefinition modelData = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F).texOffs(0, 32).addBox(hornX, hornY, hornZ, 4.0F, 7.0F, 6.0F).texOffs(20, 32).addBox(hornX, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F).texOffs(0, 32).addBox(hornX + 10.0F, hornY, hornZ, 4.0F, 7.0F, 6.0F, true).texOffs(20, 32).addBox(hornX + 10.0F, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F, true), PartPose.offset(0.0F, 6.0F, -8.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }
}
