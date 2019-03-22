package com.github.bukkitbasics;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.github.bukkitbasics.Commands.DynamicCommandsExecutor;

public class DynamicCommands {
	private static CommandMap map;
	private static void getmap() {
        try{
            Field cMap = SimplePluginManager.class.getDeclaredField("commandMap");
            cMap.setAccessible(true);
            map = (CommandMap) cMap.get(Bukkit.getPluginManager());
        } catch (NoSuchFieldException e) {
        	BBLogger.exception(e);
        } catch (IllegalAccessException e) {
        	BBLogger.exception(e);
        }
    }
    
    public static boolean discord_command_enable = false;
    public static boolean website_command_enable = false;
    private static ArrayList<Command> commands = new ArrayList<Command>();
    public static void init() {
    	getmap();
    	if (discord_command_enable) {
    		commands.add(new DynamicCommandsExecutor("discord").setUsage("/discord").setPermissionMessage("BukkitBasics.custom.discord").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.discord")));
    	}
    	if (website_command_enable) {
    		commands.add(new DynamicCommandsExecutor("website").setUsage("/website").setPermissionMessage("BukkitBasics.custom.website").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.website")));
    		
    	}
    	
    	for (int i = 0; i != commands.size(); i++) {
    		map.register(commands.get(i).getName(),commands.get(i));
    	}
    }
    public static void clean() {
    	for (int i = 0; i != commands.size(); i++) {
    		commands.get(i).unregister(map);
    	}
    	commands.clear();
    }
}