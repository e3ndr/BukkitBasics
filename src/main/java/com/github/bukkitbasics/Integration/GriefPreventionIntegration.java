package com.github.bukkitbasics.Integration;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.ryanhamshire.GriefPrevention.GriefPrevention;

public class GriefPreventionIntegration implements BukkitBasicsIntegrate {
	@Override
	public boolean LandPermission(Player player, Location loc) {
		if (GriefPrevention.instance.allowBreak(player, player.getLocation().getBlock(), loc) != null) return false;
		return true;
	}
}
