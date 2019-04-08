package com.github.bukkitbasics.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class PlayerUtil {
	public boolean isVanished(Player player) {
		for (MetadataValue meta : player.getMetadata("vanished")) {
			if (meta.asBoolean()) return true;
		}
		return false;
	}
	
	public static boolean hasLandPermission(Player player, Location loc) {
		boolean state = true;
		for (Method method : Integration.LandPermissionMethods()) {
			try {
				boolean status = (Boolean) method.invoke(method.getDeclaringClass().newInstance(), player, loc);
				if (!status) state = false;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				BBLogger.exception(e);
			}
			if (!state) break;
		}
		
		return state;
	}
}
