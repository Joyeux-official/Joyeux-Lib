package com.joyeuxlib;

import com.joyeuxlib.core.JoyeuxLibBlocks;
import com.joyeuxlib.core.JoyeuxLibSounds;
import com.joyeuxlib.core.worldgen.ExpandedJigsawStructure;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoyeuxLib implements ModInitializer {
	public static final String MOD_ID = "joyeux-lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Block BoxBlock;
    public static final StructureType<ExpandedJigsawStructure> EXPANDED_JIGSAW =
            Registry.register(
                    Registries.STRUCTURE_TYPE,
                    new Identifier(MOD_ID, "expanded_jigsaw"),
                    () -> ExpandedJigsawStructure.CODEC
            );
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