package me.ziprow.qolplugin.events;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_8_R3.TileEntitySign;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EditSigns implements Listener
{
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e)
	{
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK
		|| e.getPlayer().isSneaking()
		|| !(e.getClickedBlock().getState() instanceof Sign)) return;

		Location loc = e.getClickedBlock().getLocation();
		BlockPosition blockPosition = new BlockPosition(loc.getX(), loc.getY(), loc.getZ());
		TileEntitySign tile = (TileEntitySign)((CraftWorld)loc.getWorld()).getTileEntityAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		EntityPlayer entityPlayer = ((CraftPlayer)e.getPlayer()).getHandle();

		tile.a(((CraftPlayer)e.getPlayer()).getHandle()); // set player to edit sign
		tile.isEditable = true; // set editable

		entityPlayer.playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(blockPosition)); // send packet to open sign editor*/
	}

}
