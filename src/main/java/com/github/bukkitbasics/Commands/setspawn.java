package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Config.lang;

public class setspawn implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (player.getWorld().getName() == variables.main_world_name) {
			Bukkit.getWorld(variables.main_world_name).setSpawnLocation(player.getLocation());
			String location = (int) player.getLocation().getX() + ", " + (int) player.getLocation().getY() + ", " + (int) player.getLocation().getZ();
			player.sendMessage(lang.get("spawn.set"));
			return true;
		} else {
			sender.sendMessage("You must be in the overworld to set spawn!"); // TODO lang
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> empty = new ArrayList<String>();
		empty.add("");
		return empty;
	}
}