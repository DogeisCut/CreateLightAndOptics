package com.dogeiscut.loptics;

import com.simibubi.create.Create;

import com.simibubi.create.foundation.data.CreateRegistrate;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightAndOptics implements ModInitializer {
	public static final String ID = "loptics";
	public static final String NAME = "Create: Light and Optics";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

	@Override
	public void onInitialize() {
		//AllSoundEvents.prepare();
		//AllTags.init();
		AllCreativeModeTabs.init();
		AllBlocks.register();
		//AllItems.register();
		//AllFluids.register();
		//AllPaletteBlocks.register();
		//AllMenuTypes.register();
		//AllEntityTypes.register();
		AllBlockEntityTypes.register();
		//AllEnchantments.register();
		//AllRecipeTypes.register();

		// fabric exclusive, squeeze this in here to register before stuff is used
		REGISTRATE.register();

		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(
				() -> () -> "{} is accessing Porting Lib from the client!",
				() -> () -> "{} is accessing Porting Lib from the server!"
		), NAME);
	}

	public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper helper) {
		//LightAndOpticsDatagen.addExtraRegistrateData();

		//TagGen.datagen();
		//TagLangGen.datagen();

		//pack.addProvider(AllSoundEvents::provider);

		//pack.addProvider(AllAdvancements::new);
		//pack.addProvider(StandardRecipeGen::new);
		//pack.addProvider(MechanicalCraftingRecipeGen::new);
		//pack.addProvider(SequencedAssemblyRecipeGen::new);
		//pack.addProvider(ProcessingRecipeGen::registerAll);
		//pack.addProvider(GeneratedEntriesProvider::new);
		//pack.addProvider(DamageTypeTagGen::new);
		//pack.addProvider(CreateRecipeSerializerTagsProvider::new);
	}

	public static Identifier asResource(String path) {
		return new Identifier(ID, path);
	}
}
