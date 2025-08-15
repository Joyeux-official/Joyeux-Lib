package com.joyeuxlib.core.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.BannedPlayerEntry;
import net.minecraft.server.BannedPlayerList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * @author Capozi
 * @license GNU General Public License v3.0
 * utility methods for banning and making unregistered death messages
 **/
public class DeathUtils {
    public void ban(PlayerEntity user, LivingEntity entity, String reason){
        ServerPlayerEntity target = (ServerPlayerEntity) entity;
        ServerPlayerEntity source = (ServerPlayerEntity) user;
        MinecraftServer server = source.getServer();
        if (server != null) {
            GameProfile profile = target.getGameProfile();
            BannedPlayerList banList = server.getPlayerManager().getUserBanList();
            if (!banList.contains(profile)) {
                BannedPlayerEntry entry = new BannedPlayerEntry(
                        profile,
                        null,
                        source.getName().toString(),
                        null,
                        reason
                );
                banList.add(entry);
                target.networkHandler.disconnect(Text.literal("You have been sentenced to an eternity in the Otherside").formatted(Formatting.AQUA));
            }
        }
    }
    public void createDeathMessage(PlayerEntity player, String message) {
        MinecraftServer server = ((ServerWorld) player.getWorld()).getServer();
        GameProfile profile = player.getGameProfile();
        Text deathMessage = Text.literal(player + message);
        server.getPlayerManager().broadcast(deathMessage, false);
    }
    public void createArbitraryDeathMessage(PlayerEntity player, String message) {
        MinecraftServer server = ((ServerWorld) player.getWorld()).getServer();
        GameProfile profile = player.getGameProfile();
        Text deathMessage = Text.literal(message);
        server.getPlayerManager().broadcast(deathMessage, false);
    }
}
