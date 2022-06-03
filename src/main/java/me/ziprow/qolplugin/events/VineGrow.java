package me.ziprow.qolplugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Vine;

public class VineGrow implements Listener
{

	@EventHandler
	public void onVineClick(PlayerInteractEvent e)
	{
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK
		&& e.getClickedBlock().getType() == Material.VINE
		&& e.getClickedBlock().getState().getData() instanceof Vine
		&& e.getItem() != null
		&& (e.getItem().getType() == Material.VINE
		|| e.getItem().getType() == Material.INK_SACK && e.getItem().getDurability() == (short)15))
		{
			Vine originalVine = (Vine)e.getClickedBlock().getState().getData();
			Block b = e.getClickedBlock();

			do b = b.getRelative(BlockFace.DOWN);
			while(b.getType() == Material.VINE);

			if(b.getType() == Material.AIR)
			{
				b.setType(Material.VINE);
				if(b.getState().getData() instanceof Vine)
				{
					Vine v = (Vine)b.getState().getData();
					for(BlockFace face : new BlockFace[] {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST})
						if(originalVine.isOnFace(face))
							v.putOnFace(face);
					b.setData(v.getData());
					b.getState().update(true);
				}

				if(e.getItem().getType() == Material.VINE)
					e.getPlayer().getInventory().removeItem(new ItemStack(Material.VINE));
				else
					e.getPlayer().getInventory().removeItem(new ItemStack(Material.INK_SACK, 1, (short)15));
			}
		}
	}

}
