package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.dogeiscut.loptics.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;

import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

import net.minecraft.util.math.Direction;

import net.minecraft.util.math.MathHelper;

import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class LaserEmitterRenderer extends KineticBlockEntityRenderer<LaserEmitterBlockEntity> {

	public LaserEmitterRenderer(BlockEntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(LaserEmitterBlockEntity be, BlockState state) {
		return CachedBufferer.partial(AllPartialModels.LASER_EMITTER_COG, state);
	}

	@Override
	public void renderSafe(LaserEmitterBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		BlockPos pos = blockEntity.getPos();
		Direction direction = blockEntity.getLaserDirection();
		double distance = blockEntity.getLaserDistance();
		double thickness = blockEntity.getLaserStrength() * 0.1; // Adjust thickness based on preference

		renderBeamLayer(matrices, vertexConsumers, new float[]{255f, 255f, 255f});
	}

	private static void renderBeamLayer(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float[] color) {
		matrices.push();
		matrices.translate(0.5D, 0.0D, 0.5D);

		float r = color[0];
		float g = color[1];
		float b = color[2];

		int yOffset = 0;

		int dist = 600;

		renderBeamFace(matrices, vertexConsumers.getBuffer(RenderLayer.getSolid()), r, g, b, 1.0F, yOffset, dist, 0.0F, 0.2F, 0.25F, 0.0F, 0.0F, 0.0F, 0.5f, 0.5f);

		matrices.pop();
	}

	private static void renderBeamFace(MatrixStack matrices, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float u1, float v1, float u2, float v2, float x1, float z1, float x2, float z2) {
		renderBeamVertex(matrices, vertices, red, green, blue, alpha, height, x1, z1, u2, v1);
		renderBeamVertex(matrices, vertices, red, green, blue, alpha, yOffset, x1, z1, u2, v2);
		renderBeamVertex(matrices, vertices, red, green, blue, alpha, yOffset, x2, z2, u1, v2);
		renderBeamVertex(matrices, vertices, red, green, blue, alpha, height, x2, z2, u1, v1);
	}

	private static void renderBeamVertex(MatrixStack matrices, VertexConsumer vertices, float red, float green, float blue, float alpha, int y, float x, float z, float u, float v) {
		matrices.push();
		matrices.translate(x, y, z);
		vertices.vertex(matrices.peek().getPositionMatrix(), 0.0F, 0.0F, 0.0F)
				.color(red, green, blue, alpha)
				.texture(u, v)
				.overlay(OverlayTexture.DEFAULT_UV)
				.light(15728880)
				.normal(0.0F, 1.0F, 0.0F)
				.next();
		matrices.pop();
	}

}
