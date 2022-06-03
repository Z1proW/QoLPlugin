package me.ziprow.qolplugin.events;

import me.ziprow.qolplugin.QoLPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class Timber implements Listener
{
	
	private final List<Material> logs = Arrays.asList(Material.LOG, Material.LOG_2);
	private final List<Material> axes = Arrays.asList(Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE);

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(isAnAxe(e.getPlayer().getItemInHand()))
			breakTree(e.getBlock().getLocation());
	}
	
	private boolean isAnAxe(ItemStack i)
	{
		return axes.contains(i.getType());
	}
	
	private void breakTree(Location loc)
	{
		Block b = loc.getBlock();
		
		if(isALog(b))
		{
			b.breakNaturally();
			
			Bukkit.getScheduler().runTaskLater(QoLPlugin.main, () ->
			{
				for(int x = -1; x <= 1; x++)
					for(int y = -1; y <= 1; y++)
						for(int z = -1; z <= 1; z++)
							breakTree(loc.clone().add(x, y, z));
			}, 2);
		}
	}
	
	private boolean isALog(Block b)
	{
		return logs.contains(b.getType());
	}
	
}
