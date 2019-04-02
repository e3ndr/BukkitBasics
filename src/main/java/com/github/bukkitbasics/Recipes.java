package com.github.bukkitbasics;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

import com.github.bukkitbasics.Util.BBLogger;

public class Recipes {

	@SuppressWarnings("deprecation")
	public static void init() {
		if (variables.removeBookRecipe) {
			Iterator<Recipe> it = Bukkit.getServer().recipeIterator();
			Recipe recipe;
			while(it.hasNext()) {
				recipe = it.next();
				if (recipe != null && (recipe.getResult().getType() == Material.WRITABLE_BOOK || recipe.getResult().getType() == Material.LEGACY_BOOK_AND_QUILL)) {
					it.remove();
					BBLogger.println("Removed book and quill recipe.");
				}
			}
		}
		
		
		
	}
	
}
