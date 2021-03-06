package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Config.WarpDatabase;
import com.github.bukkitbasics.Config.lang;

public class setwarp implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("BukkitBasics.warp.set") && args.length > 0) {
			Player player = (Player) sender;
			
			String perm = "null";
			
			if (args.length >= 2) {
				perm = args[1];
			}
			
			String[] data = {
					String.valueOf(player.getLocation().getX()), 
					String.valueOf(player.getLocation().getY()), 
					String.valueOf(player.getLocation().getZ()), 
					player.getLocation().getWorld().getName(), 
					String.valueOf(player.getLocation().getPitch()), 
					String.valueOf(player.getLocation().getYaw()),
					perm,
					args[0]
			};
			String result = WarpDatabase.add(args[0], data);
			if (result.equals("")) {
				sender.sendMessage(lang.get("warp.set").replace("$warp", args[0]));
				return true;
			} else {
				sender.sendMessage(lang.get("error.must-be-alphanumerical-including-&_").replace("$name", "Warp"));
				return false;
			}
		} else if (!sender.hasPermission("BukkitBasics.warp.set")) {
			sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.warp.set"));
			return false;
		} else {
			sender.sendMessage(lang.get("error.syntax"));
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length > 1) {
			ArrayList<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
		return null;
	}

}
