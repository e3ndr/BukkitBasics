package com.github.bukkitbasics.Util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Integration.GriefPreventionIntegration;
import com.github.bukkitbasics.Integration.FactionsIntegration;

public class PlayerUtil {
	public boolean isVanished(Player player) {
		for (MetadataValue meta : player.getMetadata("vanished")) {
			if (meta.asBoolean()) return true;
		}
		return false;
	}
	
	public static boolean hasLandPermission(Player player, Location loc) {
		boolean state = true;
		if (variables.factionsPresent) {
			if (!FactionsIntegration.landPermission(player, loc)) state = false;
		}
		if (variables.griefpreventionPresent) {
			if (GriefPreventionIntegration.landPermission(player, loc) != null) state = false;
		}
		
		return state;
	}
}
