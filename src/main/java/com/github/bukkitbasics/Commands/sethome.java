package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Config.HomeDatabase;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Util.PlayerUtil;

public class sethome implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage(lang.get("error.player-only"));
			return true;
		}
		
		if (args.length == 1) {
			if (label.equals("sethome")) {
				if (sender.hasPermission("BukkitBasics.home.set")) {
					Player player = (Player) sender;
					
					if (PlayerUtil.hasLandPermission(player, player.getLocation())) {
						String[] data = {
								String.valueOf(player.getLocation().getX()), 
								String.valueOf(player.getLocation().getY()), 
								String.valueOf(player.getLocation().getZ()), 
								player.getLocation().getWorld().getName(), 
								String.valueOf(player.getLocation().getPitch()), 
								String.valueOf(player.getLocation().getYaw())
						};
						HomeDatabase.add(args[0], data, player.getUniqueId().toString());
						sender.sendMessage(lang.get("home.set"));
						return true;
					} else {
						sender.sendMessage(lang.get("no.perm.land"));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.set"));
					return true;
				}
			} else if (label.equals("delhome")) {
				if (sender.hasPermission("BukkitBasics.home.delete")) {
					Player player = (Player) sender;
					if (HomeDatabase.list(player.getUniqueId().toString()).contains(args[0])) {
						HomeDatabase.delete(args[0], player.getUniqueId().toString());
						sender.sendMessage(lang.get("home.delete").replace("$home", args[0]));
						return true;
					} else {
						sender.sendMessage(lang.get("home.not_found"));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.delete"));
					return true;
				}
			}
		} else if (args.length == 2) {
			if (label.equals("sethome")) {
				if (sender.hasPermission("BukkitBasics.home.set.others")) {
					Player player = (Player) sender;
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getPlayer(args[1]);
					
					if (!player.hasPlayedBefore()) {
						sender.sendMessage(lang.get("error.player-never-played").replace("$player", args[1]));
						return true;
					} else if (PlayerUtil.hasLandPermission(player, player.getLocation())) {
						String[] data = {
								String.valueOf(player.getLocation().getX()), 
								String.valueOf(player.getLocation().getY()), 
								String.valueOf(player.getLocation().getZ()), 
								player.getLocation().getWorld().getName(), 
								String.valueOf(player.getLocation().getPitch()), 
								String.valueOf(player.getLocation().getYaw())
						};
						HomeDatabase.add(args[0], data, target.getUniqueId().toString());
						sender.sendMessage(lang.get("home.set").replace("$home", args[0]));
						return true;
					} else {
						sender.sendMessage(lang.get("no.perm.land"));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.set.others"));
					return true;
				}
			} else if (label.equals("delhome")) {
				if (sender.hasPermission("BukkitBasics.home.delete.others")) {
					@SuppressWarnings("deprecation")
					Player player = Bukkit.getPlayer(args[1]);
					if (HomeDatabase.list(player.getUniqueId().toString()).contains(args[0])) {
						HomeDatabase.delete(args[0], player.getUniqueId().toString());
						sender.sendMessage(lang.get("home.delete"));
						return true;
					} else {
						sender.sendMessage(lang.get("home.not_found").replace("$home", args[0]));
						return true;
					}
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.home.delete.others"));
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
		if (alias.equals("sethome")) {
			ArrayList<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		} else if (alias.equals("delhome") && args.length == 1) {
			return HomeDatabase.listArray(player.getUniqueId().toString());
		}
		return null;
	}
}
