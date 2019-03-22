package com.github.bukkitbasics.Config;

import java.io.File;
import java.io.IOException;

import com.github.bukkitbasics.BBLogger;
import com.github.bukkitbasics.DynamicCommands;
import com.github.bukkitbasics.variables;
import com.github.xcore.TextDataBase;
import com.github.xcore.Datatype.DataBase;
import com.github.xcore.Util.valueof;

public class PluginConfig {
	private static String configfile = "./plugins/BukkitBasics/config.txt";
	private static void read() throws IOException {
        DataBase db = TextDataBase.getDataBase(configfile);
        String[][] data = db.getData();
		
		for (int i = 0; i != data.length; i++) {
			switch (data[i][0]) {
				case "motd": variables.motd = data[i][1]; break;
				case "use-server-motd": variables.use_server_motd = valueof.bool(data[i][1], false);
				case "show-motd-message": variables.showMotd = valueof.bool(data[i][1], false);
				case "show-custom-join-message": variables.customJoinMessage = valueof.bool(data[i][1], false);
				case "console-color": variables.use_color = valueof.bool(data[i][1], true);
				case "discord-command-enable": DynamicCommands.discord_command_enable = valueof.bool(data[i][1], false);
				case "website-command-enable": DynamicCommands.website_command_enable = valueof.bool(data[i][1], false);
				
			}
		}
	}
	private static void writeDefault() throws IOException {
		String[][] data = {
				{"motd","&8[&4Server&8]&r Welcome to the server $player!"},
				{"use-server-motd","false"},
				{"show-motd-message","true"},
				{"show-custom-join-message","false"},
				{"console-color","true"},
				{"discord-command-enable", "false"},
				{"website-command-enable", "false"}
		};
		DataBase db = new DataBase(configfile, data);
		
	    TextDataBase.writeDataBase(db);
	}
	public static void init() {
		File configFile = new File(configfile);
		if (!configFile.exists()) {
			BBLogger.println("Making Config");
			new File("./plugins/BukkitBasics/").mkdirs();
	        try {
				configFile.createNewFile();
				writeDefault();
			} catch (IOException e) {
				BBLogger.exception(e);
			}
		}
		try {
			read();
		} catch (IOException e) {
			BBLogger.exception(e);
		}
	}
}
