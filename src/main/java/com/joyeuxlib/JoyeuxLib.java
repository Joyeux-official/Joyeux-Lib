package com.joyeuxlib;

import com.joyeuxlib.core.JoyeuxLibBlocks;
import com.joyeuxlib.core.JoyeuxLibSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoyeuxLib implements ModInitializer {
	public static final String MOD_ID = "joyeux-lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Block BoxBlock;

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}


	@Override
	public void onInitialize() {

		JoyeuxLibBlocks.registerJoyeuxLibBlocks();
		JoyeuxLibSounds.init();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(JoyeuxLibBlocks.JOY_BLOCK.asItem());
			entries.add(JoyeuxLibBlocks.KOY_BLOCK.asItem());
		});
	}
}