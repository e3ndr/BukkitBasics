package com.github.bukkitbasics.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.github.bukkitbasics.lang;

public class resetLang implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {	
			lang.create();
			return true;
		} else {
			sender.sendMessage("This command can only be run by console.");
			return false;
		}
	}

}
