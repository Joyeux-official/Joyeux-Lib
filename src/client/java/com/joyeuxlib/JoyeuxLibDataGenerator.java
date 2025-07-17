package com.joyeuxlib;

import com.joyeuxlib.datagenproviders.JoyeuxLibEnglishLangProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibModelProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibRecipeProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibSoundProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.jetbrains.annotations.ApiStatus;

/**
* @apiNote THIS IS ONLY FOR INTERNAL USE
 **/
@ApiStatus.Internal
public class JoyeuxLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(this::englishLibProvider);
	}

	public JoyeuxLibEnglishLangProvider englishLibProvider(FabricDataOutput output) {
		JoyeuxLibEnglishLangProvider joyENUSLang = new JoyeuxLibEnglishLangProvider(output);
		joyENUSLang.addTranslation("block.joyeux-lib.joy_block","Joy");
		joyENUSLang.addTranslation("block.joyeux-lib.koy_block","Koy");
		return joyENUSLang;
	}
}
