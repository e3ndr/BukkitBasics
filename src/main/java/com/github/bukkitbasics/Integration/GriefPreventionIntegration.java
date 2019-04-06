package com.github.bukkitbasics.Integration;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.ryanhamshire.GriefPrevention.GriefPrevention;

public class GriefPreventionIntegration {
	public static String landPermission(Player player, Location loc) {
		return GriefPrevention.instance.allowBreak(player, player.getLocation().getBlock(), player.getLocation());
	}
}
