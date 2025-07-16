package com.joyeuxlib.core;

import com.joyeuxlib.JoyeuxLib;
import com.joyeuxlib.core.blocks.BoxBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class JoyeuxLibBlocks  {

public static final Block JOY_BLOCK = registerBlock("joy_block", new BoxBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque()));
public static final Block KOY_BLOCK = registerBlock("koy_block", new BoxBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque()));


    private static Block registerBlock(String name, Block block) {

       registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, new Identifier(JoyeuxLib.MOD_ID, name), block);
    }


private static Item registerBlockItem(String name, Block block) {

    return Registry.register(Registries.ITEM, new Identifier(JoyeuxLib.MOD_ID, name),
    new BlockItem(block, new FabricItemSettings()));


}

    public static void registerJoyeuxLibBlocks() {
    }
}
