package amymialee.peculiarpieces.client;

import amymialee.peculiarpieces.blockentities.PedestalBlockEntity;
import amymialee.peculiarpieces.blocks.PedestalBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    private ItemStack cachedStack;

    @Override
    public void render(PedestalBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        if (blockEntity.getCachedState().getBlock() instanceof PedestalBlock) {
            if (blockEntity.getCachedState().get(PedestalBlock.POWERED)) {
                ItemStack newStack = blockEntity.getStack(1);
                if (newStack != cachedStack) {
                    cachedStack = newStack;
                }
            } else {
                ItemStack newStack = blockEntity.getStack(0);
                if (newStack != cachedStack) {
                    cachedStack = newStack;
                }
            }
        }
        if (cachedStack != null && blockEntity.getWorld() != null) {
            double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 16.0) / 16.0;
            matrices.translate(0.5, 1.3 + offset, 0.5);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4));
            int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(cachedStack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, blockEntity.getWorld(), 0);
        }
        matrices.pop();
    }
}