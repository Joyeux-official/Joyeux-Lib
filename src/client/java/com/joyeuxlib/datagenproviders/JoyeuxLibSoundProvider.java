package com.joyeuxlib.datagenproviders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.joyeuxlib.JoyeuxLib;
import com.joyeuxlib.core.util.StringCursor;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class JoyeuxLibSoundProvider implements DataProvider {
    protected final FabricDataOutput dataOutput;
    private final Map<String, Set<SoundEventWrapper>> sounds;
    private final boolean extractVariants;

    public JoyeuxLibSoundProvider(FabricDataOutput dataOutput) {
        this(dataOutput, true);
    }

    public JoyeuxLibSoundProvider(FabricDataOutput dataOutput, boolean extractVariants) {
        this.sounds = new HashMap();
        this.dataOutput = dataOutput;
        this.extractVariants = extractVariants;
    }

    private boolean canAdd(String name, boolean check) {
        return check && !checkName(name);
    }

    public void addSound(String name, SoundEvent event) {
        this.addSound(name, true, event);
    }

    public void addSound(String name, boolean check, SoundEvent event) {
        if (!this.canAdd(name, check)) {
            ((Set)this.sounds.computeIfAbsent(name, (s) -> new HashSet())).add(new SoundEventWrapper(event));
        }
    }

    public void addSound(String name, SoundEvent... events) {
        this.addSound(name, true, events);
    }

    public void addSound(String name, boolean check, SoundEvent... events) {
        if (!this.canAdd(name, check)) {
            Set<SoundEventWrapper> set = (Set)this.sounds.computeIfAbsent(name, (s) -> new HashSet());

            for(SoundEvent event : events) {
                set.add(new SoundEventWrapper(event));
            }

        }
    }

    public CompletableFuture<?> run(DataWriter writer) {
        getSoundsFromMod(this.dataOutput.getModId()).forEach((sound) -> {
            String path = sound.getId().getPath();
            this.addSound(path, false, sound);
            if (this.extractVariants) {
                String newPath = extractPath(path);
                if (newPath == null) {
                    return;
                }

                this.addSound(newPath, false, sound);
            }

        });
        JsonObject soundsJson = new JsonObject();
        this.sounds.forEach((soundName, soundEvents) -> soundsJson.add(soundName, serializeSounds(soundEvents)));
        return DataProvider.writeToPath(writer, soundsJson, this.getOutputPath());
    }

    public Path getOutputPath() {
        return this.dataOutput.resolvePath(DataOutput.OutputType.RESOURCE_PACK).resolve(this.dataOutput.getModId()).resolve("sounds.json");
    }

    public String getName() {
        return "Sound Definitions";
    }

    private static JsonObject serializeSounds(Iterable<SoundEventWrapper> wrappers) {
        JsonObject obj = new JsonObject();
        JsonArray sounds = new JsonArray();

        for(SoundEventWrapper wrapper : wrappers) {
            sounds.add(wrapper.event.getId().toString());
        }

        obj.add("sounds", sounds);
        return obj;
    }

    private static @Nullable String extractPath(String path) {
        StringCursor cursor = new StringCursor(path, path.length() - 1, -1);

        while(Character.isDigit(cursor.peek())) {
            cursor.next();
        }

        return cursor.substring();
    }

    private static boolean checkName(String name) {
        if (name.contains(" ")) {
            JoyeuxLib.LOGGER.error("Sound event name cannot contain spaces: {}", name);
            return false;
        } else {
            char[] var1 = name.toCharArray();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Character character = var1[var3];
                if (Character.isUpperCase(character)) {
                    JoyeuxLib.LOGGER.error("Sound event name cannot contain capital letters: {}", name);
                    return false;
                }
            }

            return true;
        }
    }

    public static Stream<SoundEvent> getSoundsFromMod(String namespace) {
        return Registries.SOUND_EVENT.stream().filter((sound) -> sound.getId().getNamespace().equals(namespace));
    }

    static class SoundEventWrapper {
        private final SoundEvent event;

        public SoundEventWrapper(SoundEvent event) {
            this.event = event;
        }

        public int hashCode() {
            return this.event.getId().hashCode();
        }
    }
}
