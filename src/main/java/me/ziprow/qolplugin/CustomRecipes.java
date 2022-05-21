package me.ziprow.qolplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CustomRecipes
{

	@SuppressWarnings("deprecation")
	CustomRecipes()
	{
		// backToBlocks
		final Material[] blocks = {Material.STONE, Material.SANDSTONE, Material.WOOD, Material.COBBLESTONE, Material.BRICK, Material.SMOOTH_BRICK, Material.NETHER_BRICK, Material.QUARTZ_BLOCK};
		final Material[] stairs = {Material.COBBLESTONE_STAIRS, Material.SANDSTONE_STAIRS, Material.WOOD_STAIRS, Material.COBBLESTONE_STAIRS, Material.BRICK_STAIRS, Material.SMOOTH_STAIRS, Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS};
		final Material[] stairs2 = {Material.WOOD_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.JUNGLE_WOOD_STAIRS, Material.ACACIA_STAIRS, Material.DARK_OAK_STAIRS};

		for(int i = 0; i < 8; i++)
		{
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(blocks[i], 1)).addIngredient(2, Material.STEP, i));
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(blocks[i], 6)).addIngredient(4, stairs[i]));
		}

		for(int i = 0; i < 6; i++)
		{
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.WOOD, 1, (short)i)).addIngredient(2, Material.WOOD_STEP, i));
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.WOOD, 6, (short)i)).addIngredient(4, stairs2[i]));
		}

		// dropperToDispenser
		Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.DISPENSER)).shape(" IS", "IXS", " IS").setIngredient('S', Material.STRING).setIngredient('I', Material.STICK).setIngredient('X', Material.DROPPER));

		// fleshToLeather
		Bukkit.addRecipe(new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH));

		// straightToShapeLess
		Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.PAPER, 3)).addIngredient(3, Material.SUGAR_CANE));
		Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.BREAD)).addIngredient(3, Material.WHEAT));

		// flintToGravel
		Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.GRAVEL)).addIngredient(4, Material.FLINT));

		// horseArmor
		Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.DIAMOND_BARDING)).shape("  O", "OOO", "OXO").setIngredient('O', Material.DIAMOND).setIngredient('X', Material.SADDLE));
		Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.GOLD_BARDING)).shape("  O", "OOO", "OXO").setIngredient('O', Material.GOLD_INGOT).setIngredient('X', Material.SADDLE));
		Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.IRON_BARDING)).shape("  O", "OOO", "OXO").setIngredient('O', Material.IRON_INGOT).setIngredient('X', Material.SADDLE));

		// saddle
		Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.SADDLE)).shape("  O", "OOO", "O O").setIngredient('O', Material.LEATHER));

		// nameTags
		Bukkit.addRecipe(new ShapedRecipe(new ItemStack(Material.NAME_TAG)).shape("  S", " P ", "P  ").setIngredient('P', Material.PAPER).setIngredient('S', Material.STRING));

		// woolToString
		for(int i = 0; i < 16; i++)
			Bukkit.addRecipe(new ShapelessRecipe(new ItemStack(Material.STRING, 4)).addIngredient(Material.WOOL, i));
	}

}
