package com.github.bukkitbasics;

public class DebugPrinter {
	private static BukkitBasics instance;
	private static boolean testmode = false;

	// used internally
	
	public static void println(Object obj) {
		if (testmode) {
			System.out.println(String.valueOf(obj));
		} else {
			instance.getLogger().info(String.valueOf(obj));
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
}
