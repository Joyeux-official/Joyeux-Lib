package com.joyeuxlib.datagenproviders;

import com.joyeuxlib.core.JoyeuxLibBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class JoyeuxLibLootTableProvider extends FabricBlockLootTableProvider {
    public JoyeuxLibLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    @Override
    public void generate() {
        addDrop(JoyeuxLibBlocks.JOY_BLOCK);
        addDrop(JoyeuxLibBlocks.KOY_BLOCK);
    }
}
