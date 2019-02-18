package com.github.bukkitbasics;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.bukkitbasics.Commands.coords;
import com.github.bukkitbasics.Commands.bbdebug;
import com.github.bukkitbasics.Commands.motd;
import com.github.bukkitbasics.Commands.resetLang;
import com.github.bukkitbasics.Commands.setLang;
import com.github.bukkitbasics.Commands.setspawn;
import com.github.bukkitbasics.Commands.spawn;
import com.github.bukkitbasics.Config.PlayerDataBase;
import com.github.bukkitbasics.Config.PluginConfig;
import com.github.bukkitbasics.Listeners.LoginListener;

public final class BukkitBasics extends JavaPlugin {
	static BukkitBasics instance;
    

	@Override
    public void onEnable() {
		instance = this;
		variables.setVars();
		DebugPrinter.instance(instance);
		lang.init();
		PluginConfig.init();
		getServer().getPluginManager().registerEvents(new LoginListener(), this);
		
		
		this.getCommand("spawn").setExecutor(new spawn());
		this.getCommand("setspawn").setExecutor(new setspawn());
		this.getCommand("coords").setExecutor(new coords());
		this.getCommand("setlang").setExecutor(new setLang());
		this.getCommand("resetlang").setExecutor(new resetLang());
		this.getCommand("motd").setExecutor(new motd());
		this.getCommand("bbdebug").setExecutor(new bbdebug());
		
	}

	@Override
    public void onDisable() {
		instance = null;
	}
	
}
