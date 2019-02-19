package com.github.bukkitbasics.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;

public class suicide implements CommandExecutor {

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

}
