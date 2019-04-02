package com.github.bukkitbasics.Config;

import java.io.File;
import java.io.IOException;

import com.github.bukkitbasics.DynamicCommands;
import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Util.BBLogger;
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
				case "use-server-motd": variables.use_server_motd = valueof.bool(data[i][1], false);
				case "show-motd-message": variables.showMotd = valueof.bool(data[i][1], false);
				case "show-custom-join-message": variables.customJoinMessage = valueof.bool(data[i][1], false);
				case "show-custom-leave-message": variables.customLeaveMessage = valueof.bool(data[i][1], false);
				case "console-color": variables.use_color = valueof.bool(data[i][1], true);
				case "discord-command-enable": DynamicCommands.discord_command_enable = valueof.bool(data[i][1], false);
				case "website-command-enable": DynamicCommands.website_command_enable = valueof.bool(data[i][1], false);
				case "help-command-enable": DynamicCommands.help_command_enable = valueof.bool(data[i][1], false);
				case "vote-command-enable": DynamicCommands.vote_command_enable = valueof.bool(data[i][1], false);
				case "remove-book-recipe": variables.removeBookRecipe = valueof.bool(data[i][1], false);
				case "dynamic-ping-motd": variables.dynamicPingMotd = valueof.bool(data[i][1], false);
				case "broadcast-silent-events-to-admins": variables.silentBroadcasts = valueof.bool(data[i][1], false);
				case "silent-events-enabled": variables.silentEventsEnable = valueof.bool(data[i][1], false);
				case "use-respawn-handler": variables.useRespawnHandler = valueof.bool(data[i][1], true);
				
			}
		}
	}
	private static void writeDefault() throws IOException {
		String[][] data = {
				{"use-server-motd","false"},
				{"show-motd-message","true"},
				{"show-custom-join-message","false"},
				{"show-custom-leave-message","false"},
				{"console-color","true"},
				{"discord-command-enable", "false"},
				{"website-command-enable", "false"},
				{"help-command-enable", "false"},
				{"vote-command-enable", "false"},
				{"remove-book-recipe", "false"},
				{"dynamic-ping-motd", "false"},
				{"broadcast-silent-events-to-admins", "true"},
				{"silent-events-enabled", "true"},
				{"use-respawn-handler", "true"}
				
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
