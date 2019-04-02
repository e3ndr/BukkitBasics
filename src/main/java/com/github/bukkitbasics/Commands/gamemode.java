package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Config.lang;

public class gamemode implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String cmd = command.getName();
		
		switch (cmd) {
			case "gm": return gamemode(sender, args);
			case "gamemode": return gamemode(sender, args);
			case "gms": return gm_(sender, args, 0);
			case "gmc": return gm_(sender, args, 1);
			case "gma": return gm_(sender, args, 2);
			case "gmsp": return gm_(sender, args, 3);
		}
		return false;
	}

	private boolean gm_(CommandSender sender, String[] args, int mode) {
		GameMode gamemode = null;
		switch (mode) {
			case 0: gamemode = GameMode.SURVIVAL; break;
			case 1: gamemode = GameMode.CREATIVE; break;
			case 2: gamemode = GameMode.ADVENTURE; break;
			case 3: gamemode = GameMode.SPECTATOR; break;
		}
		
		if (args.length != 0) {
			if (sender.hasPermission("BukkitBasics.gamemode.others")) {
				if (!sender.hasPermission("BukkitBasics.gamemode." + gamemode.toString().toLowerCase()) ) {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.gamemode" + gamemode.toString().toLowerCase()));
					return true;
				}
				return gamemodeOthers(sender, gamemode, args[0]);
			} else {
				sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.gamemode.others"));
				return false;
			}
		} else {
			if (sender.hasPermission("BukkitBasics.gamemode." + gamemode.toString().toLowerCase())) {
				Player player = (Player) sender;
				player.setGameMode(gamemode);
				player.sendMessage(lang.get("gamemode.updated.0")
						.replace("$player", sender.getName())
						.replace("$mode", gamemode.toString())
						);
				return true;
			} else {
				sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.gamemode" + gamemode.toString().toLowerCase()));
				return true;
			}
		}
	}

	private boolean gamemode(CommandSender sender, String[] args) {
		if (args.length > 0) {
			GameMode gamemode;
			switch (args[0].toLowerCase()) {
				case "s": gamemode = GameMode.SURVIVAL; break;
				case "survival": gamemode = GameMode.SURVIVAL; break;
				case "0": gamemode = GameMode.SURVIVAL; break;
				case "c": gamemode = GameMode.CREATIVE; break;
				case "creative": gamemode = GameMode.CREATIVE; break;
				case "1": gamemode = GameMode.CREATIVE; break;
				case "a": gamemode = GameMode.ADVENTURE; break;
				case "adventure": gamemode = GameMode.ADVENTURE; break;
				case "2": gamemode = GameMode.ADVENTURE; break;
				case "sp": gamemode = GameMode.SPECTATOR; break;
				case "spectator": gamemode = GameMode.SPECTATOR; break;
				case "3": gamemode = GameMode.SPECTATOR; break;
				
				default: return false;
			}
			if (args.length == 1) {
				switch (gamemode.toString()) {
				case "SURVIVAL": return gamemodeSelf(sender, gamemode);
				case "CREATIVE": return gamemodeSelf(sender, gamemode);
				case "ADVENTURE": return gamemodeSelf(sender, gamemode);
				case "SPECTATOR": return gamemodeSelf(sender, gamemode);
				
				}
			}
			if (args.length == 2) {
				if (sender.hasPermission("BukkitBasics.gamemode.others")) {
					return gamemodeOthers(sender, gamemode, args[1]);
				} else {
					sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.gamemode.others"));
					return false;
				}
			}
		}
		return false;
	}

	private boolean gamemodeSelf(CommandSender sender, GameMode gamemode) {
		if (sender.hasPermission("BukkitBasics.gamemode." + gamemode.toString().toLowerCase())) {
			Player player = (Player) sender;
			player.setGameMode(gamemode);
			player.sendMessage(lang.get("gamemode.updated.0")
					.replace("$player", sender.getName())
					.replace("$mode", gamemode.toString())
					);
			return true;
		} else {
			sender.sendMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.gamemode." + gamemode.toString().toLowerCase()));
			return false;
		}
	}
	@SuppressWarnings({ "unused", "deprecation" })
	private boolean gamemodeOthers(CommandSender sender, GameMode gamemode, String player) {
		if (false) {
			// TODO @a selector
		} else {
			if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(player))) {
				Player victim = Bukkit.getPlayer(player);
				victim.setGameMode(gamemode);
				victim.sendMessage(lang.get("gamemode.updated.1")
						.replace("$player", sender.getName())
						.replace("$mode", gamemode.toString())
						);
				sender.sendMessage(lang.get("gamemode.updated.others")
						.replace("$player", sender.getName())
						.replace("$mode", gamemode.toString())
						);

				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		if (args.length > 2 || command.getName().equalsIgnoreCase("gms") || command.getName().equalsIgnoreCase("gmc") || command.getName().equalsIgnoreCase("gma") || command.getName().equalsIgnoreCase("gmsp")) {
			ArrayList<String> empty = new ArrayList<String>();
			empty.add("");
			return empty;
		}
		if (args.length == 1) {
			ArrayList<String> complete = new ArrayList<String>();
			complete.add("0");
			complete.add("1");
			complete.add("2");
			complete.add("3");
			
			complete.add("s");
			complete.add("c");
			complete.add("a");
			complete.add("sp");
			
			complete.add("survival");
			complete.add("creative");
			complete.add("adventure");
			complete.add("spectator");
			
			return complete;
		}
		return null;
	}
	
}
