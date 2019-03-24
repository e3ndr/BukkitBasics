package com.github.bukkitbasics.Commands;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import com.github.bukkitbasics.BBLogger;
import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.variables;

public class DynamicCommandsExecutor extends BukkitCommand {
    public DynamicCommandsExecutor(String name) {
        super(name);
        this.description = "";
        this.usageMessage = "/" + name;
        this.setPermission(name);
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
    	if (alias.equals("discord")) {
    		sender.sendMessage(lang.get("custom.discord"));
    		return true;
    	} else if (alias.equals("website")) {
    		sender.sendMessage(lang.get("custom.website"));
    		return true;
    	} else if (alias.equals("help")) {
    		if (variables.help_message.length != 0) {
	    		for (int i = 0; i != variables.help_message.length; i++) {
	    			sender.sendMessage(BBLogger.transformColor(variables.help_message[i]));
	    		}
    		}
    		return true;
    	}
    	
		return false;
    }
}
