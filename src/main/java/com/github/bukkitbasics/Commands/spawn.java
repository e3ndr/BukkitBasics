package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Config.WarpDatabase;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Util.BBLogger;

public class spawn implements CommandExecutor, TabCompleter {
	
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	
    	if (args.length > 0 && !sender.hasPermission("BukkitBasics.spawn.others")) {
    		sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.spawn.others"));
    		return true;
    	} else if (args.length == 0 && !sender.hasPermission("BukkitBasics.spawn")) {
    		sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.spawn"));
    		return true;
    	}
    	
    	String[] data = WarpDatabase.get("spawn", (Player) sender);
		Location loc;
		if (data.length == 0) {
	    	Location pos = Bukkit.getServer().getWorld(variables.main_world_name).getSpawnLocation();
	    	loc = new Location(Bukkit.getWorld(variables.main_world_name), pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
	    	BBLogger.println("Could not find spawn warp information (Required for \'/spawn\') Please run \'/setspawn\' to create it. To make \'/warp spawn\' inaccessible set it to a permission that nobody has.");
		} else {
			double x = Double.valueOf(data[0]);
			double y = Double.valueOf(data[1]);
			double z = Double.valueOf(data[2]);
			String world = data[3];
			double pitch = Double.valueOf(data[4]);
			double yaw = Double.valueOf(data[5]);
			
			loc = new Location(Bukkit.getWorld(world), x, y, z);
			loc.setPitch((float) pitch);
			loc.setYaw((float) yaw);
		}
    	
    	if (args.length > 0 && sender.hasPermission("BukkitBasics.spawn.others")) {
    		Player player = Bukkit.getPlayer(args[0]);
    		player.teleport(loc);
    		player.sendMessage(lang.get("spawn.teleport"));
    		return true;
    	}
    	
    	if (args.length == 0 && sender.hasPermission("BukkitBasics.spawn")) {
    		Player player = (Player) sender;
    		player.teleport(loc);
    		player.sendMessage(lang.get("spawn.teleport"));
    		return true;
    	}
		return false;
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