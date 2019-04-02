package com.github.bukkitbasics.Config;

import java.io.File;
import java.io.IOException;

import com.github.bukkitbasics.Util.BBLogger;
import com.github.xcore.TextDataBase;
import com.github.xcore.Datatype.DataBase;

public class lang {
	private static File lang;
	private static DataBase LangDB;
	public static void init() {
		lang = new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/lang.txt");
		if (!lang.exists()) {
			BBLogger.println("Making Lang Config");
			new File(System.getProperty("user.dir") + "/plugins/BukkitBasics/").mkdirs();
	        try {
				lang.createNewFile();
			} catch (IOException e) {
				BBLogger.exception(e);
			}
			create();
		}
		update();
	}
	public static String[][] defaultData = {
			{"_comment0", "The Character \"&\" will be replaced by § when executed, \\& will not be replaced."},
			{"lang.update", "&8[&4Server&8]&r updated lang $lang to $key"},
			{"spawn.teleport", "&8[&4Server&8]&r Teleported you to spawn"},
			{"spawn.set", "&8[&4Server&8]&r Set world spawn to &7$location"},
			{"coords.0", "&8[&4Server&8]&r You are at: &7$location"},
			{"coords.1", "&8[&4Server&8]&r With a pitch of: &7$pitch &rand a yaw of: &7$yaw"},
			{"join.message", "&e$player joined the game"},
			{"leave.message", "&e$player left the game"},
			{"no.perm", "&8[&4Server&8]&r Sorry, but you do not have permission &7$permission &rthat is required to do this"},
			{"no.perm.others", "&8[&4Server&8]&r Sorry, but that player does not have &7$permission &rthat is required to do this"},
			{"gamemode.updated.0", "&8[&4Server&8]&r Your gamemode was updated to $mode"},
			{"gamemode.updated.1", "&8[&4Server&8]&r $player updated your gamemode to $mode"},
			{"gamemode.updated.others", "&8[&4Server&8]&r Updated the gamemode for $player to $mode"},
			{"fly", "&8[&4Server&8]&r Updated flight to $boolean"},
			{"fly.others.0", "&8[&4Server&8]&r Updated flight permission for $player to $boolean"},
			{"fly.others.1", "&8[&4Server&8]&r $player updated your flight permission to $boolean"},
			{"error.syntax", "&8[&4Server&8]&r Invalid syntax"},
			{"warp.set", "&8[&4Server&8]&r Warp set!"},
			{"warp.others", "&8[&4Server&8]&r $player warped you to $warp"},
			{"warp.self", "&8[&4Server&8]&r Warped you to $warp"},
			{"warp.list", "&8[&4Server&8]&r Warps: $list"},
			{"warp.none", "&8[&4Server&8]&r There are no warps available."},
			{"warp.no_perm", "&8[&4Server&8]&r Sorry, but you do not have permission &7$permission &rthat is required to warp here"},
			{"warp.not_found", "&8[&4Server&8]&r Warp not found!"},
			{"internal.error","&8[&4Server&8]&r An error internal occured, notify a staff member"},
			{"custom.discord","&8[&4Server&8]&r https://..."},
			{"custom.motd","Welcome to the server! \\n $online players online"},
			{"custom.website","&8[&4Server&8]&r https://..."},
			{"custom.help","This is a help command $player! \\n newline"},
			{"custom.vote","This is the vote command!"},
			{"dynamic.motd","Welcome to the server! \\n $online players online"},
			{"silent.join", "&a$player joined silently"},
			{"silent.leave", "&a$player left silently"}
	};
	public static void create() {
		DataBase db = new DataBase("./plugins/BukkitBasics/lang.txt", defaultData);
		try {
			TextDataBase.writeDataBase(db);
		} catch (IOException e) {
			BBLogger.println("Creation of lang config failed, stack trace:");
			e.printStackTrace();
		}
		LangDB = db;
	}
	public static String get(String key) {
		String[][] data = LangDB.getData();
		
		for (int i = 0; i != data.length; i++) {
			if (data[i][0].equals(key)) {
				return BBLogger.transformColor(data[i][1]).replace("$line", "\n").replace("\\n", "\n");
			}
		}
		BBLogger.println("Cannot find the language key \"" + key + ",\" set it using /setlang");
		return key;
	}
	public static void update() {
		try {
			LangDB = TextDataBase.getDataBase("./plugins/BukkitBasics/lang.txt");
		} catch (IOException e) {
			BBLogger.exception(e);
		}
	}
	public static void setLang(String langKey, String value) {
		String[][] data = LangDB.getData();
		value = value.replace("=", "\\=");
		for (int i = 0; i != data.length; i++) {
			if (data[i][0] == langKey) {
				data[i][1] = value;
				LangDB = new DataBase(LangDB.getLocation(), data);
				try {
					TextDataBase.writeDataBase(LangDB);
				} catch (IOException e) {
					BBLogger.println("Modification of lang config failed, stack trace:");
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
			BBLogger.println("Modification of lang config failed, stack trace:");
			e.printStackTrace();
		}
		update();
	}
}
