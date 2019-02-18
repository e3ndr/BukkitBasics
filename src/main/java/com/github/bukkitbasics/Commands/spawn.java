package com.github.bukkitbasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.variables;

public class spawn implements CommandExecutor {
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	Location pos = Bukkit.getServer().getWorld(variables.main_world_name).getSpawnLocation();
    	Location loc = new Location(Bukkit.getWorld(variables.main_world_name), pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    	Player player = (Player) sender;
    	player.teleport(loc);
    	player.sendMessage(lang.get("spawn.teleport"));
    	return true;
    }

}