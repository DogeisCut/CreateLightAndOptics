package com.dogeiscut.loptics;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterCogInstance;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterRenderer;
import com.dogeiscut.loptics.content.optics.laserRedirector.LaserRedirectorBlockEntity;
import com.dogeiscut.loptics.content.optics.laserRedirector.LaserRedirectorRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.dogeiscut.loptics.LightAndOptics.REGISTRATE;

public class AllBlockEntityTypes {
	public static final BlockEntityEntry<LaserEmitterBlockEntity> LASER_EMITTER = REGISTRATE
			.blockEntity("laser_emitter", LaserEmitterBlockEntity::new)
			.instance(() -> LaserEmitterCogInstance::new, false)
			.validBlocks(AllBlocks.LASER_EMITTER)
			.renderer(() -> LaserEmitterRenderer::new)
			.register();

	public static final BlockEntityEntry<LaserRedirectorBlockEntity> LASER_REDIRECTOR = REGISTRATE
			.blockEntity("laser_redirector", LaserRedirectorBlockEntity::new)
			.validBlocks(AllBlocks.LASER_REDIRECTOR)
			.renderer(() -> LaserRedirectorRenderer::new)
			.register();

	public static void register() {}
}
