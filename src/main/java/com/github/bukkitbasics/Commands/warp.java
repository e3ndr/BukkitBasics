package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Config.WarpDatabase;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Util.BBLogger;

public class warp implements CommandExecutor, TabCompleter {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName() == "warps" || args.length == 0 && sender.hasPermission("BukkitBasics.warp.list")) {
			sender.sendMessage(lang.get("warp.list").replace("$list", WarpDatabase.list()));
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
		
		String[] data = WarpDatabase.get(args[0]);
		
		if (data.equals(null)) {
			sender.sendMessage(lang.get("warp.not_found"));
			return true;
		}
		
		double x = Double.valueOf(data[0]);
		double y = Double.valueOf(data[1]);
		double z = Double.valueOf(data[2]);
		String world = data[3];
		double pitch = Double.valueOf(data[4]);
		double yaw = Double.valueOf(data[5]);
		String perm = data[6];
		String warpName = data[7];
		
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setPitch((float) pitch);
		loc.setYaw((float) yaw);
		
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

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			return Arrays.asList(BBLogger.stripColor(WarpDatabase.list()).split(", "));
		}
		if (args.length > 2) {
			ArrayList<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
		return null;
	}
}
