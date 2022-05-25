package me.ziprow.qolplugin.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EditStands implements Listener
{
	
	@EventHandler
	public void onSignClick(PlayerInteractEntityEvent e)
	{
		Entity b = e.getRightClicked();
		
		if(!(b instanceof ArmorStand) || !e.getPlayer().isSneaking()) return;
		/*
		ArmorStand stand = (ArmorStand)b;
		
		stand.setBodyPose();
		*/
	}
	
}
