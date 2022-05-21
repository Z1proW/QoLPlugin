package me.ziprow.qolplugin.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class CloseInv implements Listener
{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if(e.getSlotType() == SlotType.OUTSIDE && e.getCursor().getType() == Material.AIR)
			e.getWhoClicked().getOpenInventory().close();
	}
	
}
