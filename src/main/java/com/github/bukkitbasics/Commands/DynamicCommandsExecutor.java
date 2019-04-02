package com.github.bukkitbasics.Commands;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import com.github.bukkitbasics.Config.lang;

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
    		sender.sendMessage(lang.get("custom.discord").replace("$player", sender.getName()));
    		return true;
    	} else if (alias.equals("website")) {
    		sender.sendMessage(lang.get("custom.website").replace("$player", sender.getName()));
    		return true;
    	} else if (alias.equals("help")) {
    		sender.sendMessage(lang.get("custom.help").replace("$player", sender.getName()));
    		return true;
    	} else if (alias.equals("vote")) {
     		sender.sendMessage(lang.get("custom.vote").replace("$player", sender.getName()));
     		return true;
     	}
     	
		return false;
    }
}
