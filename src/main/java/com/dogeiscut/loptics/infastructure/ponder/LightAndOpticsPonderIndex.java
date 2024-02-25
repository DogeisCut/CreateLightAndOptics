package com.dogeiscut.loptics.infastructure.ponder;

import com.dogeiscut.loptics.AllBlocks;
import com.dogeiscut.loptics.LightAndOptics;
import com.dogeiscut.loptics.infastructure.ponder.scenes.LaserEmitterScenes;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;


public class LightAndOpticsPonderIndex {
	static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(LightAndOptics.ID);

	public static void register() {
		HELPER.forComponents(AllBlocks.LASER_EMITTER)
				.addStoryBoard("laser_emitter/powering", LaserEmitterScenes::powering)
				.addStoryBoard("laser_emitter/redirecting", LaserEmitterScenes::redirecting)
				.addStoryBoard("laser_emitter/combining_power", LaserEmitterScenes::combiningPower)
				.addStoryBoard("laser_emitter/laser_cutting", LaserEmitterScenes::laserCutting);
	}


}
