package com.joyeuxlib.datagenproviders;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class JoyeuxLibEnglishLangProvider extends FabricLanguageProvider {
    public JoyeuxLibEnglishLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {

        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("block.joyeux-lib.joy_block","Joy");
        translationBuilder.add("block.joyeux-lib.koy_block","Koy");
    }
}