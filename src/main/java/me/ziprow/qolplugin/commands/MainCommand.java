package me.ziprow.qolplugin.commands;

import me.ziprow.qolplugin.QoLPlugin;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!sender.hasPermission("qol.user"))
		{
			sender.sendMessage("You do not have proper permissions !");
			return true;
		}

		switch(args[0].toLowerCase())
		{
			case "help":
				help(sender);
				break;

			case "reload":
				if(sender.hasPermission("qol.admin"))
					reload();
				else sender.sendMessage("You do not have proper permissions !");
				break;

			case "settings":
				if(sender.hasPermission("qol.admin"))
					sendSettings(sender);
				else sender.sendMessage("You do not have proper permissions !");
				break;

			case "toggle":
				if(sender.hasPermission("qol.admin"))
					if(args.length == 2)
						toggleSetting(sender, args[1]);
					else sender.sendMessage("No setting specified");
				else sender.sendMessage("You do not have proper permissions !");
				break;

			default:
				sender.sendMessage("That is not a command !");
				help(sender);
				break;
		}

		return true;
	}

	private void help(CommandSender sender)
	{
		sender.sendMessage(QoLPlugin.PREFIX + "Commands");
		sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
		sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/qol help");
		sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/qol reload");
		sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/qol settings");
		sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/qol toggle <setting>");
		sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
	}

	private void reload()
	{
		QoLPlugin.reload();
	}

	private void sendSettings(CommandSender sender)
	{
		sender.sendMessage(QoLPlugin.PREFIX + "Settings");
		sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
		for(String key : QoLPlugin.config.getKeys(false))
			if(QoLPlugin.config.get(key) instanceof Boolean)
			{
				TextComponent setting = new TextComponent((QoLPlugin.config.getBoolean(key) ? ChatColor.GREEN + "[+] " : ChatColor.RED + "[-] ") + ChatColor.GRAY + key);
				setting.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Toggle " + key).create()));
				setting.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/qol toggle " + key));
				if(sender instanceof Player)
					((Player)sender).spigot().sendMessage(setting);
				else sender.sendMessage(setting.getText());
			}
		sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
	}

	private void toggleSetting(CommandSender sender, String key)
	{
		if(QoLPlugin.config.get(key) instanceof Boolean)
		{
			QoLPlugin.config.set(key, !QoLPlugin.config.getBoolean(key));
			QoLPlugin.main.saveConfig();
			QoLPlugin.reload();
			sendSettings(sender);
		}
	}

}
