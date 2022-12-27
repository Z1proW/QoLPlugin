package me.ziprow.qolplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SwapArmor implements Listener
{

	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;

		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		ItemStack tmp;

		switch(i.getType())
		{
			case CHAINMAIL_HELMET:
			case DIAMOND_HELMET:
			case GOLD_HELMET:
			case IRON_HELMET:
			case LEATHER_HELMET:
				tmp = p.getEquipment().getHelmet();
				p.getEquipment().setHelmet(i);
				p.setItemInHand(tmp);
				break;

			case CHAINMAIL_CHESTPLATE:
			case DIAMOND_CHESTPLATE:
			case GOLD_CHESTPLATE:
			case IRON_CHESTPLATE:
			case LEATHER_CHESTPLATE:
				tmp = p.getEquipment().getChestplate();
				p.getEquipment().setChestplate(i);
				p.setItemInHand(tmp);
				break;

			case LEATHER_LEGGINGS:
			case CHAINMAIL_LEGGINGS:
			case DIAMOND_LEGGINGS:
			case GOLD_LEGGINGS:
			case IRON_LEGGINGS:
				tmp = p.getEquipment().getLeggings();
				p.getEquipment().setLeggings(i);
				p.setItemInHand(tmp);
				break;

			case CHAINMAIL_BOOTS:
			case DIAMOND_BOOTS:
			case GOLD_BOOTS:
			case IRON_BOOTS:
			case LEATHER_BOOTS:
				tmp = p.getEquipment().getBoots();
				p.getEquipment().setBoots(i);
				p.setItemInHand(tmp);
				break;

			default:
				break;
		}
	}

}
