package com.joyeuxlib.core;

import com.joyeuxlib.JoyeuxLib;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class JoyeuxLibSounds {

    //cleaver sounds
    public static final SoundEvent BOOWOMP = register("boowomp");
    public static final SoundEvent BOOWOMP_BUTEVIL = register("boowomp_butevil");




    private static SoundEvent register(String name) {
        Identifier id = JoyeuxLib.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }


    public static void init() {
    }
}

