package com.github.bukkitbasics.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.bukkitbasics.BBLogger;
import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.variables;

public class LoginListener implements Listener {
	 @EventHandler
	 public void onPlayerJoin(PlayerJoinEvent event) {
		 Player player = event.getPlayer();
		 
		 // join message
		 if (variables.customJoinMessage) {
			 event.setJoinMessage(BBLogger.transformColor(lang.get("join").replace("$player", player.getDisplayName())));
		 }
		 
		 // motd
		 if (variables.showMotd) {
			 if (variables.use_server_motd) {
					player.sendMessage(Bukkit.getServer().getMotd());
				} else {
					player.sendMessage(BBLogger.transformColor(variables.motd.replace("$player", player.getDisplayName())));
				}
		 }
		 
		 
		 
	 }
}
