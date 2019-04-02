package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Config.WarpDatabase;
import com.github.bukkitbasics.Config.lang;

public class setspawn implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (sender.hasPermission("BukkitBasics.spawn.set")) {
			Bukkit.getWorld(player.getLocation().getWorld().getName()).setSpawnLocation(player.getLocation());
			player.sendMessage(lang.get("spawn.set"));
			String[] data = {
					String.valueOf(player.getLocation().getX()), 
					String.valueOf(player.getLocation().getY()), 
					String.valueOf(player.getLocation().getZ()), 
					player.getLocation().getWorld().getName(), 
					String.valueOf(player.getLocation().getPitch()), 
					String.valueOf(player.getLocation().getYaw()),
					"null",
					"spawn"
			};
			WarpDatabase.add("spawn", data);
			return true;
		} else {
			sender.sendMessage(lang.get("warp.no_perm").replace("$permission", "BukkitBasics.spawn.set")); // TODO lang
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