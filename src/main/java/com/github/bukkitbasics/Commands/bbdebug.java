package com.github.bukkitbasics.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.bukkitbasics.variables;

public class bbdebug implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("§8[§4START DEBUG§8]§r");
		
		sender.sendMessage("Motd: m:" + variables.motd + " u:" + variables.motdUse  + " s:" + variables.showMotd);
		
		sender.sendMessage("§8[§4END DEBUG§8]§r");
		
		return true;
	}

}
