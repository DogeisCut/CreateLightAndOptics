package com.dogeiscut.loptics.content.optics;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class LaserHoldingRenderer<T extends LaserHoldingBlockEntity> extends KineticBlockEntityRenderer<T> {

	public LaserHoldingRenderer(BlockEntityRendererFactory.Context context) {
		super(context);
	}

	private static void renderBeamVertex(MatrixStack matrices, VertexConsumer vertices, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
		matrices.push();
		matrices.translate(x, y, z);
		//todo: somehow figure out how to render this as a solid color instead of being textured.
		vertices.vertex(matrices.peek().getPositionMatrix(), 0.0F, 0.0F, 0.0F)
				.color(r, g, b, a)
				.texture(u, v)
				.overlay(OverlayTexture.DEFAULT_UV)
				.light(15728880)
				.normal(0.0F, 1.0F, 0.0F)
				.next();
		matrices.pop();
	}

	@Override
	public void renderSafe(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		Direction direction = blockEntity.getLaserDirection();
		double distance = Math.ceil(blockEntity.getLaserHitDistance());
		double thickness = (blockEntity.getLaserStrength() / 200) + 0.1;
		boolean active = blockEntity.getActive();
		if (active) {
			drawLaser(matrices, vertexConsumers.getBuffer(RenderLayer.getDebugQuads()), direction, distance, thickness, false);
			drawLaser(matrices, vertexConsumers.getBuffer(RenderLayer.getDebugQuads()), direction, distance, thickness + 0.1, true);
		}
	}

	private void drawLaser(MatrixStack matrices, VertexConsumer vertexConsumer, Direction direction, double laserDist, double laserThickness, boolean invert) {

		float size = ((float) laserThickness);

		float p = size / 2;
		float n = -size / 2;

		float dist = ((float) laserDist);

		matrices.push();
		matrices.translate(0.5, 0.5, 0.5);
		matrices.multiply(direction.getRotationQuaternion());

			// Front face
			renderBeamVertex(matrices, vertexConsumer, n, 0, p, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, 0, p, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, p, 1, 1, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, p, 0, 1, 1f, 1f, 1f, 1f);

			// Right face
			renderBeamVertex(matrices, vertexConsumer, p, 0, p, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, 0, n, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, n, 1, 1, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, p, 0, 1, 1f, 1f, 1f, 1f);

			// Back face
			renderBeamVertex(matrices, vertexConsumer, p, 0, n, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, 0, n, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, n, 1, 1, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, n, 0, 1, 1f, 1f, 1f, 1f);

			// Left face
			renderBeamVertex(matrices, vertexConsumer, n, 0, n, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, 0, p, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, p, 1, 1, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, n, 0, 1, 1f, 1f, 1f, 1f);

			//fade
			// Front face
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, p, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, p, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist, p, 1, 1, 1f, 1f, 1f, 0f);
			renderBeamVertex(matrices, vertexConsumer, n, dist, p, 0, 1, 1f, 1f, 1f, 0f);

			// Right face
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, p, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, n, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, p, dist, n, 1, 1, 1f, 1f, 1f, 0f);
			renderBeamVertex(matrices, vertexConsumer, p, dist, p, 0, 1, 1f, 1f, 1f, 0f);

			// Back face
			renderBeamVertex(matrices, vertexConsumer, p, dist-1, n, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, n, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist, n, 1, 1, 1f, 1f, 1f, 0f);
			renderBeamVertex(matrices, vertexConsumer, p, dist, n, 0, 1, 1f, 1f, 1f, 0f);

			// Left face
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, n, 0, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist-1, p, 1, 0, 1f, 1f, 1f, 1f);
			renderBeamVertex(matrices, vertexConsumer, n, dist, p, 1, 1, 1f, 1f, 1f, 0f);
			renderBeamVertex(matrices, vertexConsumer, n, dist, n, 0, 1, 1f, 1f, 1f, 0f);

			// Top face
			//renderBeamVertex(matrices, vertexConsumer, n, dist, n, 0, 0);
			//renderBeamVertex(matrices, vertexConsumer, n, dist, p, 0, 1);
			//renderBeamVertex(matrices, vertexConsumer, p, dist, p, 1, 1);
			//renderBeamVertex(matrices, vertexConsumer, p, dist, n, 1, 0);
		matrices.pop();
	}

	public boolean rendersOutsideBoundingBox(T laserEmitterBlockEntity) {
		return true;
	}

	public int getRenderDistance() {
		return 256;
	}

	public boolean isInRenderDistance(T laserEmitterBlockEntity, Vec3d vec3d) {
		return Vec3d.ofCenter(laserEmitterBlockEntity.getPos()).multiply(1.0D, 0.0D, 1.0D).isInRange(vec3d.multiply(1.0D, 0.0D, 1.0D), this.getRenderDistance());
	}
}

