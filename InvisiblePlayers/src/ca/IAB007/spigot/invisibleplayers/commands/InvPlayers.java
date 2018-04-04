package ca.IAB007.spigot.invisibleplayers.commands;

import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.IAB007.spigot.invisibleplayers.InvisiblePlayers;
import ca.IAB007.spigot.invisibleplayers.config.Configuration;
import ca.IAB007.spigot.invisibleplayers.enumeration.InvisibleReason;
import ca.IAB007.spigot.invisibleplayers.enumeration.Option;

/**
CREATED BY IAB007

DO NOT COPY WITHOUT PERMISSION
*/

public class InvPlayers implements CommandExecutor {
	
	public InvisiblePlayers ip;
	
	public InvPlayers(InvisiblePlayers ip) {
		this.ip = ip;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission(Configuration.adminpermission)) {
				if(args.length == 0 || args[0].equalsIgnoreCase("toggle")) {
					toggleInvisible(p);
				}else {
					if(args[0].equalsIgnoreCase("reload")) {
						p.sendMessage(Configuration.getStringFromConfig("messages.commands.reload_before"));
						Configuration.loadConfiguration(ip);
						p.sendMessage(Configuration.getStringFromConfig("messages.commands.reload_end"));
					}else if(args[0].equalsIgnoreCase("list")) {
						if(Configuration.option == Option.ALWAYS) {
							p.sendMessage(Configuration.getStringFromConfig("messages.commands.list_everybody"));
						}else {
							p.sendMessage(Configuration.getStringFromConfig("messages.commands.list"));
							for(Entry<Player, InvisibleReason> ent : InvisiblePlayers.reason.entrySet()) {
								p.sendMessage("§a" + ent.getKey().getName() + " : " + ent.getValue().getString());
							}
						}
					}else {
						p.sendMessage(Configuration.getStringFromConfig("messages.commands.help"));
					}
				}
			}else if(p.hasPermission(Configuration.permission)){
				toggleInvisible(p);
			}else {
				p.sendMessage(Configuration.getStringFromConfig("messages.commands.no_perm"));
			}
		}else {
			sender.sendMessage((String) Configuration.getFromConfig("messages.commands.not_player"));
		}
		return false;
	}
	
	public void toggleInvisible(Player p) {
		
	}

}
