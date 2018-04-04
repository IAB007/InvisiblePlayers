package ca.IAB007.spigot.invisibleplayers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ca.IAB007.spigot.invisibleplayers.commands.InvPlayers;
import ca.IAB007.spigot.invisibleplayers.config.Configuration;
import ca.IAB007.spigot.invisibleplayers.enumeration.InvisibleReason;

/**
CREATED BY IAB007

DO NOT COPY WITHOUT PERMISSION
*/

public class InvisiblePlayers extends JavaPlugin{

	public static Map<Player, InvisibleReason> reason = new HashMap<>();
	
	@Override
	public void onEnable() {
		Configuration.loadConfiguration(this);
		getCommand("invplayers").setExecutor(new InvPlayers(this));
		loadEvent();
	}
	
	public void loadEvent() {
		getServer().getPluginManager().registerEvents(new Event(), this);
		switch(Configuration.option) {
		case ALWAYS:
			getServer().getPluginManager().registerEvents(new AlwaysEvent(), this);
			break;
		case INVISIBLE:
			getServer().getPluginManager().registerEvents(new InvisibleEvent(), this);
			break;
		default:
			break;
		}
	}
	
}
