package com.dogeiscut.loptics;

import com.simibubi.create.foundation.render.RenderTypes;

import net.fabricmc.api.ClientModInitializer;

public class LightAndOpticsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AllPartialModels.init();

		RenderTypes.init();
	}
}
