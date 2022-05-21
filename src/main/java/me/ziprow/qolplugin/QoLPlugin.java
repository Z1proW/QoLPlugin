package me.ziprow.qolplugin;

import me.ziprow.qolplugin.Events.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class QoLPlugin extends JavaPlugin
{

	private final Listener[] events = {new Timber(this), new CloseInv(), new EditSigns(), new EditStands(), new RotateRedstone()};

	@Override
	public void onEnable()
	{
		regEvents();
		addRecipes();
	}

	private void regEvents()
	{
		for(Listener e : events)
			getServer().getPluginManager().registerEvents(e, this);
	}

	private void addRecipes()
	{
		new CustomRecipes();
	}

}
