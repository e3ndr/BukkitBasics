package com.github.bukkitbasics.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Util.BBLogger;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

public class HomeDatabase {
	public static String[] get(String key, String uuid) {
		// soft depends
		if (key.equalsIgnoreCase("faction") && variables.factionsPresent) {
			FPlayer fPlayer = FPlayers.getInstance().getByPlayer(Bukkit.getPlayer(UUID.fromString(uuid)));
			Location loc = fPlayer.getFaction().getHome();
			
			String[] data = {
					String.valueOf(loc.getX()),
					String.valueOf(loc.getY()),
					String.valueOf(loc.getZ()),
					String.valueOf(loc.getWorld()),
					String.valueOf(loc.getPitch()),
					String.valueOf(loc.getYaw())
			};
			return data;
		}
		
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(new File("./plugins/BukkitBasics/homes/" + uuid + ".yml"));
		key = key.toLowerCase();
		if (!yml.contains(key)) {
			return new String[0];
		}
		String[] data = {
				(String) yml.get(key + ".x"),
				(String) yml.get(key + ".y"),
				(String) yml.get(key + ".z"),
				(String) yml.get(key + ".world"),
				(String) yml.get(key + ".pitch"),
				(String) yml.get(key + ".yaw")
		};
		return data;
	}
	public static void delete (String key, String uuid) {
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(new File("./plugins/BukkitBasics/homes/" + uuid + ".yml"));
		yml.set(key, null);
		update(yml, uuid);
	}
	public static void update(YamlConfiguration yml, String uuid) {
		try {
			yml.save("./plugins/BukkitBasics/homes/" + uuid + ".yml");
		} catch (IOException e) {
			BBLogger.exception(e);
		}
	}
	public static void add(String key, String[] data, String uuid) {
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(new File("./plugins/BukkitBasics/homes/" + uuid + ".yml"));
		
		key = BBLogger.stripColor(key.toLowerCase());
		yml.createSection(key);
		yml.set(key + ".x", data[0]);
		yml.set(key + ".y", data[1]);
		yml.set(key + ".z", data[2]);
		yml.set(key + ".world", data[3]);
		yml.set(key + ".pitch", data[4]);
		yml.set(key + ".yaw", data[5]);
		update(yml, uuid);
	}
	public static String list(String uuid) {
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(new File("./plugins/BukkitBasics/homes/" + uuid + ".yml"));
		Set<String> list = yml.getRoot().getKeys(false);
		String strList = "";
		for (String str : list) {
			strList = strList + "§r, " + str;
		}
		
		// soft depends
		if (variables.factionsPresent) {
			strList = strList + "§r, " + "Faction";
		}
		
		if (strList.length() == 0) {
			return "";
		}
		return strList.substring(4);
	}
	public static ArrayList<String> listArray(String uuid) {
		return new ArrayList<String>(Arrays.asList(BBLogger.stripColor(list(uuid)).split(", ")));
	}
}