package com.github.bukkitbasics.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.github.bukkitbasics.variables;
import com.github.bukkitbasics.Config.lang;

public class DynamicServerMotd implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
    	if (variables.dynamicPingMotd) {
    		event.setMotd(lang.get("dynamic.motd").replace("$online", "" + event.getNumPlayers()).replace("$line", "\n").replace("\\n", "\n"));
    	}
    }
}
