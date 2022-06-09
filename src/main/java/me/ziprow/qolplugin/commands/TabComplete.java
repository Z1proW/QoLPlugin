package me.ziprow.qolplugin.commands;

import me.ziprow.qolplugin.QoLPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TabComplete implements TabCompleter
{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		if(!(sender instanceof Player)) return null;

		Player player = (Player)sender;
		List<String> arguments = new ArrayList<>();

		if(command.getName().equals("qol"))
		{
			if(args.length == 1)
			{
				if(player.hasPermission("qol.user"))
					arguments.add("help");

				if(player.hasPermission("qol.admin"))
				{
					arguments.add("reload");
					arguments.add("settings");
					arguments.add("toggle");
				}

				Iterator<String> iter = arguments.iterator();

				while(iter.hasNext())
				{
					String str = iter.next().toLowerCase();

					if(!str.contains(args[0].toLowerCase()))
						iter.remove();
				}
			}

			if(Objects.equals(args[0], "toggle"))
			{
				for(String key : QoLPlugin.getFileConfig().getKeys(false))
					if(QoLPlugin.getFileConfig().get(key) instanceof Boolean)
						arguments.add(key);
			}
		}
		return arguments;
	}

}