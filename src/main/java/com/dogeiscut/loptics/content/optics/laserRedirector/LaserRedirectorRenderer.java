package com.dogeiscut.loptics.content.optics.laserRedirector;

import com.dogeiscut.loptics.content.optics.LaserHoldingRenderer;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

@Environment(EnvType.CLIENT)
public class LaserRedirectorRenderer extends LaserHoldingRenderer<LaserRedirectorBlockEntity> {
	public LaserRedirectorRenderer(BlockEntityRendererFactory.Context context) {
		super(context);
	}
}
