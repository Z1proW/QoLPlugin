package me.ziprow.qolplugin;

import com.google.common.io.ByteStreams;
import me.ziprow.qolplugin.Events.CloseInv;
import me.ziprow.qolplugin.Events.RotateRedstone;
import me.ziprow.qolplugin.Events.Timber;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public final class QoLPlugin extends JavaPlugin
{

	@Override
	public void onEnable()
	{
		FileConfiguration config = loadConfig();

		if(config.getBoolean("close-inv"))
			regEvent(new CloseInv());

		if(config.getBoolean("rotate-redstone"))
			regEvent(new RotateRedstone());

		if(config.getBoolean("timber"))
			regEvent(new Timber(this));

		new CustomRecipes(config);
	}

	private FileConfiguration loadConfig()
	{
		File configFile = new File(getDataFolder(), "config.yml");

		if(!configFile.exists())
		{
			File folder = getDataFolder();

			if(!folder.exists())
				folder.mkdir();

			File resourceFile = new File(folder, "config.yml");

			try
			{
				if(!resourceFile.exists())
				{
					resourceFile.createNewFile();

					try(InputStream in = getResource("config.yml");
						OutputStream out = Files.newOutputStream(resourceFile.toPath()))
					{
						ByteStreams.copy(in, out);
					}
				}
			}
			catch(IOException e) {e.printStackTrace();}
		}

		return getConfig();
	}

	private void regEvent(Listener... events)
	{
		for(Listener e : events)
			getServer().getPluginManager().registerEvents(e, this);
	}

}
