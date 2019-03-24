package com.github.bukkitbasics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.github.bukkitbasics.Commands.DynamicCommandsExecutor;
import com.github.bukkitbasics.Commands.setwarp;

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
    		File helpFile = new File("./plugins/BukkitBasics/");
    		helpFile.mkdirs();
    		helpFile = new File("./plugins/BukkitBasics/help.txt");
    		
    		if (!helpFile.exists()) {
    			helpFile.createNewFile();
    		}
    		
    		FileReader fr = new FileReader(helpFile);
            BufferedReader br = new BufferedReader(fr);
            
            // If we skipped this it would read lines incorrectly.
            String line = null;
            while ((line = br.readLine()) != null) {
            	if (!line.substring(0, 1).equals("#")) {
            		String[] data = {line};
            		variables.help_message = Stream.concat(Arrays.stream(variables.help_message), Arrays.stream(data))
                    .toArray(String[]::new);
            	}
            }
            
            br.close();
    		commands.add(new DynamicCommandsExecutor("help").setUsage("/help").setPermissionMessage(lang.get("no.perm").replace("$permission", "BukkitBasics.custom.help")));
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