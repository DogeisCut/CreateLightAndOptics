package com.dogeiscut.loptics;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterCogInstance;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.dogeiscut.loptics.LightAndOptics.REGISTRATE;

public class AllBlockEntityTypes {
	public static final BlockEntityEntry<LaserEmitterBlockEntity> LASER_EMITTER = REGISTRATE
			.blockEntity("millstone", LaserEmitterBlockEntity::new)
			.instance(() -> LaserEmitterCogInstance::new, false)
			.validBlocks(AllBlocks.LASER_EMITTER)
			.renderer(() -> LaserEmitterRenderer::new)
			.register();

	public static void register() {}
}
