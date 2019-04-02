package com.github.bukkitbasics;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.github.bukkitbasics.Commands.DynamicCommandsExecutor;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Util.BBLogger;

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
    public static boolean help_command_enable = false;
    public static boolean vote_command_enable;
    public static ArrayList<Command> commands = new ArrayList<Command>();
	public static void init() throws IOException {
    	getmap();
    	if (discord_command_enable) {
    		commands.add(new DynamicCommandsExecutor("discord").setUsage("/discord").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.discord")));
    	}
    	if (website_command_enable) {
    		commands.add(new DynamicCommandsExecutor("website").setUsage("/website").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.website")));
    	}
    	if (help_command_enable) {
    		commands.add(new DynamicCommandsExecutor("help").setUsage("/help").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.help")));
    	}
    	if (vote_command_enable) {
    		commands.add(new DynamicCommandsExecutor("vote").setUsage("/vote").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.vote")));
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