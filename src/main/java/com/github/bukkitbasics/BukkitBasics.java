package com.github.bukkitbasics;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.bukkitbasics.Commands.bbdebug;
import com.github.bukkitbasics.Commands.coords;
import com.github.bukkitbasics.Commands.fly;
import com.github.bukkitbasics.Commands.gamemode;
import com.github.bukkitbasics.Commands.home;
import com.github.bukkitbasics.Commands.motd;
import com.github.bukkitbasics.Commands.resetLang;
import com.github.bukkitbasics.Commands.setLang;
import com.github.bukkitbasics.Commands.sethome;
import com.github.bukkitbasics.Commands.setspawn;
import com.github.bukkitbasics.Commands.setwarp;
import com.github.bukkitbasics.Commands.spawn;
import com.github.bukkitbasics.Commands.suicide;
import com.github.bukkitbasics.Commands.warp;
import com.github.bukkitbasics.Config.PluginConfig;
import com.github.bukkitbasics.Config.WarpDatabase;
import com.github.bukkitbasics.Config.lang;
import com.github.bukkitbasics.Listeners.DynamicServerMotd;
import com.github.bukkitbasics.Listeners.GeneralListener;
import com.github.bukkitbasics.Util.BBLogger;
import com.github.bukkitbasics.Util.Integration;

public final class BukkitBasics extends JavaPlugin {
	public static BukkitBasics instance;
    public static boolean reload;

	@Override
    public void onEnable() {
		instance = this;
		variables.setVars();
		WarpDatabase.init();
		lang.init();
		PluginConfig.init();
		getServer().getPluginManager().registerEvents(new GeneralListener(), this);
		getServer().getPluginManager().registerEvents(new DynamicServerMotd(), this);
		
		BBLogger.println(("&dBukkitBasics &aversion " + this.getDescription().getVersion()).replace("&", "�"));
		BBLogger.println(("\"&a" + this.getDescription().getDescription() + "&r\"").replace("&", "�"));
		
		// Non reloadables
		if (reload) {
			BBLogger.println("�4 Currently �ccustom commands �4and �cbook and quill disabling �4is not supported by reloading. Restart the server to see changes take effect.");
			reload = false;
		} else {
			try {
				DynamicCommands.init();
			} catch (IOException e) {
				BBLogger.exception(e);
			}
			Recipes.init();
		}
		
		// Soft dependencies
		try {
			Plugin[] plugins = getServer().getPluginManager().getPlugins();
			for (Plugin plugin : plugins) {
				switch (plugin.getName()) {
					case "Factions": variables.factionsPresent = true; BBLogger.println("Factions found! Integrating!"); Integration.register("com.github.bukkitbasics.Integration.FactionsIntegration"); continue;
					case "GriefPrevention": variables.griefpreventionPresent = true; BBLogger.println("GriefPrevention found! Integrating!"); Integration.register("com.github.bukkitbasics.Integration.GriefPreventionIntegration"); continue;
				
				}
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			BBLogger.exception(e);
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
		this.getCommand("home").setExecutor(new home());
		this.getCommand("sethome").setExecutor(new sethome());
		
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
		this.getCommand("home").setTabCompleter(new home());
		this.getCommand("sethome").setTabCompleter(new sethome());
		
	}

	@Override
    public void onDisable() {
		instance = null;

		if (reload) {
			Bukkit.getServer().getPluginManager().enablePlugin(this);
		}
	}
	
}
