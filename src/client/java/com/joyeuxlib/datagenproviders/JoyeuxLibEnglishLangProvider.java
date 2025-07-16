package com.joyeuxlib.datagenproviders;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class JoyeuxLibEnglishLangProvider extends FabricLanguageProvider {

    public Map<String, String> translations = new HashMap<>();

    public JoyeuxLibEnglishLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {

        super(dataGenerator);
    }

    /**
     * Adds a translation to the provider.
     *
     * @param key   The translation key.
     * @param value The translation value.
     */
    public void add(String key, String value) {
        translations.put(key, value);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("block.joyeux-lib.joy_block","Joy");
        translationBuilder.add("block.joyeux-lib.koy_block","Koy");
        if (!translations.isEmpty()) {
            translations.forEach(translationBuilder::add);
        }
    }
}