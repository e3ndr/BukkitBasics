package com.github.bukkitbasics.Integration;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.struct.Relation;

public class FactionsIntegration {
	public static boolean landPermission(Player player, Location loc) {
		Faction faction = Board.getInstance().getFactionAt(new FLocation(loc));
		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);

		Relation relation = faction.getRelationTo(fPlayer.getFaction());
		if (!relation.isAlly() || !relation.isMember()) return false;
		return true;
	}
}
