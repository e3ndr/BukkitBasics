package com.github.bukkitbasics.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.DebugPrinter;
import com.github.bukkitbasics.lang;

public class setLang implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender){	
			lang.create();
			return true;
		} else {
			if (!(args.length >= 2)) {
				return false;
			}
			String value = "";
			for (int i = 1; i != args.length; i++) {
				value = value + args[i] + " ";
			}
			Player player = (Player) sender;
			switch (args[0]) {
				case "spawn.teleport": lang.setLang(args[0], value); break;
				case "spawn.set": lang.setLang(args[0], value); break;
				case "coords.0": lang.setLang(args[0], value); break;
				case "coords.1": lang.setLang(args[0], value); break;
				
				default: return false;
			}
			DebugPrinter.println(player.getName() + " set " + args[0] + " to " + value);
			player.sendMessage("Set " + args[0] + " to " + value.replace("&", "§"));
			return true;
		}
	}

}
