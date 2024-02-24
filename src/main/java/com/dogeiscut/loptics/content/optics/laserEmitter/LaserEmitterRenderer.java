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

import net.minecraft.util.math.RotationAxis;

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
		double thickness = blockEntity.getLaserStrength(); // Adjust thickness based on preference

		drawLaser(matrices, vertexConsumers.getBuffer(RenderLayer.getCutoutMipped()), direction, distance, thickness);
	}

	private void drawLaser(MatrixStack matrices, VertexConsumer vertexConsumer, Direction direction, double laserDist, double laserThickness) {

		float size = ((float) laserThickness);

		float p = size / 2;
		float n = -size / 2;

		float dist = ((float) laserDist) + 0.5f;

		matrices.push();
		matrices.translate(0.5, 0.5, 0.5);
		matrices.multiply(direction.getRotationQuaternion());
		// Front face
		renderBeamVertex(matrices, vertexConsumer, n, 0, p, 0, 0);
		renderBeamVertex(matrices, vertexConsumer, p, 0, p, 1, 0);
		renderBeamVertex(matrices, vertexConsumer, p, dist, p, 1, 1);
		renderBeamVertex(matrices, vertexConsumer, n, dist, p, 0, 1);

		// Right face
		renderBeamVertex(matrices, vertexConsumer, p, 0, p, 0, 0);
		renderBeamVertex(matrices, vertexConsumer, p, 0, n, 1, 0);
		renderBeamVertex(matrices, vertexConsumer, p, dist, n, 1, 1);
		renderBeamVertex(matrices, vertexConsumer, p, dist, p, 0, 1);

		// Back face
		renderBeamVertex(matrices, vertexConsumer, p, 0, n, 0, 0);
		renderBeamVertex(matrices, vertexConsumer, n, 0, n, 1, 0);
		renderBeamVertex(matrices, vertexConsumer, n, dist, n, 1, 1);
		renderBeamVertex(matrices, vertexConsumer, p, dist, n, 0, 1);

		// Left face
		renderBeamVertex(matrices, vertexConsumer, n, 0, n, 0, 0);
		renderBeamVertex(matrices, vertexConsumer, n, 0, p, 1, 0);
		renderBeamVertex(matrices, vertexConsumer, n, dist, p, 1, 1);
		renderBeamVertex(matrices, vertexConsumer, n, dist, n, 0, 1);

		// Top face
		renderBeamVertex(matrices, vertexConsumer, n, dist, n, 0, 0);
		renderBeamVertex(matrices, vertexConsumer, n, dist, p, 0, 1);
		renderBeamVertex(matrices, vertexConsumer, p, dist, p, 1, 1);
		renderBeamVertex(matrices, vertexConsumer, p, dist, n, 1, 0);
		matrices.pop();
	}


	private static void renderBeamVertex(MatrixStack matrices, VertexConsumer vertices,float x, float y, float z, float u, float v) {
		matrices.push();
		matrices.translate(x, y, z);
		vertices.vertex(matrices.peek().getPositionMatrix(), 0.0F, 0.0F, 0.0F)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.texture(u, v)
				.overlay(OverlayTexture.DEFAULT_UV)
				.light(15728880)
				.normal(0.0F, 1.0F, 0.0F)
				.next();
		matrices.pop();
	}

}
