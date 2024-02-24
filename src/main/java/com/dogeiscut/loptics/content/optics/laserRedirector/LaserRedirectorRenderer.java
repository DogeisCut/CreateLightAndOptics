package com.dogeiscut.loptics.content.optics.laserRedirector;

import com.dogeiscut.loptics.content.optics.LaserHoldingRenderer;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class LaserRedirectorRenderer extends LaserHoldingRenderer<LaserRedirectorBlockEntity> {
	public LaserRedirectorRenderer(BlockEntityRendererFactory.Context context) {
		super(context);
	}
}
