package slexom.earthtojava.client.renderer.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.block.entity.RainbowBedBlockEntity;
import slexom.earthtojava.init.BlockEntityTypeInit;
import slexom.earthtojava.init.EntityModelLayersInit;

@Environment(EnvType.CLIENT)
public class RainbowBedBlockEntityRenderer implements BlockEntityRenderer<RainbowBedBlockEntity> {
    private final ModelPart head;
    private final ModelPart foot;

    public RainbowBedBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        head = context.bakeLayer(EntityModelLayersInit.RAINBOW_BED_HEAD_MODEL_LAYER);
        foot = context.bakeLayer(EntityModelLayersInit.RAINBOW_BED_FOOT_MODEL_LAYER);
    }

    public static LayerDefinition createHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(50, 6).addBox(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 1.5707964F));
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(50, 18).addBox(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 3.1415927F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createFootLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(50, 0).addBox(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(50, 12).addBox(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 4.712389F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public void render(RainbowBedBlockEntity bedBlockEntity, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int light, int overlay) {
        Material spriteIdentifier = new Material(Sheets.BED_SHEET, ResourceLocation.parse("earthtojavamobs:entity/bed/rainbow"));
        Level world = bedBlockEntity.getLevel();
        if (world != null) {
            BlockState blockState = bedBlockEntity.getBlockState();
            DoubleBlockCombiner.NeighborCombineResult<RainbowBedBlockEntity> propertySource = DoubleBlockCombiner.combineWithNeigbour(BlockEntityTypeInit.RAINBOW_BED.get(), RainbowBedBlock::getBlockType, RainbowBedBlock::getConnectedDirection, ChestBlock.FACING, blockState, world, bedBlockEntity.blockPosition(), (levelAccessor, blockPos) -> false);
            int k = propertySource.apply(new BrightnessCombiner<>()).get(light);
            renderPiece(matrixStack, vertexConsumerProvider, blockState.getValue(RainbowBedBlock.PART) == BedPart.HEAD ? head : foot, blockState.getValue(RainbowBedBlock.FACING), spriteIdentifier, k, overlay, false);
        } else {
            renderPiece(matrixStack, vertexConsumerProvider, head, Direction.SOUTH, spriteIdentifier, light, overlay, false);
            renderPiece(matrixStack, vertexConsumerProvider, foot, Direction.SOUTH, spriteIdentifier, light, overlay, true);
        }
    }

    private void renderPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, ModelPart modelPart, Direction direction, Material material, int i, int j, boolean bl) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5625F, bl ? -1.0F : 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        VertexConsumer vertexConsumer = material.buffer(multiBufferSource, RenderType::entitySolid);
        modelPart.render(poseStack, vertexConsumer, i, j);
        poseStack.popPose();
    }

}