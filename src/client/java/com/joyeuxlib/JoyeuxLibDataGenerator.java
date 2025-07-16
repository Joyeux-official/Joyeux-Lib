package com.joyeuxlib;

import com.joyeuxlib.datagenproviders.JoyeuxLibEnglishLangProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibModelProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibRecipeProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibSoundProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class JoyeuxLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(JoyeuxLibEnglishLangProvider::new);
		pack.addProvider(JoyeuxLibModelProvider::new);
		pack.addProvider(JoyeuxLibRecipeProvider::new);
		generateSoundData(pack);

	}

	public void generateSoundData(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> new JoyeuxLibSoundProvider(output))));
	}
}
