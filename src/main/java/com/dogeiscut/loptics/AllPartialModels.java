package com.dogeiscut.loptics;

import com.jozufozu.flywheel.core.PartialModel;

public class AllPartialModels {
	public static final PartialModel LASER_EMITTER_COG = block("laser_emitter/inner");


		private static PartialModel block (String path){
			return new PartialModel(LightAndOptics.asResource("block/" + path));
		}

		private static PartialModel entity (String path){
			return new PartialModel(LightAndOptics.asResource("entity/" + path));
		}

		public static void init () {
			// init static fields
		}
}
