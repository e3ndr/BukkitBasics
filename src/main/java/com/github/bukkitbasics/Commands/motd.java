package com.github.bukkitbasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.bukkitbasics.variables;

public class motd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (variables.showMotd) {
			if (variables.motdUse) {
				sender.sendMessage(Bukkit.getServer().getMotd());
				return true;
			} else {
				sender.sendMessage(variables.motd);
				return true;
			}
		}
		return false;
	}

}
