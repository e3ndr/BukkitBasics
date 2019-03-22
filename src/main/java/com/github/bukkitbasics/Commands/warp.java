package com.github.bukkitbasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.Config.WarpDatabase;

public class warp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName() == "warps" || args.length == 0 && sender.hasPermission("BukkitBasics.warp.list")) {
			sender.sendMessage(lang.get("warp.list").replace("$list", WarpDatabase.get("@").substring(1)));
			return true;
		} else if ((command.getName() == "warps" || args.length == 0) && !(sender.hasPermission("BukkitBasics.warp.list"))) {
			sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.warp.list"));
			return true;
		}
		
		if (args.length == 1 && !(sender.hasPermission("BukkitBasics.warp"))) {
			sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.warp"));
			return true;
		}
		if (args.length > 1 && !(sender.hasPermission("BukkitBasics.warp.others"))) {
			sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.warp.others"));
			return true;
		}
		
		if (sender instanceof ConsoleCommandSender && args.length == 1) {
			sender.sendMessage("Console is only able to teleport players directly (\"warp <location> <player>\")");
			return true;
		}
		
		String data = WarpDatabase.get(args[0]);
		
		if (data == "_no warps available") {
			sender.sendMessage(lang.get("warp.none"));
			return true;
		}
		if (data.substring(0, 1) == "_") {
			sender.sendMessage(lang.get("warp.not_found"));
			return true;
		}
		
		// format is: x,y,z,world,pitch,yaw,perm,name
		int x = 0; // TODO prevent malformed String data
		int y = 0; // TODO prevent malformed String data
		int z = 0; // TODO prevent malformed String data
		String world = ""; // TODO prevent malformed String data
		Float pitch = 0f; // TODO prevent malformed String data
		Float yaw = 0f; // TODO prevent malformed String data
		String perm = ""; // TODO prevent malformed String data
		String warpName = ""; // TODO prevent malformed String data
		
		x = Integer.valueOf(data.substring(0, data.indexOf(",")));
		data = data.substring(data.indexOf(",") + 1);
		y = Integer.valueOf(data.substring(0, data.indexOf(",")));
		data = data.substring(data.indexOf(",") + 1);
		z = Integer.valueOf(data.substring(0, data.indexOf(",")));
		data = data.substring(data.indexOf(",") + 1);
		world = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		pitch = Float.valueOf(data.substring(0, data.indexOf(",")));
		data = data.substring(data.indexOf(",") + 1);
		yaw = Float.valueOf(data.substring(0, data.indexOf(",")));
		data = data.substring(data.indexOf(",") + 1);
		perm = data.substring(0, data.indexOf(","));
		data = data.substring(data.indexOf(",") + 1);
		warpName = data.replace("&", "§");
		Location loc = new Location(Bukkit.getWorld(world), x, y, z); // TODO yaw & pitch
    	Player player;
    	
    	if (args.length < 2) {
    		player = (Player) sender;
    		if (sender.hasPermission(perm) || (perm.equals("perm") || perm.equals("null"))) {
    			player.sendMessage(lang.get("warp.self").replace("$warp", warpName));
    		} else {
    			player.sendMessage(lang.get("warp.no_perm").replace("$permission", perm));
    		}
    	} else {
    		player = Bukkit.getPlayer(args[1]);
	    	player.sendMessage(lang.get("warp.others").replace("$warp", warpName).replace("$player", sender.getName()));
    	}
		
    	player.teleport(loc);
    	
		return true;
	}

}
