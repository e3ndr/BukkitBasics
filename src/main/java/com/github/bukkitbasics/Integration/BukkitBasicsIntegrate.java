package com.github.bukkitbasics.Integration;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface BukkitBasicsIntegrate {
	/**
	 * 
	 * @param player The player
	 * @param loc The location of the block
	 * @return true if they have land permission, false if they do not.
	 */
	public boolean LandPermission(Player player, Location loc);
}
