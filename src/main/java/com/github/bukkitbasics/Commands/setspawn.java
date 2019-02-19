package com.github.bukkitbasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.variables;

public class setspawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (player.getWorld().getName() == variables.main_world_name) {
			Bukkit.getWorld(variables.main_world_name).setSpawnLocation(player.getLocation());
			String location = (int) player.getLocation().getX() + ", " + (int) player.getLocation().getY() + ", " + (int) player.getLocation().getZ();
			player.sendMessage(lang.get("spawn.set").replace("$location", location));
			return true;
		} else {
			sender.sendMessage("You must be in the overworld to set spawn!");
			return false;
		}
	}
}
