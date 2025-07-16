package com.joyeuxlib.datagenproviders;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.TranslatableTextContent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * A language provider for the English language (en_us) for JoyeuxLib.
 * This class is used to generate the language file for the mod.
 * @author Theo, Duzo, Loqor
 * @license GNU General Public License v3.0
 * @apiNote this is a direct copy of the LanguageProvider from AmbleKit because I'm lazy :p - Loqor
 */
public class JoyeuxLibEnglishLangProvider extends FabricLanguageProvider {
    private final FabricDataOutput output;
    protected final String modid;
    protected HashMap<String, String> translations = new HashMap<>();

    public JoyeuxLibEnglishLangProvider(FabricDataOutput output) {
        super(output);
        this.output = output;
        this.modid = output.getModId();
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        for (String key : translations.keySet()) {
            builder.add(key, translations.get(key));
        }

        output.getModContainer()
                .findPath("assets/" + modid + "/lang/" + "en_us" + ".existing.json")
                .ifPresent(existingFilePath -> {
                    try {
                        builder.add(existingFilePath);
                    } catch (Exception e) {
                        LOGGER.warn("Failed to add existing language file! ({}) | ", "en_us",
                                e);
                    }
                });
    }

    /**
     * Adds a translation to the language file.
     *
     * @param item
     *            The item to add the translation for.
     * @param translation
     *            The translation.
     */
    public void addTranslation(Item item, String translation) {
        translations.put(item.getTranslationKey(), translation);
    }

    /**
     * Adds a translation to the language file.
     *
     * @param itemGroup
     *            The item group to add the translation for.
     * @param translation
     *            The translation.
     */
    public void addTranslation(ItemGroup itemGroup, String translation) {
        if (!(itemGroup.getDisplayName().getContent() instanceof TranslatableTextContent translatable))
            return;

        translations.put(translatable.getKey(), translation);
    }

    /**
     * Adds a translation to the language file.
     *
     * @param key
     *            The key to add the translation for.
     * @param translation
     *            The translation.
     */
    public void addTranslation(String key, String translation) {
        translations.put(key, translation);
    }

    /**
     * Adds a translation to the language file
     *
     * @param block
     *            The block to add the translation for
     * @param translation
     *            The translation
     */
    public void addTranslation(Block block, String translation) {
        translations.put(block.getTranslationKey(), translation);
    }

    public static String getNameFromKey(String key) {
        int lastDot = key.lastIndexOf('.');
        if (lastDot == -1) {
            return key;
        }
        String suffix = key.substring(lastDot + 1);

        // split at _
        String[] parts = suffix.split("_");

        // capitalise beginning of each string and join with space
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            builder.append(part.substring(0, 1).toUpperCase());
            builder.append(part.substring(1));
            builder.append(" ");
        }

        // remove last space
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}