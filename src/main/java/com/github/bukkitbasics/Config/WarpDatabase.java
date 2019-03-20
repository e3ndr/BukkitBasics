package com.github.bukkitbasics.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.github.bukkitbasics.DebugPrinter;

public class WarpDatabase {
	public static File warps;
	public static ArrayList<String> data = new ArrayList<String>();
	
	public static void init() {
		warps = new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/warps.txt");
		if (!warps.exists()) {
			DebugPrinter.println("Making Warp Database");
			new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/").mkdir();
	        try {
				warps.createNewFile();
				PrintWriter pw = new PrintWriter(warps);
				pw.write("");
		        pw.flush();
		        pw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		update();
	}
	private static ArrayList<String> warpsRaw = new ArrayList<String>();
	public static String get(String key) {
		if (warpsRaw.get(0) == null) {
        	return "_no warps available";
        }
        
        String warpList = "_";
        for (int i = 0; i != warpsRaw.size(); i++) {
        	String databaseLine = warpsRaw.get(i);
        	if (databaseLine == "" || databaseLine == null) {
        		continue;
        	}
        	if (databaseLine.length() > key.length() && databaseLine.substring(0, databaseLine.indexOf(":")).replace("&", "§").equals(key)) {
        		return databaseLine.substring(key.length() + 1);
        	}
        	warpList = warpList + databaseLine.substring(0, databaseLine.indexOf(":")) + "§r, ";
        }
        
		return warpList.substring(0, warpList.length() - 2).replace("&", "§");
	}
	public static void update() {
		File file = warps;
		try {
			FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	
	        // If we skipped this it would read lines incorrectly.
	        String line = null;
	        while ((line = br.readLine()) != null) {
	        	if (!line.equals(null) || !line.substring(0, 1).equals("#")) {
	            	warpsRaw.add(line);
	        	}
	        }
	        
	        br.close();
		} catch (IOException e) {
			// Literally love this song idk why pls help https://www.youtube.com/watch?v=gBRi6aZJGj4
			e.printStackTrace();
		}
	}
	public static String add(String key, String data) {
		for (int i = 0; i != key.length(); i++) {
			switch (key.charAt(i)) {
				case 'a': continue;
				case 'b': continue;
				case 'c': continue;
				case 'd': continue;
				case 'e': continue;
				case 'f': continue;
				case 'g': continue;
				case 'h': continue;
				case 'i': continue;
				case 'j': continue;
				case 'k': continue;
				case 'l': continue;
				case 'm': continue;
				case 'n': continue;
				case 'o': continue;
				case 'p': continue;
				case 'q': continue;
				case 'r': continue;
				case 's': continue;
				case 't': continue;
				case 'u': continue;
				case 'v': continue;
				case 'w': continue;
				case 'x': continue;
				case 'y': continue;
				case 'z': continue;
				case '0': continue;
				case '1': continue;
				case '2': continue;
				case '3': continue;
				case '4': continue;
				case '5': continue;
				case '6': continue;
				case '7': continue;
				case '8': continue;
				case '9': continue;
				case '_': continue;
				case '&': continue;
				
				default: return "Char " + key.charAt(i) + " (" + i + ") invalid";
			}
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(warps, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println(key + ":" + data);
        pw.flush();
        pw.close();
        
        update();
        
        return "";
	}
}