package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class LaserEmitterRenderer extends SafeBlockEntityRenderer<LaserEmitterBlockEntity> {
	public LaserEmitterRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	protected void renderSafe(LaserEmitterBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource bufferSource, int light, int overlay) {

	}
}
