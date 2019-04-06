package com.github.bukkitbasics.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Config.HomeDatabase;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Util.PlayerUtil;

public class home implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender && !(label.equals("homes") && args.length == 1)) {
			sender.sendMessage(lang.get("error.player-only"));
			return true;
		}
		if (label.equals("homes") || (label.equals("home") && args.length == 0)) {
			if (args.length == 1) {
				if (sender.hasPermission("BukkitBasics.home.list.others")) {
					@SuppressWarnings("deprecation")
					Player player = Bukkit.getPlayer(args[0]);
					if (player.hasPlayedBefore()) {
						sender.sendMessage(lang.get("home.list").replace("$list", HomeDatabase.list(player.getUniqueId().toString())));
						return true;
					} else {
						sender.sendMessage(lang.get("error.player-never-played").replace("$player", args[0]));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.list.others"));
					return true;
				}
			} else {
				if (sender.hasPermission("BukkitBasics.home.list")) {
					Player player = (Player) sender;
					
					String list = HomeDatabase.list(player.getUniqueId().toString());
					if (list.length() == 0) {
						sender.sendMessage(lang.get("home.none"));
						return true;
					}
					
					sender.sendMessage(lang.get("home.list").replace("$list", list));
					return true;
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.list"));
					return true;
				}
			}
		} else if (label.equals("home")) {
			if (args.length == 1) {
				if (sender.hasPermission("BukkitBasics.home")) {
					Player player = (Player) sender;
					if (PlayerUtil.hasLandPermission(player, player.getLocation())) {
						String[] data = HomeDatabase.get(args[0], player.getUniqueId().toString());
						
						if (data.length == 0) {
							sender.sendMessage(lang.get("home.not_found"));
							return true;
						}
						
						Location loc = new Location(Bukkit.getWorld(data[3]), 0, 0, 0);
						loc.setX(Double.valueOf(data[0]));
						loc.setY(Double.valueOf(data[1]));
						loc.setZ(Double.valueOf(data[2]));
						loc.setPitch(Double.valueOf(data[4]).floatValue());
						loc.setYaw(Double.valueOf(data[5]).floatValue());
						
						player.teleport(loc);
						player.sendMessage(lang.get("home.teleport").replace("$home", args[0]));
						return true;
					} else {
						sender.sendMessage(lang.get("no.perm.land"));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home"));
					return true;
				}
			} else if (args.length == 2) {
				if (sender.hasPermission("BukkitBasics.home.others")) {
					@SuppressWarnings("deprecation")
					Player player = Bukkit.getPlayer(args[1]);
					
					if (!player.hasPlayedBefore()) {
						sender.sendMessage(lang.get("error.player-never-played").replace("$player", args[0]));
						return true;
					} else if (PlayerUtil.hasLandPermission(player, player.getLocation())) {
						String[] data = HomeDatabase.get(args[0], player.getUniqueId().toString());
						
						if (data.length == 0) {
							sender.sendMessage(lang.get("home.not_found"));
							return true;
						}
						
						Location loc = new Location(Bukkit.getWorld(data[3]), 0, 0, 0);
						loc.setX(Double.valueOf(data[0]));
						loc.setY(Double.valueOf(data[1]));
						loc.setZ(Double.valueOf(data[2]));
						loc.setPitch(Double.valueOf(data[4]).floatValue());
						loc.setYaw(Double.valueOf(data[5]).floatValue());
						
						Player executor = (Player) sender;
						executor.teleport(loc);
						executor.sendMessage(lang.get("home.teleport.others").replace("$home", args[0]).replace("$player", args[1]));
						return true;
					} else {
						sender.sendMessage(lang.get("no.perm.land"));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.others"));
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			return null;
		}
		Player player = (Player) sender;
		if (alias.equals("home") && args.length == 1) {
			return HomeDatabase.listArray(player.getUniqueId().toString());
		}
		return null;
	}
}
