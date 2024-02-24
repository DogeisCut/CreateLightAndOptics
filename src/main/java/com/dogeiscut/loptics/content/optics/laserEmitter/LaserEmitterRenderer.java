package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.dogeiscut.loptics.AllPartialModels;
import com.dogeiscut.loptics.content.optics.LaserHoldingRenderer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;

import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
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

import net.minecraft.util.math.Vec3d;

import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class LaserEmitterRenderer extends LaserHoldingRenderer<LaserEmitterBlockEntity> {

	public LaserEmitterRenderer(BlockEntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(LaserEmitterBlockEntity be, BlockState state) {
		return CachedBufferer.partial(AllPartialModels.LASER_EMITTER_COG, state);
	}
}
