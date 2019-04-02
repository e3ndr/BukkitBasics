package com.github.bukkitbasics.Util;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import com.github.bukkitbasics.BukkitBasics;
import com.github.bukkitbasics.variables;

public class BBLogger {
	private static boolean testmode = false;

	public static void println(Object obj) {
		String msg = String.valueOf(obj);
		String prefix = "§8[§dBukkitBasics§8]§r ";
		if (!variables.use_color) {
			msg = stripColor(msg);
			prefix = stripColor(prefix);
		}
		if (testmode) {
			System.out.println(msg);
		} else {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage(prefix + msg);
		}
	}
	
	public static void testmode(boolean b) {
		testmode = b;
		println("Print type changed.");
		return;
	}
	public static void exception(Exception e) {
		if (!testmode) {
			if (variables.debug_printstack) {
				e.printStackTrace();	
			} else {
				println("§4An error occured, run §c/bbdebug stacktrace true§4 to enable stacktrace printing");
			}
		} else {
			e.printStackTrace();
		}
	}
	public static String stripColor(String msg) {
		return msg
				.replace("§4", "")
				.replace("§c", "")
				.replace("§6", "")
				.replace("§e", "")
				.replace("§2", "")
				.replace("§a", "")
				.replace("§b", "")
				.replace("§3", "")
				.replace("§1", "")
				.replace("§9", "")
				.replace("§d", "")
				.replace("§5", "")
				.replace("§f", "")
				.replace("§7", "")
				.replace("§8", "")
				.replace("§0", "")
				.replace("§l", "")
				.replace("§n", "")
				.replace("§o", "")
				.replace("§k", "")
				.replace("§m", "")
				.replace("§r", "");
	}
	public static String transformColor(String msg) {
		return msg
				.replace("&4", "§4")
				.replace("&c", "§c")
				.replace("&6", "§6")
				.replace("&e", "§e")
				.replace("&2", "§2")
				.replace("&a", "§a")
				.replace("&b", "§b")
				.replace("&3", "§3")
				.replace("&1", "§1")
				.replace("&9", "§9")
				.replace("&d", "§d")
				.replace("&5", "§5")
				.replace("&f", "§f")
				.replace("&7", "§7")
				.replace("&8", "§8")
				.replace("&0", "§0")
				.replace("&l", "§l")
				.replace("&n", "§n")
				.replace("&o", "§o")
				.replace("&k", "§k")
				.replace("&m", "§m")
				.replace("&r", "§r");
	}
	
}
