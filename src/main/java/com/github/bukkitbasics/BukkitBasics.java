package com.github.bukkitbasics;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.bukkitbasics.Commands.bbdebug;
import com.github.bukkitbasics.Commands.coords;
import com.github.bukkitbasics.Commands.fly;
import com.github.bukkitbasics.Commands.gamemode;
import com.github.bukkitbasics.Commands.motd;
import com.github.bukkitbasics.Commands.resetLang;
import com.github.bukkitbasics.Commands.setLang;
import com.github.bukkitbasics.Commands.setspawn;
import com.github.bukkitbasics.Commands.setwarp;
import com.github.bukkitbasics.Commands.spawn;
import com.github.bukkitbasics.Commands.suicide;
import com.github.bukkitbasics.Commands.warp;
import com.github.bukkitbasics.Config.PluginConfig;
import com.github.bukkitbasics.Config.WarpDatabase;
import com.github.bukkitbasics.Listeners.LoginListener;

public final class BukkitBasics extends JavaPlugin {
	public static BukkitBasics instance;
    public static boolean reload;

	@Override
    public void onEnable() {
		instance = this;
		variables.setVars();
		BBLogger.instance(instance);
		WarpDatabase.init();
		lang.init();
		PluginConfig.init();
		getServer().getPluginManager().registerEvents(new LoginListener(), this);
		
		BBLogger.println(("&dBukkitBasics &aversion " + this.getDescription().getVersion()).replace("&", "§"));
		BBLogger.println(("\"&a" + this.getDescription().getDescription() + "&r\"").replace("&", "§"));
		
		// Non reloadables
		if (reload) {
			BBLogger.println("§4 Currently §ccustom commands §4and §cbook and quill disabling §4is not supported by reloading. Restart the server to see changes take effect.");
			reload = false;
		} else {
			try {
				DynamicCommands.init();
			} catch (IOException e) {
				BBLogger.exception(e);
			}
			Recipes.init();
		}
		
		// execution
		this.getCommand("spawn").setExecutor(new spawn());
		this.getCommand("setspawn").setExecutor(new setspawn());
		this.getCommand("coords").setExecutor(new coords());
		this.getCommand("setlang").setExecutor(new setLang());
		this.getCommand("resetlang").setExecutor(new resetLang());
		this.getCommand("motd").setExecutor(new motd());
		this.getCommand("bbdebug").setExecutor(new bbdebug());
		this.getCommand("suicide").setExecutor(new suicide());
		this.getCommand("gamemode").setExecutor(new gamemode());
		this.getCommand("gms").setExecutor(new gamemode());
		this.getCommand("gmc").setExecutor(new gamemode());
		this.getCommand("gma").setExecutor(new gamemode());
		this.getCommand("gmsp").setExecutor(new gamemode());
		this.getCommand("fly").setExecutor(new fly());
		this.getCommand("warp").setExecutor(new warp());
		this.getCommand("setwarp").setExecutor(new setwarp());
		
		// tab completion
		this.getCommand("spawn").setTabCompleter(new spawn());
		this.getCommand("setspawn").setTabCompleter(new setspawn());
		this.getCommand("coords").setTabCompleter(new coords());
		this.getCommand("setlang").setTabCompleter(new setLang());
		this.getCommand("resetlang").setTabCompleter(new resetLang());
		this.getCommand("motd").setTabCompleter(new motd());
		this.getCommand("bbdebug").setTabCompleter(new bbdebug());
		this.getCommand("suicide").setTabCompleter(new suicide());
		this.getCommand("gamemode").setTabCompleter(new gamemode());
		this.getCommand("gms").setTabCompleter(new gamemode());
		this.getCommand("gmc").setTabCompleter(new gamemode());
		this.getCommand("gma").setTabCompleter(new gamemode());
		this.getCommand("gmsp").setTabCompleter(new gamemode());
		this.getCommand("fly").setTabCompleter(new fly());
		this.getCommand("warp").setTabCompleter(new warp());
		this.getCommand("setwarp").setTabCompleter(new setwarp());
		
	}

	@Override
    public void onDisable() {
		instance = null;

		if (reload) {
			Bukkit.getServer().getPluginManager().enablePlugin(this);
		}
	}
	
}
