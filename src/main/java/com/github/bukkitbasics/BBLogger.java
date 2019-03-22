package com.github.bukkitbasics;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class BBLogger {
	private static BukkitBasics instance;
	private static boolean testmode = false;

	public static void println(Object obj) {
		String msg = String.valueOf(obj);
		String prefix = "§8[§d" + instance.getName() + "§8]§r ";
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
	
	public static void instance(BukkitBasics inst) {
		instance = inst;
		return;
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
	private static String stripColor(String msg) {
		return msg.replace("§4", "")
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
}
