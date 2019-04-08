
package com.github.bukkitbasics.Util;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.Integration.BukkitBasicsIntegrate;

public class Integration {
	/**
	 * 
	 * @param main The method that implements BukkitBasicsIntegrate
	 * @throws ClassNotFoundException This is thrown if the class provided does not exist.
	 * @throws SecurityException This is probably never thrown.
	 * @throws NoSuchMethodException This is thrown if you do not have the proper methods(for some reason).
	 */
	public static void register(String main) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class<?> c = Class.forName(main);
		if (BukkitBasicsIntegrate.class.isAssignableFrom(c.getClass())) {
			BBLogger.println("§4Error while registering method \"§c" + main + "§4\"");
		}
		LandPermissionMethods.add(
				c.getMethod("LandPermission", Player.class, Location.class)
		);
	}
	private static ArrayList<Method> LandPermissionMethods = new ArrayList<Method>();
	public static ArrayList<Method> LandPermissionMethods() {
		return LandPermissionMethods;
	}
}
