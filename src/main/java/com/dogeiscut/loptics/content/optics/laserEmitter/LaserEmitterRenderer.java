package com.dogeiscut.loptics.content.optics.laserEmitter;


import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

import net.minecraft.util.math.Direction;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3dStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

public class LaserEmitterRenderer implements BlockEntityRenderer<LaserEmitterBlockEntity> {

	public LaserEmitterRenderer(BlockEntityRendererFactory.Context context) {

	}

	@Override
	public void render(LaserEmitterBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		BlockPos pos = blockEntity.getPos();
		Direction direction = blockEntity.getLaserDirection();
		double distance = blockEntity.getLaserDistance();
		double thickness = blockEntity.getLaserStrength() * 0.1; // Adjust thickness based on preference
		matrices.push();
	}




}
