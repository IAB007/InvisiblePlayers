package ca.IAB007.spigot.invisibleplayers.config;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import ca.IAB007.spigot.invisibleplayers.InvisiblePlayers;
import ca.IAB007.spigot.invisibleplayers.enumeration.Option;

/**
CREATED BY IAB007

DO NOT COPY WITHOUT PERMISSION
*/

public class Configuration {
	
	public static Option option = Option.INVISIBLE;
	public static String permission = "invplayers.usecommand";
	public static String adminpermission = "invplayers.usecommand.admin";
	private static YamlConfiguration config;

	

	public static void loadConfiguration(InvisiblePlayers ip) {
		try {
			if(!ip.getDataFolder().exists()) {
				ip.getDataFolder().mkdir();
			}
			File f = new File(ip.getDataFolder() + "/config.yml");
			if(!f.exists()) {
				InputStream stream = ip.getResource("config.yml");
				FileUtils.copyInputStreamToFile(stream, f);
			}else {
				YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
				Configuration.config = config;
				try {
					if(config.contains("option")) {
						try {
							option = Option.valueOf(config.getString("option"));
						}catch(Exception e) {
							System.out.println(config.get("messages.config.option_unavalible", "The default option is unavailable"));
						}
					}else {
						System.out.println(config.get("messages.config.option_unavalible", "The default option is unavailable"));
					}
					if(config.contains("permission")) {
						permission = config.getString("permission");
					}else {
						System.out.println(config.get("messages.config.permission_not_found", "The permission wasn't able to load"));
					}
				}catch(Exception e) {
					System.out.println(config.get("messages.config.error_loading", "An error occurred while loading the configuration. Report the error :"));
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			System.out.println("An error occurred while loading the configuration. Report the error :");
			e.printStackTrace();
		}
		
	}
	
	public static String getStringFromConfig(String s) {
		return config.getString(s).replaceAll("&", "§");
	}
	
	public static Object getFromConfig(String s) {
		return config.get(s);
	}
	
}
