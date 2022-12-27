package me.ziprow.qolplugin;

import me.ziprow.qolplugin.commands.MainCommand;
import me.ziprow.qolplugin.commands.TabComplete;
import me.ziprow.qolplugin.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class QoLPlugin extends JavaPlugin
{

	public static final String PREFIX = ChatColor.DARK_PURPLE + "[QoLPlugin] " + ChatColor.RESET;
	public static final String CONSOLE_PREFIX = "[QoLPlugin] ";

	private static QoLPlugin MAIN;
	private static List<Recipe> RECIPES;
	private static List<Listener> LISTENERS;

	@Override
	public void onEnable()
	{
		MAIN = this;

		saveDefaultConfig();

		loadConfig();

		getCommand("qol").setExecutor(new MainCommand());
		getCommand("qol").setTabCompleter(new TabComplete());
	}

	private void loadConfig()
	{
		loadEvents();
		loadRecipes();
	}

	private void regEvent(Listener event)
	{
		getServer().getPluginManager().registerEvents(event, this);
	}

	private void unregEvent(Listener event)
	{
		HandlerList.unregisterAll(event);
	}

	public static void reload()
	{
		Iterator<Recipe> iter = MAIN.getServer().recipeIterator();
		while(iter.hasNext())
		{
			Recipe r = iter.next();
			if(RECIPES.contains(r))
				iter.remove();
		}

		LISTENERS.forEach(MAIN::unregEvent);

		MAIN.reloadConfig();
		MAIN.loadConfig();

		Bukkit.getLogger().info(CONSOLE_PREFIX + "Settings Reloaded");
	}

	private void loadEvents()
	{
		LISTENERS = new ArrayList<>();

		if(getSetting("close-inv"))
			LISTENERS.add(new CloseInv());

		if(getSetting("rotate-redstone"))
			LISTENERS.add(new RotateRedstone());

		if(getSetting("timber"))
			LISTENERS.add(new Timber());

		if(getSetting("grow-vines"))
			LISTENERS.add(new VineGrow());

		if(getSetting("edit-signs"))
			LISTENERS.add(new EditSigns());

		if(getSetting("edit-stands"))
			LISTENERS.add(new EditStands());

		if(getSetting("swap-armor"))
			LISTENERS.add(new SwapArmor());

		LISTENERS.forEach(this::regEvent);
	}

	@SuppressWarnings("deprecation")
	public void loadRecipes()
	{
		RECIPES = new ArrayList<>();

		// straightToShapeless
		if(getSetting("shapeless-bread"))
			RECIPES.add(new ShapelessRecipe(new ItemStack(Material.BREAD)).addIngredient(3, Material.WHEAT));
		if(getSetting("shapeless-books"))
			RECIPES.add(new ShapelessRecipe(new ItemStack(Material.PAPER, 3)).addIngredient(3, Material.SUGAR_CANE));

		// fleshToLeather
		if(getSetting("flesh-to-leather"))
			RECIPES.add(new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH));

		// backToBlocks
		final Material[] blocks = {Material.STONE, Material.SANDSTONE, Material.WOOD, Material.COBBLESTONE, Material.BRICK, Material.SMOOTH_BRICK, Material.NETHER_BRICK, Material.QUARTZ_BLOCK};

		if(getSetting("stairs-to-blocks"))
		{
			final Material[] stairs = {Material.COBBLESTONE_STAIRS, Material.SANDSTONE_STAIRS, Material.WOOD_STAIRS, Material.COBBLESTONE_STAIRS, Material.BRICK_STAIRS, Material.SMOOTH_STAIRS, Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS};
			final Material[] stairs2 = {Material.WOOD_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.JUNGLE_WOOD_STAIRS, Material.ACACIA_STAIRS, Material.DARK_OAK_STAIRS};

			for(int i = 0; i < 8; i++)
				RECIPES.add(new ShapelessRecipe(new ItemStack(blocks[i], 6)).addIngredient(4, stairs[i]));
			for(int i = 0; i < 6; i++)
				RECIPES.add(new ShapelessRecipe(new ItemStack(Material.WOOD, 6, (short)i)).addIngredient(4, stairs2[i]));

			RECIPES.add(new ShapelessRecipe(new ItemStack(Material.RED_SANDSTONE, 6)).addIngredient(4, Material.RED_SANDSTONE_STAIRS));
		}
		if(getSetting("slabs-to-blocks"))
		{
			for(int i = 0; i < 8; i++)
				RECIPES.add(new ShapelessRecipe(new ItemStack(blocks[i], 1)).addIngredient(2, Material.STEP, i));
			for(int i = 0; i < 6; i++)
				RECIPES.add(new ShapelessRecipe(new ItemStack(Material.WOOD, 1, (short)i)).addIngredient(2, Material.WOOD_STEP, i));

			// doesn't work for sandstone slabs because it makes chiseled variant
			// recipes.add(new ShapelessRecipe(new ItemStack(Material.RED_SANDSTONE, 6)).addIngredient(4, Material.STONE_SLAB2));
		}

		// QoL crafts
		if(getSetting("flint-to-gravel"))
			RECIPES.add(new ShapelessRecipe(new ItemStack(Material.GRAVEL)).addIngredient(4, Material.FLINT));
		if(getSetting("wool-to-string"))
			for(int i = 0; i < 16; i++)
				RECIPES.add(new ShapelessRecipe(new ItemStack(Material.STRING, 4)).addIngredient(Material.WOOL, i));
		if(getSetting("dropper-to-dispenser"))
			RECIPES.add(new ShapelessRecipe(new ItemStack(Material.DISPENSER, 1)).addIngredient(Material.DROPPER).addIngredient(Material.BOW));
		if(getSetting("dropper-to-dispenser-shaped"))
			RECIPES.add(new ShapedRecipe(new ItemStack(Material.DISPENSER)).shape(getConfig().getStringList("dropper-to-dispenser-pattern").toArray(new String[0])).setIngredient('S', Material.STRING).setIngredient('I', Material.STICK).setIngredient('X', Material.DROPPER));

		// UncraftableNowCraftable
		if(getSetting("horse-armor"))
		{
			final Material[] materials = new Material[] {Material.DIAMOND, Material.GOLD_INGOT, Material.IRON_INGOT};

			for(Material mat : materials)
				RECIPES.add(new ShapedRecipe(new ItemStack(Material.DIAMOND_BARDING)).shape(getConfig().getStringList("horse-armor-pattern").toArray(new String[0])).setIngredient('O', mat).setIngredient('X', Material.SADDLE));
		}
		if(getSetting("saddle"))
			RECIPES.add(new ShapedRecipe(new ItemStack(Material.SADDLE)).shape(getConfig().getStringList("saddle-pattern").toArray(new String[0])).setIngredient('O', Material.LEATHER));
		if(getSetting("nametag"))
			RECIPES.add(new ShapedRecipe(new ItemStack(Material.NAME_TAG)).shape(getConfig().getStringList("nametag-pattern").toArray(new String[0])).setIngredient('P', Material.PAPER).setIngredient('S', Material.STRING));

		RECIPES.forEach(Bukkit::addRecipe);
	}

	public static QoLPlugin getInstance()
	{
		return MAIN;
	}

	public static FileConfiguration getFileConfig()
	{
		return MAIN.getConfig();
	}

	public static boolean getSetting(String path)
	{
		return getFileConfig().getBoolean(path);
	}

}
