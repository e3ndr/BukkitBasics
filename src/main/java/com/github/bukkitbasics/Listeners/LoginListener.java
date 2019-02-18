package com.github.bukkitbasics.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.bukkitbasics.variables;

public class LoginListener implements Listener {
	 @EventHandler
	 public void onPlayerJoin(PlayerJoinEvent event) {
		 Player player = event.getPlayer();
		 if (variables.showMotd) {
			 if (variables.motdUse) {
					player.sendMessage(Bukkit.getServer().getMotd());
				} else {
					player.sendMessage(variables.motd);
				}
		 }
		 Bukkit.getServer().getMotd();
	 }
}
