package com.github.bukkitbasics.Commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.bukkitbasics.BBLogger;
import com.github.bukkitbasics.BukkitBasics;
import com.github.bukkitbasics.lang;
import com.github.bukkitbasics.variables;
import com.github.xcore.Util.valueof;

public class bbdebug implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 2) {
			if (args[0].equals("stacktrace")) {
				if (valueof.bool(args[1], false)) {
					variables.debug_printstack = true;
					sender.sendMessage("§8[§4BUKKIT BASICS DEBUG§8]§r Enabled stacktrace printing");
					return true;
				} else if (!valueof.bool(args[1], false) && variables.debug_printstack) {
					variables.debug_printstack = false;
					sender.sendMessage("§8[§4BUKKIT BASICS DEBUG§8]§r Disabled stacktrace printing");
					return true;
				} else if (variables.debug_printstack = valueof.bool(args[1], false)) {
					variables.debug_printstack = false;
					sender.sendMessage("§8[§4BUKKIT BASICS DEBUG§8]§r Stacktrace printing already set to " + valueof.bool(args[1], false));
					return true;
				}
			}
		}
		if (args.length == 1) {
			switch (args[0]) {
			case "throw": sender.sendMessage("§8[§4BUKKIT BASICS DEBUG§8]§r Throwing an error"); try { throw new IOException("test"); } catch (IOException e) { BBLogger.exception(e); } sender.sendMessage(lang.get("internal.error")); return true;
			case "reload": sender.sendMessage("§8[§4BUKKIT BASICS DEBUG§8]§r Reloading!"); BukkitBasics.reload = true; Bukkit.getServer().getPluginManager().disablePlugin(BukkitBasics.instance); return true;
			}
		}
		// sender.sendMessage("§8[§4BUKKIT BASICS DEBUG§8]§r");
		return false;
	}

}
