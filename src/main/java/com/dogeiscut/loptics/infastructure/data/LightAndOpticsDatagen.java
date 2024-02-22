package com.dogeiscut.loptics.infastructure.data;


import com.dogeiscut.loptics.LightAndOptics;
import com.tterrag.registrate.providers.ProviderType;

import java.util.function.BiConsumer;

public class LightAndOpticsDatagen {
	public static void addExtraRegistrateData() {
		//CreateRegistrateTags.addGenerators();

		LightAndOptics.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
			BiConsumer<String, String> langConsumer = provider::add;

			//provideDefaultLang("interface", langConsumer);
			//provideDefaultLang("tooltips", langConsumer);
			//AllAdvancements.provideLang(langConsumer);
			//AllSoundEvents.provideLang(langConsumer);
			//providePonderLang(langConsumer);
		});
	}
}
