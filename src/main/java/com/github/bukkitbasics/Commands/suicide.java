package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;

public class suicide implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("BukkitBasics.suicide")) {
			Player player = (Player) sender;
			player.damage(player.getHealth() + 1);
			return true;
		} else {
			sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.suicide"));
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
