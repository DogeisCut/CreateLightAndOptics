package com.dogeiscut.loptics;

import com.simibubi.create.compat.archEx.ArchExCompat;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class LightAndOpticsData implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		ExistingFileHelper helper = ExistingFileHelper.withResourcesFromArg();
		FabricDataGenerator.Pack pack = generator.createPack();
		LightAndOptics.REGISTRATE.setupDatagen(pack, helper);
		LightAndOptics.gatherData(pack, helper);
		ArchExCompat.init(pack);
	}
}
