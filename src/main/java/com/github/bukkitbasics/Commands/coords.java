package com.github.bukkitbasics.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;

public class coords implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		String location = (int) player.getLocation().getX() + ", " + (int) player.getLocation().getY() + ", " + (int) player.getLocation().getZ();
		
		player.sendMessage(lang.get("coords.0").replace("$location", location)
				.replace("$X", String.valueOf(player.getLocation().getX()))
				.replace("$Y", String.valueOf(player.getLocation().getY()))
				.replace("$Z", String.valueOf(player.getLocation().getZ()))
				.replace("$pitch", String.valueOf(player.getLocation().getPitch()))
				.replace("$yaw", String.valueOf(player.getLocation().getYaw())));
		player.sendMessage(lang.get("coords.1")
				.replace("$location", location)
				.replace("$X", String.valueOf(player.getLocation().getX()))
				.replace("$Y", String.valueOf(player.getLocation().getY()))
				.replace("$Z", String.valueOf(player.getLocation().getZ()))
				.replace("$pitch", String.valueOf(player.getLocation().getPitch()))
				.replace("$yaw", String.valueOf(player.getLocation().getYaw())));
		
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> empty = new ArrayList<String>();
		empty.add("");
		return empty;
	}

}
