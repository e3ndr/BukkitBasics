package com.github.bukkitbasics;

import org.bukkit.Bukkit;

public class variables {
	public static String main_world_name = "world";
	public static boolean use_server_motd = false;
	public static boolean showMotd = true;
	public static boolean customJoinMessage = false;
	public static boolean debug_printstack = false;
	public static boolean use_color = true;
	public static boolean removeBookRecipe = false;
	public static boolean customLeaveMessage = false;
	public static boolean dynamicPingMotd = false;
	public static boolean silentBroadcasts = true;
	public static boolean silentEventsEnable = true;
	
	
	
	public static void setVars() {
		 main_world_name = Bukkit.getWorlds().get(0).getName();
	}

}
