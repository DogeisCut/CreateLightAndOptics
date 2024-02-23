package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.dogeiscut.loptics.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;

import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

import net.minecraft.util.math.Direction;

import org.joml.Matrix4f;

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

		matrices.push();
		matrices.translate(0.5, 0.5, 0.5); // Translate to the center of the block

		drawUnlitCube(matrices, vertexConsumers.getBuffer(RenderLayer.getSolid()), light, overlay);

		matrices.pop();
	}

	private void drawUnlitCube(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay) {
		Matrix4f matrix = matrices.peek().getPositionMatrix();
		VertexConsumer buffer = vertexConsumer;

		float size = 1.0f; // Adjust the size of your cube as needed

		// Vertices of the cube
		float x1 = -size / 2;
		float y1 = -size / 2;
		float z1 = -size / 2;
		float x2 = size / 2;
		float y2 = size / 2;
		float z2 = size / 2;

		// Face 1 (Front)
		buffer.vertex(matrix, x1, y1, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 0).normal(0, 0, -1).next();
		buffer.vertex(matrix, x2, y1, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 0).normal(0, 0, -1).next();
		buffer.vertex(matrix, x2, y2, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 1).normal(0, 0, -1).next();
		buffer.vertex(matrix, x1, y2, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 1).normal(0, 0, -1).next();

		// Face 2 (Back)
		buffer.vertex(matrix, x2, y1, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 0).normal(0, 0, 1).next();
		buffer.vertex(matrix, x1, y1, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 0).normal(0, 0, 1).next();
		buffer.vertex(matrix, x1, y2, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 1).normal(0, 0, 1).next();
		buffer.vertex(matrix, x2, y2, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 1).normal(0, 0, 1).next();

		// Face 3 (Top)
		buffer.vertex(matrix, x1, y2, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 0).normal(0, 1, 0).next();
		buffer.vertex(matrix, x2, y2, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 0).normal(0, 1, 0).next();
		buffer.vertex(matrix, x2, y2, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 1).normal(0, 1, 0).next();
		buffer.vertex(matrix, x1, y2, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 1).normal(0, 1, 0).next();

		// Face 4 (Bottom)
		buffer.vertex(matrix, x1, y1, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 0).normal(0, -1, 0).next();
		buffer.vertex(matrix, x2, y1, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 0).normal(0, -1, 0).next();
		buffer.vertex(matrix, x2, y1, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 1).normal(0, -1, 0).next();
		buffer.vertex(matrix, x1, y1, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 1).normal(0, -1, 0).next();

		// Face 5 (Left)
		buffer.vertex(matrix, x1, y1, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 0).normal(-1, 0, 0).next();
		buffer.vertex(matrix, x1, y1, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 0).normal(-1, 0, 0).next();
		buffer.vertex(matrix, x1, y2, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 1).normal(-1, 0, 0).next();
		buffer.vertex(matrix, x1, y2, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 1).normal(-1, 0, 0).next();

		// Face 6 (Right)
		buffer.vertex(matrix, x2, y1, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 0).normal(1, 0, 0).next();
		buffer.vertex(matrix, x2, y1, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 0).normal(1, 0, 0).next();
		buffer.vertex(matrix, x2, y2, z2).color(255, 255, 255, 255).light(light).overlay(overlay).texture(1, 1).normal(1, 0, 0).next();
		buffer.vertex(matrix, x2, y2, z1).color(255, 255, 255, 255).light(light).overlay(overlay).texture(0, 1).normal(1, 0, 0).next();

	}



}
