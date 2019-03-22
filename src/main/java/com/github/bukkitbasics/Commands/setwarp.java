package com.github.bukkitbasics.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.Config.WarpDatabase;

public class setwarp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("BukkitBasics.warp.set") && args.length > 0) {
			Player player = (Player) sender;
			
			// format is: x,y,z,world,pitch,yaw,perm,name
			String result = WarpDatabase.add(args[0], 
					player.getLocation().getBlockX() + "," + 
					player.getLocation().getBlockY() + "," + 
					player.getLocation().getBlockZ() + "," + 
					player.getLocation().getWorld().getName() + "," + 
					player.getLocation().getPitch() + "," + 
					player.getLocation().getYaw() + "," + 
					"perm" + "," +
					args[0]
					);
			if (result.equals("")) {
				sender.sendMessage(lang.get("warp.set").replace("$warp", args[0]));
				return true;
			} else {
				sender.sendMessage(lang.get("error.syntax"));
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

}
