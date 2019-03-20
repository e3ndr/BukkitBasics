package com.github.bukkitbasics;

import java.io.File;
import java.io.IOException;

import com.github.xcore.TextDataBase;
import com.github.xcore.Datatype.DataBase;

public class lang {
	private static File lang;
	private static DataBase LangDB;
	public static void init() {
		lang = new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/lang.txt");
		if (!lang.exists()) {
			DebugPrinter.println("Making Lang Config");
			new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/").mkdirs();
	        try {
				lang.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			create();
		}
		update();
	}
	public static void create() {
		String[][] data = {
				{"_comment0", "The Character \"&\" will be replaced by § when executed, \\& will not be replaced."},
				{"spawn.teleport", "&8[&4Server&8]&r Teleported you to spawn"},
				{"spawn.set", "&8[&4Server&8]&r Set world spawn to &7$location"},
				{"coords.0", "&8[&4Server&8]&r You are at: &7$location"},
				{"coords.1", "&8[&4Server&8]&r With a pitch of: &7$pitch &rand a yaw of: &7$yaw"},
				{"join.message", "&e$player joined the game"},
				{"no.perm", "&8[&4Server&8]&r Sorry, but you do not have permission &7$permission &rthat is required to do this"},
				{"gamemode.updated.0", "&8[&4Server&8]&r Your gamemode was updated to $mode"},
				{"gamemode.updated.1", "&8[&4Server&8]&r $player updated your gamemode to $mode"},
				{"gamemode.updated.others", "&8[&4Server&8]&r Updated the gamemode for $player to $mode"},
				{"fly", "&8[&4Server&8]&r Updated flight to $boolean"},
				{"fly.others.0", "&8[&4Server&8]&r Updated flight permission for $player to $boolean"},
				{"error.syntax", "&8[&4Server&8]&r Invalid syntax"},
				{"warp.set", "&8[&4Server&8]&r Warp set!"},
				{"warp.others", "&8[&4Server&8]&r $player warped you to $warp"},
				{"warp.self", "&8[&4Server&8]&r Warped you to $warp"},
				{"warp.list", "&8[&4Server&8]&r Warps: $list"},
				{"warp.none", "&8[&4Server&8]&r There are no warps available."},
				{"warp.not_found", "&8[&4Server&8]&r Warp not found!"}
		};
		DataBase db = new DataBase("./plugins/BukkitBasics/lang.txt", data);
		try {
			TextDataBase.writeDataBase(db);
		} catch (IOException e) {
			DebugPrinter.println("Creation of lang config failed, stack trace:");
			e.printStackTrace();
		}
		LangDB = db;
	}
	public static String get(String key) {
		String[][] data = LangDB.getData();
		
		for (int i = 0; i != data.length; i++) {
			if (data[i][0].equals(key)) {
				return data[i][1].replace("\\&", "↔").replace("&", "§").replace("↔", "&");
			}
		}
		
		return key;
	}
	public static void update() {
		try {
			LangDB = TextDataBase.getDataBase("./plugins/BukkitBasics/lang.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setLang(String langKey, String value) {
		String[][] data = LangDB.getData();
		value = value.replace("=", "\\=");
		for (int i = 0; i != data.length; i++) {
			if (data[i][0] == langKey) {
				data[i][1] = value;
				LangDB = new DataBase(LangDB.getInfo(), data);
				try {
					TextDataBase.writeDataBase(LangDB);
				} catch (IOException e) {
					DebugPrinter.println("Modification of lang config failed, stack trace:");
					e.printStackTrace();
				}
				update();
				return;
			}
		}
		LangDB.addData(new String[][] {
			{langKey, value}
		});
		try {
			TextDataBase.writeDataBase(LangDB);
		} catch (IOException e) {
			DebugPrinter.println("Modification of lang config failed, stack trace:");
			e.printStackTrace();
		}
		update();
	}
}
