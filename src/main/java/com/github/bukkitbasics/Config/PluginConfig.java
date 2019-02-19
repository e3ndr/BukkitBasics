package com.github.bukkitbasics.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.github.bukkitbasics.DebugPrinter;
import com.github.bukkitbasics.variables;

public class PluginConfig {
	private static ArrayList<String> configRaw = new ArrayList<String>();
	
	private static String file= "plugins/BukkitBasics/config.txt";
	private static void read() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // If we skipped this it would read lines incorrectly.
        String line = null;
        while ((line = br.readLine()) != null) {
        	if (!line.substring(0, 1).equals("#")) {
            	configRaw.add(line);
        	}
        }
        
        br.close();
        
        for (int i = 0; i != configRaw.size(); i++) {
        	String configLine = configRaw.get(i);
        	
        	if (configLine.length() > 6 && configLine.substring(0, 5).equals("motd:")) {
        		variables.motd = configLine.substring(5, configLine.length());
        	}
        	if (configLine.length() > 7 && configLine.substring(0, 8).equals("motdUse:")) {
        		if (configLine.substring(8, configLine.length()).equals("true")) {
        			variables.motdUse = true;
        		} else {
        			variables.motdUse = false;
        		}
        	}
        	if (configLine.length() > 8 && configLine.substring(0, 9).equals("showMotd:")) {
        		if (configLine.substring(9, configLine.length()).equals("false")) {
        			variables.showMotd = false;
        		} else {
        			variables.showMotd = true;
        		}
        	}
        	if (configLine.length() > 17 && configLine.substring(0, 18).equals("customJoinMessage:")) {
        		if (configLine.substring(18, configLine.length()).equals("false")) {
        			variables.customJoinMessage = false;
        		} else {
        			variables.customJoinMessage = true;
        		}
        	}
        	
        }
        
	}
	private static void writeDefault() throws IOException {
		FileWriter fileWriter = new FileWriter(file);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    
	    // motd
	    printWriter.println("# PLUGIN CONFIG FOR BUKKIT BASICS");
	    printWriter.println("#");
	    printWriter.println("# These are the variables for /motd.");
	    printWriter.println("# motd is the message you want to send to the player on join, string");
	    printWriter.println("# motdUse decides wether or not to use the server's native motd that is shown in the multiplayer screen, boolean");
	    printWriter.println("# showMotd decides wether you want to show the motd to the player on join, boolean");
	    printWriter.println("motd:Welcome to the server!");
	    printWriter.println("motdUse:false");
	    printWriter.println("showMotd:true");
	    printWriter.println("#");
	    printWriter.println("# Miscellanous");
	    printWriter.println("customJoinMessage:false");
	    
	    printWriter.close();
	}
	public static void init() {
		File configFile = new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/configFile.json");
		if (!configFile.exists()) {
			DebugPrinter.println("Making Config");
			new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/").mkdir();
	        try {
				configFile.createNewFile();
				writeDefault();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
