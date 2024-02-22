package com.dogeiscut.loptics;

import net.fabricmc.api.ClientModInitializer;

public class LightAndOpticsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AllPartialModels.init();
	}
}
