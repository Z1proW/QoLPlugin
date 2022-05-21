package me.ziprow.qolplugin.Events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RotateRedstone implements Listener
{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK || !e.getPlayer().isSneaking()) return;
		
		Block b = e.getClickedBlock();
		
		switch(b.getType())
		{
			case DIODE_BLOCK_OFF: case DIODE_BLOCK_ON:
				b.setData((byte)(((b.getData() + 1) % 4) + 4*(b.getData() / 4)));
				break;
			
			case REDSTONE_COMPARATOR_OFF: case REDSTONE_COMPARATOR_ON:
				b.setData((byte)((b.getData() + 1) % 4));
				break;
				
			default: return;
		}
		
		e.setCancelled(true);
	}
	
}
