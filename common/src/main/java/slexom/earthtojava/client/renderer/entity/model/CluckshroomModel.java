package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import slexom.earthtojava.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomModel<T extends CluckshroomEntity> extends ChickenModel<T> {

    public CluckshroomModel(ModelPart root) {
        super(root);
    }

    public ModelPart getHead() {
        return head;
    }
}
