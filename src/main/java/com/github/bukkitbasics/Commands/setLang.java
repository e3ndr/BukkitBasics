package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.github.bukkitbasics.Config.lang;

public class setLang implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("lang.update") && args.length == 2) {
			lang.setLang(args[0], args[1]);
			sender.sendMessage(lang.get("lang.update").replace("$lang", args[0]).replace("$key", args[1]));
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			ArrayList<String> keys = new ArrayList<String>();
			for (String[] lang : lang.defaultData) {
				keys.add(lang[0]);
			}
			return keys;
		}
		
		ArrayList<String> empty = new ArrayList<String>();
		empty.add("");
		return empty;
	}
}
