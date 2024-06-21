package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

@Environment(EnvType.CLIENT)
public class RainbowSheepModel<T extends E2JBaseMonoColorSheepEntity> extends SheepModel<T> {

    public RainbowSheepModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(36, 0).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 32);
    }

}
