package com.github.bukkitbasics;

import org.bukkit.Bukkit;

public class variables {
	public static String main_world_name = "world";
	
	// motd
	public static String motd = "";
	public static boolean motdUse = false;
	public static boolean showMotd = true;
	
	
	public static void setVars() {
		 main_world_name = Bukkit.getWorlds().get(0).getName();
	}
}
