package com.github.bukkitbasics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class lang {
	private static File lang;
	public static void init() {
		lang = new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/lang.json");
		if (!lang.exists()) {
			DebugPrinter.println("Making Lang Config");
			new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/").mkdir();
	        try {
				lang.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			create();
		}
		set();
	}
	@SuppressWarnings("unchecked")
	public static void create() {
		JSONObject jo = new JSONObject();
        
        // putting data to JSONObject
		jo.put(" ", "&8[&4Server&8]&r Teleported you to spawn");
		jo.put("spawn.set", "&8[&4Server&8]&r Set world spawn to &7$location");
		jo.put("coords.0", "&8[&4Server&8]&r You are at: &7$location");
		jo.put("coords.1", "&8[&4Server&8]&r With a pitch of: &7$pitch &rand a yaw of: &7$yaw");
		jo.put("join.message", "&e$player joined the game");
		jo.put("no.perm", "&8[&4Server&8]&r Sorry, but you do not have permission &7$permission &rthat is required to do this");
		jo.put("gamemode.updated.0", "&8[&4Server&8]&r Your gamemode was updated to $mode");
		jo.put("gamemode.updated.1", "&8[&4Server&8]&r $player updated your gamemode to $mode");
		jo.put("gamemode.updated.others", "&8[&4Server&8]&r Updated the gamemode for $player to $mode");
		jo.put("fly", "&8[&4Server&8]&r Updated flight to $boolean");
		jo.put("fly.others.0", "&8[&4Server&8]&r Updated flight permission for $player to $boolean");
		jo.put("fly.others.1", "&8[&4Server&8]&r $player updated your flight to $boolean");
		
		jo.put("_comment2", "If a string is missing you it will be replaced by the default message followed by : (lang.message, loc1, pl0) where 1 and 0 are true and false respectively and show what can be used.");
		jo.put("_comment1", "The sequences $permission, $location, $player, $X, $Y, $Z, $playerYaw, $playerPitch, and $target will be replaced by the cooresponding information, only where applicable");
		jo.put("_comment0", "The Character \"&\" will be replaced by § when executed.");
        PrintWriter pw = null;
		try {
			pw = new PrintWriter(lang);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
	}
	private static String spawn_teleport = "&8[&4Invalid&8]&r Teleported you to spawn (spawn.teleport, loc0, pl0, x0, y0, z0, yw0, p0, tg0)".replace("&", "§");
	private static String spawn_set = "&8[&4Invalid&8]&r Set world spawn to &location (spawn.set, loc1, pl0, x0, y0, z0, yw0, p0, tg0)".replace("&", "§");
	private static String coords_0 = "&8[&4Invalid&8]&r You are at: &7$location (spawn.set, loc1, pl0, x1, y1, z1, yw1, p1, tg0)".replace("&", "§");
	private static String coords_1 = "&8[&4Invalid&8]&r With a pitch of: &7$pitch &rand a yaw of: &7$yaw (spawn.set, loc1, pl0, x1, y1, z1, yw1, p1, tg0)".replace("&", "§");
	private static String join_message = "&e$player joined the game)".replace("&", "§");
	private static String no_perm = "&8[&4Server&8]&r Sorry, but you do not have permission &7$permission &rthat is required to do this".replace("&", "§");
	private static String gamemode_updated_0 = "&8[&4Server&8]&r Your gamemode was updated to $mode".replace("&", "§");
	private static String gamemode_updated_1 = "&8[&4Server&8]&r $player updated your gamemode to $mode".replace("&", "§");
	private static String gamemode_updated_others = "&8[&4Server&8]&r Updated the gamemode for $player to $mode".replace("&", "§");
	private static String fly = "&8[&4Server&8]&r Updated flight to $boolean".replace("&", "§");
	private static String fly_others_0 = "&8[&4Server&8]&r Updated flight permission for $player to $boolean".replace("&", "§");
	private static String fly_others_1 = "&8[&4Server&8]&r $player updated your flight to $boolean".replace("&", "§");
	
	private static void set() {
		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader(lang));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // typecasting obj to JSONObject 
       JSONObject jo = (JSONObject) obj; 
       
       if (!(jo.get("spawn.teleport") == null)) {spawn_teleport = ((String) jo.get("spawn.teleport")).replace("&", "§");}
       if (!(jo.get("spawn.set") == null)) {spawn_set = ((String) jo.get("spawn.set")).replace("&", "§");}
       if (!(jo.get("coords.0") == null)) {coords_0 = ((String) jo.get("coords.0")).replace("&", "§");}
       if (!(jo.get("coords.1") == null)) {coords_1 = ((String) jo.get("coords.1")).replace("&", "§");}
       if (!(jo.get("join.message") == null)) {join_message = ((String) jo.get("join.message")).replace("&", "§");}
       if (!(jo.get("no.perm") == null)) {no_perm = ((String) jo.get("no.perm")).replace("&", "§");}
       if (!(jo.get("gamemode.updated.0") == null)) {gamemode_updated_0 = ((String) jo.get("gamemode.updated.0")).replace("&", "§");}
       if (!(jo.get("gamemode.updated.1") == null)) {gamemode_updated_1 = ((String) jo.get("gamemode.updated.1")).replace("&", "§");}
       if (!(jo.get("gamemode.updated.others") == null)) {gamemode_updated_others = ((String) jo.get("gamemode.updated.others")).replace("&", "§");}
       if (!(jo.get("fly") == null)) {fly = ((String) jo.get("fly")).replace("&", "§");}
       if (!(jo.get("fly.others.0") == null)) {fly_others_0 = ((String) jo.get("fly.others.0")).replace("&", "§");}
       if (!(jo.get("fly.others.1") == null)) {fly_others_1 = ((String) jo.get("fly.others.1")).replace("&", "§");}
       
	}
	public static String get(String name) {
		switch (name) {
			case "spawn.teleport": return spawn_teleport;
			case "spawn.set": return spawn_set;
			case "coords.0": return coords_0;
			case "coords.1": return coords_1;
			case "join": return join_message;
			case "no.perm": return no_perm;
			case "gamemode.updated.0": return gamemode_updated_0;
			case "gamemode.updated.1": return gamemode_updated_1;
			case "gamemode.updated.others": return gamemode_updated_others;
			case "fly": return fly;
			case "fly.others.0": return fly_others_0;
			case "fly.others.1": return fly_others_1;
			
		}
		return "null";
	}
	@SuppressWarnings("unchecked")
	public static void setLang(String lang0, String value) {
		JSONObject jo = new JSONObject();
        // putting data to JSONObject
		jo.put(lang0, value);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(lang);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
        set();
	}
}
