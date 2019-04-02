package com.github.bukkitbasics.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Util.BBLogger;

public class GeneralListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
    	Player player = event.getPlayer();

		if (variables.silentEventsEnable && player.hasPermission("BukkitBasics.silent.leave")) {
			event.setQuitMessage(null);
			ArrayList<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
			for (Player p : players) {
				if (variables.silentBroadcasts && p.hasPermission("BukkitBasics.silent.see")) {
					p.sendMessage(lang.get("silent.leave").replace("$player", player.getDisplayName()));
				}
			}
		}
		
    	if (variables.customLeaveMessage) {
			event.setQuitMessage(BBLogger.transformColor(lang.get("leave.message").replace("$player", player.getDisplayName())));
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (variables.silentEventsEnable && player.hasPermission("BukkitBasics.silent.join")) {
			event.setJoinMessage(null);
			ArrayList<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
			for (Player p : players) {
				if (variables.silentBroadcasts && p.hasPermission("BukkitBasics.silent.see")) {
					p.sendMessage(lang.get("silent.join").replace("$player", player.getDisplayName()));
				}
			}
		}
		
		// join message
		if (variables.customJoinMessage) {
			event.setJoinMessage(BBLogger.transformColor(lang.get("join.message").replace("$player", player.getDisplayName())));
		}
		
		// motd
		if (variables.showMotd) {
			if (variables.use_server_motd) {
				player.sendMessage(Bukkit.getServer().getMotd());
			} else {
				player.sendMessage(lang.get("custom.motd").replace("$player", player.getName()).replace("$online", "" + Bukkit.getServer().getOnlinePlayers().size()).replace("$line", "\n").replace("\\n", "\n"));
			}
		}

	}
}
