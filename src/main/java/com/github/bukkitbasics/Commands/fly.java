package com.github.bukkitbasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;

public class fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			if (sender.hasPermission("BukkitBasics.fly")) {
				Player player = (Player) sender;
				if (player.getGameMode().getValue() == 1 ||player.getGameMode().getValue() == 3) {
					return true;
				}
				player.setAllowFlight(!player.getAllowFlight());
				sender.sendMessage(lang.get("fly").replace("$boolean", "" + player.getAllowFlight()));
				return true;
			} else {
				sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.fly"));
				return false;
			}
		} else {
			if (sender.hasPermission("BukkitBasics.fly.others")) {
				Player player = Bukkit.getPlayer(args[0]);
				if (player.getGameMode().getValue() == 1 ||player.getGameMode().getValue() == 3) {
					return true;
				}
				player.setAllowFlight(!player.getAllowFlight());
				player.sendMessage(lang.get("fly.others.1")
						.replace("$player", sender.getName())
						.replace("$mode", "" + player.getAllowFlight())
						);
				sender.sendMessage(lang.get("fly.others.0")
						.replace("$player", sender.getName())
						.replace("$boolean", "" + player.getAllowFlight())
						);
				return true;
			} else {
				sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.fly.others"));
				return false;
			}
		}
	}

}
