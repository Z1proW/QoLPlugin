package me.ziprow.qolplugin.Events;

import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EditSigns implements Listener
{
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e)
	{
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getPlayer().isSneaking()) return;
		
		BlockState b = e.getClickedBlock().getState();

		if(!(b instanceof Sign)) return;
		/*
		Sign sign = (Sign)b;
		String[] lines = sign.getLines();
		
		// ...
		
		sign.update();
		*/
	}

}
