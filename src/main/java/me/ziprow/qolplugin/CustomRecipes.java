package me.ziprow.qolplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CustomRecipes
{

	@SuppressWarnings("deprecation")
	CustomRecipes(FileConfiguration config)
	{
		// straightToShapeless
		if(config.getBoolean("shapeless-bread"))
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.BREAD)).addIngredient(3, Material.WHEAT));
		if(config.getBoolean("shapeless-books"))
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.PAPER, 3)).addIngredient(3, Material.SUGAR_CANE));

		// fleshToLeather
		if(config.getBoolean("flesh-to-leather"))
			Bukkit.addRecipe(new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH));

		// backToBlocks
		final Material[] blocks = {Material.STONE, Material.SANDSTONE, Material.WOOD, Material.COBBLESTONE, Material.BRICK, Material.SMOOTH_BRICK, Material.NETHER_BRICK, Material.QUARTZ_BLOCK};

		if(config.getBoolean("stairs-to-blocks"))
		{
			final Material[] stairs = {Material.COBBLESTONE_STAIRS, Material.SANDSTONE_STAIRS, Material.WOOD_STAIRS, Material.COBBLESTONE_STAIRS, Material.BRICK_STAIRS, Material.SMOOTH_STAIRS, Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS};
			final Material[] stairs2 = {Material.WOOD_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.JUNGLE_WOOD_STAIRS, Material.ACACIA_STAIRS, Material.DARK_OAK_STAIRS};

			for(int i = 0; i < 8; i++)
				Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(blocks[i], 6)).addIngredient(4, stairs[i]));
			for(int i = 0; i < 6; i++)
				Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.WOOD, 6, (short)i)).addIngredient(4, stairs2[i]));

			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.RED_SANDSTONE, 6)).addIngredient(4, Material.RED_SANDSTONE_STAIRS));
		}
		if(config.getBoolean("slabs-to-blocks"))
		{
			for(int i = 0; i < 8; i++)
				Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(blocks[i], 1)).addIngredient(2, Material.STEP, i));
			for(int i = 0; i < 6; i++)
				Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.WOOD, 1, (short)i)).addIngredient(2, Material.WOOD_STEP, i));

			// doesn't work for sandstone slabs because it makes chiseled variant
			// Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.RED_SANDSTONE, 6)).addIngredient(4, Material.STONE_SLAB2));
		}

		// QoL crafts
		if(config.getBoolean("flint-to-gravel"))
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.GRAVEL)).addIngredient(4, Material.FLINT));
		if(config.getBoolean("wool-to-string"))
			for(int i = 0; i < 16; i++)
				Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.STRING, 4)).addIngredient(Material.WOOL, i));
		if(config.getBoolean("dropper-to-dispenser"))
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.DISPENSER, 1)).addIngredient(Material.DROPPER).addIngredient(Material.BOW));
		if(config.getBoolean("dropper-to-dispenser-shaped"))
			Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.DISPENSER)).shape(config.getStringList("dropper-to-dispenser-pattern").toArray(new String[0])).setIngredient('S', Material.STRING).setIngredient('I', Material.STICK).setIngredient('X', Material.DROPPER));

		// UncraftableNowCraftable
		if(config.getBoolean("horse-armor"))
		{
			final Material[] materials = new Material[] {Material.DIAMOND, Material.GOLD_INGOT, Material.IRON_INGOT};

			for(Material mat : materials)
				Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.DIAMOND_BARDING)).shape(config.getStringList("horse-armor-pattern").toArray(new String[0])).setIngredient('O', mat).setIngredient('X', Material.SADDLE));
		}
		if(config.getBoolean("saddle"))
			Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.SADDLE)).shape(config.getStringList("saddle-pattern").toArray(new String[0])).setIngredient('O', Material.LEATHER));
		if(config.getBoolean("nametag"))
			Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.NAME_TAG)).shape(config.getStringList("nametag-pattern").toArray(new String[0])).setIngredient('P', Material.PAPER).setIngredient('S', Material.STRING));
	}

}
