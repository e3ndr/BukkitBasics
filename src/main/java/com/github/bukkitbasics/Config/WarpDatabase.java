package com.github.bukkitbasics.Config;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Util.BBLogger;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

public class WarpDatabase {
	public static File warps;
	public static YamlConfiguration yml;
	public static void init() {
		warps = new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/warps.yml");
		yml = YamlConfiguration.loadConfiguration(warps);
		update();
	}
	public static String[] get(String key, Player player) {
		key = key.toLowerCase();
		
		// soft depends
		if (key.equalsIgnoreCase("faction") && variables.factionsPresent && player != null) {
			FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
			Location loc = fPlayer.getFaction().getHome();
			
			String[] data = {
					String.valueOf(loc.getX()),
					String.valueOf(loc.getY()),
					String.valueOf(loc.getZ()),
					String.valueOf(loc.getWorld()),
					String.valueOf(loc.getPitch()),
					String.valueOf(loc.getYaw()),
					"BukkitBasics.integration.faction.warp",
					"Faction"
			};
			return data;
		}
		
		if (!yml.contains(key)) {
			return new String[0];
		}
		String[] data = {
				(String) yml.get(key + ".x"),
				(String) yml.get(key + ".y"),
				(String) yml.get(key + ".z"),
				(String) yml.get(key + ".world"),
				(String) yml.get(key + ".pitch"),
				(String) yml.get(key + ".yaw"),
				(String) yml.get(key + ".perm"),
				(String) yml.get(key + ".stylized_name")
		};
		return data;
	}
	public static void update() {
		try {
			yml.save(warps);
		} catch (IOException e) {
			BBLogger.exception(e);
		}
	}
	public static String add(String key, String[] data) {
		for (int i = 0; i != key.length(); i++) {
			switch (key.charAt(i)) {
				case 'a': continue;
				case 'b': continue;
				case 'c': continue;
				case 'd': continue;
				case 'e': continue;
				case 'f': continue;
				case 'g': continue;
				case 'h': continue;
				case 'i': continue;
				case 'j': continue;
				case 'k': continue;
				case 'l': continue;
				case 'm': continue;
				case 'n': continue;
				case 'o': continue;
				case 'p': continue;
				case 'q': continue;
				case 'r': continue;
				case 's': continue;
				case 't': continue;
				case 'u': continue;
				case 'v': continue;
				case 'w': continue;
				case 'x': continue;
				case 'y': continue;
				case 'z': continue;
				case '0': continue;
				case '1': continue;
				case '2': continue;
				case '3': continue;
				case '4': continue;
				case '5': continue;
				case '6': continue;
				case '7': continue;
				case '8': continue;
				case '9': continue;
				case '_': continue;
				case '&': continue;
				
				default: return "Char " + key.charAt(i) + " (" + i + ") invalid";
			}
		}
		String name = key;
		key = BBLogger.stripColor(key.toLowerCase());
		yml.createSection(key);
		yml.set(key + ".x", data[0]);
		yml.set(key + ".y", data[1]);
		yml.set(key + ".z", data[2]);
		yml.set(key + ".world", data[3]);
		yml.set(key + ".pitch", data[4]);
		yml.set(key + ".yaw", data[5]);
		yml.set(key + ".perm", data[6]);
		yml.set(key + ".stylized_name", name);
		update();
		return "";
	}
	public static String list() {
		Set<String> list = yml.getRoot().getKeys(false);
		String strList = "";
		for (String str : list) {
			strList = strList + "§r, " + str;
		}
		if (strList.length() == 0) {
			return "";
		}
		return strList.substring(4);
	}
}