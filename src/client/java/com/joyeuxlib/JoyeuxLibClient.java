package com.joyeuxlib;

import com.joyeuxlib.core.JoyeuxLibBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class JoyeuxLibClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		BlockRenderLayerMap.INSTANCE.putBlock(JoyeuxLibBlocks.JOY_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(JoyeuxLibBlocks.KOY_BLOCK, RenderLayer.getCutout());
	}
}