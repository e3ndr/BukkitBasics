package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.github.bukkitbasics.BBLogger;
import com.github.bukkitbasics.variables;

public class motd implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (variables.showMotd) {
			if (variables.use_server_motd) {
				sender.sendMessage(Bukkit.getServer().getMotd());
				return true;
			} else {
				sender.sendMessage(BBLogger.transformColor(variables.motd.replace("$player", sender.getName())));
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> empty = new ArrayList<String>();
		empty.add("");
		return empty;
	}
	
}
